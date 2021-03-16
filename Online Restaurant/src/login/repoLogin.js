import axios from 'axios';


export const login = user => {
    return axios
        .post(`http://localhost:52004/api/customers/login`, 
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
            
            localStorage.setItem('userStorageId', response.data.userId)
            console.log(response.data.userId)

            localStorage.setItem('userOccupationId', response.data.userOccupationId)
            console.log(response.data.userOccupationId)

            console.log(response.data)

            return response.data
            
        })
        .catch(err =>  alert("Authentification failed!"))
}

