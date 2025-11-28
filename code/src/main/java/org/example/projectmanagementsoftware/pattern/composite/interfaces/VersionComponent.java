package org.example.projectmanagementsoftware.pattern.composite.interfaces;

import java.util.List;

public interface VersionComponent {
    String getName();
    default List<VersionComponent> getChildren() {
        return List.of();
    }
}
