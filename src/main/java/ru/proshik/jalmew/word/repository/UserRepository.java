package ru.proshik.jalmew.word.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.proshik.jalmew.word.model.Users;

import java.util.Optional;

/**
 * Created by proshik on 06.05.16.
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByLogin(String login);
}
