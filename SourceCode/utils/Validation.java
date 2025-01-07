package utils;

import entities.User;

import java.util.regex.Pattern;

public class Validation {
    //Check hop le cua email
    public static boolean isValidEmail(String email){
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex,email);
    }
    //Ham kiem tra mat khau hop le
    public static boolean isValidPassword(String password){
        return password.length() >= 7 && password.length() <= 15 && password.matches(".*[A-Z].*") &&
                password.matches(".*[.,\\-_;].*");
    }
    //Check password, password cần chứa 7 ký tự đến 15 ký tự
    //password chứa ít nhất 1 ký tự in hoa, 1 ký tự đặc biệt (. , - _ ;)

    //Kiem tra so dien thoai hop le
    public static boolean isValidPhoneNumber(String phone) {
        String regex = "^0\\d{8}[1-9]$";
        return Pattern.matches(regex,phone);
    }
}
