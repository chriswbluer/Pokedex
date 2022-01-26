package sunrise.pokedex.springmvc.model;

import java.util.Objects;

public class Pokemon {

    private Long id;

    private String name;

    private Integer attack;

    private Integer defense;

    public Pokemon() {
    }

    public Pokemon(Long id, String name, Integer attack, Integer defense) {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.defense = defense;
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

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pokemon)) {
            return false;
        }
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(id, pokemon.id) && Objects.equals(name, pokemon.name) && Objects.equals(attack, pokemon.attack) && Objects.equals(defense, pokemon.defense);
    }

    public int hashCode() {
        return Objects.hash(id, name, attack, defense);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + id + "'" +
            ", name='" + name + "'" +
            ", attack='" + attack + "'" +
            ", defense='" + defense + "'" +
            "}";
    }

}
