import axios from 'axios';

export function  incarcareFisier(file,idContractTerti) {
        let formData = new FormData();

        formData.append("file", file);
     
        return axios.post(`http://localhost:8443/fisiere/incarcare/${idContractTerti}`, formData, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`,
                "Content-Type": "multipart/form-data",
            }
        })
        .then(response => 
            response.data );
}

