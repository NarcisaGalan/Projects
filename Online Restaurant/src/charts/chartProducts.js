import React, { PureComponent } from 'react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, } from 'recharts';
import { Row, Col } from 'reactstrap';

import './charts.css';
 
import "react-datepicker/dist/react-datepicker.css";
import { getTopProducts } from './repository';


export default class ChartProducts extends PureComponent {
    static jsfiddleUrl = 'https://jsfiddle.net/alidingling/30763kr7/';

    constructor(props) {
		super(props);
		this.state = {
            products: [],
            menus:[]
		}
    }
    
    componentWillMount() {
		getTopProducts().then((products) => {
			this.setState({ products });
        });
        
	}

    
    render() {
        const { products } = this.state;
        console.log(products[0]);
        return (
            <div>
                <Row>
                    <Col>
                        <h1 id="text">Graphic information about products</h1>
                        <BarChart
                            width={700}
                            height={400}
                            data={products}
                            margin={{
                                top: 50, right: 30, left: 30, bottom: 0,
                            }}
                        >
                            <CartesianGrid strokeDasharray="3 3" />
                            <XAxis dataKey="name"/>
                            <YAxis />
                            <Tooltip />
                            <Legend />
                            <Bar dataKey="products_Ordered" name="# Number of orders" fill="#aa4b6b" />
                            
                        </BarChart>


                    </Col>
                    <Col>
                    <h1 id="text">Graphic information about menu</h1>
                        <BarChart
                            width= {700}
                            height={400}
                            data={products}
                            margin={{
                                top: 50, right: 30, left: 30, bottom: 0,
                            }}
                        >
                            <CartesianGrid strokeDasharray="3 3" />
                            <XAxis dataKey="name" />
                            <YAxis />
                            <Tooltip />
                            <Legend />
                            <Bar dataKey="products_Ordered" name="# Number of orders" fill="#aa4b6b" />
                            
                        </BarChart>


                    </Col>
                </Row>

            </div>
        );
    }
}
