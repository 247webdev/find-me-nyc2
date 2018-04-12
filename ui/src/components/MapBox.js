import React, { Component } from 'react';
import { Map, TileLayer, Marker, Popup } from 'react-leaflet';
import L from 'leaflet'

import MarkerComponent from './MarkerComponent'

const stamenTonerTiles = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
const stamenTonerAttr = '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>';
const mapCenter = [40.701835, -73.941262];
const zoomLevel = 10;

class MapBox extends Component {
	state = {
		results: [
			{
				title: "address1",
				lat: 40.701835,
	    	lng: -73.941262
			},
			{
				title: "address2",
				lat: 41.701835,
	    	lng: -72.941262
			} 
  	]
	}
  render() {
  	
    return (
      <div>
        <Map center={mapCenter} zoom={zoomLevel}>

          <TileLayer attribution={stamenTonerAttr} url={stamenTonerTiles} />

	        {this.state.results.map((result, index) => 
	        	// <MarkerComponent index={index} lat={result.lat} lng={result.lng} title={result.title} />
	          <Marker key={index} position={[result.lat, result.lng]}>
		          <Popup>
		            <span>{result.title}</span>
		          </Popup>
		        </Marker>
	        )}
          
        </Map>
      </div>
    );
  }
}

export default MapBox