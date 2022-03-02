package sunrise.pokedex.springboot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;

@Entity
@Builder
@Table(name = "user")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "EMAIL")
	private String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pokemonOwner", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<Pokemon> pokemons;

	public User() {
	}

	public User(Long id, String username, String address, String email, List<Pokemon> pokemons) {
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
