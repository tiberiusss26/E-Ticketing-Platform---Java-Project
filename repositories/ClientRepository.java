package repositories;

import com.mysql.cj.xdevapi.RemoveStatement;
import database.MySqlConnection;
import model.Client;
import model.User;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public class ClientRepository implements IUserRepository {

    private final MySqlConnection connection;
    private static final String ClientByUsername = "SELECT * FROM client WHERE username = ?";
    private static final String InsertClient = "INSERT INTO client VALUES (?,?,?,?)";
    private static final String DeleteCLient = "DELETE FROM client where username = ? ";

    public ClientRepository(MySqlConnection connection) {
        this.connection = connection;
    }


    @Override
    public Optional<User> getUserByUsername(String username) {
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(ClientByUsername);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return Optional.empty();
            }
            String hash = rs.getString("password");
            Date dateTime = rs.getObject("creationDate", Date.class);
            double credit = rs.getDouble("creditScore");

            Client client = new Client(username, hash, dateTime, credit);

            return Optional.of(client);


        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void addUser(User user) {

        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(InsertClient);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setDate(3, (java.sql.Date) user.getCreationDate());
            statement.setDouble(4, 0);

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
            PreparedStatement statement = connection.getConnection().prepareStatement(DeleteCLient);
            statement.setString(1,user.getUsername());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
