package scenario.variable;

import protocol.Variable;

import java.util.List;

public class ConstantVariable<T> implements Variable {

    private List<T> field;
    public ConstantVariable(List<T> container){
        field = container;
    }

    @Override
    public List<T> getValue() {
        return field;
    }
}
