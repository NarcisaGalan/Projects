import axios from 'axios'; 

export function getCustomersById(id){
	return axios.get(`http://localhost:52004/api/customers/${id}`)
	.then(response => response.data);
}

export function getProducts() {
	return axios.get(`http://localhost:52004/api/products`)
		.then(response => response.data);
}
export function updateCustomer(id){
	return axios.put(`http://localhost:52004/api/customers/${id}`)
	.then(response =>response.data)
}
export function postCartProducts(cart) {
	return axios.post(`http://localhost:52004/api/shoppingcarts`, {cart})
		.then(response => response.data);
}

export function getCartProducts() {
	return axios.get(`http://localhost:52004/api/shoppingcarts`)
	
		.then(response => 
			{
				localStorage.setItem('cart', response.data)
				return response.data
			})
}

export function getProductById(id) {
	return  axios.get(`http://localhost:52004/api/products/${id}`)
	.then(response => response.data);

}

export function getQuantity(idCustomer, idProduct) {
	return  axios.get(`api/shoppingcarts/getquantity/${idCustomer}/${idProduct}`)
	.then(response => response.data);
}




export function getProductsByCustomerId(id){
	return axios.get(`http://localhost:52004/api/shoppingcarts/getproducts/${id}`)
	.then(response => response.data);
}

export function deleteProductFromCartByCustomerId(customerId, productId){
	return axios.delete(`http://localhost:52004/api/shoppingcarts/${customerId}/${productId}`)
	.then(response => response.data);
	
}

export function getTotalFromCart(customerId){
	return axios.get(`http://localhost:52004/api/shoppingcarts/total/${customerId}/${customerId}`)
	.then(response => response.data);
}

//id shopping cart pentru un customer deja logat
export function getShoppingCartByCustomerId(customerId){
	return axios.get(`http://localhost:52004/api/shoppingcarts/${customerId}`)
	.then(response => 
		{
		
		localStorage.setItem('shoppingStorageId', response.data.id)
		console.log(response.data.id)
		return response.data
	});
}
