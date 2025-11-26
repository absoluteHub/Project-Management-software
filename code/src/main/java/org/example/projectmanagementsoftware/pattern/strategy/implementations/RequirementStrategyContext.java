package org.example.projectmanagementsoftware.pattern.strategy.implementations;

import org.example.projectmanagementsoftware.domain.enums.RequirementType;
import org.example.projectmanagementsoftware.pattern.strategy.intefraces.RequirementStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RequirementStrategyContext {

    private final Map<RequirementType, RequirementStrategy> strategies = new HashMap<>();

    public RequirementStrategyContext(List<RequirementStrategy> strategyList) {
        for (RequirementStrategy s : strategyList) {
            strategies.put(s.getSupportedType(), s);
        }
    }

    public RequirementStrategy getStrategy(RequirementType type) {
        return strategies.get(type);
    }
}
