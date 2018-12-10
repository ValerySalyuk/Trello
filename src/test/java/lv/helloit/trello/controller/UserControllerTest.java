package lv.helloit.trello.controller;

import lv.helloit.trello.dto.user.User;
import lv.helloit.trello.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void shouldCreateUser() {

        User input = new User();
        input.setName("Name");
        input.setLastName("Last Name");
        input.setAge(25);

        Long id = userController.add(input);

        Optional<User> user = userController.particularUser(id);

        assertThat(user).isPresent();

        assertThat(user.get().getName()).isEqualTo("Name");
        assertThat(user.get().getLastName()).isEqualTo("Last Name");
        assertThat(user.get().getAge()).isEqualTo(25);

    }

}