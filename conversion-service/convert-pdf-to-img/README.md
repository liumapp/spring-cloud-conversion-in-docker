# convert-pdf-to-img
Using ICEpdf to convert pdf files to picture , page by page . 

## todo list 

* add bus 

* get order from bus , and convert pdf to img .

## start rabbitMQ in docker 

* first of all , we need to set up rabbitMQ's image in docker , just simple run 

        docker pull rabbitmq:3-management
        
    or you can simple use ./build-image.sh
    
    plz make sure you have docker in your machine . 
    
* secondly , we need to make a container for rabbitMQ in docker , i've make this done in docker-compose.yml

      rabbitmq:
        image: rabbitmq:3-management
        restart: always
        container_name: rabbitmq
        hostname: rabbitmq
        environment:
          RABBITMQ_DEFAULT_USER: "springcloud"
          RABBITMQ_DEFAULT_PASS: "123456"
        ports:
          - "5672:5672"
          - "15672:15672"

    you can update the values of environment.
    
    plz pay attention , this is the simplest configuration. 
    
    I do not recommend using it in a production environment.
    
* when you build your images , simply run 

        docker-compose up -d 
    
    then you can go to your browser , and visit http://localhost:15672 to open rabbitMQ's web panel.
    
    the username is "springcloud" , and the password is "123456".    

## run pdf to img

* simple run junit test , and you can find how it works . 

    plz config both yml files in main resouces and test resources . 
