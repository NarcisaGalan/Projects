import axios from "axios";

const resources = {};

const requestCreator = () => {
  let cancel;

  return async query => {
    if (cancel) {
      // Anulam requesturile vechi inainte de a face unul nou
      cancel.cancel();
    }
    // Creeaza un CancelToken nou pentru noul request
    cancel = axios.CancelToken.source();
    try {
      if (resources[query]) {
        // Returneaza raspunsul daca acesta exista
        return resources[query];
      }
      const config ={
        headers: {Authorization: `Bearer ${localStorage.getItem("token")}`}
      };
      const res = await axios(query,config, { cancelToken: cancel.token });
      console.log("Rezultat din utils "+res.data);
      const result = res.data;
      // Salveaza raspuns
      resources[query] = result;

      return result;
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