#Bunnings Coding Test
To Run this project please run the following script
1.  CREATE DATABASE wesfarmers2
2.  CREATE USER 'wesfarmers'@'IP address of app host' IDENTIFIED BY 'password';
3.  GRANT ALL PRIVILEGES ON wesfarmers2.* TO 'wesfarmers'@'IP address of app host';
4.  Configure these properties in application.properties under src/main/resources
5.  Run mvn spring-boot:run (as this is a spring boot app)
6.  you can check the application staus from browser if needed by hitting http://localhost:8080/health
7.  you need to configure input and output directories so application would know the paths
8.  Since this application is dependent on all six files it looks for six files in batch if the files are not 
    six it assumes that other files are being generated and will not process the files.
9.  After processing the files it deletes the files but output file is generated

    
