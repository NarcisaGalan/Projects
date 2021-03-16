import axios from 'axios'; 

export function getComments(date) {
	return  axios.get(`http://localhost:8000/api/commentsday/${date}`)
	

}