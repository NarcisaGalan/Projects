import axios from 'axios'; 

export function getFacultati(idUniversitate) {  
const config = {
    headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
};
console.log("get facultati");
return	 axios.get(`http://localhost:8443/universitate/${idUniversitate}/facultate`, config)
        .then(response => 
            response.data )
            .catch(error => {
                console.log(error);
            });
        
}

export function getDepartamente(idFacultate) {  
    const config = {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
    };
    console.log("get departamente");
    return	 axios.get(`http://localhost:8443/universitate/${idFacultate}/departament`, config)
            .then(response => 
                response.data )  
            .catch(error => {
                    console.log(error);
                });
            
}


