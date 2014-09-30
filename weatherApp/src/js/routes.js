var express = require('express'),
    url = require('url'),
    util = require('util'),
    expressValidator = require('express-validator');
var weatherService = require('./weather-service');


module.exports = (function (app) {
    'use strict';

    app.use(expressValidator([]));

    //title of the app
    var title = "weatherApp";

    //response array
    var resultArray = [];


    // static input list to query weather
    var inputList = [
        {city: 'Campbell', state: 'CA' },
        {city: 'Omaha', state: 'NE'},
        {city: 'Austin', state: 'TX'},
        {city: 'Timonium', state: 'MD'}

    ];

    //holds url parts.
    var url_parts = null;
    //weather api key
    var key = "4aa584a0102e5f96";


    /**
     * Middleware to capture request parts and call printURLParams to output query parameters to the console.
     */
    app.use(function (req, res, next) {

        console.log("Middleware... printing request query parameters");
        url_parts = url.parse(req.url, true);
        printURLParams(url_parts);
        next();
    });


    app.get('/', function (req, res) {
        var apiInfo = {weatherReport: '/weather/v1/', weatherReportByStateAndCity: '/weather/v1/reportByCityAndState?state=?&city=?'};
        res.render('index', { result: apiInfo, title: title});

    });

    //default api call, which process  static inputList array of city and state and invokes weather api.
    app.get('/weather/v1/', function (req, res) {

        var counter = 0;
        resultArray = [];

        console.log('Router::::default processing weather request');

        inputList.forEach(function (requestData) {
            //invokes weather api using async call..and call back. Once all the list is complete,
            //              it will call final method which will route to results page
            asyncWeatherServiceCall(requestData, function (data, response) {
                addWeatherDataToArray(data, response);
                counter++;
                if (inputList.length == counter) {
                    final(res);
                }
            });


        });

    });

    // async call to weather app and callback once its complete.
    function asyncWeatherServiceCall(requestData, callback) {
        console.log('asyncWeatherServiceCall with request state and city::[' + requestData.state + '::' + requestData.city + ']');

        weatherService.getWeatherData(key, requestData.state, requestData.city, function (data, response) {
            callback(data, response);
        });

    }

    //final call to render results page.
    function final(res) {
        res.render('weather', { result: resultArray, title: title});
    }


    // generate report by city and state
    app.get('/weather/v1/reportByCityAndState', function (req, res) {
        resultArray = [];
        req.checkQuery('state').notEmpty();
        req.checkQuery('city').notEmpty();

        var errors = req.validationErrors();
        if (errors) {
            res.send('There have been validation errors: ' + util.inspect(errors));
            return;
        }
        weatherService.getWeatherData(key, url_parts.query.state, url_parts.query.city, function (data, response) {

            addWeatherDataToArray(data, response);
            final(res);
        });


    });

    // prints url query parameters
    var printURLParams = function (url_parts) {
        var queryParams = url_parts.query;

        //if no query params present.
        if (!Object.keys(queryParams).length) {
            console.log("No Query parameters present in request");
            return;
        }
        //if query params exist print name and value.
        for (var param in queryParams) {
            console.log("Query Param Name :::[" + param + "]::: Param Value:::[" + queryParams[param] + "]");
        }
    };

    //add weather data (response from wunderground api call) to result array.
    //takes http response  and response data and constructs json object with statusCode and response data
    var addWeatherDataToArray = function (data, response) {
        resultArray.push({statusCode: response.statusCode, data: data});

    };


    // Handle 404
    app.use(function (req, res) {

        var appPort = app.get('port');
        res.render('error', { title: title, status: '404', message: 'Page not found', port: appPort,
            defaultApi: appPort + '/weather/v1'});

    });


});

