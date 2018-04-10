import React, { Component } from 'react'
import { Redirect, Link} from 'react-router-dom'
    
class Signup extends Component {
  state = {
    username: '',
    redirectToSearchPage: false
  }

  saveUserInput = (e) => {
    const username = e.target.value;
    this.setState({username})
  }

  handleSubmit = (e) => {
    e.preventDefault();

    this.props.users.forEach((user) => {
      if(user.userName === this.state.username) {
        this.setState({redirectToSearchPage: true})
      }
    })
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
      marginBottom: "20px",
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
        <h2>Login</h2>
        <p>{this.state.error}</p>
        <form onSubmit={this.handleSubmit} id="login-form">
          <div>
            <input
              id="login-user-name"
              type="text"
              name="userName"
              placeholder='username'
              style={inputStyles}
              onChange={this.saveUserInput} />
          </div>

          <div>
            <input
              id="user-login-submit"
              type="submit"
              style={buttonStyles}
              value="Login" />
          </div>
        </form>
        <Link style={linkStyles} to="/signup">or sign up if you don't have an account</Link>
      </div>
    )
  }
  
}

export default Signup