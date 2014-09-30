    weatherService = require('../src/js/weather-service');
var nock = require("nock");
var http = require("http");
var expect = require("expect.js");

describe('weather data ', function () {
    describe('getWeatherData', function () {
        it('should return 200 response', function () {
            var key = "dummy";
            var state = "TX";
            var city = "Austin";

            var mockResponse={
                current_observation :{
                    display_location :{
                        city:'Austin',
                        state:'TX',
                        zipCode:'00000'
                    },
                    observation_location:{
                        temp_f :'82'
                    }

                }
            };

            var api = nock("http://api.wunderground.com/")
                .get("/api/dummy/conditions/q/TX/Austin.json")
                .reply(200, {data: mockResponse});
            weatherService.getWeatherData(key, state, city, function (result) {

                expect(result.status).eql(result.statusCode);
                expect('Austin').eql(result.data.current_observation.display_location.city);
                expect('82').eql(result.data.current_observation.observation_location.temp_f);


            });

            expect(true, api.isDone());
        });
    });
});
