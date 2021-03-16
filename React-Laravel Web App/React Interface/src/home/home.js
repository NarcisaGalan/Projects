import React from 'react';

import BackgroundImg from '../commons/images/future-medicine.jpg';

import {Button, Container, Jumbotron} from 'reactstrap';
import { withNamespaces } from 'react-i18next';
import {  withRouter } from 'react-router-dom';

const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "100%",
    height: "900px",
    margin:0,
    backgroundImage: `url(${BackgroundImg})`
};


class Home extends React.Component {


    render() {
        const {t}=this.props;
        return (

            <div id = "back">
                <h1 id= "pagetitle" >{t("home.msg")}</h1>
                <Jumbotron fluid style={backgroundStyle}>
                    
                    <Container fluid>
                        <p className="lead">
                            <Button color="primary" id = "fixedbutton" onClick={() => window.open('http://users.utcluj.ro/~ianghel/DAW/')}>
                            {t("home.button1")}</Button>
                        </p>
                    </Container>
                </Jumbotron>
                
                
            </div>
        )
    };
}

export default withRouter( withNamespaces()(Home))
