package mta.user.behavior.modification;

import mta.user.behavior.modification.item.BehaviorModificationItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BehaviorModification {
    private List<BehaviorModificationItem> behaviorModificationItems;

    public BehaviorModification() {
        behaviorModificationItems = new ArrayList<BehaviorModificationItem>();
    }

    public BehaviorModification(List<BehaviorModificationItem> behaviorModificationItems) {
        this.behaviorModificationItems = behaviorModificationItems;
    }

    public void add(BehaviorModificationItem behaviorModificationItem) {
        behaviorModificationItems.add(behaviorModificationItem);
    }

    public void delete(BehaviorModificationItem behaviorModificationItem) {
        behaviorModificationItems.remove(behaviorModificationItem);
    }

    public void setBehaviorModificationItems(List<BehaviorModificationItem> behaviorModificationItems) {
        this.behaviorModificationItems = behaviorModificationItems != null
                ? new ArrayList<BehaviorModificationItem>(behaviorModificationItems)
                : new ArrayList<BehaviorModificationItem>();
    }

    public List<BehaviorModificationItem> getBehaviorModificationItems() {
        return Collections.unmodifiableList(behaviorModificationItems);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BehaviorModification that)) {
            return false;
        }
        return Objects.equals(behaviorModificationItems, that.behaviorModificationItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(behaviorModificationItems);
    }

    @Override
    public String toString() {
        return "BehaviorModification{"
                + "behaviorModificationItems=" + behaviorModificationItems
                + '}';
    }

}
