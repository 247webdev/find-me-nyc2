import React from 'react'
import L from 'leaflet'
import { Marker, Popup } from 'react-leaflet';
    
const MarkerComponent = (props) => {

	const image = L.Icon({
       iconUrl: "http://gkv.com/wp-content/uploads/leaflet-maps-marker-icons/map_marker-red-small.png",
       // shadowUrl: require('../public/marker-shadow.png'),
       iconSize:     [38, 95], // size of the icon
       shadowSize:   [50, 64], // size of the shadow
       iconAnchor:   [22, 94], // point of the icon which will correspond to marker's location
       shadowAnchor: [4, 62],  // the same for the shadow
       popupAnchor:  [-3, -76]// point from which the popup should open relative to the iconAnchor
   })

	const position = [props.lat, props.lng]

  return (
  	<div>
	    <Marker key={props.index} position={position}>
	    	<Popup>
	        <span>
	          {props.title}
	        </span>
	      </Popup>
	    </Marker>
    </div>
  )
}

export default MarkerComponent