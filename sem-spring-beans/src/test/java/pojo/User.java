package pojo;

public class User {

    private Person person;

    public User() {
    }

    public User(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "User{" +
                "person=" + person +
                '}';
    }
}
