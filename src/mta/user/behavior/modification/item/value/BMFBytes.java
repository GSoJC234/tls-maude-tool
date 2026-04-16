package mta.user.behavior.modification.item.value;

import java.util.Objects;

public class BMFBytes implements BehaviorModificationFunctionCall {

    private String hexValue;

    public BMFBytes() {
    }

    public BMFBytes(String hexValue) {
        this.hexValue = hexValue;
    }

    public String getHexValue() {
        return hexValue;
    }

    public void setHexValue(String hexValue) {
        this.hexValue = hexValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BMFBytes bmfBytes)) {
            return false;
        }
        return Objects.equals(hexValue, bmfBytes.hexValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hexValue);
    }

    @Override
    public String toString() {
        return "BMFBytes{"
                + "hexValue='" + hexValue + '\''
                + '}';
    }
}
