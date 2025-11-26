package org.example.projectmanagementsoftware.pattern.strategy.implementations;

import org.example.projectmanagementsoftware.domain.Requirement;
import org.example.projectmanagementsoftware.domain.enums.RequirementStatus;
import org.example.projectmanagementsoftware.domain.enums.RequirementType;
import org.example.projectmanagementsoftware.pattern.strategy.intefraces.RequirementStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FunctionalRequirementStrategy implements RequirementStrategy {

    @Override
    public RequirementType getSupportedType() {
        return RequirementType.FUNCTIONAL;
    }

    @Override
    public String generateDescription(String baseDescription) {
        return "[Функціональна вимога]\n" +
                "Опис: " + baseDescription + "\n" +
                "Вимога визначає конкретну поведінку системи.";
    }

    @Override
    public List<RequirementStatus> allowedTransitions() {
        return List.of(
                RequirementStatus.DRAFT,
                RequirementStatus.APPROVED,
                RequirementStatus.IN_PROGRESS,
                RequirementStatus.DONE
        );
    }

    @Override
    public void enrichRequirement(Requirement r) {
        r.setTitle("FUNC-" + r.getTitle());
    }
}
