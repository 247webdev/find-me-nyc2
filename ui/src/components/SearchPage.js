import React, { Component } from 'react'

import ResultList from './ResultList'
import Navbar from './Navbar'
    
class SearchPage extends Component {
	state = {
		submitted: false,
		searchInput: ''
	}

	saveNewInput = (e) => {
		const searchInput = e.target.value;
		this.setState({searchInput})
	}

	handleSubmit = (e) => {
		e.preventDefault();
    this.setState({'submitted': true });
    this.props.handleSearchSubmit(this.state.searchInput)
	}

	render() {
		const searchContainerStyles = {
			width: "80%",
    	margin:" 0 auto",
    	textAlign: "center",
    	fontFamily: "avenir",
    	marginTop: "50px"
		}

		const blurbStyles = {
			width: "60%",
			textAlign: "left",
			color: "gray",
			margin: "0 auto",
			marginBottom: "50px",
			marginTop: "50px"
		}

		const formStyles = {
			display: "flex",
			alignItems: "center",
			justifyContent: "center"
		}

		const searchInputStyles = {
			width: "300px",
    	height: "40px",
    	padding: "0 10px",
    	fontSize: "20px"
		}

		const buttonStyles = {
			border: "0",
    	background: "#e46766",
    	height: "42px",
    	color: "white",
    	marginLeft: "20px",
    	cursor: "pointer"
		}

		return (
	    <div id="search-wrapper">
	    	<Navbar />
	    	<div style={searchContainerStyles}>
	    		<h1>Find the data you need!</h1>
	    		<p id='search-blurb' style={blurbStyles}>Search for public hearings and meetings, public auctions and sales, solicitations and awards and official rules proposed and adopted by city agencies.</p>
		      <div>
		        <form style={formStyles} id='search-form' onSubmit={this.handleSubmit}>
		          <input id='search-input' style={searchInputStyles} name="search" type="text" placeholder="Search" onChange={this.saveNewInput}/>
		          <input id='search-submit' style={buttonStyles} type="submit" value="Search"/>
		        </form>
		      </div>
		      {
		      	this.state.submitted ?
		      	<ResultList results={this.props.results} /> : null
		      }
	    	</div>
	    </div>
	  )
	}
}

export default SearchPage