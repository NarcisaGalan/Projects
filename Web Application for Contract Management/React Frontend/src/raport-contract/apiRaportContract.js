import axios from 'axios'; 
import Swal from 'sweetalert2';

export function getStatFunctiiByContract(idContract) {  
    const config = {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
    };
    console.log("get statfunctii si angajati");
    return	 axios.get(`http://localhost:8443/angajat/${idContract}`, config)
            .then(response => 
                response.data );
            
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
