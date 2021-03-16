 import React from 'react';
 import {withRouter} from 'react-router-dom'


import './productItem.css';
import { Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle, Button, Container } from 'reactstrap';


class ProductItem extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			id: 0,
			details: 'no details',
			price: 0.0,
			status: 'in cart',
			quantity: 1,
			productId: 0,
			customerId: 0,
		}
		
	}



redirectTo = () => {

	this.props.history.push(`login`)

}
	render() {
		const { product } = this.props;
		return (

			<Card id="card">
				<CardImg id="img" top width="100%" src={product.imageurl} alt="Card image cap" />
				<CardBody>
					<CardTitle id="titleProduct">{product.name}  {product.price} RON</CardTitle>

					<CardText>{product.description}</CardText>
					<CardSubtitle>Ingrediente: {product.ingredients}</CardSubtitle>

					<Button onClick={this.redirectTo} className="btn btn-sm btn-primary float-right " id="btn" > Add to cart</Button>
					

				</CardBody>
			</Card>

		)
	}
}
export default withRouter(ProductItem)