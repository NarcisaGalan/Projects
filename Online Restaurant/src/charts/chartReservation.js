import React, { PureComponent } from 'react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, } from 'recharts';
import { Row, Col } from 'reactstrap';

import './charts.css';
import './date.css';
import DatePicker from "react-datepicker";
 
import "react-datepicker/dist/react-datepicker.css";
import moment from 'moment';
import { getReservation } from './repository';


export default class ChartReservation extends PureComponent {
    static jsfiddleUrl = 'https://jsfiddle.net/alidingling/30763kr7/';


    constructor(props) {
        super(props)
        this.state = {
            startDate: new Date(),
            reservations: [
                {
                    hourOfDay : ""

                }
            ]
        };
        this.handleChange = this.handleChange.bind(this);
        
    }



    handleChange = date => {
        this.setState({
            startDate: date
        });
        date = moment(date).format("YYYY-MM-DD");
        console.log("mment=  " + date);
        console.log(typeof date);

        getReservation(date.toString()).then((reservations) => {
			this.setState({ reservations });
       });
        
    };


    render() {
        const { reservations } = this.state;
        
        console.log(reservations);
        return (
            <div>
                <Row>
                    <Col>
                        <h1 id="text">Graphic information about reservation/hour</h1>
                        <BarChart
                            width={700}
                            height={400}
                            data={reservations}
                            margin={{
                                top: 50, right: 30, left: 30, bottom: 0,
                            }}
                        >
                            <CartesianGrid strokeDasharray="3 3" />
                            <XAxis dataKey="hourOfDay" name="# Hour"  />
                            <YAxis />
                            <Tooltip />
                            <Legend />
                            <Bar dataKey="reservationsNo" name="# Number of reservations" fill="#aa4b6b" />
                            
                        </BarChart>


                    </Col>
                    <Col>
                    <h1 id="choose">Choose the date</h1>
                        <DatePicker placeholderText="Click to select a date" 
                            
                            onChange={this.handleChange}
                        />
                    </Col>
                </Row>

            </div>
        );
    }
}
