package system.service;

import system.common.fileIO.ReadNWrite;
import system.model.People;
import system.model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static system.common.fileIO.ThrowingConsumer.throwingConsumerWrapper;

public class PeopleService implements ReadNWrite<Object> {

    public void registerProfile(People newPeople)
    {
        List<Object> userList = readFile();
        newPeople.setPeopleID("PID" + Integer.toString(generateNum(100000, 999999)));
        userList.stream()
                .filter(existingUser -> !((User)existingUser).getUsername().equals("ADMIN"))
                .filter(existingUser -> ((People)existingUser).getPeopleID().equals(newPeople.getPeopleID()))
                .findFirst().ifPresentOrElse(
                        user -> registerProfile(newPeople),
                        () -> {
                            userList.add(newPeople);
                            update(userList);
                        }
                );
    }

    public void modifyProfile(People modifiedPpl)
    {
        List<Object> newUserList = readFile().stream().map(user -> {
            if (!((User) user).getUsername().equals("ADMIN")) {
                if (!((User) user).getUsername().equals(modifiedPpl.getUsername())) {
                    return user;
                }
                return modifiedPpl;
            }
            return user;
        }).collect(Collectors.toList());
        update(newUserList);
    }

    public Optional<People> getPeopleDetails(String userID)
    {
        return readFile().stream()
                .filter(existingUser -> !((User) existingUser).getUsername().equals("ADMIN") && ((People) existingUser).getUsername().equals(userID)).map(user -> (People)user)
                .findFirst();
    }

    public boolean deletePeople(String userID)
    {
        List<Object> newUserList = readFile().stream()
                .filter(existingUser -> ((User)existingUser).getUsername().equals("ADMIN") || !((People)existingUser).getUsername().equals(userID))
                .collect(Collectors.toList());

        update(newUserList);

        return true;
    }

    private static int generateNum(int min, int max){
        Random rand = new Random();
        return min + rand.nextInt((max - min) + 1);
    }

    public boolean checkUserID(String userID)
    {
        Object foundUser = readFile().stream()
                .filter(existingUser -> ((User)existingUser).getUsername().equals(userID))
                .findFirst().orElse(null);
        return foundUser != null;
    }

    public List<People> getAllPeople() {
        return readFile().stream()
                .filter(user -> !((User)user).getUsername().equals("ADMIN"))
                .map(user -> ((People)user))
                .collect(Collectors.toList());
    }

    @Override
    public void update(List<Object> arrayList) {
        try(ObjectOutputStream oos =
                    new ObjectOutputStream(Files.newOutputStream(Paths.get(User.getDataUser())));) {
            arrayList.forEach(throwingConsumerWrapper(oos::writeObject));
            oos.flush();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public List<Object> readFile() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(Files.newInputStream(Paths.get(User.getDataUser())))) {
            List<Object> pplList = new ArrayList<>();
            readFileHelper(ois, pplList);
            return pplList;
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
