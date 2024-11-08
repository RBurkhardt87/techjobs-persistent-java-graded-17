package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity {

    //TODO: removed id and name fields/getters/setters since extending AbstractEntity

    //TODO: change employer from String to Employer type. Refactor the rest of code to match
    //TODO: add @ManyToOne annotation above the employer field.

    //if this is the non-owning entity, do I need to add mappedBy="jobs" ? Still not sure I understand
    @ManyToOne
    private Employer employer;

    //TODO: Change skills field to be a collection, Add @ManyToMany
    //* Think this is our primary class. Maybe add the cascade attribute here. That way pass down to skills
    @ManyToMany
    private List<Skill> skills = new ArrayList<>();


    public Job() {
    }

    //TODO: update constructor
    // Initialize the id and value fields.
    public Job(Employer anEmployer, List<Skill> someSkills) {
        super();
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    // Getters and setters.
    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    //TODO: update getter/setter for skills
    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
