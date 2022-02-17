package sunrise.pokedex.springmvc.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import sunrise.pokedex.springmvc.view.PokemonViewImpl;
import sunrise.pokedex.springmvc.model.Pokemon;

@Service
public class PokemonManagerImpl implements PokemonManager {

    @Autowired
    private RestTemplate restTemplate;

    private String providerUrl = "http://localhost:8081/pokemon/";

    public PokemonViewImpl convertToView(Pokemon pokemon) {
        PokemonViewImpl pokemonView = new PokemonViewImpl();
        BeanUtils.copyProperties(pokemon, pokemonView);
        return pokemonView;
    }

    public Pokemon convertToImpl(PokemonViewImpl pokemon) {
        Pokemon pokemonImpl = new Pokemon();
        BeanUtils.copyProperties(pokemon, pokemonImpl);
        return pokemonImpl;
    }

    @Override
    public PokemonViewImpl findById(Long id) {
        Pokemon pokemonImpl = restTemplate.getForObject(providerUrl + id, Pokemon.class);
        PokemonViewImpl pokemonView = convertToView(pokemonImpl);
        return pokemonView;
    }

    @Override
    public PokemonViewImpl findByUsername(String name) {
        Pokemon pokemonImpl = restTemplate.getForObject(providerUrl + name, Pokemon.class);
        PokemonViewImpl pokemonView = convertToView(pokemonImpl);
        return pokemonView;
    }

    @Override
    public PokemonViewImpl savePokemon(PokemonViewImpl pokemonViewImpl) {
        Pokemon pokemonImpl = convertToImpl(pokemonViewImpl);
        Pokemon restTemplatePokemonImpl = restTemplate
                .postForEntity(providerUrl, new HttpEntity(pokemonImpl), Pokemon.class).getBody();
        PokemonViewImpl pokemonView = convertToView(restTemplatePokemonImpl);
        return pokemonView;
    }

    @Override
    public PokemonViewImpl updatePokemon(PokemonViewImpl pokemonViewImpl) {
        Pokemon pokemonImpl = convertToImpl(pokemonViewImpl);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", Long.toString(pokemonImpl.getId()));
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> requestEntity = new HttpEntity<Pokemon>(pokemonImpl, headers);
        Pokemon updatedPokemonImpl = restTemplate.exchange(providerUrl + "/{id}", HttpMethod.PUT,
                requestEntity, Pokemon.class, params).getBody();
        PokemonViewImpl pokemonView = convertToView(updatedPokemonImpl);
        return pokemonView;
    }

    @Override
    public Long deletePokemonById(Long id) {
        try {
            restTemplate.delete(providerUrl + id);
        } catch (Exception e) {
            throw new Exception("Something went wrong. Error: " + e);
        }
        return id;
    }

    @Override
    public List<PokemonViewImpl> findAllPokemons() {
        Pokemon[] pokemonList = restTemplate.getForEntity(providerUrl, Pokemon[].class).getBody();
        List<PokemonViewImpl> pokemonViewList = new ArrayList<PokemonViewImpl>();
        for (Pokemon pokemon : pokemonList) {
            PokemonViewImpl pokemonView = new PokemonViewImpl();
            BeanUtils.copyProperties(pokemon, pokemonView);
            pokemonViewList.add(pokemonView);
        }
        return pokemonViewList;
    }

    @Override
    public List<Pokemon> deleteAllPokemons() {
        try {
            restTemplate.delete(providerUrl);
        } catch (Exception e) {
            throw new Exception("Something went wrong. Error: " + e);
        }
        return Collections.emptyList();
    }

    @Override
    public Boolean isPokemonExist(PokemonViewImpl pokemon) {
        Pokemon pokemonImpl = convertToImpl(pokemon);
		return pokemonImpl != null && pokemonImpl.getId() != null && findById(pokemonImpl.getId()) != null;
    }
    
}
