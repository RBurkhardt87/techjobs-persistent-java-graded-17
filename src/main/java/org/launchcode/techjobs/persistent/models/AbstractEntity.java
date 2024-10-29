package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;


@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private int id;


    //TODO: I tried to update the getters/setters to include validation in declaration. Didn't work. Found a suggestion
    // to put validation over the setter since that's how the subclasses get access to the name. It does work there, but
    // I am still not seeing the specific message to user being displayed to user through the template. So...
    //? How do I get the error message to display to the user from abstract parent class .... Instructions didn't mention
    //? changing anything in the templates I don't believe..

    private String name;


    public int getId() {
        return id;
    }


    public @NotNull @Size(max = 75, message = "Must be less than 75 characters") String getName() {
        return name;
    }


    @NotNull
    @Size(max = 75, message = "Must be less than 75 characters")
    public void setName(@NotNull @Size(max = 75, message = "Must be less than 75 characters") String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
