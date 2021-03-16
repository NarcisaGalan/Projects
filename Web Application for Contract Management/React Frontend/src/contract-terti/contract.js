import React from 'react';
import { withRouter } from 'react-router-dom';
import './contract.css'
import { Button, Col, Row, Container} from 'reactstrap';
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Page1 from "./page1"
import Page2 from "./page2"
import 'react-confirm-alert/src/react-confirm-alert.css';

const backgroundStyle = {
  backgroundPosition: 'center',
  backgroundSize: 'cover',
  backgroundRepeat: 'no-repeat',
  height: "1550px",


};

class ContractTerti extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      errors: {}
    }
    this.next = this.next.bind(this);
    this.onChange = this.onChange.bind(this);
  }
  onChange(e) {
    this.setState({ [e.target.name]: e.target.value })
  }
  next() {
          this.slider.slickNext();  
  }

  render() {
    const settings = {
      dots: true,
      infinite: false,
      speed: 500,
      slidesToShow: 1,
      draggable: false,
      swipe: false,
      arrows: false

    };

    return (
      <div id="pageBackground2" style={backgroundStyle}>
        <h1 id="pageTitle">Întocmire Contract Terți</h1>
        <Container fluid >
          <Row>
            <Col sm="12" md={{ size: 10, offset: 1 }}>
              <Slider ref={c => (this.slider = c)} {...settings}>
                <div style={{ textAlign: "center" }} key={1} >
                  <Page1/>
                  <Button id="btn" className="btn btn-primary float-right "  onClick={this.next}> Înaintează</Button>
                </div>
                <div key={2} >
                  <Page2  />
                  
                </div>
              </Slider>

            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}
export default withRouter(ContractTerti);