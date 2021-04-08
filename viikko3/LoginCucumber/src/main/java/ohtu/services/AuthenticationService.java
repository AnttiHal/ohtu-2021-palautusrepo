package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;  
import ohtu.data_access.UserDao;

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

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        
        System.out.println("username: "+username + "password:" + password );
        if (Pattern.matches("^[a-zA-Z]{3,}$", username)&&Pattern.matches("^([a-z]+[0-9]|[0-9]+[a-z])[a-z0-9]*{8,}$", password)) {
            
            return false;           
        }         
        else {
            return true;
        }        
    }
}
