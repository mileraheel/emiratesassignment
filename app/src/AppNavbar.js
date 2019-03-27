import React, { Component } from 'react';
import { Button, Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink } from 'reactstrap';
import { Link } from 'react-router-dom';

export default class AppNavbar extends Component {
  constructor(props) {
    super(props);
    this.state = {isOpen: false};
    this.toggle = this.toggle.bind(this);
  }

  toggle() {
    this.setState({
      isOpen: !this.state.isOpen
    });
  }

  render() {
    return <Navbar>
    
      <h2>Emirates Group Test</h2>
      
        <Button color="#758e75" tag={Link} to="/">All Todo</Button>
        <Button color="#758e75" tag={Link} to="/pending">All Pending Todo</Button>
        <Button color="#758e75" tag={Link} to="/completed">All Completed Todo</Button>
      
    </Navbar>;
  }
}