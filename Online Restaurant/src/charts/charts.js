import React, { Component } from 'react';
import ChartReservation from './chartReservation'
import ChartProducts from './chartProducts'
import './charts.css'


export default class Charts extends Component {
   render(){
    return (
        <div id = "back">
            
            <ChartReservation/>
            <ChartProducts/>
              

        </div>
    )

   }
      
}

