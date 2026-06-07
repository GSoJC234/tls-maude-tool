package mta.user.scenario;

import mta.user.common.ScenarioExpression;

import java.util.Objects;

public class ScenarioSpec {

    private ScenarioExpression currentScenarioProperty;

    public ScenarioExpression getCurrentScenarioProperty() {
        return currentScenarioProperty;
    }

    public void setCurrentScenarioProperty(ScenarioExpression currentScenarioProperty) {
        this.currentScenarioProperty = currentScenarioProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScenarioSpec that)) {
            return false;
        }
        return Objects.equals(currentScenarioProperty, that.currentScenarioProperty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentScenarioProperty);
    }

    @Override
    public String toString() {
        return "ScenarioSpec{"
                + "currentScenarioProperty=" + currentScenarioProperty
                + '}';
    }
}
