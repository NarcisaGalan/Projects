import axios from 'axios'; 
import Swal from 'sweetalert2';

export function getContracteLunare(data){
    const config ={
      headers: {Authorization: `Bearer ${localStorage.getItem("token")}`}
    };
    return axios.get(`http://localhost:8443/contractTerti/all/byMonth/${data}`, config)
        .then(response => response.data)
        .catch(err => {
            
            Swal.fire("Eroare", err.response.data, "error")
            
        })
  
}

export function getRaportLunar(idContract,startDate,endDate){
  const config ={
    headers: {Authorization: `Bearer ${localStorage.getItem("token")}`}
  };
  return axios.get(`http://localhost:8443/raport/raportLunar/${startDate}/${endDate}/${idContract}`, config)
      .then(response => response.data)
      .catch(err => {
          
          Swal.fire("Eroare", err.response.data, "error")
          
      })

}