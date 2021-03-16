import React from 'react';
import { Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle, Button } from 'reactstrap';
import Modal from 'react-modal';
import Modal2 from 'react-modal';
import axios from 'axios'

/*Style*/
import './MenuStyle.css';

import '../../principal/nav.css'
import '../../commons/styles/buttons.css';
import '../../reservation/styles/styleModal.css'
import '../../reservation/styles/styleSelect.css';

import imgMenu from '../../menu/menu.jpg';

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
      isActive2: false,

      //campuri pentru meniu cu produs
      idMeniuNew: 0,
      name: "",
      description: "",
      price: 0.0,
      ingredients: "",
      weight: 0.0,
      kcal: 0,
      imageurl: "",
      menus: [],
      teams: [],

      productsFromMenuID: [],

      products: '',
      menuIdRemember: 0
    }
  }


  render() {
    const { menu, productsFromMenuID } = this.props;

    return (

      <Card id="card">
        <CardImg id="img" top width="100%" src={imgMenu} alt="Card image cap" />
        <CardBody>
          <CardTitle id="titleProduct">{menu.detail}  {menu.price} RON</CardTitle>
          <CardText id="titleProduct">{menu.products}</CardText>
        </CardBody>
      </Card>




    )
  }
}
export default MenuCartItem
