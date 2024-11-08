package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {


    @NotBlank
    @Size(min= 1, max = 500, message = "Must be under 500 characters")
    private String description;

    //TODO: Add jobs field, make it @ManyToMany(mappedBy="skills")
    //* Think this is secondary class. That is why we are using mappedBy attribute
    @ManyToMany(mappedBy = "skills")
    private List<Job> jobs = new ArrayList<>();

    public Skill() {
    }

    //TODO: update constructor
    public Skill(String description, List<Job> jobs) {
        this.description = description;
        this.jobs = jobs;
    }

    public @NotBlank @Size(max = 500, message = "Must be under 500 characters") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank @Size(max = 500, message = "Must be under 500 characters") String description) {
        this.description = description;
    }

    //TODO: generate getters/setters for jobs field
    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
