import React from 'react';
import { Container,Row } from 'reactstrap';

import '../reservation/styles/styleTable.css'
import '../principal/nav.css'
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
        const apiUrl = 'http://localhost:52004/api/Menus';

        fetch(apiUrl)
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        menus: result
                    });
                },
                (error) => {
                    this.setState({ error });
                }
            )
            
    }
    

    deleteProduct(id, productId) {/*DELETE RESERVATION WITH ID*/
        const { menus } = this.state;

        const apiUrl = 'http://localhost:52004/api/Menus/' + menus.id + '/' + menus.productId;
        //const apiUrl = 'http://localhost:52004/api/Menus/1/3';
        const formData = new FormData();
        formData.append('id', id);

        const options = {
            method: 'DELETE',
            //body: formData
        }

        fetch(apiUrl, options)
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        response: result,
                        menus: menus.filter(menu => menu.id !== id)
                    });
                },
            )
            .then(res=> {
                this.setState({
                    alert_message: "success"
                });
            })
            .catch(error=>{
                this.setState({
                    alert_message: "error"
                });
            })
            //window.location.reload(false);
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