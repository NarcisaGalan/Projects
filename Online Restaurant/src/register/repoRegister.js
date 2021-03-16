import axios from 'axios'

export const register = newUser => {
    return axios
        .post(`http://localhost:52004/api/customers`, newUser, {
            headers: { 'Content-Type': 'application/json' }
        })
        .then(response => {
            console.log(response)
        })
        .catch(err => {
            console.log(err)
        })
}