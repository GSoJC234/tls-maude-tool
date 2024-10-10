package Scenario.MessageContent;

import java.util.ArrayList;
import java.util.List;

public class CompressionMethods implements MessageContent {
    private List<CompressionMethod> methods;
    public CompressionMethods(){
        methods = new ArrayList<CompressionMethod>();
    }
    public void addCompressionMethod(CompressionMethod method){
        methods.add(method);
    }
}
