package sunrise.pokedex.springmvc.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sunrise.pokedex.springmvc.model.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    HibernateTemplate hibernateTemplate;

    public User findById(Long id) {
        return hibernateTemplate.get(User.class, id);
    }

    public User findByUsername(String username) {
        String hql = "FROM User p WHERE p.username = :username"; // HQL Query
        Query query = sessionFactory.openSession().createQuery(hql);
        query.setParameter("username", username);
        return (User) query.uniqueResult();
    }

    @Transactional
    public User saveUser(User user) {
        hibernateTemplate.save(user);
        return user;
    }

    @Transactional
    public User updateUser(User user) {
        User resultingUser = hibernateTemplate.load(User.class, user.getId());
        resultingUser.setId(user.getId());
        resultingUser.setUsername(user.getUsername());
        resultingUser.setAddress(user.getAddress());
        resultingUser.setEmail(user.getEmail());
        hibernateTemplate.update(resultingUser);
        return resultingUser;
    }

    @Transactional
    public Long deleteUserById(Long id) {
        Query query = sessionFactory.openSession().createSQLQuery("DELETE FROM User WHERE id = :id");
        query.setLong("id", id);
        // new Long(your_integer);
        return (Long.valueOf(query.executeUpdate()));
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = sessionFactory.openSession().createCriteria(User.class); // Criteria Query
        return (List<User>) criteria.list();
    }

    @Transactional
    public List<User> deleteAllUsers() {
        sessionFactory.openSession().createQuery("DELETE FROM User").executeUpdate();
        return Collections.emptyList();
    }

    public Boolean isUserExist(User user) {
        Query query = sessionFactory.openSession().createQuery("FROM User p WHERE p.username = :username");
    	query.setString("username", user.getUsername());
    	List<?> pList = query.list();
    	if (pList.isEmpty()) {
    		return false;
    	}
    	return true;
    }

}
