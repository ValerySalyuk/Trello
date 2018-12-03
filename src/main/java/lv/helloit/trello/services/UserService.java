package lv.helloit.trello.services;

import lv.helloit.trello.dto.dao.UsersDAOImplementation;
import lv.helloit.trello.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UsersDAOImplementation usersDAO;

    @Autowired
    public UserService(UsersDAOImplementation usersDAO) {
        this.usersDAO = usersDAO;
    }

    public Long addUser(User user) {
        return usersDAO.insert(user);
    }


    public List<User> userList() {
        return new ArrayList<>(usersDAO.getAll());
    }

    public User getUser(Long id) {
        Optional<User> user = usersDAO.getById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    public boolean updateUser(User newUser) {
        usersDAO.update(newUser);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (usersDAO.getById(userId).isPresent()) {
            usersDAO.delete(userId);
            return true;
        }
        return false;
    }

}
