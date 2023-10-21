package be.thomasmore.party.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Collection;

@Entity
public class Animal {

    @Id
    private int id;
    private String name;
    private String city;
    private String bio;
    @ManyToMany
    private Collection<Party> parties;

    public Animal(int id, String name, String city, String bio) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
