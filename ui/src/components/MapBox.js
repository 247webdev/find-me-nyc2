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
  	const image = L.Icon({
       iconUrl: "http://gkv.com/wp-content/uploads/leaflet-maps-marker-icons/map_marker-red-small.png",
       // shadowUrl: require('../public/marker-shadow.png'),
       iconSize:     [38, 95], // size of the icon
       shadowSize:   [50, 64], // size of the shadow
       iconAnchor:   [22, 94], // point of the icon which will correspond to marker's location
       shadowAnchor: [4, 62],  // the same for the shadow
       popupAnchor:  [-3, -76]// point from which the popup should open relative to the iconAnchor
   })
  	
    return (
      <div>
        <Map
          center={mapCenter}
          zoom={zoomLevel}>
          <TileLayer
              attribution={stamenTonerAttr}
              url={stamenTonerTiles} />
          {this.state.results.map((result, index) => {
          	<Marker key={result.index} position={[result.lat, result.lng]} icon={image} />
          	// <MarkerComponent title={result.title} lat={result.lat} lng={result.lng} index={index} />
          	// console.log('hello')
          })}
          
        </Map>
      </div>
    );
  }
}

export default MapBox