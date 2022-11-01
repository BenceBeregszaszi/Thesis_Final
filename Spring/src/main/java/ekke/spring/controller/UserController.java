package ekke.spring.controller;

import ekke.spring.dto.ForgetPasswordDto;
import ekke.spring.dto.UserDto;
import ekke.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController extends ControllerBase{

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDto>> readUser(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<UserDto> readUserById(@PathVariable final Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<UserDto> updateUser(@PathVariable final Long id, @RequestBody final UserDto userDto) {
        return ResponseEntity.ok(userService.update(id, userDto));
    }


    @PutMapping("/users/forget-password/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Void> forgetPassword(@PathVariable final Long id, @RequestBody final ForgetPasswordDto forgetPasswordDto) {
        userService.forgetPassword(id, forgetPasswordDto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Void> deleteUser(@PathVariable final Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
