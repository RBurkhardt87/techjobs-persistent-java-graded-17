package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Employer extends AbstractEntity {


    @NotBlank
    @Size(min = 2, max = 100, message = "Must be between 2 and 100 characters")
    private String location;



    @OneToMany
    @JoinColumn(name = "employer_id")
    private List<Job> jobs = new ArrayList<>();




    public Employer() {
    }


    public Employer(String location) {
        super();
        this.location = location;
    }



    public @NotBlank @Size(min = 2, max = 100, message = "Must be between 2 and 100 characters") String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank @Size(min = 2, max = 100, message = "Must be between 2 and 100 characters") String location) {
        this.location = location;
    }


    public List<Job> getJobs() {
        return jobs;
    }
}
