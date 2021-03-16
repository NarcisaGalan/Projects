import axios from 'axios';
import swal from 'sweetalert2'

export const login = user => {

    return axios
        .post(`http://localhost:8443/login`, 
         {
                email: user.email,
                password: user.password
        }, 

             {
			headers: {'Content-Type': 'application/json'},
			 },
		)
		.then(response => 
            {
                localStorage.setItem('token', response.data.userDTO.token)
                localStorage.setItem('userID', response.data.userDTO.idUser)
                localStorage.setItem('userRole', response.data.userDTO.role)
                localStorage.setItem('userEmail',response.data.userDTO.email)
                if(response.data.userDTO.role === "director"){
                    localStorage.setItem('idAngajat',response.data.userDTO.idAngajat)
                    localStorage.setItem('numeAngajat',response.data.angajatInfoDTO.numeAngajat)
                    localStorage.setItem('facultate',response.data.angajatInfoDTO.facultate.nume)
                    localStorage.setItem('departament',response.data.angajatInfoDTO.departament.nume)
                    localStorage.setItem('codDepartament',response.data.angajatInfoDTO.departament.codDepartament)
                    localStorage.setItem('idFacultate',response.data.angajatInfoDTO.facultate.idFacultate)
                    localStorage.setItem('universitate',response.data.angajatInfoDTO.universitate.nume)
                }else{
                    localStorage.setItem('numeMembru',response.data.membru.nume)
                    localStorage.setItem('functieMembru',response.data.membru.functie)
                }
              
            return response
            
        })
        .catch(err => swal.fire("Autentificare eșuată!", "E-mail sau parolă greșite! Vă rugăm să reintroduceți credențialele!", "error"))
}

