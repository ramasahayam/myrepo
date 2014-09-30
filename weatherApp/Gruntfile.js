// Gruntfile.js

var path = require('path');
// our wrapper function (required by grunt and its plugins)
// all configuration goes inside this function
module.exports = function (grunt) {
    "use strict";
    // ===========================================================================
    // CONFIGURE GRUNT ===========================================================
    // ===========================================================================
    grunt.initConfig({
        // get the configuration info from package.json ----------------------------
        // this way we can use things like name and version (pkg.name)
        pkg: grunt.file.readJSON('package.json'),

        // when this task is run, lint the Gruntfile and all js files in src
        build: ['Grunfile.js', 'src/js/*.js'],
        jshint: {
            all: [
                "Gruntfile.js",
                "lib/**/*.js",
                "src/js/*.js",
                "test/*.js"

            ],
            options: {
                reporter: require('jshint-stylish') // use jshint-stylish to make our errors look and read good
            }


        },
        watch: {
            src: {
                files: ['src/js/*.js'],
                tasks: ['default']
            },
            test: {
                files: ['test/*.js'],
                tasks: [ 'test']
            }
        },
         mochacli: {
             src: "src/js/*.js",

             options: {

            specs: "test/*.js"

        },
        all: ["test/*.js"]
    },
        express: {
             defaults: {
                options: {
                    port:8080,
                    server: path.resolve('./server'),
                    serverreload: true
                }
            }
        }

    });

    // ==================== =======================================================
    // LOAD GRUNT PLUGINS ========================================================
    // ===========================================================================
    // we can only load these if they are in our package.json
    // make sure you have run npm install so our app can find these
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks("grunt-mocha-cli");

    //grunt express
    grunt.loadNpmTasks('grunt-express');



    grunt.registerTask('default', ['jshint']);

    grunt.registerTask('test', ['jshint','mochacli']) ;

    grunt.registerTask('startServer', ['express']);






};
