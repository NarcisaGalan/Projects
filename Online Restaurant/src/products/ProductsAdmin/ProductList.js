import React from 'react';
import ProductItem from './ProductItem';
import { getProducts } from '../../componentCart/repository';
import { Container,Row,Col,Button} from 'reactstrap';
import Modal from 'react-modal';
import  './productItem.css';
import axios from 'axios';



export default class ProductList extends React.Component {
	constructor(props) {
		super(props);
		this.state = {

			id: 0,price: 0.0,
			name: "",
			description: 'no details',
			ingredients: "",
			weight: 0.0,
			kcal: 0,
			imageurl: "",

			isActive: false,

			products: []
		}
		this.onChange = this.onChange.bind(this);
		this.addProduct = this.addProduct.bind(this);
	}

	componentWillMount() {
		getProducts().then((products) => {
			this.setState({ products });
		});
		Modal.setAppElement('body');
	}

	onChange(e) {
		this.setState({ [e.target.name]: e.target.value })
	}

	toggleModal = () => {
		this.setState({
			isActive: !this.state.isActive
		})
	}


	openModal = () =>{

		const newProduct = {
			id: this.state.id,
			name: this.state.name,
			price: this.state.price,
			description: this.state.description,
			ingredients: this.state.ingredients,
			weight: this.state.weight,
			kcal: this.state.kcal,
			imageurl: this.state.imageurl
		}

		this.toggleModal();

	}


	addProduct = () =>{

		const newProduct = {
			id: this.state.id,
			name: this.state.name,
			price: this.state.price,
			description: this.state.description,
			ingredients: this.state.ingredients,
			weight: this.state.weight,
			kcal: this.state.kcal,
			imageurl: this.state.imageurl
		}

		console.log('id produs = ' + this.state.id)
		console.log('price = ' + this.state.price)
		console.log('description =' + this.state.description)
		console.log('ingredients = ' + this.state.ingredients)
		console.log('weight = ' + this.state.weight)
		console.log('kcal = ' + this.state.kcal)
		console.log('imageurl = ' + this.state.imageurl)

		const url = 'http://localhost:52004/api/Products/';

		axios.post(url, newProduct, {
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify(newProduct),

		})
			.then(res => {
				console.log(res)
			});

		

		this.setState({
			alert_message: "success"
		});
		window.location.reload(false);


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
				<Button onClick={this.openModal} className="btn btn-primary" id="fixedbutton" > Add Product</Button>
				<Modal

						size="xl"
						aria-labelledby="example-modal-sizes-title-sm" isOpen={this.state.isActive} onRequestClose={this.toggleModal}>
						<button type="submit" onClick={this.toggleModal} className="btn btn-secondary">Close</button>
						<button type="submit" onClick={this.addProduct} className="btn btn-secondary">Save</button>

						<div className="container">
							<div className="row">
								<div className="col-md-6 mt-5 mx-auto">
									<form noValidate onSubmit={this.onSubmit}>
										<h1 className="h3 mb-3 front-weight-normal">Edit Product</h1>

										<div className="form-group">
											<label htmlFor="price">Name</label>
											<input type="text"
												className="form-control"
												name="name"
												placeholder="Enter price"
												value={this.state.name}
												onChange={this.onChange} />
										</div>

										<div className="form-group">
											<label htmlFor="price">price</label>
											<input type="text"
												className="form-control"
												name="price"
												placeholder="Enter price"
												value={this.state.price}
												onChange={this.onChange} />
										</div>

										<div className="form-group">
											<label htmlFor="description">Description</label>
											<input type="text"
												className="form-control"
												name="description"
												placeholder="Enter description"
												value={this.state.description}
												onChange={this.onChange} />
										</div>

										<div className="form-group">
											<label htmlFor="ingredients">Ingredients</label>
											<input type="text"
												className="form-control"
												name="ingredients"
												placeholder="Enter ingredients"
												value={this.state.ingredients}
												onChange={this.onChange} />
										</div>

										<div className="form-group">
											<label htmlFor="imageurl">Image URL</label>
											<input type="text"
												className="form-control"
												name="imageurl"
												placeholder="Enter image URL"
												value={this.state.imageurl}
												onChange={this.onChange} />
										</div>

										<div className="form-group">
											<label htmlFor="weight">Weight</label>
											<input type="text"
												className="form-control"
												name="weight"
												placeholder="Enter weight"
												value={this.state.weight}
												onChange={this.onChange} />
										</div>

										<div className="form-group">
											<label htmlFor="kcal">kCal</label>
											<input type="text"
												className="form-control"
												name="kcal"
												placeholder="Enter kCal"
												value={this.state.kcal}
												onChange={this.onChange} />
										</div>


									</form>
								</div>
							</div>
						</div>

					</Modal>
	
			</div>
		);
	}
}
