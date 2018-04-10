import React, { Component } from 'react'
import { Redirect, Link } from "react-router-dom";
    
class Signup extends Component {
  state = {
    user: {},
    redirectToSearchPage: false
  }

  handleSubmit = (e) => {
    e.preventDefault();

    this.props.createUser(this.state.user)

    this.setState({redirectToSearchPage: true})
  }

  handleChange = (e) => {
    const attributeToChange = e.target.name
    const newValue = e.target.value

    const updatedUser = { ...this.state.user }
    updatedUser[attributeToChange] = newValue
    this.setState({ user: updatedUser })
  }

  render() {
    // found this redirect trick online
    if(this.state.redirectToSearchPage) {
      return <Redirect to="/search" />
    }

    const containerStyles = {
      alignItems: "center",
      height: "100vh",
      justifyContent: "center",
      display: "flex",
      width: "100vw",
      flexDirection: "column",
      color: "gray"
    }

    const inputStyles = {
      margin: "10px 0",
      padding: "0 10px",
      height: "30px"
    }

    const buttonStyles = {
      width: "100%",
      marginBottom: "20px",
      background: "#e46766",
      border: "0",
      color: "white",
      padding: "10px"
    }

    const linkStyles = {
      textDecoration: "none",
      color: "#6cb6d8"
    }

    return (
      <div style={containerStyles}>
        <h2>Signup</h2>
          <form onSubmit={this.handleSubmit} id="signup-form">
            <div>
              <input
                  id="new-user-user-name"
                  type="text"
                  name="userName"
                  placeholder='username'
                  style={inputStyles}
                  onChange={this.handleChange} />
            </div>

            <div>
              <input
                  id="new-user-first-name"
                  type="text"
                  name="firstName"
                  placeholder='first name'
                  style={inputStyles}
                  onChange={this.handleChange} />
            </div>

            <div>
              <input
                  id="new-user-last-name"
                  type="text"
                  name="lastName"
                  placeholder='last name'
                  style={inputStyles}
                  onChange={this.handleChange} />
            </div>

            <div>
              <input
                  id="new-user-submit"
                  type="submit"
                  style={buttonStyles}
                  value="Create" />
            </div>
          </form>
          <Link style={linkStyles} to="/login">or login if you already have an account</Link>
      </div>
    )
  }
  
}

export default Signup