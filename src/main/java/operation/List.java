package operation;

import test.Operation;
import test.Service;

import java.io.File;

public class List extends Base {
    public List(){
        super();
    }
    public List(File f, Operation operation) {
        super(f, operation);
    }

    @Override
    public void print(){
        for(String s : compose.services.keySet()){
         System.out.println(s+"");
        }
    }
}
