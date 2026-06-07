package mta.user.behavior;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BehaviorDeviationSpecification {

    private final List<BehaviorSpec> behaviorSpecs = new ArrayList<BehaviorSpec>();

    public List<BehaviorSpec> getBehaviorSpecs() {
        return Collections.unmodifiableList(behaviorSpecs);
    }

    public void addBehaviorSpec(BehaviorSpec behaviorSpec) {
        if (behaviorSpec != null) {
            behaviorSpecs.add(behaviorSpec);
        }
    }

    public void setBehaviorSpecs(List<BehaviorSpec> behaviorSpecs) {
        this.behaviorSpecs.clear();
        if (behaviorSpecs != null) {
            this.behaviorSpecs.addAll(behaviorSpecs);
        }
    }
}
