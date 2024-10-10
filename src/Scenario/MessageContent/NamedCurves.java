package Scenario.MessageContent;

import java.util.ArrayList;
import java.util.List;

public class NamedCurves implements MessageContent {
    private List<NamedCurve> namedCurves;

    public NamedCurves() {
        namedCurves = new ArrayList<NamedCurve>();
    }
    public void addNamedCurve(NamedCurve namedCurve) {
        namedCurves.add(namedCurve);
    }
}
