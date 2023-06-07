package repositories;

import database.MySqlConnection;
import model.Client;
import model.Organizer;
import model.User;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrganizerRepository implements IUserRepository {

    private final MySqlConnection connection;
    private static final String OrganizerByUsername = "SELECT * FROM organizer WHERE username = ?";
    private static final String InsertOrganizer = "INSERT INTO organizer VALUES (?,?,?,?,?,?)";
    private static final String DeleteOrganizer = "DELETE FROM organizer where username = ? ";

    private static final String listOrganizers =
            "SELECT username, password, creationDate, name, companyName, balance FROM organizer";

    public OrganizerRepository(MySqlConnection connection) {
        this.connection = connection;
    }

    public List<Organizer> listOrganizers() {
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(listOrganizers);

            List<Organizer> organizers = new ArrayList<>();

            while (rs.next()) {
                String username = rs.getString("username");
                String hash = rs.getString("password");
                Date dateTime = rs.getObject("creationDate", Date.class);
                String name = rs.getString("name");
                String companyName = rs.getString("companyName");
                double balance = rs.getDouble("balance");

                Organizer organizer = new Organizer(username,hash,dateTime,name, companyName,balance);
                organizers.add(organizer);
            }

            return organizers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
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
            String name = rs.getString("name");
            String companyName = rs.getString("companyName");
            double balance = rs.getDouble("balance");


            return Optional.of(new Organizer(username, hash, dateTime, name, companyName, balance));


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
