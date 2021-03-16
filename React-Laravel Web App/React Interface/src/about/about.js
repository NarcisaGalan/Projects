import React from 'react';
import {Container, Jumbotron} from 'reactstrap';
import img from '../commons/images/about.jpg';
import { withNamespaces } from 'react-i18next';
import {  withRouter } from 'react-router-dom';

const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "100%",
    height: "650px",
    margin: 0,
};


function About({t}) {

        return (

            <div>
               
                 <Jumbotron fluid style={backgroundStyle} id= "backA">
                    <Container fluid >
                     <h1 id= "titleA">{t("about.title")}</h1>
                     <h2 id="paragraphA">{t("about.des")}</h2>
                     <img src={img} width="150" height="200" />
                    </Container>
                </Jumbotron>
                
            </div>
        )
};


export default withRouter(withNamespaces() (About))