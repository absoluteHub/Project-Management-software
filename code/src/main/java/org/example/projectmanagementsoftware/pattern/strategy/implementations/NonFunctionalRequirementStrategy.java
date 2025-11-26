package org.example.projectmanagementsoftware.pattern.strategy.implementations;

import org.example.projectmanagementsoftware.domain.Requirement;
import org.example.projectmanagementsoftware.domain.enums.RequirementStatus;
import org.example.projectmanagementsoftware.domain.enums.RequirementType;
import org.example.projectmanagementsoftware.pattern.strategy.intefraces.RequirementStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NonFunctionalRequirementStrategy implements RequirementStrategy {

    @Override
    public RequirementType getSupportedType() {
        return RequirementType.NON_FUNCTIONAL;
    }

    @Override
    public String generateDescription(String baseDescription) {
        return "[Нефункціональна вимога]\n" +
                "Критерії:\n" +
                "- Продуктивність: <вкажіть>\n" +
                "- Безпека: <вкажіть>\n" +
                "- Якість: <вкажіть>\n\n" +
                baseDescription;
    }

    @Override
    public List<RequirementStatus> allowedTransitions() {
        return List.of(
                RequirementStatus.DRAFT,
                RequirementStatus.APPROVED,
                RequirementStatus.TESTING,
                RequirementStatus.DONE
        );
    }

    @Override
    public void enrichRequirement(Requirement r) {
        r.setTitle("NFR-" + r.getTitle());
    }
}
