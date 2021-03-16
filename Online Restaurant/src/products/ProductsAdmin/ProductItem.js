import React from 'react';
import { withRouter } from 'react-router-dom'
import axios from 'axios'
import '../productItem.css';
import Modal from 'react-modal';
import { Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle, Button } from 'reactstrap';


class ProductItem extends React.Component {

	constructor(props) {
		super(props);
		this.state = {

			newProductId: 0,

			productId: 0,
			price: 0.0,
			description: 'no details',
			ingredients: "",
			weight: 0.0,
			kcal: 0,
			imageurl: "",

			isActive: false,
		}
		this.onChange = this.onChange.bind(this);
		this.updateDB = this.updateDB.bind(this);
	}

	onChange(e) {
		this.setState({ [e.target.name]: e.target.value })
	}

	componentWillMount() {
		Modal.setAppElement('body');
	}

	toggleModal = () => {
		this.setState({
			isActive: !this.state.isActive
		})
	}

	deleteProduct = () => {

		let productId = this.props.product.id;
		const url = `http://localhost:52004/api/Products/` + productId;


		console.log(this.state.id);
		console.log(this.props.product.price);
		console.log(this.state.customerId);

		const options = {
			method: 'delete',
			headers: {
				'Accept': 'application/json, text/plain, */*',
				'Content-Type': 'application/json'
			},

		}
		fetch(url, options)

		this.setState({ alert_message: "success" });

		window.location.reload(false);

	}

	editProduct = () => {

		this.setState({
			productId: this.props.product.id,
			price: this.props.product.price,
			description: this.props.product.description,
			ingredients: this.props.product.ingredients,
			weight: this.props.product.weight,
			kcal: this.props.product.kcal,
			imageurl: this.props.product.imageurl

		});

		console.log('id produs = ' + this.state.productId)
		console.log('price = ' + this.state.price)
		console.log('description =' + this.state.description)
		console.log('ingredients = ' + this.state.ingredients)
		console.log('weight = ' + this.state.weight)
		console.log('kcal = ' + this.state.kcal)
		console.log('imageurl = ' + this.state.imageurl)

		this.toggleModal()

	}

	updateDB = () => {


		const updateProduct = {
			id: this.state.productId,
			name: this.props.product.name,
			price: this.state.price,
			description: this.state.description,
			ingredients: this.state.ingredients,
			weight: this.state.weight,
			kcal: this.state.kcal,
			imageurl: this.state.imageurl
		}

		console.log('produs = ' + updateProduct.price)

		const url = 'http://localhost:52004/api/Products/' + this.props.product.id;

		axios.put(url, updateProduct, {
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify(updateProduct),

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
		const { product } = this.props;

		return (

			<Card id="card">
				<CardImg id="img" top width="100%" src={product.imageurl} alt="Card image cap" />
				<CardBody>
					<CardTitle id="titleProduct">{product.name}  {product.price} RON</CardTitle>

					<CardText>{product.description}</CardText>
					<CardSubtitle>Ingrediente: {product.ingredients}</CardSubtitle>

					<Button onClick={this.deleteProduct} className="btn btn-sm btn-primary float-right " id="btn" > Delete</Button>
					<Button onClick={this.editProduct} className="btn btn-sm btn-primary" id="btnEdit" > Edit</Button>

					<Modal

						size="xl"
						aria-labelledby="example-modal-sizes-title-sm" isOpen={this.state.isActive} onRequestClose={this.toggleModal}>
						<button type="submit" onClick={this.toggleModal} className="btn btn-secondary">Close</button>
						<button type="submit" onClick={this.updateDB} className="btn btn-secondary">Save</button>

						<div className="container">
							<div className="row">
								<div className="col-md-6 mt-5 mx-auto">
									<form noValidate onSubmit={this.onSubmit}>
										<h1 className="h3 mb-3 front-weight-normal">Edit Product</h1>

										<div className="form-group">
											<label htmlFor="price">Price</label>
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

				</CardBody>
			</Card>

		)
	}
}
export default withRouter(ProductItem)