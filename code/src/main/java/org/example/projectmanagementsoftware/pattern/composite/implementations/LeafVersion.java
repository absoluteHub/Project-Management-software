package org.example.projectmanagementsoftware.pattern.composite.implementations;

import lombok.Getter;
import lombok.Setter;
import org.example.projectmanagementsoftware.domain.Version;
import org.example.projectmanagementsoftware.pattern.composite.interfaces.VersionComponent;

@Setter
@Getter
public class LeafVersion implements VersionComponent {

    private final Version version;

    public LeafVersion(Version version) {
        this.version = version;
    }

    @Override
    public String getName() {
        return version.getVersionNumber();
    }

}

