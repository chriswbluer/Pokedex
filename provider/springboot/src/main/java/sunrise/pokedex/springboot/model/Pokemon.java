package sunrise.pokedex.springboot.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Builder
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ATTACK")
    private Integer attack;

    @Column(name = "DEFENSE")
    private Integer defense;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "POKEMON_OWNER", referencedColumnName = "USERNAME")
    @JsonBackReference
    private User pokemonOwner;

    public Pokemon() {
    }

    public Pokemon(Long id, String name, Integer attack, Integer defense, User pokemonOwner) {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.pokemonOwner = pokemonOwner;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttack() {
        return this.attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return this.defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public User getPokemonOwner() {
        return this.pokemonOwner;
    }

    public void setPokemonOwner(User pokemonOwner) {
        this.pokemonOwner = pokemonOwner;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", attack='" + getAttack() + "'" +
                ", defense='" + getDefense() + "'" +
                ", pokemonOwner='" + getPokemonOwner() + "'" +
                "}";
    }

}
