package org.example.service;

import org.example.accessor.UserAccessor;
import org.example.accessor.models.UserDTO;
import org.example.accessor.models.UserRole;
import org.example.accessor.models.UserState;
import org.example.exceptions.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserService {

    @Autowired
    private UserAccessor userAccessor;

    public void addNewUser(final String email, final String name, final String password, final String phone) {
        if (phone.length() != 10) {
            throw new InvalidDataException("Phone no " + phone + " is invalid!");
        }
        if (password.length() < 6) {
            throw new InvalidDataException("Password is too simple!");
        }
        if (name.length() < 5) {
            throw new InvalidDataException("Name is not correct!");
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (!pat.matcher(email).matches()) {
            throw new InvalidDataException("Email is not correct!");
        }

        UserDTO userDTO = userAccessor.getUserByEmail(email);
        if (userDTO != null) {
            throw new InvalidDataException("User with given email already exists!");
        }
        userDTO = userAccessor.getUserByphone(phone);
        if (userDTO != null) {
            throw new InvalidDataException("User with given phone already exists!");
        }

        userAccessor.addNewUser(email, name, password, phone, UserState.ACTIVE, UserRole.ROLE_USER);
    }
}
