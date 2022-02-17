package sunrise.pokedex.springboot.view;

import sunrise.pokedex.springboot.model.User;

public interface PokemonView {

    public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

    public Integer getAttack();

    public void setAttack(Integer attack);

    public Integer getDefense();

    public void setDefense(Integer defense);

}
