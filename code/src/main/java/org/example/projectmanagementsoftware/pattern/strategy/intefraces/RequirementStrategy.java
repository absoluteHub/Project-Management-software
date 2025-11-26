package org.example.projectmanagementsoftware.pattern.strategy.intefraces;

import org.example.projectmanagementsoftware.domain.Requirement;
import org.example.projectmanagementsoftware.domain.enums.RequirementStatus;
import org.example.projectmanagementsoftware.domain.enums.RequirementType;

import java.util.List;

public interface RequirementStrategy {

    RequirementType getSupportedType();

    String generateDescription(String baseDescription);

    List<RequirementStatus> allowedTransitions();

    void enrichRequirement(Requirement requirement);
}
