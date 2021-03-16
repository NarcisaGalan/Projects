import React from 'react';
import ProductItem from './ProductItem';
import { getProducts } from '../../componentCart/repository';
import { Container,Row,Col } from 'reactstrap';
import  './productItem.css';



export default class ProductList extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			products: []
		}
	}

	componentWillMount() {
		getProducts().then((products) => {
			this.setState({ products });
		});
	}

	render() {
		const { products } = this.state;
		return (
			<div id="backProducts">

				<Container fluid >
					
						<Col id="distance">
						<Row>
					{
						products.map((product, index) => <ProductItem product={product} key={index} />)
					}
					</Row>
					</Col>
					
				</Container>
			
			</div>
		);
	}
}
