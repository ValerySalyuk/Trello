package lv.helloit.trello.dto.user;

public class User {

    private Long id;
    private Integer age;
    private String name;
    private String lastName;

    public User() {
    }

    public User(Long id, Integer age, String name, String lastName) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.lastName = lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
