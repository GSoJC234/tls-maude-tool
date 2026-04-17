package mta.maude.module;

import mta.user.scenario.ScenarioSpec;

public interface ModuleSectionRenderer {

    String placeholder();

    String render(ScenarioSpec scenarioSpec);
}
