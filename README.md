# ElectionDataQuality
Spring Boot project dedicated to visualizing and modifying precinct election data.

# Preprocessing
Demographic and election data was preprocessed at a precint level.
Shape files for state, district, and precincts were converted into geojson files for leaflet, an open-source JavaScript library for interactive maps.
//for errors at some point

All data was formatted into a standard model for easier insertion into a relational database.
An explanation on how data was preprocessed along with all source files can be found in Sources.docx.
All associated python scripts created for preprocessing can be found in {STATE}\_PREPROCESSING folder.

#Relational Database
Database was created based on the standard model mentioned above.
/schema/schema_file.sql can be used to import the schema into your local database. 

#Java server
/src/main/java/server/ directory contains all java files used to run the server.or the management for persistence and object/relational mapping.
Java entity classes represent each table found in the relational database. javax.persistence api is used for managing persistence and object/relational mapping.
Java Repo interfaces use Spring Data JPA. Extending JpaRepository allows us to configure queries beyond basic CRUD queries to the spring.datasource.url define in application.properties.  
Java Controller classes are request handlers that map client request to a specific URI to their respective methods in the server.

#Client
/src/main/webapp/index.html is the client.
JavaScript functions make HTTP requests to the Java Controller classes mentioned above.
JavaScript functions handle displaying the data retrieved from the Java server.
JavaScript functions handle sending update requests whenever the client makes changes to any malleable data. Allows for persistance.
Script points to Leaflet, an external script file, through the src attribute. Leaflet is used to display a global map. One of the main data points retrieved with the previously mentioned JavaScript function is geojson of each state/district/precinct.
Geojson information is injected into leaflet which allows the display of state/district/precinct shapes onto the leaflet map.

#Configuration
/src/main/resources/application.properties must be changed with the link to your database along with credentials.
Database must be populated with data, the sql file mentioned earlier does not contain all data. (That sql dmp file is too big to upload to git)

#Running application
Run as Java Projects with any IDE. Terminal should show all debug information relating to the project.
Connect via browser to the link localhost:8080/ and the client side web application should appear. 
