package lv.helloit.trello.services;

import lv.helloit.trello.dto.user.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserService {

    private Map<Long, User> userMap = new HashMap<>();
    private Long lastId = 0L;

    public Long addUser(User user) {
        ++lastId;
        user.setId(lastId);
        userMap.put(lastId, user);
        return lastId;
    }

    public boolean userExists(Long id) {
        return userMap.containsKey(id);
    }

    public Collection<User> userList() {
        return userMap.values();
    }

    public User getUser(Long id) {
        if (userExists(id)) {
            return userMap.get(id);
        } else {
            return null;
        }
    }

    public boolean updateUser(Long id, User u) {
        if (userExists(id)) {
            u.setId(id);
            userMap.replace(id, u);
            return true;
        }
        return false;
    }

    public boolean deleteUser(Long id) {
        if (userExists(id)) {
            userMap.remove(id);
            return true;
        }
        return false;
    }

}
