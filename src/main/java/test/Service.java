package test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class Service {
    public String image;
    public List<String> ports = new ArrayList<>();
    public Map<String,String> environment = new HashMap<>();
    public List<String> volumes = new ArrayList<>();
    public List<String> env_file = new ArrayList<>();

    public Map<String,String> build = new HashMap<>();
    public List<String>  depends_on = new ArrayList<>();
    public String entrypoint;

    public List<String> links = new ArrayList<>();
    public String privileged;
    public String restart;
    public String hostname;
    public String working_dir;
    public String command;



    public void setEntrypoint(Object entries){
        if(entries instanceof List)
            this.entrypoint = ((List<String>) entries).stream().collect(Collectors.joining(" "));
        if(entries instanceof String){
            this.entrypoint =(String) entries;
        }
    }

    public void setEnvironment(Object environment){
        if(environment instanceof List){
            System.err.println("environment as a list is not supported");
        }
        if(environment instanceof Map){
            this.environment = (Map)environment;
        }
    }
}
