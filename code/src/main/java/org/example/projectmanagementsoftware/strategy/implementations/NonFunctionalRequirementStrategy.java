package org.example.projectmanagementsoftware.strategy.implementations;

import org.example.projectmanagementsoftware.strategy.intefraces.RequirementStrategy;

public class NonFunctionalRequirementStrategy implements RequirementStrategy {

    @Override
    public String generateDescription(String baseDescription) {
        return "[Нефункціональна вимога] " + baseDescription +
                ". Має відповідати стандартам продуктивності, якості та безпеки.";
    }
}
