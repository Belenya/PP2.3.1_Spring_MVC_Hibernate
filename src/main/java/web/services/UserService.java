package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.AbstractDAO;
import web.dao.UserDAO;
import web.models.User;

import java.util.List;

@Service
public class UserService implements AbstractService<User>{

    @Autowired
    private AbstractDAO<User> userDAO;

    @Override
    public void create(User o) {
        userDAO.create(o);
    }

    @Override
    public User read(User o) {
        return userDAO.read(o);
    }

    @Override
    public void update(User o) {
        userDAO.update(o);
    }

    @Override
    public void delete(User o) {
        userDAO.delete(o);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }
}
