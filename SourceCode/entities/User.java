package entities;

import java.math.BigDecimal;

public class User {
    private String username;
    private String email;
    private String password;
     private String role;// Admin - Employee - Customer

    public User(String username, String email, String password,String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return username + "," + email + "," + password + "," + role;
    }


    public static User fromString(String userString) {
        String[] parts = userString.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid user string: " + userString);
        }

        String username = parts[0];
        String email = parts[1];
        String password = parts[2];
        String role = parts[3];
        return new User(username,email,password,role);
    }



}
