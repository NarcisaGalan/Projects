import React from 'react';
import { Link } from 'react-router-dom';
import { deleteProductFromCartByCustomerId, getProductsByCustomerId, getTotalFromCart } from './repository';
import CartItem from './CartItem';


export default class Cart extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
		
			products: [],
			total: 0,
		}
	}


	componentDidMount(){
		getProductsByCustomerId(localStorage.getItem('userStorageId')).then((products) => {

			this.setState({ products });
		  });		
	}

	componentWillMount(){
		getTotalFromCart(localStorage.getItem('userStorageId')).then((total) => {

			this.setState({ total });
		  });
	}

	onRefresh(){

		
	}



	render() {
		const { products, total } =  this.state;

		return (
			<div id= "backProducts">
			<div className="container">
				<h3 className="card-title">Cart</h3>
				
				<hr/>
				{
				products.map((product, index) => <CartItem product={product} remove={this.removeFromCart} key={index}/> )
				}
				<hr/>

				{ products.length ? <div><h4><small>Total Amount:</small><span className="float-right ">{total} Lei</span></h4><hr/></div>: ''}

				{ !products.length ? <h3 className="text-warning">No item on the cart</h3>: ''}

				<Link to="/checkout"><button className="btn btn-success float-right">Checkout</button></Link>
				<br/><br/><br/>
			</div>
			</div>
			
		);
	}
}
