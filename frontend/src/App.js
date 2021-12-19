import React, {Component} from 'react';
import {Route, BrowserRouter, Routes} from 'react-router-dom'
import './App.css';
import ListServices from "./components/ListServices";
import AddServiceForm from './components/AddServiceForm';

class App extends Component {
    render() {
        return (
            <BrowserRouter>
                <div className={"App-header"}>
                    <Routes>
                        <Route path="/" element={<ListServices />} />
                        <Route path="/add" element={<AddServiceForm />}/>
                    </Routes>
                </div>
            </BrowserRouter>
        );
    }
}

export default App;
