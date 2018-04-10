import React from 'react'
    
const Result = (props) => {

  return (
    <div id="result-item" data-result-display>
      <h3>{props.result.agency_name}</h3>
      <p>{props.result.section_name}</p>
      <p>{props.result.short_title}</p>
      <p>{props.result.start_date}</p>
    </div>
  )
}

export default Result