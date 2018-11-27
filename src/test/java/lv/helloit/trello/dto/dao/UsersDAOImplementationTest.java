package lv.helloit.trello.dto.dao;

import lv.helloit.trello.dto.user.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Component
@Primary
class UsersDAOImplementationTest implements UsersDAO {

    private User u;

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.ofNullable(u);
    }

    @Override
    public void insert(User user) {
        u = user;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Long userId, User user) {

    }

}