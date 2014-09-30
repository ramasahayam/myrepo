// server.js

var express    = require('express'); 		//  express  package
var app        = express(); 				// define app using express

// -------register routes -------------
require('./src/js/routes')(app);

// Path to our public directory
var pub = __dirname;
app.use(express.static(pub));

// Optional since express defaults to CWD/views
app.set('views', __dirname+"/views");


// Set our default template engine to "jade"
app.set('view engine', 'jade');


var port = process.env.PORT || 8080; 		// set  port to 8080

app.set('port', port);



// -------START THE SERVER -------------
app.listen(port);    //also there is a grunt task to start the server.. grunt task: grunt startServer
                                              // [either this or grunt, if not grunt will start on next available port)

console.log('Server started on port::' + port);
module.exports = app;


