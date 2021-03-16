import React from 'react';
import { Container, Row } from 'reactstrap';

import '../../reservation/styles/styleTable.css'
import '../../principal/nav.css'
import MenuCartItem from './MenuCartItem';





class AllMenus extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            menus: [],
            response: {},
            alert_message: ''
        }
    }



    componentDidMount() {/*GET ALL MENUS */
        const apiUrlGetProductsFromMenu = "http://localhost:52004/api/Products/getproductsfrommenu"
        fetch(apiUrlGetProductsFromMenu)
            .then(response => {
                return response.json();
            })
            .then(data => {
                this.setState({
                    menus: data
                });
            })
            .catch(error => {
                console.log(error);
            });

    }

    render() {
        const { error, menus } = this.state;

        if (error) {
            return (
                <div>Error: {error.message}</div>
            )
        } else {

            return (

                <div id="backProducts">

                    <Container fluid >
                        <Row>
                            {
                                menus.map((menu, index) => <MenuCartItem menu={menu} key={index} />)
                            }
                        </Row>

                    </Container>

                </div>

            )
        }
    }
}

export default AllMenus;