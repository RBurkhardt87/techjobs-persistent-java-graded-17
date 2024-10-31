package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Skill extends AbstractEntity {

    @NotNull
    @NotBlank
    @Size(min= 1, max = 500, message = "Must be under 500 characters")
    private String description;

    public Skill() {
    }

    public @NotBlank @Size(max = 500, message = "Must be under 500 characters") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank @Size(max = 500, message = "Must be under 500 characters") String description) {
        this.description = description;
    }
}
