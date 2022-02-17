package sunrise.pokedex.springboot.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sunrise.pokedex.springboot.model.Pokemon;

@Repository
public class PokemonDaoImpl implements PokemonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Pokemon findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM pokemon WHERE id=?",
                new BeanPropertyRowMapper<Pokemon>(Pokemon.class), id);
    }

    public Pokemon findByName(String name) {
        return new Pokemon();
    }

    public Pokemon savePokemon(Pokemon pokemon) {
        try {
            jdbcTemplate.update(
                    "INSERT INTO pokemon (attack, defense, name) VALUES (?, ?, ?)",
                    new Object[] { pokemon.getAttack(), pokemon.getDefense(), pokemon.getName() });
        } catch (SQLException e) {
            throw new SQLException("Something went wrong. Error: " + e);
        }
        return pokemon;
    }

    public Pokemon updatePokemon(Pokemon pokemon) {
        try {
            jdbcTemplate.update(
                    "UPDATE pokemon SET attack = ?, defense = ?, name = ?, pokemon_owner = ? WHERE id = ?",
                    new Object[] { pokemon.getAttack(), pokemon.getDefense(), pokemon.getName(),
                            pokemon.getPokemonOwner(),
                            pokemon.getId() });
        } catch (SQLException e) {
            throw new SQLException("Something went wrong. Error: " + e);
        }
        Pokemon resultingPokemon = pokemon;
        return resultingPokemon;
    }

    public Long deletePokemonById(Long id) {
        return (Long.valueOf(jdbcTemplate.update("DELETE FROM pokemon WHERE id=?", id)));
    }

    @SuppressWarnings("unchecked")
    public List<Pokemon> findAllPokemons() {
        return jdbcTemplate.query("SELECT * FROM pokemon", new BeanPropertyRowMapper<Pokemon>(Pokemon.class));
    }

    public List<Pokemon> deleteAllPokemons() {
        return jdbcTemplate.query("DELETE FROM Pokemon", new BeanPropertyRowMapper<Pokemon>(Pokemon.class));
    }

    public Boolean isPokemonExist(Pokemon pokemon) {
        String pokemonName = pokemon.getName();
        List<?> pList = jdbcTemplate.query("SELECT * FROM pokemon WHERE name=?",
                new BeanPropertyRowMapper<Pokemon>(Pokemon.class), pokemonName);
        if (pList.isEmpty()) {
            return false;
        }
        return true;
    }

}
