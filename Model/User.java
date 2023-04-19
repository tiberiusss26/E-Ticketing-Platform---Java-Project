package Model;

import org.w3c.dom.xpath.XPathResult;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Objects;

import static Model.passHashing.getSalt;
import static Model.passHashing.get_SHA_512_SecurePassword;

public class User{
    protected String username;
    protected String password;
    protected Date creationDate;

    private String salt;

    public User(String username, String password) throws NoSuchAlgorithmException {
        this.username = username;
        this.salt = getSalt();
        this.password = get_SHA_512_SecurePassword(password, this.salt);
        creationDate = new Date();
    }

    public boolean authentication(String input) throws NoSuchAlgorithmException {
        String newPass = get_SHA_512_SecurePassword(input, salt);
        return newPass.equals(this.password);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(password, user.password);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        User u = new User("tibi","parola");
        System.out.println(u.authentication("parola"));
    }
}

