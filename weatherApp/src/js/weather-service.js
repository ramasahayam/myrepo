
var Client = require('node-rest-client').Client;


var getWeatherData = function (key, state, city, callback) {
    'use strict';
    var client = new Client();
    var args = {
        path: {      // path substitution var
            "key": key,
            "state": state,
            "city": city

        }
    };

  client.get("http://api.wunderground.com/api/${key}/conditions/q/${state}/${city}.json", args,
        function (data, response) {


            callback(data, response);
        }
    );


};

module.exports.getWeatherData = getWeatherData;
