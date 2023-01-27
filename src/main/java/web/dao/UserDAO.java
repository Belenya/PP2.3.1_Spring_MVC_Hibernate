package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.models.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import java.util.List;

@Component
public class UserDAO implements AbstractDAO<User>{

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private UserDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void create(User o) {
        var em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(o);
        em.flush();
        transaction.commit();
    }

    @Override
    public User read(User o) {
        return entityManagerFactory.createEntityManager().find(User.class, o.getId());
    }

    @Override
    public void update(User o) {
        var em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        var user = em.find(User.class, o.getId());
        user.setName(o.getName());
        user.setLastName(o.getLastName());
        user.setAge(o.getAge());
        em.flush();
        transaction.commit();
    }

    @Override
    public void delete(User o) {
        var em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        var user = em.find(User.class, o.getId());
        if (user != null) {
            em.remove(user);
        }
        em.flush();
        transaction.commit();
    }

    @Override
    public List<User> getAll() {
        return entityManagerFactory.createEntityManager().createQuery("SELECT user from User user", User.class).getResultList();
    }
}
