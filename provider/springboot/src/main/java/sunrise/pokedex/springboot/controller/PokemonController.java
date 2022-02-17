package sunrise.pokedex.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import sunrise.pokedex.springboot.manager.PokemonManager;
import sunrise.pokedex.springboot.model.Pokemon;
import sunrise.pokedex.springboot.view.PokemonViewImpl;

@RestController
public class PokemonController {

    @Autowired
    private PokemonManager pokemonManager;

    private static Logger logger = LoggerFactory.getLogger(PokemonController.class);

    // -------------------Retrieve All Pokemons--

    @RequestMapping(value = "/pokemon/", method = RequestMethod.GET)
    public ResponseEntity<?> listAllPokemons() {
        List<PokemonViewImpl> pokemons = pokemonManager.findAllPokemons();
        if (pokemons.isEmpty()) {
            return new ResponseEntity<List<Pokemon>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<PokemonViewImpl>>(pokemons, HttpStatus.OK);
    }

    // -------------------Retrieve Single Pokemon--

    @RequestMapping(value = "/pokemon/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPokemon(@PathVariable("id") Long id) {
        logger.debug("Fetching pokemon with id " + id);

        PokemonViewImpl pokemon = pokemonManager.findById(id);
        if (pokemon == null) {
            logger.debug("Pokemon with id " + id + " not found");
            return new ResponseEntity<PokemonViewImpl>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<PokemonViewImpl>(pokemon, HttpStatus.OK);
    }

    // -------------------Create a Pokemon--

    @RequestMapping(value = "/pokemon/", method = RequestMethod.POST)
    public ResponseEntity<?> createPokemon(@RequestBody PokemonViewImpl pokemon, UriComponentsBuilder ucBuilder) {
        logger.debug("Creating Pokemon " + pokemon.getName());

        if (pokemonManager.isPokemonExist(pokemon)) {
            logger.debug("A pokemon with name " + pokemon.getName() + " already exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        PokemonViewImpl pokemonViewImpl = pokemonManager.savePokemon(pokemon);

        return new ResponseEntity<PokemonViewImpl>(pokemonViewImpl, HttpStatus.CREATED);
    }

    // ------------------- Update a Pokemon--

    @RequestMapping(value = "/pokemon/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePokemon(@PathVariable("id") Long id, @RequestBody Pokemon pokemon) {
        logger.debug("Updating pokemon " + id);

        PokemonViewImpl currentPokemon = pokemonManager.findById(id);

        if (currentPokemon == null) {
            logger.debug("Pokemon with id " + id + " not found");
            return new ResponseEntity<PokemonViewImpl>(HttpStatus.NO_CONTENT);
        }

        currentPokemon.setName(pokemon.getName());
        currentPokemon.setAttack(pokemon.getAttack());
        currentPokemon.setDefense(pokemon.getDefense());

        pokemonManager.updatePokemon(currentPokemon);
        return new ResponseEntity<PokemonViewImpl>(currentPokemon, HttpStatus.OK);
    }

    // ------------------- Delete a Pokemon--

    @RequestMapping(value = "/pokemon/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePokemon(@PathVariable("id") Long id) {
        logger.debug("Fetching & Deleting Pokemon with id " + id);

        PokemonViewImpl pokemon = pokemonManager.findById(id);
        if (pokemon == null) {
            logger.debug("Unable to delete. Pokemon with id " + id + " not found");
            return new ResponseEntity<Pokemon>(HttpStatus.NO_CONTENT);
        }

        pokemonManager.deletePokemonById(id);
        return new ResponseEntity<Pokemon>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Pokemons--

    @RequestMapping(value = "/pokemon/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllPokemons() {
        logger.debug("Deleting All Pokemons");
        pokemonManager.deleteAllPokemons();
        return new ResponseEntity<Pokemon>(HttpStatus.NO_CONTENT);
    }
}
