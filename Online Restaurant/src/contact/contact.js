import React from 'react';
import { Container, Row, Col} from 'reactstrap';
import './contact.css';
import img from '../commons/images/img1.jpg';
import img2 from '../commons/images/img2.jpg';


function Contact() {

    return (

        <div >
            <Row id="backContact" fluid>
                <Col >
                    <img id="imgC" src={img} />
                </Col>
                <Col >
                    <h1 id="titleC">Contact</h1>
                    <h2 id="address"></h2>
                    <h3 id="paragraphC">Jan Kowalski, ul. Piękna 65B/m.54, 00-000 Warszawa</h3>
                    <h4 id="address"></h4>
                    <h5 id="paragraphC">+0040703031510</h5>
                    <h4 id="address"></h4>
                    <h5 id="paragraphC">contact@foodyum.com</h5>

                </Col>
                <Col >
                    <img id="imgC" src={img2} />
                </Col>
            </Row>

        </div>
    )
}

export default Contact
