package ekke.spring.controller;

import ekke.spring.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController extends ControllerBase{

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody final UserDto userDto){
        return null;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> readUser(){
        return null;
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable final Long id, @RequestBody final UserDto userDto){
        return null;
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final Long id){
        return null;
    }
}
