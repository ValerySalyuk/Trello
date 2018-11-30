package lv.helloit.trello.dto.dao;

import lv.helloit.trello.dto.user.User;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsersDAOImplementation extends DAOImplementation<User> {

    public UsersDAOImplementation(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<User> getAll() {
        return super.getAll(User.class);
    }

    public Optional<User> getById(Long id) {
        return super.getById(id, User.class);
    }

    public void delete(Long taskId) {
        super.delete(taskId, User.class);
    }
}
