import React from 'react';
import { withScriptjs } from "react-google-maps";

import  Map from './Map';

function MapRender() {

  const MapLoader = withScriptjs(Map);

  return (
<div>
  <MapLoader
      googleMapURL="https://maps.googleapis.com/maps/api/js?key=AIzaSyAxHQlvVIaIo8Qru_OIkKVD9715xj509Ww"
      loadingElement={<div style={{ height: `100%` , width: `100%` }} />}
  />
  </div>
  );
}

export default MapRender;