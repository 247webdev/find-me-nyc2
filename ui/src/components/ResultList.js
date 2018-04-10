import React from 'react'

import Result from './Result'
    
const ResultList = (props) => {

  return (
    <div id='result-list'>
      <h1>Results</h1>
      {
        props.results.slice(0, 20).map((result, index) => {
          return (

            <Result
              result={result}
              key={index}
              index={index} />
          ) 
        })
      }
    </div>
  )
}

export default ResultList