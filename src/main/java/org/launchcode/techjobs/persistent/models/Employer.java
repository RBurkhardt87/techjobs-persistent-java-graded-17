package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Employer extends AbstractEntity {


    @NotNull
    @Size(min = 2, max = 100, message = "Must be between 2 and 100 characters")
    private String location;


    public Employer() {
    }

    public @NotNull @Size(min = 2, max = 100, message = "Must be between 2 and 100 characters") String getLocation() {
        return location;
    }

    public void setLocation(@NotNull @Size(min = 2, max = 100, message = "Must be between 2 and 100 characters") String location) {
        this.location = location;
    }
}
