package lv.helloit.trello.dto.dao;

import lv.helloit.trello.dto.user.User;

import java.util.List;
import java.util.Optional;

public interface UsersDAO {
    List<User> getAll();

    Optional<User> getById(Long id);

    void insert(User user);

    void update(Long userId, User newUser);

    void delete(Long userId);
}
