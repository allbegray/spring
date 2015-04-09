# 1. Maven 개발 빌드

clean flyway:clean flyway:migrate generate-sources

# 2. 자바스크립트 관리

*빌드 된거 webapp/lib 에 업로드 다 해두었으니 필수는 아님*

##2.1. nodejs 설치
http://nodejs.org/

##2.2. npm 패키지 설치

npm install -g bower

프로젝트 패키지로 이동하여 다음 명령어 실행(글로벌로 등록해도 상관없다.)  
npm install --save-dev gulp  
npm install --save-dev del  
npm install --save-dev gulp-uglify  


##2.3. bower 자바스크립트 의존성 가져오기  
bower install

##2.4. gulp 로 minify 한 파일만 추출하여 webapp/lib 로 복사
gulp  
