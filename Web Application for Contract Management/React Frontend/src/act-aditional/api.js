import Swal from 'sweetalert2';
import axios from 'axios';


export function getActeAditionale(){
    const config ={
      headers: {Authorization: `Bearer ${localStorage.getItem("token")}`}
    };
    return axios.get(`http://localhost:8443/act-aditional/getAllByDirector/${localStorage.getItem("userID")}`, config)
        .then(response => response.data)
        .catch(err => {
            
            Swal.fire("Eroare", err.response.data, "error")
            
        })
  
  }