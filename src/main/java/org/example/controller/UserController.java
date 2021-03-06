package org.example.controller;

import org.example.controller.models.CreateUserInput;
import org.example.controller.models.VerifyEmailInput;
import org.example.controller.models.VerifyPhoneInput;
import org.example.exceptions.AccountAlreadyVerifiedException;
import org.example.exceptions.InvalidDataException;
import org.example.security.Roles;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> addNewUser(@RequestBody CreateUserInput createUserInput) {
        String name = createUserInput.getName();
        String email = createUserInput.getEmail();
        String password = createUserInput.getPassword();
        String phone = createUserInput.getPhone();

        try {
            userService.addNewUser(email, name, password, phone);
            return ResponseEntity.status(HttpStatus.OK).body("User created successfully!");
        }
        catch(InvalidDataException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping("/user/subscription")
    @Secured({Roles.User})
    public String activateSubscription(){
        userService.activateSubscription();
        return "Subscription activated successfully!";
    }

    @DeleteMapping("/user/subscription")
    @Secured({Roles.Customer})
    public String deleteSubscription(){
        userService.deleteSubscription();
        return "Subscription deleted successfully!";
    }

    @PostMapping("/user/email")
    @Secured({ Roles.User, Roles.Customer })
    public ResponseEntity<String> verifyEmail(@RequestBody VerifyEmailInput emailInput) {
        try {
            userService.verifyEmail(emailInput.getOtp());
            return ResponseEntity.status(HttpStatus.OK).body("Otp verified successfully");
        }
        catch(InvalidDataException | AccountAlreadyVerifiedException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping("/user/phone")
    @Secured({ Roles.User, Roles.Customer })
    public ResponseEntity<String> verifyPhoneNo(@RequestBody VerifyPhoneInput phoneInput) {
        try {
            userService.verifyPhone(phoneInput.getOtp());
            return ResponseEntity.status(HttpStatus.OK).body("Otp verified successfully");
        }
        catch(InvalidDataException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }


}
