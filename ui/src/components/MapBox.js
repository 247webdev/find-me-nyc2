import React, { Component } from 'react';
import { Map, TileLayer, Marker, Popup } from 'react-leaflet';
import L from 'leaflet'

import markerIcon from '../images/marker-icon.png'
import shadowIcon from '../images/marker-shadow.png'
// import MarkerComponent from './MarkerComponent'

const stamenTonerTiles = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
const stamenTonerAttr = '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>';
const mapCenter = [40.701835, -73.941262];
const zoomLevel = 10;

class MapBox extends Component {
	// state = {
	// 	results: [
	// 		{
	// 			title: "address1",
	// 			lat: 40.701835,
	//     	lng: -73.941262
	// 		},
	// 		{
	// 			title: "address2",
	// 			lat: 41.701835,
	//     	lng: -72.941262
	// 		} 
 //  	]
	// }
  render() {

  	const myIcon = new L.icon({
	    iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-red.png',
	    iconSize: [25, 41],
      iconAnchor: [12, 41],
	    popupAnchor: [1, -34],
	    shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.3/images/marker-shadow.png',
	    shadowSize: [41, 41]
		});
  	
    return (
      <div>
        <Map center={mapCenter} zoom={zoomLevel}>

          <TileLayer attribution={stamenTonerAttr} url={stamenTonerTiles} />
          {console.log(myIcon)}
	        {this.props.results.map((result, index) => 
	        	// <MarkerComponent index={index} lat={result.lat} lng={result.lng} title={result.title} />
	          <Marker key={index} position={[result.lat, result.lng]} icon={myIcon}>
		          <Popup>
		            <span>{result.short_title}</span>
		          </Popup>
		        </Marker>
	        )}
          
        </Map>
      </div>
    );
  }
}

export default MapBox