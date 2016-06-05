module.exports = function(grunt){
    grunt.initConfig({
        pkg:grunt.file.readJSON('package.json'),

        watch:{
            options:{livereload:true},
            files:['**/*'],
            //files:['app/**','server/**'],
            tasks:[]

        },
        express:{
            all:{
                options:{
                    server:'server.js',
                    hostname:'localhost',
                    port:3000,
                    bases:['./oauth-server-view'],
                    livereload:true

                }
            }
        }
//        open: {
//            all: {
//                path: 'http://localhost:3000/public/app/index.html'
//            }
//        }

    });

    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-express');
    grunt.registerTask('server',['express','watch']);

};
