package repository.graff_implementation;

import repository.graff_components.GraffNode;

public class GraffRepositoryFactory {

    public GraffRepositoryFactory() {
    }

    public Project createProject(String title, String author, GraffNode parent){
        return new Project(title, author, parent);
    }

    public Presentation createPresentation(String title, String author, GraffNode parent){
        return new Presentation(title, author, parent);
    }

    public Slide createSlide(String title, String author, GraffNode parent){
        return new Slide(title, author, parent);
    }
}
