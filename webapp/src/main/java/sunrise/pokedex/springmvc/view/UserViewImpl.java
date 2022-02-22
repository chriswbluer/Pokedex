package sunrise.pokedex.springmvc.view;

import java.util.List;
import java.util.Objects;

import sunrise.pokedex.springmvc.model.Pokemon;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserViewImpl implements UserView {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("username")
	private String username;

	@JsonProperty("address")
	private String address;

	@JsonProperty("email")
	private String email;

	@JsonProperty("pokemons")
	private List<Pokemon> pokemons;

	public UserViewImpl() {
	}

	public UserViewImpl(Long id, String username, String address, String email, List<Pokemon> pokemons) {
		this.id = id;
		this.username = username;
		this.address = address;
		this.email = email;
		this.pokemons = pokemons;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Pokemon> getPokemons() {
		return this.pokemons;
	}

	public void setPokemons(List<Pokemon> pokemons) {
		this.pokemons = pokemons;
	}

	@Override
	public String toString() {
		return "{" +
				" id='" + getId() + "'" +
				", username='" + getUsername() + "'" +
				", address='" + getAddress() + "'" +
				", email='" + getEmail() + "'" +
				", pokemons='" + getPokemons() + "'" +
				"}";
	}

}
