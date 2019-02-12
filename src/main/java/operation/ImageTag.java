package operation;

import test.Operation;
import test.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

public class ImageTag extends Base{
    private Service service;
    public ImageTag(){
        super();
    }
    public ImageTag(File f, Operation operation) {
        super(f, operation);
    }

    @Override
    public void init(){
        super.init();
        service = serviceForContainer(operation.getContainer());
    }
    @Override
    public void print(){
        try{
            ArrayList<String> fileContent = new ArrayList<String>(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));

            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).contains(service.image)) {
                    fileContent.set(i, replaceTag(fileContent.get(i)));
                    break;
                }
            }
            Files.write(file.toPath(),fileContent);
        }catch (IOException e){
            System.out.println(e);
            System.exit(1);
        }

    }
    private String replaceTag(String line){
        String newLine = "";
        int count = line.length() - line.replace(":", "").length();
        if(count < 1){
            System.out.println("could not replace tag");
            return line;
        }
        if(count == 1){
            newLine = line + ":" + operation.getArgument(0);
        }
        if(count == 2){
            int index = line.lastIndexOf(":");
            newLine = line.substring(0,index) + ":" + operation.getArgument(0);
        }

        System.out.println("replacing line from  <"+line+"> to <"+newLine+">");

        return newLine ;
    }
}
