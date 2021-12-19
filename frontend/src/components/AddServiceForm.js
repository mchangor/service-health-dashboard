import React, {Component} from 'react';
import {Button, TextField} from '@material-ui/core';
import {ServiceDetails} from '../constants/MappingConstants';
import './AddServiceForm.css';
import axios from 'axios';


const initialState = {
    ServiceDetails: ServiceDetails,
    name: '',
    url: '',
    message: ''
};


class AddServiceForm extends Component {

    constructor(props) {
        super(props);
        this.state = initialState;
    }

    handleChange = prop => event => {
        this.setState({[prop]: event.target.value});
    };

    buildServiceDetailsObjectAndSubmit = async(event) => {
        const serviceDetails = this.state.ServiceDetails;
        serviceDetails.name = this.state.serviceName;
        serviceDetails.url = this.state.url;
        event.preventDefault();

        try {
            await axios({
                method: "post",
                url: "http://localhost:8080/add",
                params: serviceDetails,
                headers: {
                    'Access-Control-Allow-Origin': '*',
                    'Content-Type': 'application/json',
                },
            }).then(response => {
                console.log("Add service response: ", response);
                console.log("Add service response data: ", response.data);
                this.setState({message: response.data});
            });
        } catch (error) {
            console.log(error);
            this.setState({message: "Error in saving the service."});
        }
    }

    resetForm = event => {
        event.currentTarget.form.reset();
        this.setState(initialState);
    };

    render() {
        const message = this.state.message;
        return (
            <div className="wrapper">
                <form onSubmit={this.buildServiceDetailsObjectAndSubmit.bind(this)}>
                    <div className="form-wrapper">
                        <TextField label="Service Name" onChange={this.handleChange('serviceName')} className={"field"}
                                   required={true}/>
                        <TextField label="URL" onChange={this.handleChange('url')} className={"field"}
                                   helperText={"URL to get service health"} required={true}/>
                        { message && <div className='message'>{message}</div> }
                        <div className={"buttonContainer"}>
                            <Button size={'medium'} type="submit" value="Submit" className={"button"} variant="contained" color="primary">
                                Save
                            </Button>
                            <Button size={'medium'} className={"button"} variant="contained" color="primary" onClick={this.resetForm.bind(this)}>
                                Reset
                            </Button>
                        </div>
                    </div>
                </form>
            </div>
        );
    }
}

export default AddServiceForm;
