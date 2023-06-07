package model;

import exceptions.LoginException;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Objects;

import static model.passHashing.getSalt;
import static model.passHashing.get_SHA_512_SecurePassword;

public class User {
    protected String username;
    protected String password;
    protected Date creationDate;

    private final String salt;

    public User(String username, String password, Date creationDate) throws NoSuchAlgorithmException {
        this.username = username;
        this.creationDate = creationDate;
        this.salt = getSalt();
        this.password = password;
    }

    public User(String username, String password) throws NoSuchAlgorithmException {
        this.username = username;
        this.salt = getSalt();
        this.password = get_SHA_512_SecurePassword(password, this.salt);
        Date date = new Date();
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDate();

        creationDate = new java.sql.Date(year, month, day);
    }

    public boolean authentication(String input) throws LoginException {
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

    public void setCreationDate(java.sql.Date creationDate) {
        this.creationDate = creationDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(password, user.password);
    }

}