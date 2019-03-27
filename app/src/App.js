import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Tasks from './Tasks';
import Pending from './Pending';
import Completed from './Completed';
import TaskEdit from './TaskEdit';
import { CookiesProvider } from 'react-cookie';

class App extends Component {
  render() {
    return (
      <CookiesProvider>
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Tasks}/>
            <Route path='/pending' exact={true} component={Pending}/>
            <Route path='/completed' exact={true} component={Completed}/>
            <Route path='/taskedit/:name' component={TaskEdit}/>
            <Route path='/tasknew' component={TaskEdit}/>
          </Switch>
        </Router>
      </CookiesProvider>
    )
  }
}

export default App;