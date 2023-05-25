package model;

import database.MySqlConnection;
import repositories.ClientRepository;
import service.UserService;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        // ceva cu sortare
        // meniu interactiv
        //interfete
        //try with resources

        MySqlConnection connection = MySqlConnection.getInstance();
        ClientRepository clientRepository = new ClientRepository(connection);

//        User user1 = new User("tibi","tibii");
        User user2 = new User("andrei", "tibii");
//        clientRepository.addUser(user2);
        clientRepository.removeUser(user2);

    }
}
