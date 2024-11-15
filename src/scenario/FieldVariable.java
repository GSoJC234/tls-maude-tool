package scenario;

import Protocol.Variable;

import java.util.List;

public class FieldVariable<T> implements Variable {

    private List<T> field;
    public FieldVariable(List<T> container){
        field = container;
    }

    @Override
    public List<T> getValue() {
        return field;
    }
}
