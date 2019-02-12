
package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import operation.Base;
import picocli.CommandLine;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

@CommandLine.Command(description = "helper application to ease the development with docker",
        name = "docker-helper",separator = " ")
public class DockerHelp implements Callable<Void>{

    @CommandLine.Option(names = {"-f", "--filepath"},required = false, description = "the path to the docker-compose.yml file. defaults to current directory")
    private String filepath = "./docker-compose.yml";

    @CommandLine.Option(names = {"-o", "--operation"},required = true, description = "the operation which should be applied")
    private String operation = "";

    @CommandLine.Option(names = {"-c", "--container"},required = false, description = "the container name")
    private String container = null;

    private static String[] args;

    public static void main(String[] args) {
        args = args;
       Callable<Void> callable = parseCommands(args);
       startOperation(callable);

    }

    private static Callable<Void> parseCommands(String... args){
        Callable<Void> callable = new DockerHelp();
        CommandLine cmd = new CommandLine(callable);

        try {
            cmd.parse(args);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            cmd.usage(System.out);
            printOptions();
            System.exit(0);
        }
        return callable;
    }

    private static void startOperation(Callable<Void> callable){
        try {
            callable.call();
        } catch (Exception e) {
            System.out.println("could not start ");
            e.printStackTrace();
        }
    }


    private static void printOptions(){
        System.out.println("\n  available options: ");
        for(Operation o : Operation.values()){
            System.out.println("    "+o);
        }
    }

    @Override
    public Void call() throws Exception {
        Operation operation = validateOperation(this.operation);
        File file = validateFile(this.filepath);
        operation.attachContainer(this.container);
        operation.doOperation(file,args);

        return null;
    }

    private Operation validateOperation(String operationWithArgs){
        try{
            String[] args = filterArgs(operationWithArgs);
            String operation = args[0];
            Operation op = Operation.valueOf(operation.trim().toUpperCase());
            op.setArguments(Arrays.copyOfRange(args,1,args.length));
            return op;
        }catch (RuntimeException e){
           e.printStackTrace();
           System.exit(1);
        }
        return Operation.LIST;
    }
    private String[] filterArgs(String input){
        return input.split(":");
    }
    private File validateFile(String filepath){
        try{
            File file = new File(filepath);
            if(!file.exists()){
                throw new RuntimeException("te file does not exist");
            }
            return file;
        }catch (RuntimeException e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
