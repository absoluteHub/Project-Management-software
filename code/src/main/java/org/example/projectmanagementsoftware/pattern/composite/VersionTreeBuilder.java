package org.example.projectmanagementsoftware.pattern.composite;

import org.example.projectmanagementsoftware.domain.Version;
import org.example.projectmanagementsoftware.pattern.composite.implementations.CompositeVersion;
import org.example.projectmanagementsoftware.pattern.composite.implementations.LeafVersion;
import org.example.projectmanagementsoftware.pattern.composite.interfaces.VersionComponent;

public class VersionTreeBuilder {

    public VersionComponent buildTree(Version version) {

        if (version.getChildren() == null || version.getChildren().isEmpty()) {
            return new LeafVersion (version);
        }

        CompositeVersion composite = new CompositeVersion(version);

        for (Version child : version.getChildren()) {
            composite.add(buildTree(child));
        }

        return composite;
    }
}

