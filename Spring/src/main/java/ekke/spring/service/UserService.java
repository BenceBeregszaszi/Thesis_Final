package ekke.spring.service;

import ekke.spring.common.CrudServices;
import ekke.spring.dto.UserDto;

import java.util.List;

public class UserService implements CrudServices<UserDto> {

    @Override
    public UserDto add(UserDto dto) {
        return null;
    }

    @Override
    public List<UserDto> getAll() {
        return null;
    }

    @Override
    public UserDto update(Long id, UserDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
