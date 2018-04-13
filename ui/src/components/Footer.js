import React from 'react'
import { Link } from 'react-router-dom'
    
const Footer = () => {
	const wrapperStyles = {
    width: "95%",
    display: "flex",
    justifyContent: "space-between",
    flexDirection: "row",
    alignItems: "center",
    margin: "10px 20px 0 20px",
    fontFamily: "avenir",
    position: "absolute",
    bottom: "0"
  }

  const footerContainerStyles = {
    width: "100%",
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center"
  }

  const linkItemStyles = {
  	marginLeft: "10px",
  	textDecoration: "none",
    color: "#6cb6d8"
  }

  return (
    <div id='footer' style={wrapperStyles}>
      <div style={footerContainerStyles}>
        <p>All Rights Reserved</p>
      	<Link style={linkItemStyles} to="http://belcortes.com" target="_blank">made by belcortes.com</Link>
      </div>
    </div>
  )
}

export default Footer