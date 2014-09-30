var request = require('supertest'),
    app = require('../server');

describe('Weather API', function() {
    describe('GET /', function() {
        it('should return 200 response', function(result) {
            request(app)
                .get('/')
                .expect(200,result);

        });
    });

});
