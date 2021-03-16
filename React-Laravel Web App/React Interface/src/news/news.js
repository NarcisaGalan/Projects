import React from 'react';
import { Player } from 'video-react';
import style from 'C:/Users/GNarc/Desktop/DAW/react-js-example/node_modules/video-react/dist/video-react.css';
import video1 from '../commons/videos/dance.mp4'
import video2 from '../commons/videos/delivering-breakfast.mp4'
import { Button, Container, Jumbotron, Row, Col } from 'reactstrap';
import BackgroundImg from '../commons/images/robot.jpg';
import newsInfo from "./news.xml";
import './news.css';
import { withNamespaces } from 'react-i18next';
import {  withRouter } from 'react-router-dom';

const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    margin: 0,
    color: 'white',
    width: "100%",
    height: "800px",
    backgroundImage: `url(${BackgroundImg})`
};

class News extends React.Component {
    state = {
        NumberAricles: "",
        Title: "",
        ImageUrl: "",
        AccessURL: "",
        newsInfo: [],

    }
    componentDidMount() {
        fetch(newsInfo).then((resp) => resp.text()).then(function (resp) {
            let parser = new DOMParser(),
                xmlDoc = parser.parseFromString(resp, "text/xml");
            // console.log("funtioneaza");
            return xmlDoc
        })
            .then((xmlDocResponse) => {
                // console.log("funtioneaza2");
                this.setState({
                    Title: xmlDocResponse.getElementsByTagName("News")[0]
                        .getElementsByTagName("Article")[0]
                        .getElementsByTagName("Title")[0].childNodes[0].nodeValue,
                    ImageUrl: xmlDocResponse.getElementsByTagName("News")[0]
                        .getElementsByTagName("Article")[0]
                        .getElementsByTagName("Image")[0].childNodes[0].nodeValue,
                    Description: xmlDocResponse.getElementsByTagName("News")[0]
                        .getElementsByTagName("Article")[0]
                        .getElementsByTagName("Description")[0].childNodes[0].nodeValue,
                    AccessURL: xmlDocResponse.getElementsByTagName("News")[0]
                        .getElementsByTagName("Article")[0]
                        .getElementsByTagName("Link")[0].childNodes[0].nodeValue
                });

            });
    }

    render() {
        const { t } = this.props;

        return (

            <div className="" id="backN" >

                <h id="title">{t("news.title")}</h>
                <Jumbotron fluid style={backgroundStyle}>
                    <Container fluid>
                        <h1 id="subtitle">{t("news.robotDance")}</h1>
                        <Player playsInline
                            poster="/assets/poster.png"
                            src={video1}
                            fluid={false}
                            width={480}
                            height={272}

                        />
                        <h2 id="subtitle">{t("news.robotFood")}</h2>
                        <Player playsInline
                            poster="/assets/poster.png"
                            src={video2}
                            fluid={false}
                            width={480}
                            height={272}

                        />
                        <p className="lead">
                            <Button outline color="primary" size="sm" id="newsButton" onClick={() => window.open('https://www.sciencedaily.com/news/computers_math/robotics/')}>
                            {t("news.button1")}</Button>
                        </p>
                    </Container>
                </Jumbotron>
                <Container fluid>
                    <Row xs="2">
                        <Col>
                            <h1 id="newsTitle">{this.state.Title}</h1>
                            <h2 id="paragraph">{this.state.Description}</h2>
                            <a href={this.state.AccessURL} class="active">Link</a>

                        </Col>
                        <Col>
                            <img id="newsImg" src={this.state.ImageUrl} />

                        </Col>

                    </Row>

                </Container>
            </div>

        )


    }


}

export default withRouter(withNamespaces()(News)) 