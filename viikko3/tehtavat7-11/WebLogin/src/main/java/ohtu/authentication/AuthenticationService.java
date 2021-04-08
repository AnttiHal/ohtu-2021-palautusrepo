package ohtu.authentication;

import java.util.regex.Pattern;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();
        
        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }

        if (username.length()<3 ) {
            status.addError("username should have at least 3 characters");
        }

        if (password.length()<8 ) {
            status.addError("password should have at least 8 characters");
        }

        if (!isValidUsername(username.toCharArray())) {
            status.addError("username should consist only letters a-z.");
        }

        if (!isValidPassword(password.toCharArray())) {
            status.addError("password should consist both letters and numbers.");
        }

        
        if (!password.equals(passwordConfirmation)) {
            status.addError("password and password confirmation do not match");
        }

        

        if (status.isOk()) {
            userDao.add(new User(username, password));
        }
        
        return status;
    }

    private static boolean isValidUsername(final char[] u) {
        boolean flag = true;
        for (char c : u) {
            if (!Character.isLetter(c)) {
                flag = false;
                break;            
            }
        }
        System.out.print(flag);
        return flag;
    }

    private static boolean isValidPassword(final char[] u) {
        
        int letters=0;
        int numbers=0;
        for (char c : u) {
            if (Character.isLetter(c)) {
                letters++;                                      
            }
            if (Character.isDigit(c)) {
                numbers++;                           
            }
            
        }
        if (numbers==0||letters==0) {
            return false;
        } else {
            return true;
        
        }
    }

}
