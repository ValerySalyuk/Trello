package lv.helloit.trello.dto.dao;

import lv.helloit.trello.dto.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class UsersDAOImplementation extends DAOImplementation<User> {

    @Autowired
    public UsersDAOImplementation(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<User> getAll() {
        return super.getAll(User.class);
    }

    public Optional<User> getById(Long id) {
        return super.getById(id, User.class);
    }

    public Optional<User> getByEmail(String email) {

        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query =  builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.equal(root.get("email"), email));
        query.select(root);

        User user = session.createQuery(query).getSingleResult();

        return Optional.ofNullable(user);

    }

    public void delete(Long taskId) {
        super.delete(taskId, User.class);
    }
}
