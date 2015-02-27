var DIST_PATH = 'webapp/lib';

var gulp = require('gulp'),
   uglify = require('gulp-uglify'),
   del = require('del');

gulp.task('clean', function(cb) {
    del([DIST_PATH], cb);
});

gulp.task('bower_components_minify_copy', ['clean'], function () {
	
	var mappings = {
		'bower_components/font-awesome/fonts/*.*' : '/font-awesome/fonts/',
		'bower_components/font-awesome/css/font-awesome.min.css' : '/font-awesome/css/',
		
		'bower_components/jquery/dist/jquery.min.js' : '/jquery/',
		
		'bower_components/jqgrid/js/minified/jquery.jqGrid.min.js' : '/jqgrid/',
		'bower_components/jqgrid/js/i18n/grid.locale-en.js' : '/jqgrid/js/i18n/',
		'bower_components/jqgrid/js/i18n/grid.locale-kr.js' : '/jqgrid/js/i18n/',
		'bower_components/jqgrid/css/ui.jqgrid.css' : '/jqgrid/css',
		
		'bower_components/jquery-ui/jquery-ui.min.js' : '/jquery-ui/',
		'bower_components/jquery-ui/themes/redmond/**/*' : '/jquery-ui/themes/redmond/',
		
		'bower_components/moment/min/moment-with-locales.min.js' : '/moment/',
		
		'bower_components/jgrowl/jquery.jgrowl.min.js' : '/jgrowl/',
		'bower_components/jgrowl/jquery.jgrowl.min.css' : '/jgrowl/',
		
		'bower_components/handlebars/handlebars.min.js' : '/handlebars/',
		
		'bower_components/angular/angular.min.js' : '/angular/'
	};
	
	for (var k in mappings) {
		gulp.src(k).pipe(gulp.dest(DIST_PATH + mappings[k]));
		console.log('copy ' + k + ' -> ' + (DIST_PATH + mappings[k]));
	}
	
});

gulp.task('default', ['bower_components_minify_copy']);