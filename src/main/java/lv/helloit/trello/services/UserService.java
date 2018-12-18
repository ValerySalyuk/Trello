package lv.helloit.trello.services;

import lv.helloit.trello.SecurityPropertiesBean;
import lv.helloit.trello.dto.dao.TasksDAOImplementation;
import lv.helloit.trello.dto.dao.UsersDAOImplementation;
import lv.helloit.trello.dto.task.Task;
import lv.helloit.trello.dto.user.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UsersDAOImplementation usersDAOImplementation;
    private final TasksDAOImplementation tasksDAOImplementation;
    private final SecurityPropertiesBean securityPropertiesBean;

    @Autowired
    public UserService(UsersDAOImplementation usersDAOImplementation,
                       TasksDAOImplementation tasksDAOImplementation,
                       SecurityPropertiesBean securityPropertiesBean) {
        this.usersDAOImplementation = usersDAOImplementation;
        this.tasksDAOImplementation = tasksDAOImplementation;
        this.securityPropertiesBean = securityPropertiesBean;

    }

    public Long addUser(User user) {

        String password = generatePassword();
        LOGGER.info("Password: " + password);
        sendPasswordEmail(user, password);
        String passwordHash = generatePasswordHash(password);
        LOGGER.info("Password hash: " + passwordHash);
        user.setPasswordHash(passwordHash);

        return usersDAOImplementation.insert(user);
    }

    public List<User> userList() {
        return new ArrayList<>(usersDAOImplementation.getAll());
    }

    public Optional<User> getUser(Long id) {
        return usersDAOImplementation.getById(id);
    }

    public Optional<User> getUser(String email) {
        return usersDAOImplementation.getByEmail(email);
    }

    public boolean updateUser(User newUser) {
        usersDAOImplementation.update(newUser);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (usersDAOImplementation.getById(userId).isPresent()) {
            usersDAOImplementation.delete(userId);
            return true;
        }
        return false;
    }

    public void assignTask(Long userId, Long taskId) {
        Optional<User> wrappedUser = this.getUser(userId);
        Optional<Task> wrappedTask = tasksDAOImplementation.getById(taskId);

        if (wrappedTask.isPresent() && wrappedUser.isPresent()) {
            User user = wrappedUser.get();
            user.getTasks().add(wrappedTask.get());

            usersDAOImplementation.update(user);
        }
    }

    private String generatePassword() {
        return RandomStringUtils.random(8, true, true);
    }

    private void sendPasswordEmail(User user, String password) {

        RestTemplate restTemplate = new RestTemplate();

        URI url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8888)
                .path("sendHtmlMail")
                .queryParam("recipientAddress", user.getEmail())
                .queryParam("subject", "Your password")
                .queryParam("body", "Your password is: " + password)
                .build("");

        String response = restTemplate.getForObject(url, String.class);

        LOGGER.info(response);

    }

    private String generatePasswordHash(String password) {
        return Sha512DigestUtils.shaHex(password + securityPropertiesBean.getSalt());
    }

}
