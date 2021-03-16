import axios from 'axios'
 

export const saveReservation = newReservation => {
    return axios
        .post("http://localhost:52004/api/reservations", newReservation, {
            headers: { 'Content-Type': 'application/json' }
        })
        .then(res => {
            console.log(res)
        })
        .catch(err => {
            console.log(err)
        })
}

export const getAllReservation = () => {
    return axios
        .get("http://localhost:52004/api/reservations",  {
            headers: { 'Content-Type': 'application/json' }
        })
        .then(res => {
            console.log(res)
        })
        .catch(err => {
            console.log(err)
        })
}
