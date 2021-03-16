import React, { PureComponent } from 'react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, } from 'recharts';
import { Row, Col } from 'reactstrap';

import './charts.css';
import './date.css';
import DatePicker from "react-datepicker";
 
import "react-datepicker/dist/react-datepicker.css";
import moment from 'moment';
import { getComments} from './repository';


export default class ChartComments extends PureComponent {
    static jsfiddleUrl = 'https://jsfiddle.net/alidingling/30763kr7/';


    constructor(props) {
        super(props)
        this.state = {
            startDate: new Date(),
            comments: []
        };
        this.handleChange = this.handleChange.bind(this);
        
    }

    handleChange = date => {
        this.setState({
            startDate: date
        });
        date = moment(date).format("YYYY-MM-DD");
      //  console.log("mment=  " + date);
       // console.log(typeof date);

        getComments(date.toString()).then((comments) => {
			this.setState({ comments });
       });
        
    };


    render() {
        const { comments } = this.state;
        var newArrayDataOfOjbect = Object.values(comments);
        return (
            <div>
                <Row>
                    <Col>
                        <h1 id="text">Graphic information about comments/day</h1>
                        <BarChart
                            width={700}
                            height={400}
                            data={newArrayDataOfOjbect}
                            margin={{
                                top: 50, right: 30, left: 30, bottom: 0,
                            }}
                        >
                            <CartesianGrid strokeDasharray="3 3" />
                            <XAxis dataKey="date" name="# Date"  />
                            <YAxis />
                            <Tooltip />
                            <Legend />
                            <Bar dataKey="commentsNumber" name="# Comments number" fill="#aa4b6b" />
                            
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
