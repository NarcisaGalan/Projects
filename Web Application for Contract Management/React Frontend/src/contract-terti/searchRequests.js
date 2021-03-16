import axios from "axios";

const resources = {};

const requestCreator = () => {
  let request;

  return async query => {
    if (request) {
      // Anulam requesturile vechi inainte de a face unul nou
      request.cancel();
    }
    // Creeaza un CancelToken nou pentru noul request
    request = axios.CancelToken.source();
    try {
      if (resources[query]) {
        // Returneaza raspunsul daca acesta exista
        return resources[query];
      }
      const config = {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
      };
      const raspuns = await axios(query, config, { cancelToken: request.token });
      console.log("Rezultat " + raspuns.data);
      // Salveaza raspuns
      resources[query] = raspuns.data;

      return raspuns.data;
    } catch (error) {
      if (axios.isCancel(error)) {
        // daca request-ul a fost anulat
        console.log("Request anulat", error.message);
      } else {
        // erori uzuale
        console.log("Server error: ", error.message);

      }
    }
  };
};

export const search = requestCreator();