import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link, withRouter } from 'react-router-dom';
import { instanceOf } from 'prop-types';
import { Cookies, withCookies } from 'react-cookie';

class Tasks extends Component {
  static propTypes = {
    cookies: instanceOf(Cookies).isRequired
  };

  constructor(props) {
    super(props);
    this.state = {tasks: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('api/')
      .then(response => response.json())
      .then(data => this.setState({tasks: data, isLoading: false}))
      .catch(() => this.props.history.push('/'))
  }

  async remove(id) {
    await fetch(`/api/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatesTasks = [...this.state.tasks].filter(i => i.id !== id);
      this.setState({tasks: updatesTasks});
    });
  }

  render() {
    const {tasks, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const taskList = tasks.map(task => {
      return <tr key={task.name}>
        <td style={{whiteSpace: 'nowrap'}}>{task.name}</td>
        <td style={{whiteSpace: 'nowrap'}}>{task.completed === true ? 'Completed' : 'Pending'}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={'/taskedit/' + task.name}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(task.id)}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/tasknew">Add Todo</Button>
          </div>
          <h3>All Todo Tasks</h3>
          <Table className="mt-4">
            <thead>
            <tr>
              <th width="20%">Name</th>
              <th width="20%">Completed/Pending</th>
              <th width="10%">Actions</th>
            </tr>
            </thead>
            <tbody>
            {taskList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default withCookies(withRouter(Tasks));