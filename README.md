# Health dashboard for the services

## Some Details
1. I have assumed that only last known status needs to be stored.
2. The services' health status is polled every 30 seconds.
3. Frontend also fetches the data from backend every 30 seconds.
## Setup
### Requirements
Node v16 - for frontend  
Maven - for backend  
Docker - for database  
### Database
Run ``docker-compose up -d``  
This will spin up a container running MySql on port 3309.  
Database: dev  
Username: dev  
Password: secret

### Backend
In ``/backend`` folder:  
Run ``mvn spring-boot:run``  
The server will start on port 8080.

Alternatively, the application can be packaged in the form of a ``jar`` and run it using following commands:  
``mvn clean package``  
``java -jar target/health-0.0.1-SNAPSHOT.jar``

### Frontend
In ``/frontend`` folder:  
#### Dev mode
Run ``npm install`` and then ``npm start``  
This will run the app in development mode. The app can be viewed by opening http://localhost:3000 in a browser.
Caution: The page will reload if any edit is made to the code. 

#### Prod mode
Run ``npm run build``  
Builds the app for production to the ``build`` folder.
It can then be served with a static server:  
  ``npm install -g serve``  
  ``serve -s build`` 
