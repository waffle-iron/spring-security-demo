package ru.proshik.jalmew.word.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.proshik.jalmew.word.model.Users;
import ru.proshik.jalmew.word.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * Created by proshik on 22.05.16.
 */
@Service
public class UserService {

    private Users user;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        Optional<Users> user = userRepository.findByLogin("user");

        if (!user.isPresent()) {
            this.user = userRepository.save(new Users("user", "password", Boolean.TRUE, "email"));
        } else {
            this.user = user.get();
        }
    }

    public Users getUser() {
        return user;
    }
}
