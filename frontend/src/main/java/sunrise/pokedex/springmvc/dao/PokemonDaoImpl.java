package sunrise.pokedex.springmvc.dao;

import java.util.Collections;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sunrise.pokedex.springmvc.model.Pokemon;

@Repository
public class PokemonDaoImpl implements PokemonDao {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    HibernateTemplate hibernateTemplate;
    

    public Pokemon findById(Long id) {
        // return hibernateTemplate.get(Pokemon.class, id);
        String hql = "FROM Pokemon p WHERE p.id = :id"; // HQL Query
		Query query = sessionFactory.openSession().createQuery(hql);
		query.setParameter("id", id);
		return (Pokemon) query.uniqueResult();
    }

    public Pokemon findByName(String name) {
        String hql = "FROM Pokemon p WHERE p.name = :name"; // HQL Query
		Query query = sessionFactory.openSession().createQuery(hql);
		query.setParameter("name", name);
		return (Pokemon) query.uniqueResult();
    }

    @Transactional
    public Pokemon savePokemon(Pokemon pokemon) {
        hibernateTemplate.save(pokemon);
		return pokemon;
    }

    @Transactional
    public Pokemon updatePokemon(Pokemon pokemon) {
        Pokemon resultingPokemon = hibernateTemplate.load(Pokemon.class, pokemon.getId());
        resultingPokemon.setId(pokemon.getId());
        resultingPokemon.setName(pokemon.getName());
        resultingPokemon.setAttack(pokemon.getAttack());
        resultingPokemon.setDefense(pokemon.getDefense());
        resultingPokemon.setPokemonOwner(pokemon.getPokemonOwner());
        hibernateTemplate.update(resultingPokemon);
        return resultingPokemon;
    }

    @Transactional
    public Long deletePokemonById(Long id) {
        Query query = sessionFactory.openSession().createSQLQuery("DELETE FROM Pokemon WHERE id = :id");
		query.setLong("id", id);
        // new Long(your_integer);
		return (Long.valueOf(query.executeUpdate()));
    }

    @SuppressWarnings("unchecked")
    public List<Pokemon> findAllPokemons() {
        Criteria criteria = sessionFactory.openSession().createCriteria(Pokemon.class); // Criteria Query
        return (List<Pokemon>) criteria.list();
    }


    @Transactional
    public List<Pokemon> deleteAllPokemons() {
        // TODO: Maybe return something else
        sessionFactory.openSession().createQuery("DELETE FROM Pokemon").executeUpdate();
        return Collections.emptyList();
    }


    public Boolean isPokemonExist(Pokemon pokemon) {
        Query query = sessionFactory.openSession().createQuery("FROM Pokemon p WHERE p.name = :name");
    	query.setString("name", pokemon.getName());
    	List<?> pList = query.list();
    	if (pList.isEmpty()) {
    		return false;
    	}
    	return true;
    }

}
