# 1. Maven 빌드

clean flyway:clean flyway:migrate generate-sources

# 2. Bower 빌드

*빌드 된거 webapp/lib 에 업로드 다 해두었으니 필수는 아님*

##2.1. nodejs 설치
http://nodejs.org/

##2.2. npm 으로 bower-installer 패키지 설치
npm install -g bower  
npm install -g bower-installer  

##2.3. 의존성 가져오기
프로젝트 루트로 이동한 다음  
bower-installer