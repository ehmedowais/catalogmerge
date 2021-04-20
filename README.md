
To Run this project
1.  Run mvn spring-boot:run (as this is a spring boot app)
2.  In this application we have enabled h2 console so you can browse the databae at http://localhost:8080/h2 use sa/sa as credentials 
3.  Application uses liquibase framework so it will generate all required tables itself
4.  you can check the application staus from browser if needed by hitting http://localhost:8080/health
5.  An index page is given if you need it at http://localhost:8080/index
6.  you need to configure input and output directories so application would know the paths currently we are using /tmp/catalogmerge/input,/tmp/catalogmerge/output
7.  Since this application is dependent on all six files it looks for six files in batch if the files are not 
    six it assumes that other files are being generated and will not process the files.
8.  After processing the files it deletes the files but output file is generated

    
