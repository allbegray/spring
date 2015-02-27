var DIST_PATH = 'webapp/lib';

var gulp = require('gulp'),
   uglify = require('gulp-uglify'),
   del = require('del');

gulp.task('clean', function(cb) {
    del([DIST_PATH], cb);
});

gulp.task('bower_components_minify_copy', ['clean'], function () {
	
	gulp.src('bower_components/font-awesome/fonts/*.*').pipe(gulp.dest(DIST_PATH + '/font-awesome/fonts/'));
	gulp.src('bower_components/font-awesome/css/font-awesome.min.css').pipe(gulp.dest(DIST_PATH + '/font-awesome/css/'));
	
	gulp.src('bower_components/jquery/dist/jquery.min.js').pipe(gulp.dest(DIST_PATH + '/jquery/'));
	
	gulp.src('bower_components/jqgrid/js/minified/jquery.jqGrid.min.js').pipe(gulp.dest(DIST_PATH + '/jqgrid/'));
	gulp.src('bower_components/jqgrid/js/i18n/grid.locale-en.js').pipe(gulp.dest(DIST_PATH + '/jqgrid/js/i18n/'));
	gulp.src('bower_components/jqgrid/js/i18n/grid.locale-kr.js').pipe(gulp.dest(DIST_PATH + '/jqgrid/js/i18n/'));
	gulp.src('bower_components/jqgrid/css/ui.jqgrid.css').pipe(gulp.dest(DIST_PATH + '/jqgrid/css/'));
	
	gulp.src('bower_components/jquery-ui/jquery-ui.min.js').pipe(gulp.dest(DIST_PATH + '/jquery-ui/'));
	gulp.src('bower_components/jquery-ui/themes/redmond/**/*').pipe(gulp.dest(DIST_PATH + '/jquery-ui/themes/redmond/'));
	
	gulp.src('bower_components/moment/min/moment-with-locales.min.js').pipe(gulp.dest(DIST_PATH + '/moment/'));
	
	gulp.src('bower_components/jgrowl/jquery.jgrowl.min.js').pipe(gulp.dest(DIST_PATH + '/jgrowl/'));
	gulp.src('bower_components/jgrowl/jquery.jgrowl.min.css').pipe(gulp.dest(DIST_PATH + '/jgrowl/'));
	
	gulp.src('bower_components/handlebars/handlebars.min.js').pipe(gulp.dest(DIST_PATH + '/handlebars/'));
	
	gulp.src('bower_components/angular/angular.min.js').pipe(gulp.dest(DIST_PATH + '/angular/'));
	
});

gulp.task('default', ['bower_components_minify_copy']);