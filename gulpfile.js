var gulp = require('gulp'),
   uglify = require('gulp-uglify');

gulp.task('minify', function () {
   gulp.src('webapp/js/jquery-common.js')
      .pipe(uglify())
      .pipe(gulp.dest('build'))
});