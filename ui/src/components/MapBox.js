import React, { Component } from 'react';
import { Map, TileLayer, Marker, Popup } from 'react-leaflet';

const stamenTonerTiles = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
const stamenTonerAttr = '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>';
const mapCenter = [40.701835, -73.941262];
const zoomLevel = 10;

class MapBox extends Component {
	state = {
    lat: 40.701835,
    lng: -73.941262
  }
  render() {
  	const position = [this.state.lat, this.state.lng]
    return (
      <div>
        <Map
          center={mapCenter}
          zoom={zoomLevel}>
          <TileLayer
              attribution={stamenTonerAttr}
              url={stamenTonerTiles} />
          <Marker position={position}>
	          <Popup>
	            <span>
	              A pretty CSS3 popup. <br /> Easily customizable.
	            </span>
	          </Popup>
	        </Marker>
        </Map>
      </div>
    );
  }
}

export default MapBox