package mta.user.scenario.state;

import mta.user.behavior.condition.BehaviorConditionExpr;

import java.util.Objects;

public class StatePropositionExpression extends StateProposition {
    private StatePropositionExpression leftExpression;
    private StatePropositionExpression rightExpression;
    private StatePropositionOperator operator;

    public void setLeftExpression(StatePropositionExpression leftExpression) {
        this.leftExpression = leftExpression;
    }
    public void setRightExpression(StatePropositionExpression rightExpression) {
        this.rightExpression = rightExpression;
    }
    public void setOperator(StatePropositionOperator operator) {
        this.operator = operator;
    }

    public StatePropositionExpression getLeftExpression() {
        return leftExpression;
    }
    public StatePropositionExpression getRightExpression() {
        return rightExpression;
    }
    public StatePropositionOperator getOperator() {
        return operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StatePropositionExpression that)) {
            return false;
        }
        return Objects.equals(leftExpression, that.leftExpression)
                && Objects.equals(rightExpression, that.rightExpression)
                && Objects.equals(operator, that.operator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftExpression, rightExpression, operator);
    }

    @Override
    public String toString() {
        return "StateProposition Expression{"
                + "leftExpression=" + leftExpression
                + ", rightExpression=" + rightExpression
                + ", operator=" + operator
                + '}';
    }
}
