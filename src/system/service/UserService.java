package system.service;

import system.common.fileIO.ReadNWrite;
import system.common.validation.Result;
import system.common.validation.Validation;
import system.model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserService implements ReadNWrite<Object> {
    public String verifyLogin(String userUsername, String userPass)
    {
        Result<String, Exception> validationResult = Validation.validateCredentials(userUsername, userPass);
        return validationResult.fold(
                // on success
                success -> {
                    Object foundUser = readFile().stream()
                            .filter(userObj -> ((User) userObj).getUsername().equals(userUsername))
                            .filter(userObj -> ((User) userObj).getPassword().equals(userPass))
                            .findFirst().orElse(new User("Invalid"));
                    return ((User)foundUser).getUsername();
                },
                // on failure
                error -> "Invalid"
        );
    }

    public int getUserCount()
    {
        return Math.toIntExact(
                readFile().stream()
                        .filter(user -> !((User) user).getUsername().equals("ADMIN"))
                        .count()
        );
    }

    @Override
    public void update(List<Object> arrayList) {

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
