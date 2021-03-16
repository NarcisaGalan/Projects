import axios from 'axios'; 

export function getTitluProiectByUser(idUser) {  
    const config = {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
    };
    console.log("get titlu");
    return	 axios.get(`http://localhost:8443/cerereImprumut/titluProiecte/${idUser}`, config)
            .then(response => 
                response.data )  
            .catch(error => {
                    console.log(error);
                });
            
}

export function getContracteByUser(idUser) {  
    const config = {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
    };
    console.log("get contracte");
    return	 axios.get(`http://localhost:8443/contractTerti/all/${idUser}`, config)
            .then(response => 
                response.data );
            
}
//by facultate
export function getDepartamente(idFacultate) {  
    const config = {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
    };
    console.log("get departamente");
    return	 axios.get(`http://localhost:8443/universitate/${idFacultate}/departament`, config)
            .then(response => 
                response.data );
            
}


export function getAllDepartamente() {  
    const config = {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
    };
    console.log("get departamente");
    return	 axios.get(`http://localhost:8443/admin/allDepartamente`, config)
            .then(response => 
                response.data );
            
}

export function getFacultati(idUniversitate) {  
    const config = {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
    };
    console.log("get facultati");
    return	 axios.get(`http://localhost:8443/universitate/${idUniversitate}/facultate`, config)
            .then(response => 
                response.data );
            
    }
    

export function getBeneficiarByContract(idContract) {  
    const config = {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
    };
    console.log("get beneficiar");
    return	 axios.get(`http://localhost:8443/beneficiar/${idContract}`, config)
            .then(response => 
                response.data );
            
}
