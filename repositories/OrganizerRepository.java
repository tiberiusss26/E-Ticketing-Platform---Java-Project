package repositories;

import database.MySqlConnection;
import model.Client;
import model.Organizer;
import model.User;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class OrganizerRepository implements IUserRepository {

    private final MySqlConnection connection;
    private static final String OrganizerByUsername = "SELECT * FROM organizer WHERE username = ?";
    private static final String InsertOrganizer = "INSERT INTO organizer VALUES (?,?,?,?,?,?)";
    private static final String DeleteOrganizer = "DELETE FROM organizer where username = ? ";

    public OrganizerRepository(MySqlConnection connection) {
        this.connection = connection;
    }


    @Override
    public Optional<User> getUserByUsername(String username) {
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(OrganizerByUsername);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return Optional.empty();
            }
            String hash = rs.getString("password");
            Date dateTime = rs.getObject("creationDate", Date.class);
            double balance = rs.getDouble("balance");
            String name = rs.getString("name");
            String companyName = rs.getString("companyName");

            Organizer organizer = new Organizer(username, hash, dateTime, name, companyName, balance);

            return Optional.of(organizer);


        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void addUser(User user) {
        Organizer organizer = (Organizer) user;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(InsertOrganizer);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setDate(3, (java.sql.Date) user.getCreationDate());
            statement.setString(4, organizer.getName());
            statement.setString(5, organizer.getCompanyName());
            statement.setDouble(6, organizer.getBalance());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void removeUser(User user) {
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(DeleteOrganizer);
            statement.setString(1, user.getUsername());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
