package mta.maude.module;

import mta.maude.module.section.*;
import mta.user.scenario.ScenarioSpec;
import mta.user.scenario.ScenarioSpecLoader;

import java.nio.file.Path;

public class ScenarioModuleSmokeTest {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java mta.maude.module.ScenarioModuleSmokeTest <scenario-spec-path>");
            System.exit(1);
        }

        Path scenarioSpecPath = Path.of(args[0]).toAbsolutePath().normalize();
        ScenarioSpec scenarioSpec = new ScenarioSpecLoader().loadScenarioSpec(scenarioSpecPath);

        String renderedModule = ModuleTemplate.base()
                .bind("profiles", new ProfilesSectionRenderer().render(scenarioSpec))
                .bind("nodes", new NodesSectionRenderer().render(scenarioSpec))
                .bind("initialStates", new InitialStateRender().render(scenarioSpec))
                .bind("constants", new ConstantsSectionRenderer().render(scenarioSpec))
                .bind("statePropositions", new StatePropositionsSectionRenderer().render(scenarioSpec))
                .bind("actionPropositions", new ActionPropositionsSectionRenderer().render(scenarioSpec))
                .bind("parameterizedBehaviorInstances", new ParameterizedBehaviorInstanceSectionRenderer().render(scenarioSpec))
                .bind("concreteBehaviorInstances", new ConcreteBehaviorInstanceSectionRenderer().render(scenarioSpec))
                .bind("scenarioProperties", new ScenarioPropertiesSectionRenderer().render(scenarioSpec))
                .render();

        System.out.println("=== Scenario Spec ===");
        System.out.println(scenarioSpecPath);
        System.out.println();
        System.out.println("=== Generated Module ===");
        System.out.println(renderedModule);
    }
}
