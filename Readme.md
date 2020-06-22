Author: Andrew Rohrer
Date: 6/14/2020

This is an app that pulls data from the US census API and displays it for a user.

The tech stack built for this app is comprised of two parts:
1. Java spring boot api backend makes a call to the US Census API to get the data. It processes 
the response in a way that will allow for the client to view it in the requested format. The 
data is organized alphabetically by region by the api before it is returned to the client.

2. The front end is a Vue.js app that makes an ajax call to the Java backend api to get the data. 
The front end is responsible for displaying the data in a list that is sorted alphabetically 
(data is sorted alphabetically from the java api). The front end allows for searching the list 
based on the name of the region or the year that the population estimate was made.

Both the java API and the Vue.js front end can be run as containers. If you wish to try the app
locally follow these instructions:

1. Make sure that you have docker installed on your machine.
2. Verify that docker is running Linux containers.
3. Clone this application to an empty directory.
4. Once cloned use a shell to get to the population-api folder.
5. Run the following command: "docker-compose up"
6. Once the command is done (it takes a few minutes) navigate to http://localhost:9999 to see
the application up and running.

How this works:
There are Dockerfiles for each the java service and the vue.js app. They detail how to build
the containers to run each app. Docker automatically downloads containers to run the builds
for each app inside a container. It takes the results of the builds from those containers and copies
the built artifacts into production web servers. The java app is set up to run on port 8080 of 
the host and the vue.js app is looking for that endpoint for the data.
