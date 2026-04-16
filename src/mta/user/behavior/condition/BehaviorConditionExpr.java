package mta.user.behavior.condition;

import java.util.Objects;

public class BehaviorConditionExpr {
    private BehaviorConditionExpr leftExpression;
    private BehaviorConditionExpr rightExpression;
    private BehaviorConditionOpr operator;

    public void setLeftExpression(BehaviorConditionExpr leftExpression) {
        this.leftExpression = leftExpression;
    }
    public void setRightExpression(BehaviorConditionExpr rightExpression) {
        this.rightExpression = rightExpression;
    }
    public void setOperator(BehaviorConditionOpr operator) {
        this.operator = operator;
    }

    public BehaviorConditionExpr getLeftExpression() {
        return leftExpression;
    }
    public BehaviorConditionExpr getRightExpression() {
        return rightExpression;
    }
    public BehaviorConditionOpr getOperator() {
        return operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BehaviorConditionExpr that)) {
            return false;
        }
        return Objects.equals(leftExpression, that.leftExpression)
                && Objects.equals(rightExpression, that.rightExpression)
                && operator == that.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftExpression, rightExpression, operator);
    }

    @Override
    public String toString() {
        return "BehaviorConditionExpr{"
                + "leftExpression=" + leftExpression
                + ", rightExpression=" + rightExpression
                + ", operator=" + operator
                + '}';
    }
}
