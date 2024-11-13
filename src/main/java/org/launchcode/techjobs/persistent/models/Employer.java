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

    //fields
    @NotBlank
    @Size(min = 2, max = 100, message = "Must be between 2 and 100 characters")
    private String location;


    /*
    The annotation @JoinColumn indicates that this entity is the owner of the relationship (that is: the corresponding
    table has a column with a foreign key to the referenced table), whereas the attribute mappedBy indicates that the
    entity in this side is the inverse of the relationship, and the owner resides in the "other" entity.
     */


    @OneToMany
    @JoinColumn(name = "employer_id")
    private List<Job> jobs = new ArrayList<>();



    //empty constructor for hibernate
    public Employer() {
    }

    public Employer(String location) {
        super();
        this.location = location;
    }



    //getters/setters
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
