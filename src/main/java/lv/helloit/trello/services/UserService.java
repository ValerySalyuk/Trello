package lv.helloit.trello.services;

import lv.helloit.trello.dto.dao.UsersDAO;
import lv.helloit.trello.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserService {

    private final UsersDAO usersDAO;

    @Autowired
    public UserService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public void addUser(User user) {
        usersDAO.insert(user);
    }

//    public boolean userExists(Long id) {
//        return userMap.containsKey(id);
//    }

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

    public boolean updateUser(Long userId, User newUser) {
        if (!userId.equals(newUser.getId()) && newUser.getId() != null) {
            return false;
        }

        if (usersDAO.getById(userId).isPresent()) {
            usersDAO.update(userId, newUser);
            return true;
        }
        return false;
    }

    public boolean deleteUser(Long userId) {
        if (usersDAO.getById(userId).isPresent()) {
            usersDAO.delete(userId);
            return true;
        }
        return false;
    }

}
