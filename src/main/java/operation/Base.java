package operation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import test.DockerCompose;
import test.Operation;
import test.Service;

import java.io.File;
import java.util.LinkedList;

public abstract class Base {
    protected File file;
    protected Operation operation;
    DockerCompose compose;
    protected String[] arguments;
    Base(){

    }
    Base(File f,Operation operation){
     this.file = f;
     this.operation = operation;
    }

    public void setFile(File f){
        this.file = f;
    }
    public void setArguments(String[] arguments){
        this.arguments = arguments;
    }
    public void setOperation(Operation operation){
        this.operation = operation;
    }

    public void init() {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            compose = mapper.readValue(file, DockerCompose.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print(){

    }

    protected Service serviceForContainer(String container){
        Service service = this.compose.services.get(container);
        if(service == null){
            System.out.println("no container found for "+container);
            System.exit(1);
        }
        return service;
    }
}
