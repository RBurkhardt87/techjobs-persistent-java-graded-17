package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Employer extends AbstractEntity {

    //fields
    @NotBlank
    @Size(min = 2, max = 100, message = "Must be between 2 and 100 characters")
    private String location;




    //TODO: continue Task 3 Add a jobs field to employer after I read chapter 18
    @OneToMany
    @JoinColumn(name = "employer_id")
    private final List<Job> jobs = new ArrayList<>();



    //empty constructor for hibernate
    public Employer() {
    }

    //getters/setters

    public @NotBlank @Size(min = 2, max = 100, message = "Must be between 2 and 100 characters") String getLocation() {
        return location;
    }

    public void setLocation(@NotNull @Size(min = 2, max = 100, message = "Must be between 2 and 100 characters") String location) {
        this.location = location;
    }

    //TODO: create a getter for jobs ArrayList
    public List<Job> getJobs() {
        return jobs;
    }
}
