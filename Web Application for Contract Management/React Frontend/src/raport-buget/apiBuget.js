
import Swal from 'sweetalert2';
import axios from 'axios';


export function getRaportBuget(idContract){
    const config ={
      headers: {Authorization: `Bearer ${localStorage.getItem("token")}`}
    };
    return axios.get(`http://localhost:8443/raport/buget/${idContract}`, config)
        .then(response => response.data)
        .catch(err => {
            
            Swal.fire("Eroare", err.response.data, "error")
            
        })
  
  }
  
  export function getIncasariTable(idContract){
    const config ={
      headers: {Authorization: `Bearer ${localStorage.getItem("token")}`}
    };
    return axios.get(`http://localhost:8443/incasare/all/${idContract}`, config)
        .then(response => response.data)
        .catch(err => {
            
            Swal.fire("Eroare", err.response.data, "error")
            
        })
  
  }

  export function getCheltuileiTable(idContract){
    const config ={
      headers: {Authorization: `Bearer ${localStorage.getItem("token")}`}
    };
    return axios.get(`http://localhost:8443/cheltuiala/allTable/${idContract}`, config)
        .then(response => response.data)
        .catch(err => {
            
            Swal.fire("Eroare", err.response.data, "error")
            
        })
  
  }

  export function getImprumutTable(idContract){
    const config ={
      headers: {Authorization: `Bearer ${localStorage.getItem("token")}`}
    };
    return axios.get(`http://localhost:8443/cerereImprumut/allAprobate/${idContract}`, config)
        .then(response => response.data)
        .catch(err => {
            
            Swal.fire("Eroare", err.response.data, "error")
            
        })
  
  }