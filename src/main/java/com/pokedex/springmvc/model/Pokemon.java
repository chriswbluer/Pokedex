package com.pokedex.springmvc.model;

import java.util.Objects;

public class Pokemon {

    private long id;

    private String name;

    private Integer attack;

    private Integer defense;

    public Pokemon() {
        id = 0;
    }

    public Pokemon(long id, String name, Integer attack, Integer defense) {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
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

    public Pokemon id(long id) {
        setId(id);
        return this;
    }

    public Pokemon name(String name) {
        setName(name);
        return this;
    }

    public Pokemon attack(Integer attack) {
        setAttack(attack);
        return this;
    }

    public Pokemon defense(Integer defense) {
        setDefense(defense);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pokemon)) {
            return false;
        }
        Pokemon pokemon = (Pokemon) o;
        return id == pokemon.id && Objects.equals(name, pokemon.name) && Objects.equals(attack, pokemon.attack)
                && Objects.equals(defense, pokemon.defense);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, attack, defense);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", attack='" + getAttack() + "'" +
                ", defense='" + getDefense() + "'" +
                "}";
    }

}
