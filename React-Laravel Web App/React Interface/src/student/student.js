import React from 'react';
import {Container, Jumbotron} from 'reactstrap';
import {  withRouter } from 'react-router-dom';


const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "100%",
    height: "650px",
    margin: 0,
};


class Student extends React.Component {


    render() {

        return (

            <div>
              
                <h1 id= "titleC">Contact </h1>
                 <Jumbotron fluid style={backgroundStyle} id= "backC">
                    <Container fluid >
                    <h2 id="address">Address</h2>
                    <h3 id="paragraphC">Jan Kowalski, ul. Piękna 65B/m.54, 00-000 Warszawa</h3>
                    <h4 id="address">Number</h4>
                    <h5 id="paragraphC">+0040703031510</h5>
                    <h4 id="address">E-mail</h4>
                    <h5 id="paragraphC">g.narcisamaria@gmail.com</h5>
                    
                    </Container>
                </Jumbotron>
                
            </div>
        )
    };
}

export default withRouter(Student);