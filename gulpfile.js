var BOWER_COMPONENTS_PATH = 'bower_components';
var DIST_PATH = 'webapp/lib';

var gulp = require('gulp'),
   uglify = require('gulp-uglify'),
   del = require('del');

gulp.task('clean', function(cb) {
    del([DIST_PATH], cb);
});

gulp.task('bower_components_minify_copy', ['clean'], function () {
	
	var mappings = {
		
		'/parsleyjs/dist/*.min.*' : '/parsleyjs/',
		'/parsleyjs/src/i18n/ko.js' : '/parsleyjs/i18n/',
			
		'/bootstrap/dist/css/*.min.*' : '/bootstrap/css/',
		'/bootstrap/dist/fonts/*.*' : '/bootstrap/fonts/',
		'/bootstrap/dist/js/*.min.js' : '/bootstrap/js/',
			
		'/font-awesome/fonts/*.*' : '/font-awesome/fonts/',
		'/font-awesome/css/font-awesome.min.css' : '/font-awesome/css/',
		
		'/jquery/dist/jquery.min.js' : '/jquery/',
		
		'/jqgrid/js/minified/jquery.jqGrid.min.js' : '/jqgrid/',
		'/jqgrid/js/i18n/grid.locale-en.js' : '/jqgrid/js/i18n/',
		'/jqgrid/js/i18n/grid.locale-kr.js' : '/jqgrid/js/i18n/',
		'/jqgrid/css/ui.jqgrid.css' : '/jqgrid/css',
		
		'/jquery-ui/jquery-ui.min.js' : '/jquery-ui/',
		'/jquery-ui/themes/redmond/**/*' : '/jquery-ui/themes/redmond/',
		
		'/moment/min/moment-with-locales.min.js' : '/moment/',
		
		'/jgrowl/jquery.jgrowl.min.js' : '/jgrowl/',
		'/jgrowl/jquery.jgrowl.min.css' : '/jgrowl/',
		
		'/handlebars/handlebars.min.js' : '/handlebars/',
		
		'/angular/angular.min.js' : '/angular/',
		
		'/underscore/underscore-min.js' : '/underscore/'
	};
	
	for (var k in mappings) {
		gulp.src(BOWER_COMPONENTS_PATH + k).pipe(gulp.dest(DIST_PATH + mappings[k]));
		console.log('copy ' + (BOWER_COMPONENTS_PATH + k) + ' -> ' + (DIST_PATH + mappings[k]));
	}
	
});

gulp.task('default', ['bower_components_minify_copy']);