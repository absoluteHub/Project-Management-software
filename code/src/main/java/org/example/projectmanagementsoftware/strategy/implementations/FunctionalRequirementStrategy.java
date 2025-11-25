package org.example.projectmanagementsoftware.strategy.implementations;

import org.example.projectmanagementsoftware.strategy.intefraces.RequirementStrategy;

public class FunctionalRequirementStrategy implements RequirementStrategy {

    @Override
    public String generateDescription(String baseDescription) {
        return "[Функціональна вимога] " + baseDescription +
                ". Ця функція повинна бути реалізована у відповідності до бізнес-логіки.";
    }
}
