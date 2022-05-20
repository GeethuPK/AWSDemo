FROM openjdk:11
EXPOSE 8080
ADD target/spring-boot-demo-jar.jar spring-boot-demo-jar.jar 
ENTRYPOINT ["java","-jar","/spring-boot-demo-jar.jar"]
