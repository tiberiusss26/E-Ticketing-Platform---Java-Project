package Service;

import Exceptions.LoginException;
import Model.Client;
import Model.Organizer;
import Model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.nio.file.StandardOpenOption.APPEND;

public class UserService {
    private List<Client> clients = new ArrayList<>();
    private List<Organizer> organizers = new ArrayList<>();

    private final String reportsPath = "/Users/tiberiutitiriga/Desktop/PAO/E-Ticketing-Platform---Java-Project/Reports";

    public UserService() {
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void addOrganizer(Organizer organizer) {
        organizers.add(organizer);
    }

    // afisam clientii in ordinea banilor disponibili
    public void listAllCLients() {
        clients.sort(Comparator.comparing(Client::getCreditScore));
        System.out.println(clients);
    }

    public void listAllOrganizers() {
        organizers.sort(Comparator.comparing(Organizer::getBalance));
        System.out.println(organizers);
    }

    public Client getClientByUsername(String username) {
        return clients.stream()
                .filter(c -> c.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Client not found!"));
    }

    public Organizer getOrganizertByUsername(String username) {
        return organizers.stream()
                .filter(c -> c.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Organizer not found!"));
    }


    public void generateReport() {
        Path path = Paths.get(reportsPath);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String time = simpleDateFormat.format(new Date());
        Path reportPath = path.resolve("report" + time + ".csv");

        try {
            Files.createFile(reportPath);
            Files.writeString(reportPath, generateContent(), APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User registerUser(String username, String password) throws LoginException, NoSuchAlgorithmException {
        User newUser = new User(username, password);
        return newUser;
    }

    public User createUser(User newUser) throws LoginException, NoSuchAlgorithmException {
        String username = getClientByUsername(newUser.getUsername()).getUsername();
        if (username.isEmpty()) {
            username = getOrganizertByUsername(newUser.getUsername()).getUsername();
        }
        if (!username.isEmpty()) {
            throw new LoginException("Username not valid!");
        }
        String usern = newUser.getUsername();
        String pass = newUser.getPassword();
        if (newUser.getClass().toString().equals("class Model.Client")) {
            Client newUser1 = new Client(usern,pass);
            clients.add(newUser1);
        } else {
            Organizer newUser1 = new Organizer(usern, pass);
            organizers.add(newUser1);
        }
        return newUser;
    }

    private StringBuilder generateContent() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("USERNAME");
        stringBuilder.append(", ");
        stringBuilder.append("PASSWORD");
        stringBuilder.append(", ");
        stringBuilder.append("CREATION_DATE");
        stringBuilder.append(", ");
        stringBuilder.append("USER_TYPE");
        stringBuilder.append(", ");
        stringBuilder.append("BALANCE");
        stringBuilder.append("\n");

        clients.forEach(client -> {
            stringBuilder.append(client.getUsername());
            stringBuilder.append(", ");
            stringBuilder.append(client.getPassword());
            stringBuilder.append(", ");
            stringBuilder.append(client.getCreationDate());
            stringBuilder.append(", ");
            stringBuilder.append("CLIENT");
            stringBuilder.append(", ");
            stringBuilder.append(client.getCreditScore());
            stringBuilder.append("\n");
        });

        organizers.forEach(org -> {
            stringBuilder.append(org.getUsername());
            stringBuilder.append(", ");
            stringBuilder.append(org.getPassword());
            stringBuilder.append(", ");
            stringBuilder.append(org.getCreationDate());
            stringBuilder.append(", ");
            stringBuilder.append("ORGANIZER");
            stringBuilder.append(", ");
            stringBuilder.append(org.getBalance());
            stringBuilder.append("\n");
        });

        return stringBuilder;
    }

}