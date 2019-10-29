# docker-helper

first steps with graal/substrateVM and [native image](https://medium.com/palantir/first-steps-with-graal-and-substrate-vm-193f8a8bf60e)

this app aims to ease the day to day development with docker/docker-compose,  
as i have found myself tired of scrolling throught a large docker-compose.yml file all the time.  
this will parse the docker-compose.yml file and ouputs specific information.  

  available options: 
 ```
    list : lists the available containers
    ports : shows the ports for a container
    image : shows the image for a container
    env_file : shows the env_files for a container
    replace_tag : replaces the image tag of a container
```

## Usage

so for example lets say we want to find the ports wich our super awesome webapp is mapped in the docker-compose file, so we can access it in the browser.

### the long way 
 ```
java -jar docker-helper.jar -f docker-compose.yml -o ports -c some-awesome-web-app
 ```
### getting better
to eliminate the need for the java -jar part and only as a cherry on top this was compiled with substrateVM.
this makes these commands run really fast and imo is really cool in general.  
after this is done this is an executable:
 ```
docker-helper -f docker-compose.yml -o ports -c some-awesome-web-app
 ```

### but can we do more
even with a native command this is still quite wordy. 
lets make this a bash function!

```
#!/bin/bash
function ports(){
  docker-helper -f /home/user/docker-compose.yml -o ports -c $1
}
```

done : 

```
#> ports some-awesome-web-app
mapped ports: 
8080->80
```
