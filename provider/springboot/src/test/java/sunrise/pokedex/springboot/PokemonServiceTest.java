package sunrise.pokedex.springboot;

import sunrise.pokedex.springboot.dao.PokemonDao;
import sunrise.pokedex.springboot.model.Pokemon;
import sunrise.pokedex.springboot.service.PokemonServiceImpl;

import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringBootTest
public class PokemonServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    PokemonDao pokemonDao;

    @InjectMocks
    PokemonServiceImpl pokemonServiceImpl = new PokemonServiceImpl();

    private Pokemon pokemon = new Pokemon();

    @BeforeClass
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveMockPokemonWithValidParametersAndVerifyItWasSaved() {
        pokemon = buildMockPokemonDao();
        when(pokemonServiceImpl.savePokemon(pokemon)).thenReturn(pokemon);
        pokemonServiceImpl.savePokemon(pokemon);
        Mockito.verify(pokemonDao).savePokemon(Mockito.any(Pokemon.class));
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void attemptToSaveMockNullPokemonAndVerifyItWasNotSaved() {
        Pokemon pokemon = null;
        pokemonServiceImpl.savePokemon(pokemon);
        Mockito.verify(pokemonDao, Mockito.times(0)).savePokemon(Mockito.any(Pokemon.class));
    }

    @Test
    public void getPokemonWithValidId() {
        Pokemon pokemon = buildMockPokemonDao();
        when(pokemonServiceImpl.findById((long) 1)).thenReturn(pokemon);
        Pokemon fromService = pokemonServiceImpl.findById((long) 1);
        Assert.assertEquals(pokemon, fromService);

    }

    @Test(expectedExceptions = RuntimeException.class)
    public void throwRuntimeExceptionWhenAttemptingToGetPokemonWithNullId() {
        Pokemon pokemon = buildMockPokemonDao();
        pokemon.setId(null);
        pokemonServiceImpl.findById(pokemon.getId());
        Mockito.verify(pokemonDao, Mockito.times(0)).findById(pokemon.getId());
    }

    @Test
    public void getPokemonWithValidName() {
        Pokemon pokemon = buildMockPokemonDao();
        when(pokemonServiceImpl.findByName(pokemon.getName())).thenReturn(pokemon);
        Pokemon fromService = pokemonServiceImpl.findByName(pokemon.getName());
        Assert.assertEquals(pokemon, fromService);
    }

    @Test
    public void updatePokemonIfGivenValidPokemon() {
        Pokemon pokemon = buildMockPokemonDao();
        pokemonServiceImpl.updatePokemon(pokemon);
        Mockito.verify(pokemonDao, Mockito.times(1)).updatePokemon(pokemon);
    }

    @Test
    public void deletePokemonByIdIfGivenValidId() {
        Pokemon pokemon = buildMockPokemonDao();
        pokemonServiceImpl.deletePokemonById(pokemon.getId());
        Mockito.verify(pokemonDao, Mockito.times(1)).deletePokemonById(pokemon.getId());
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void doNotDeletePokemonByIdIfGivenInvalidId() {
        Pokemon pokemon = buildMockPokemonDao();
        pokemonServiceImpl.deletePokemonById(null);
        Mockito.verify(pokemonDao, Mockito.times(0)).deletePokemonById(pokemon.getId());
    }

    @Test
    public void savePokemonAndFindAllPokemon_ReturnList_AssertThatTheListOfPokemonisNotEmpty() {
        Pokemon pokemon = buildMockPokemonDao();
        pokemonServiceImpl.savePokemon(pokemon);
        List<Pokemon> listOfPokemonBuiltFromService = pokemonServiceImpl.findAllPokemons();
        Assert.assertTrue(listOfPokemonBuiltFromService.isEmpty());
    }

    @Test
    public void saveMockPokemon_DeleteAllPokemon_VerifyDeletionOfAllPokemon() {
        Pokemon pokemon = buildMockPokemonDao();
        pokemonServiceImpl.savePokemon(pokemon);
        List<Pokemon> listOfPokemonBuiltBeforeDeletion = pokemonServiceImpl.findAllPokemons();
        pokemonServiceImpl.deleteAllPokemons();
        Assert.assertTrue(listOfPokemonBuiltBeforeDeletion.isEmpty());
    }

    @Test
    public void savePokemonAndVerifyItsExistence() {
        Pokemon pokemon = buildMockPokemonDao();
        pokemonServiceImpl.savePokemon(pokemon);
        pokemonServiceImpl.isPokemonExist(pokemon);
        Mockito.verify(pokemonDao, Mockito.times(1)).isPokemonExist(pokemon);
    }

    public Pokemon buildMockPokemonDao() {
        return Pokemon.builder()
                .id((long) 1)
                .name("Mewtwo")
                .attack(10)
                .defense(10)
                .build();
    }
}
