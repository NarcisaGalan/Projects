import React from 'react';
import CartItem from './MenuCartItem';

export default class Cart extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            menus: [],
            total: 0
        }
    }

    /*FETCH FOR GETALLMENUS */
    componentWillMount() {
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

    render() {
        const { menus } = this.state;
        return (
            <div className=" container" >
                <h3 className="card-title">Menus</h3>
                {
                    menus.map((menu, index) => <CartItem menu={menu} key={index} />)
                }
            </div>

        );
    }
}
