import React from 'react';
import { Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle, Button } from 'reactstrap';
import Modal from 'react-modal';
import axios from 'axios'

/*Style*/
import './MenuStyle.css';

class MenuCartItem extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      menuId: 0,
      //campuri pentru menu
      price: 0.0,
      productId: 0,
      details: '',

      // For Modal
      isActive: false,

      //campuri pentru meniu cu produs
      idMeniuNew: 0,
      name: "",
      description: "",
      price: 0.0,
      ingredients: "",
      weight: 0.0,
      kcal: 0,
      imageurl: "",
    }
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentWillMount() {
    Modal.setAppElement('body');
  }

  toggleModal = () => {
    this.setState({
      isActive: !this.state.isActive
    })
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value })
  }

  componentDidMount() {
    let id = this.props.menu.id;
    this.setState({
      menuId: id,
    })
    let productIdfromProps = this.props.menu.productId;

    const apiUrl = 'http://localhost:52004/api/Menus/getproduct/' + id + '/' + productIdfromProps;

    fetch(apiUrl)
      .then(response => {
        return response.json();
      })
      .then(data => {
        
        this.setState({
          idMeniuNew: data.id,
          name: data.name,
          description: data.description,
          ingredients: data.ingredients,
          weight: data.weight,
          kcal: data.kcal,
          imageurl: data.imageurl,
        });
      })
      .catch(error => {
        console.log(error);
      });

  }

  editMenu = () => {
    let id = this.props.menu.id;
    this.setState({
      menuId: id,
    })
    let productIdfromProps = this.props.menu.productId;

    const apiUrl = 'http://localhost:52004/api/Menus/' + id + '/' + productIdfromProps;
    //http://localhost:52004/api/Menus/getproduct/2/4

    axios.get(apiUrl)
      .then(res => {
        // const persons = res.data;
        this.setState({
          menuId: res.data.id,
          price: res.data.price,
          productId: res.data.productId,
          details: res.data.details,
        });
        console.log(res)
      })

    console.log('id' + this.state.menuId)
    console.log('price=' + this.state.price)
    console.log('productId=' + this.state.productId)
    console.log('details=' + this.state.details)

    console.log(apiUrl)

    this.toggleModal()

  }

  onSubmit(e) {
    e.preventDefault()

    const newMenu = {
      id: parseInt(this.state.menuId),
      details: this.state.details,
      price: parseFloat(this.state.price),
      productId: parseInt(this.state.productId),
    }

    console.log('Menu ID ' + this.state.menuId);
    console.log(' Detalii ' + this.state.details);
    console.log('Price ' + this.state.price);
    console.log('Product ID ' + this.state.productId);

    console.log('meniu = ' + newMenu.price)


    const options = {
      method: 'put',
      headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newMenu)
    }

    fetch('http://localhost:52004/api/Menus/' + this.state.menuId, options)
      .then(res => {
        console.log(res)
      })

    console.log('http://localhost:52004/api/Menus/' + this.state.menuId)


    //this.toggleModal();
    window.location.reload(false); //asta imi da refres dar nu se mai vede record-ul
  }


  deleteMenu = () => {
    let id = this.props.menu.id;
    let productId = this.props.menu.productId;

    const apiUrl = 'http://localhost:52004/api/Menus/' + id + '/' + productId;

    const options = {
      method: 'delete',
      headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
      },

    }
    fetch(apiUrl, options)

    window.location.reload(false)
  }


  render() {
    const { menu } = this.props;

    return (

      <Card id="card">
        <CardImg id="img" top width="100%" src={this.state.imageurl} alt="Card image cap" />
        <CardBody>
          <CardTitle id="titleProduct">{menu.details}  {menu.price} RON</CardTitle>
          <CardSubtitle id="titleProduct">{this.state.name}</CardSubtitle>

          <CardText>{this.state.id}</CardText>
          {/* <CardText>{this.state.description}</CardText> */}


          <Button onClick={this.deleteMenu} > Delete Menu</Button>
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          <Button onClick={this.editMenu}>Edit Menu</Button>

          <Modal
            className="color-modal"
            size="xl"
            aria-labelledby="example-modal-sizes-title-sm" isOpen={this.state.isActive} onRequestClose={this.toggleModal}>
            <button type="submit" onClick={this.toggleModal} className="btn btn-secondary">Close</button>


            <div className="container">
              <div className="row">
                <div className="col-md-6 mt-5 mx-auto">
                  <form noValidate onSubmit={this.onSubmit}>
                    <h1 className="h3 mb-3 front-weight-normal">
                      Edit Menu
                    </h1>



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
                      <label htmlFor="details">Details</label>
                      <input type="text"
                        className="form-control"
                        name="details"
                        placeholder="Enter details"
                        value={this.state.details}
                        onChange={this.onChange} />
                    </div>

                    {/* <div className="form-group">
                      <label htmlFor="productId">Produs</label>
                      <input type="text"
                        className="form-control"
                        name="productId"
                        placeholder="Enter productId"
                        value={this.state.productId}
                        onChange={this.onChange} />
                    </div> */}


                    <button type="submit" className="btn btn-secondary">Save</button>
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
export default MenuCartItem
