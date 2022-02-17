package sunrise.pokedex.springboot.view;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import sunrise.pokedex.springboot.model.Pokemon;
import sunrise.pokedex.springboot.model.User;

public class PokemonViewImpl implements PokemonView {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("attack")
    private Integer attack;

    @JsonProperty("defense")
    private Integer defense;

    public PokemonViewImpl() {
    }

    public PokemonViewImpl(Long id, String name, Integer attack, Integer defense) {
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
