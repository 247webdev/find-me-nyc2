import React, { Component } from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import axios from 'axios'

import UserList from './components/UserList'
import SearchPage from './components/SearchPage'
import Signup from './components/Signup'
import Login from './components/Login'

class App extends Component {
  state = {
    users: [],
    results: [],
    searchInput: ''
  }

  deleteUser = async (userId, index) => {
    try {
      await axios.delete(`${process.env.REACT_APP_USERS_API}/users/${userId}`)

      const updatedUsersList = [...this.state.users]
      updatedUsersList.splice(index, 1)

      this.setState({users: updatedUsersList})
    } catch (error) {
        console.log(`Error deleting User with ID: ${userId}`)
    }
  }

  createUser = async (newUser) => {
    try {
        const newUserResponse = await axios.post(`${process.env.REACT_APP_USERS_API}/users`, newUser)
        const newUserFromDatabase = newUserResponse.data

        const updatedUsersList = [...this.state.users]
        updatedUsersList.push(newUserFromDatabase)

        this.setState({users: updatedUsersList})

    } catch (error) {
        console.log("Error creating new User")
    }
  }

  handleUserChange = (e, index) => {
    const attributeToChange = e.target.name
    console.log(attributeToChange)
    const newValue = e.target.value

    const updatedUsersList = [...this.state.users]
    const userToUpdate = updatedUsersList[index]
    userToUpdate[attributeToChange] = newValue
    
    this.setState({users: updatedUsersList})
    console.log(this.state.users)
  }

  updateUser = async (index) => {
    try {
      const userToUpdate = this.state.users[index]
      await axios.patch(`${process.env.REACT_APP_USERS_API}/users/${userToUpdate.id}`, userToUpdate)
    } catch(error) {
        console.log('Error updating idea!')
        console.log(error)
    }
  }

  handleSearchSubmit = (searchInput) => {
    this.setState({searchInput})
    axios.get(`https://data.cityofnewyork.us/resource/buex-bi6w.json?section_name=Public%20Hearings%20and%20Meetings`)
      .then((response) => {
        console.log(response.data)
        this.setState({results: response.data})
      })
      .catch((error) => {
          console.log('Error retrieving users!')
          console.log(error)
      })
  }

  componentDidMount() {
    axios.get(`${process.env.REACT_APP_USERS_API}/users`)
      .then((response) => {
        console.log(response)
        this.setState({users: response.data})
      })
      .catch((error) => {
          console.log('Error retrieving users!')
          console.log(error)
      })
  }

  render() {
    const UserListComponent = () => (
        <UserList 
          users={this.state.users} 
          deleteUser={this.deleteUser}
          handleUserChange={this.handleUserChange}
          updateUser={this.updateUser} />
    )

    const SearchPageComponent = () => (
      <SearchPage 
        results={this.state.results}
        handleSearchSubmit={this.handleSearchSubmit} />)

    const SignupComponent = () => <Signup createUser={this.createUser} />

    const LoginComponent = () => <Login users={this.state.users} />

    return (
      <div>
        <Router>
          <Switch>
            <Route exact path="/admin" render={UserListComponent}/>
            <Route exact path="/search" render={SearchPageComponent}/>
            <Route exact path="/signup" render={SignupComponent}/>
            <Route exact path="/login" render={LoginComponent}/>
          </Switch>
        </Router>
      </div>
    );
  }
}

export default App;
