package sunrise.pokedex.springboot.view;

import java.util.List;

import sunrise.pokedex.springboot.model.Pokemon;

public interface UserView {

    public Long getId();

	public void setId(Long id);

	public String getUsername();

	public void setUsername(String username);

	public String getAddress();

	public void setAddress(String address);

	public String getEmail();

	public void setEmail(String email);

	public List<Pokemon> getPokemons();

	public void setPokemons(List<Pokemon> pokemons);
    
}
