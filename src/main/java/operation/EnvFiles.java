package operation;

import test.Operation;
import test.Service;

import java.io.File;

public class EnvFiles extends Base {
    public EnvFiles(){
        super();
    }
    public EnvFiles(File f, Operation operation) {
        super(f, operation);
    }

    @Override
    public void print(){
        Service service = serviceForContainer(operation.getContainer());
        System.out.println("env-files : ");
        service.env_file.stream().map(e->"  "+e).forEach(System.out::println);

    }
}
