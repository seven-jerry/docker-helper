# docker-helper

first steps with graal/substrateVM and [native image](https://medium.com/palantir/first-steps-with-graal-and-substrate-vm-193f8a8bf60e)

this app aims to ease the day to day development with docker/docker-compose,
as i have found myself tired of scrolling throught a large docker-compose.yml file all the time.
this will parse the docker-compose.yml file and ouputs specific information.
for example,
to find out wich port is mapped for the web-app: 
```
ports web-app
mapped ports: 
8080->80
```
  available options: 
 ```
    list : lists the available containers
    ports : shows the ports for a container
    image : shows the image for a container
    env_file : shows the env_files for a container
    replace_tag : replaces the image tag of a container
```

and as a cherry on top this was compiled with substrateVM to make these commands run really fast.
