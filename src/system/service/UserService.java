package system.service;

import system.model.User;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static system.view.Login.isValidPassword;

public class UserService {
    public String verifyLogin(String userUsername, String userPass)
    {
        //Checks if the password format is valid before validating the credential
        if (isValidPassword(userPass)){
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(Files.newInputStream(Paths.get(User.getDataUser())));
                Object obj = null;
                while ((obj = ois.readObject()) != null)
                {
                    if (((User) obj).getUsername().equals(userUsername) &&
                            ((User) obj).getPassword().equals(userPass))
                    {
                        return ((User) obj).getUsername();
                    }
                }
            }
            catch (EOFException ex) {System.out.println("client closed");} catch (ClassNotFoundException |
                                                                                  IOException ex) { ex.printStackTrace(); } finally {
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException ex) { ex.printStackTrace(); }
            }
        }
        return "Invalid";
    }

    public int getUserCount()
    {
        ObjectInputStream ois = null;
        int count = 0;
        try {
            ois = new ObjectInputStream(Files.newInputStream(Paths.get(User.getDataUser())));
            Object obj = null;
            while ((obj = ois.readObject()) != null)
            {
                if (!((User) obj).getUsername().equals("ADMIN"))
                {
                    count++;
                }
            }
        }
        catch (EOFException ex) {System.out.println("client closed");} catch (ClassNotFoundException | IOException ex) { ex.printStackTrace(); } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) { ex.printStackTrace(); }
            return count;
        }
    }
}
