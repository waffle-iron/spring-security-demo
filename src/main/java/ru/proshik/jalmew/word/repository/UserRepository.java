package ru.proshik.jalmew.word.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.proshik.jalmew.word.model.User;

/**
 * Created by proshik on 06.05.16.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
