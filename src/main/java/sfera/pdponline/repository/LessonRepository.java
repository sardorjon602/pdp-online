package sfera.pdponline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sfera.pdponline.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    boolean existsByTitleIgnoreCase(String title);
    boolean existsByTitleIgnoreCaseAndIdNot(String title, Long id);
}