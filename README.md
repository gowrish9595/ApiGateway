# Api Gateway

The application receives the request and perform below actions.

1. Request Logging To Database
2. Authentication Based on JWT token
3. Request Routing

Pre Request

1. JAVA 11
2. maven 3.6 and above

Build Application
mvn clean install

Configurations.

1. enable ssl
   server:
     enabled: true
2. Configure List of background services in route_config.yml
   as service id is to url map
   <pre>
   routing:
     services:
         service1: http://service1:8081/
         service2: http://service2:8082/
         loginService: http://login:8083/
  
   </pre>
3. configure pattern and service to forward
   <pre>
    routing:  
        rules:
         - pattern: ".*/test1.*"
           serviceToForward: service1
   </pre>

4. public key to validate jwt token
   <pre>
   security:
       key:
        path:
          public: /home/gowrishankar/.ssh/publickey.crt
   </pre>
  
