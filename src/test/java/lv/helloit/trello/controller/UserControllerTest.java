package lv.helloit.trello.controller;

import lv.helloit.trello.dto.user.User;
import lv.helloit.trello.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserService userService;

    @Test
    public void shouldCreateUser() {

        userService.addUser(new User(null, 25, "TestName", "TestSurname"));

        User user = userService.getUser(1L);

        assertThat(user.getName()).isEqualTo("TestName");
        assertThat(user.getLastName()).isEqualTo("TestSurname");
        assertThat(user.getAge()).isEqualTo(25);

    }

}