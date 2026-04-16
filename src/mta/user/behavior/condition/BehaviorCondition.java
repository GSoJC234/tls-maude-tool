package mta.user.behavior.condition;

import java.util.Objects;

public class BehaviorCondition {

    private BehaviorConditionExpr expr;

    public BehaviorCondition() {
        this.expr = null;
    }

    public BehaviorCondition(BehaviorConditionExpr expr) {
        this.expr = expr;
    }

    public BehaviorConditionExpr getExpr() {
        return expr;
    }

    public void setExpression(BehaviorConditionExpr expr) {
        this.expr = expr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BehaviorCondition that)) {
            return false;
        }
        return Objects.equals(expr, that.expr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expr);
    }

    @Override
    public String toString() {
        return "BehaviorCondition{"
                + "expr=" + expr
                + '}';
    }
}
