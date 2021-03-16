import React from 'react';
import { Link , withRouter } from 'react-router-dom';
import axios from 'axios'

import './productItem.css';
import { Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle, Button, Container } from 'reactstrap';
import SuccessAlert from '../../reservation/ModalForRequest/SuccessAlert'


class ProductItem extends React.Component {

	constructor(props) {
		super(props);
		let id_shopp = JSON.parse(parseInt(localStorage.getItem("userStorageId")));
		let id_customer = JSON.parse(parseInt(localStorage.getItem("userStorageId")));
		this.state = {

			id: id_shopp,
			details: 'no details',
			price: 0.0,
			status: 'in cart',
			quantity: 1,
			productId: 0,
			customerId: id_customer,
			
			alert_message: '',
		}

	}


	addToCart = () => {

		const newCart = {
			id: this.state.id,
			details: this.state.details,
			price: this.props.product.price,
			status: this.state.status,
			quantity: this.state.quantity,
			productId: this.props.product.id,
			customerId: parseInt(localStorage.getItem("userStorageId")),
		}

		console.log(this.state.id);
		console.log(this.props.product.price);
		console.log(this.state.customerId);

		axios.post(`http://localhost:52004/api/shoppingcarts`, newCart, {
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify(newCart),

		})
			.then(res => {
				console.log(res)
				});


		this.setState({
				alert_message: "success"});

				window.location.reload(false)

	}
	

	render() {
		const { product } = this.props;
		return (
			<div>
			<hr />
                    { this.state.alert_message == "success" ? <SuccessAlert /> : null }
		<Card id="card">
			<CardImg id="img" top width="100%" src={product.imageurl} alt="Card image cap" />
			<CardBody>
				<CardTitle id="titleProduct">{product.name}  {product.price} RON</CardTitle>

				<CardText>{product.description}</CardText>
				<CardSubtitle>Ingrediente: {product.ingredients}</CardSubtitle>

				<Button onClick={this.addToCart} className="btn btn-sm btn-primary float-right " id="btn" > Add to cart</Button>


			</CardBody>
		</Card>
		</div>

		)
	}
}
export default withRouter(ProductItem)