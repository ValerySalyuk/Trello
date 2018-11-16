package lv.helloit.trello.services;

import lv.helloit.trello.dto.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService victim;

    @Before
    public void setUp() {
        victim = new UserService();
    }

    @Test
    public void shouldAddNewUser() {

        User u = new User();
        u.setAge(50);
        u.setName("John");
        u.setLastName("Snow");

        victim.addUser(u);

        assertEquals(victim.userList().size(), 1);

        assertEquals(victim.getUser(1L).getName(), "John");

    }

    @Test
    public void userShouldExist() {

        assertFalse(victim.userExists(1L));

        User u = new User();
        u.setAge(50);
        u.setName("John");
        u.setLastName("Snow");

        victim.addUser(u);

        assertTrue(victim.userExists(1L));

    }

    @Test
    public void shouldReturnUser() {

        assertFalse(victim.userExists(1L));

        User u = new User();
        u.setAge(50);
        u.setName("John");
        u.setLastName("Snow");

        victim.addUser(u);

        assertTrue(victim.userExists(1L));

    }

    @Test
    public void shouldDeleteUser() {

        assertFalse(victim.userExists(1L));
        victim.deleteUser(1L);
        assertFalse(victim.userExists(1L));

        User u = new User();
        u.setAge(50);
        u.setName("John");
        u.setLastName("Snow");

        victim.addUser(u);

        assertTrue(victim.userExists(1L));

        victim.deleteUser(1L);

        assertFalse(victim.userExists(1L));

    }

    @Test
    public void shouldUpdateUser() {

        User u1 = new User();
        u1.setAge(50);
        u1.setName("John");
        u1.setLastName("Snow");

        victim.addUser(u1);

        assertEquals(victim.getUser(1L).getName(), "John");
        assertEquals(victim.getUser(1L).getLastName(), "Snow");
        assertEquals(victim.getUser(1L).getAge(), (Integer) 50);

        User u2 = new User();
        u2.setAge(33);
        u2.setName("Emily");
        u2.setLastName("Brown");

        victim.updateUser(1L, u2);

        assertEquals(victim.getUser(1L).getName(), "Emily");
        assertEquals(victim.getUser(1L).getLastName(), "Brown");
        assertEquals(victim.getUser(1L).getAge(), (Integer) 33);

        assertFalse(victim.userExists(7L));
        User u3 = new User();
        u3.setAge(44);
        u3.setName("Max");
        u3.setLastName("Payne");

        victim.updateUser(7L, u3);

        assertFalse(victim.userExists(7L));
        assertEquals(victim.getUser(1L).getName(), "Emily");
        assertEquals(victim.getUser(1L).getLastName(), "Brown");
        assertEquals(victim.getUser(1L).getAge(), (Integer) 33);

    }

}