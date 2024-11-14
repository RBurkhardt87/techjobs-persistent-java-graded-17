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


    @ManyToMany(mappedBy = "skills")
    private List<Job> jobs = new ArrayList<>();



    public Skill() {
    }

    public Skill(String description) {
        super();
        this.description = description;
    }



    public @NotBlank @Size(max = 500, message = "Must be under 500 characters") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank @Size(max = 500, message = "Must be under 500 characters") String description) {
        this.description = description;
    }


    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
