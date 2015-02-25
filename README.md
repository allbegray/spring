# 1. Maven 빌드

clean flyway:clean flyway:migrate generate-sources

# 2. Javascript Component 빌드

#2.1. nodejs 설치
http://nodejs.org/

#2.2. bpm 으로 bower, grunt 패키지 설치
npm install -g bower  
npm install -g bower-installer  
npm install -g grunt-cli  

#2.3. 의존성 가져오기
프로젝트 루트로 이동한 다음  
bower-installer