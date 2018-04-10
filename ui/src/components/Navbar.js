import React from 'react'
import { Link } from 'react-router-dom'

import logo from '../images/logo.png'
    
const Navbar = () => {
	const wrapperStyles = {
    width: "95%",
    display: "flex",
    justifyContent: "space-between",
    flexDirection: "row",
    alignItems: "center",
    margin: "10px 20px 0 20px",
    fontFamily: "avenir"
  }

  const logoStyles = {
  	margin: "0",
    height: "50px"
  }

  const linkItemStyles = {
  	marginLeft: "10px",
  	textDecoration: "none",
    color: "#6cb6d8"
  }

  return (
    <div id='navbar' style={wrapperStyles}>
      <img style={logoStyles} src={logo} alt="logo"/>
      <div>
      	<Link style={linkItemStyles} to="/admin">admin</Link>
      	<Link style={linkItemStyles} to="/search">search</Link>
      	<Link style={linkItemStyles} to="/login">logout</Link>
      </div>
    </div>
  )
}

export default Navbar