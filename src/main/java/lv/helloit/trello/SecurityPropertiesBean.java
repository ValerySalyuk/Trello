package lv.helloit.trello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:security.properties")
public class SecurityPropertiesBean {

    @Value("${auth.su.username:defaultAdmin}")
    private String suName;
    @Value("${auth.su.password}")
    private String suPassword;
    @Value("${auth.salt.salt}")
    private String salt;

    public String getSuName() {
        return suName;
    }

    public String getSuPassword() {
        return suPassword;
    }

    public String getSalt() {
        return salt;
    }
}
