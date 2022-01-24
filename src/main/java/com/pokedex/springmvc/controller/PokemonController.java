package com.pokedex.springmvc.controller;

import com.pokedex.springmvc.service.PokemonService;
import com.pokedex.springmvc.model.Pokemon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class PokemonController {

    @Autowired
    PokemonService pokemonService;

    // -------------------Retrieve All Pokemons--

    @RequestMapping(value = "/pokemon/", method = RequestMethod.GET)
    public ResponseEntity<List<Pokemon>> listAllPokemons() {
        List<Pokemon> pokemons = pokemonService.findAllPokemons();
        if (pokemons.isEmpty()) {
            return new ResponseEntity<List<Pokemon>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Pokemon>>(pokemons, HttpStatus.OK);
    }

    // -------------------Retrieve Single Pokemon--

    @RequestMapping(value = "/pokemon/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> getPokemon(@PathVariable("id") long id) {
        System.out.println("Fetching pokemon with id " + id);
        Pokemon pokemon = pokemonService.findById(id);
        if (pokemon == null) {
            System.out.println("Pokemon with id " + id + " not found");
            return new ResponseEntity<Pokemon>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Pokemon>(pokemon, HttpStatus.OK);
    }

    // -------------------Create a Pokemon--

    @RequestMapping(value = "/pokemon/", method = RequestMethod.POST)
    public ResponseEntity<Void> createPokemon(@RequestBody Pokemon pokemon, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Pokemon " + pokemon.getName());

        if (pokemonService.isPokemonExist(pokemon)) {
            System.out.println("A pokemon with name " + pokemon.getName() + " already exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        pokemonService.savePokemon(pokemon);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/pokemon/{id}").buildAndExpand(pokemon.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Pokemon--

    @RequestMapping(value = "/pokemon/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable("id") long id, @RequestBody Pokemon pokemon) {
        System.out.println("Updating pokemon " + id);

        Pokemon currentPokemon = pokemonService.findById(id);

        if (currentPokemon == null) {
            System.out.println("Pokemon with id " + id + " not found");
            return new ResponseEntity<Pokemon>(HttpStatus.NOT_FOUND);
        }

        currentPokemon.setName(pokemon.getName());
        currentPokemon.setAttack(pokemon.getAttack());
        currentPokemon.setDefense(pokemon.getDefense());

        pokemonService.updatePokemon(currentPokemon);
        return new ResponseEntity<Pokemon>(currentPokemon, HttpStatus.OK);
    }

    // ------------------- Delete a Pokemon--

    @RequestMapping(value = "/pokemon/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Pokemon> deletePokemon(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Pokemon with id " + id);

        Pokemon pokemon = pokemonService.findById(id);
        if (pokemon == null) {
            System.out.println("Unable to delete. Pokemon with id " + id + " not found");
            return new ResponseEntity<Pokemon>(HttpStatus.NOT_FOUND);
        }

        pokemonService.deletePokemonById(id);
        return new ResponseEntity<Pokemon>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Pokemons--

    @RequestMapping(value = "/pokemon/", method = RequestMethod.DELETE)
    public ResponseEntity<Pokemon> deleteAllPokemons() {
        System.out.println("Deleting All Pokemons");

        pokemonService.deleteAllPokemons();
        return new ResponseEntity<Pokemon>(HttpStatus.NO_CONTENT);
    }
}
