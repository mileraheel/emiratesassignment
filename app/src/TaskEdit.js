import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { instanceOf } from 'prop-types';
import { Cookies, withCookies } from 'react-cookie';

class TaskEdit extends Component {
  static propTypes = {
    cookies: instanceOf(Cookies).isRequired
  };

  emptyItem = {
    name: '',
    completed: ''
  };

  constructor(props) {
    super(props);
    this.state = {
      item: this.emptyItem,
      request:''
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    if (this.props.match.params.name !== undefined) {
      try {
        const task = await (await fetch(`/api/${this.props.match.params.name}`)).json();
        this.setState({item: task});

        this.setState({request:'PUT'});
      } catch (error) {
        this.props.history.push('/');
      }
    } else {
      this.setState({request:'POST'});
    }
  }

  handleChange(event) {
    const target = event.target;
    let value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    if (name === "completed") {
      value = target.checked;
    }
    item[name] = value;
    this.setState({item});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;
    const {request} = this.state;
    await fetch('/api/', {
      method: request,
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(item)
    });
    this.props.history.push('/');
  }

  render() {
    const {item} = this.state;
    const title = <h2>{item.name ? 'Edit Todo' : 'Add Todo'}</h2>;

    return <div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
        <div>
          <FormGroup>

            <Label for="name">Name</Label>
            <Input type="text" name="name" id="name" value={item.name || ''}
                   onChange={this.handleChange} autoComplete="name"/>
          </FormGroup>
          <FormGroup>
            <Label for="completed">Completed</Label>
            <Input type="checkbox" name="completed" id="completed" value={item.completed}
                   onChange={this.handleChange} autoComplete="complete-level1"/>
          </FormGroup>
          </div>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withCookies(withRouter(TaskEdit));