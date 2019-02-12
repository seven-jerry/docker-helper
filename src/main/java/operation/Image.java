package operation;

import test.Operation;
import test.Service;

import java.io.File;

public class Image extends Base {
    public Image(){
        super();
    }
    public Image(File f, Operation operation) {
        super(f, operation);
    }

    @Override
    public void print(){
        Service service = serviceForContainer(operation.getContainer());

        System.out.println("image : ");
        System.out.println("  "+service.image);
    }
}
