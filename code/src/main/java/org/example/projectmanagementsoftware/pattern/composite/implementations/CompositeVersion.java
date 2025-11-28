package org.example.projectmanagementsoftware.pattern.composite.implementations;

import lombok.Getter;
import org.example.projectmanagementsoftware.domain.Version;
import org.example.projectmanagementsoftware.pattern.composite.interfaces.VersionComponent;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CompositeVersion implements VersionComponent {

    private final Version version;
    private final List<VersionComponent> children = new ArrayList<> ();

    public CompositeVersion(Version version) {
        this.version = version;
    }

    public void add(VersionComponent component) {
        children.add(component);
    }

    @Override
    public String getName() {
        return version.getVersionNumber();
    }

    @Override
    public List<VersionComponent> getChildren() {
        return children;
    }
}

