import React, { Component } from 'react';
import img from '../../commons/images/restaurantPlan.gif';
import AllReservation from './allReservation';
import { withRouter } from 'react-router-dom'




class Reservation extends Component {

    constructor(props) {
        super(props)

        this.state = {
            
        }
    }



    render() {
        return (
            <div>
                <section>
                    <img src={img} />
                    <AllReservation />
                </section>
            </div>
        );
    }
}

export default withRouter(Reservation);