import axios from 'axios'; 

export function getTopProducts() {
	return axios.get(`http://localhost:52004/api/products/getProductsChart`)
		.then(response => response.data);
}

export function getReservation(date) {
	return  axios.get(`http://localhost:52004/api/reservations/getreservationchart/${date}`)
	.then(response => response.data);

}