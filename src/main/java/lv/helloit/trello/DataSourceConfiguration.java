package lv.helloit.trello;

import lv.helloit.trello.dto.task.Task;
import lv.helloit.trello.dto.user.User;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(Task.class)
                .addAnnotatedClass(User.class)
                .configure()
                .buildSessionFactory();
    }

}
