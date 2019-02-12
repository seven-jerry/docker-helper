package test;

import operation.*;


import java.io.File;
import java.util.Objects;
import java.util.function.Supplier;

public enum Operation {
    LIST(List::new,"lists the available containers"),
    PORTS(Ports::new,"shows the ports for a container"),
    IMAGE(Image::new,"shows the image for a container"),
    ENV_FILE(EnvFiles::new,"shows the env_files for a container"),
    REPLACE_TAG(ImageTag::new,"replaces the image tag of a container");

    private String container;
    private String description;
    private Supplier<Base> supplier;
    private String[] arguments;
     Operation(Supplier<Base> supplier,String description){
        this.description = description;
        this.supplier = supplier;
    }

    public void attachContainer(String container){
        this.container = container;
    }

    public String getContainer(){
        return Objects.requireNonNull(this.container);
    }
    @Override
    public String toString(){
        return this.name().toLowerCase() + " : " + this.description;
    }

    public void setArguments(String[] argumnets) {
        this.arguments = argumnets;
    }

    public String getArgument(int index) {
        Objects.requireNonNull(arguments);
        return arguments[index];
    }

    public void doOperation(File composeFile, String[] args){
        Base b = this.supplier.get();
        b.setOperation(this);
        b.setFile(composeFile);
        b.setArguments(args);
        b.init();
        b.print();
    }

}
