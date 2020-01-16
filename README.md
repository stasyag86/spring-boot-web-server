# spring-boot-web-server
simple spring boot application to store car charging stations

I have used ConcurrentHashMap to store the stations, which is thread safe, so input new charging station or update (stop charging station) will be executed within O(1).
While each time we do insert or update it increments a synchronized counter in order to retrieve the summary within O(1) too.

The API works like this:    
POST 	localhost:8080/evbox/chargingSessions	(with response body)          
PUT 	localhost:8080/evbox/chargingSessions/{id}               
GET 	localhost:8080/evbox/chargingSessions                 
GET 	localhost:8080/evbox/chargingSessions/summary          

In order to run the application, run EvboxWebServiceApplication.java as Java Application in your IDE
or run those commands inside your working directory: 
  mvn install  
mvn spring-boot:run

