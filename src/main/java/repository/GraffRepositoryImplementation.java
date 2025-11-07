package repository;

import raf.graffito.dsw.core.GraffRepository;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_implementation.Workspace;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraffRepositoryImplementation implements GraffRepository {
    private Workspace workspace; //root

    public GraffRepositoryImplementation() {
        workspace = new Workspace("Workspace", "",null);
    }
    @Override
    public Workspace getWorkspace() {
        return workspace;
    }

    @Override
    public void addChild(GraffNodeComposite parent, GraffNode child) {
        if (parent.addChild(child)) child.setParent(parent);
    }

}
