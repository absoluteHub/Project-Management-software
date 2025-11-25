package org.example.projectmanagementsoftware.strategy.implementations;

import org.example.projectmanagementsoftware.domain.enums.RequirementType;
import org.example.projectmanagementsoftware.strategy.intefraces.RequirementStrategy;

public class RequirementStrategyFactory {

    public static RequirementStrategy getStrategy(RequirementType type) {
        return switch (type) {
            case FUNCTIONAL -> new FunctionalRequirementStrategy();
            case NON_FUNCTIONAL -> new NonFunctionalRequirementStrategy();
        };
    }
}