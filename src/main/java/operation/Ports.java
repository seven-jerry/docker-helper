package operation;

import test.Operation;
import test.Service;

import java.io.File;

public class Ports extends Base {
    public Ports(){
        super();
    }
    public Ports(File f, Operation operation) {
        super(f, operation);
    }

    @Override
    public void print(){
        Service service = serviceForContainer(operation.getContainer());

        System.out.println("mapped ports: ");
        service.ports.stream().map(e->"  "+e).forEach(System.out::println);
    }
}
