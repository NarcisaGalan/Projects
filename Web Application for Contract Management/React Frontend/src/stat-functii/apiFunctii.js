import axios from 'axios'; 

export function getStatFunctiiByContract(idContract) {  
    const config = {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
    };
    console.log("get statfunctii si angajati");
    return	 axios.get(`http://localhost:8443/angajat/${idContract}`, config)
            .then(response => 
                response.data );
            
}

