package ru.proshik.jalmew.word.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.proshik.jalmew.word.model.Theme;

/**
 * Created by proshik on 06.05.16.
 */
@Repository
public interface ThemeRepository extends CrudRepository<Theme, Long> {

}
