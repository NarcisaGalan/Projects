/*global google*/
import React, { Component } from "react";
import {
    withGoogleMap,
    GoogleMap,
    DirectionsRenderer
} from "react-google-maps";


class Map extends Component {
    state = {
        directions: null,

        currentPosition : {
            lat: 0.0,
            lng: 0.0
          } 
        
        
};

success = position => {
    const currentPosition = {
      lat: position.coords.latitude,
      lng: position.coords.longitude
    } 
   
    this.setState({currentPosition: currentPosition});
    console.log(this.state.currentPosition);
  };

componentDidMount() {
    
    const directionsService = new google.maps.DirectionsService();
    navigator.geolocation.getCurrentPosition(this.success);
    
  
    const origin = this.state.currentPosition;
    const destination = { lat: 46.772360 , lng:  23.585236};


    directionsService.route(
        {
            origin: origin,
            destination: destination,
            travelMode: google.maps.TravelMode.DRIVING,
            
        },
        (result, status) => {
            if (status === google.maps.DirectionsStatus.OK) {
                console.log(result)
                this.setState({
                    directions: result
                });
            } else {
                console.error(`error fetching directions ${result}`);
            }
        }
    );
}

render() {
    const GoogleMapExample = withGoogleMap(props => (
        <GoogleMap
            defaultCenter={{ lat: 46.765882, lng:  23.578617 }}
            defaultZoom={13}
        >
            <DirectionsRenderer
                directions={this.state.directions}
            />
        </GoogleMap>
    ));

    return (
        <div>
            <GoogleMapExample
                containerElement={<div style={{ height: `500px`, width: "500px" }} />}
                mapElement={<div style={{ height: `100%` }} />}
            />
        </div>


       );
    }
}

export default Map;