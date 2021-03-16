import axios from 'axios'; 
import swal from 'sweetalert2';

export function getUniversitati() {  
const config = {
    headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
};
console.log("get universitati");
return	 axios.get(`http://localhost:8443/universitate/all`, config)
        .then(response => 
            response.data )
            .catch(error => {
                console.log(error);
            });
        
}

export function searchBeneficiarByDenumireContains(denumire){
  const config ={
    headers: {Authorization: `Bearer ${localStorage.getItem("token")}`}
  };
  console.log("Cautare dupa contains");
  return axios.get(`http://localhost:8443/beneficiar/searchByDenumireContaining/${denumire}`, config)
      .then(response => response.data)
      .catch(error => {console.log(error)});

}
 
export function searchBeneficiar(denumire,cifCui) {  
    const config = {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
    };
    console.log("API ssearch beneficiar ");
    return	 axios.get(`http://localhost:8443/beneficiar/getByDenumireAndCifCui/${denumire}/${cifCui}`, config)
            .then(response => 
                response.data )
                .catch(error => {
                    console.log(error);
                   
                });
}
export function addContract(newContract) {  
    const url = 'http://localhost:8443/contractTerti/add';
    axios.post(url, newContract, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem("token")}`
      },
      body: JSON.stringify(newContract),
    })
      .then(res => {
        console.log(res)
      }).catch(error => {
        console.log(error);
        swal.fire("Adaugare esuata!",  error.response.data, "error")
    });
     
}

export function saveBeneficiar(beneficiar) {  
    const url = 'http://localhost:8443/beneficiar/saveBeneficiar';
    axios.post(url, beneficiar, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem("token")}`
      },
      body: JSON.stringify(beneficiar),
    })
      .then(res => {
        console.log(res)
      }).catch(error => {
        console.log(error);
        swal.fire("Salvare esuata!",  error.response.data, "error")
    });
     
}