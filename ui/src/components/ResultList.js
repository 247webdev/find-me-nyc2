import React from 'react'

import Result from './Result'
import MapBox from './MapBox'
    
const ResultList = (props) => {

  const resultBoxStyles = {
    display: "flex",
    marginTop: "50px"
  }

  const resultListStyle = {
    padding: "0 100px",
    height: "400px",
    overflowY: "scroll"
  }



  return (
    <div id='result-list'>
      <div style={resultBoxStyles}>
        <div id="map-box"><MapBox results={props.results} /></div>
        <div style={resultListStyle}>
          {
            props.results.map((result, index) => {
              return (

                <Result
                  result={result}
                  key={index}
                  index={index} />
              ) 
            })
          }
        </div>
      </div>
      
    </div>
  )
}

export default ResultList