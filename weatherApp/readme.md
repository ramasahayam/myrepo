# Project Details
weather-app is a simple node js application

## Project Structure:

package.json: contains node module dependencies

### Views:
	-->/views contains view-templates, weatherApp is using jade view templates.

### Java Script source:

	-->src/js: application java script files are located in this folder (router and service)

		routes.js: contains middleware entry method (default) which prints url query parameters and execures 
				calls next.
		weather-service.js: is a service which invokes wunderground api

### Java Script test:
	--> test/js: application java script test files are located in this folder
	-> using mocha framework for testing

### server.js:
	contains server web server details. port is 8080.

### Grunt task runner:
	Gruntfile.js: Grunt task runner is used to execute jshint, test tasks.
	--> available tasks:
		--> grunt : default task and runs jshint
		--> grunt test: executes tests
		--> grunt startServer: can be used to start the server.
	Note: this task is using server.js to start the server, so it may start app on a different port other than 8080



##urls:
	(ex: host: localhost, port:8080)
	1) http://host:port/ : will route to index page, which is a welcome page and list available options.
	2) http://host:port//weather/v1/ : default route, which processes existing input city and states, calls weather 
						api and output information on ui. weather.jade.
				Input:
				{city: 'Campbell', state: 'CA' },
			        {city: 'Omaha', state: 'NE'},
			        {city: 'Austin', state: 'TX'},
			        {city: 'Timonium', state: 'MD'}

    	3)http://host:port/weather/v1/reportByCityAndState
    		which takes city and state query parameters and calls weather api for corresponding city and state, 	
    		output data ui (weather.jade)


   	4) 404 scenarios: routed to error page with details how to access default report with url information.


## Run the app:
	--> npm install
	--> npm start

	http://localhost:port/weather/v1  --> generate report for Campbell,CA;Omaha,NE;Austin,TX and Timonium, MD
	http://localhost:8080/weather/v1/reportByCityAndState?state=TX&city=Austin
