import React from 'react'
    
const Result = (props) => {

	const resultItemStyle = {
		textAlign: "right",
		marginBottom: "50px"
	}

  return (
    <div id="result-item" style={resultItemStyle} data-result-display>
      <h3>{props.result.short_title}</h3>
      <p>{props.result.address}</p>
      <p>{props.result.section_name}</p>
      <p>{props.result.event_date}</p>
    </div>
  )
}

export default Result