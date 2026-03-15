package sfera.pdponline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sfera.pdponline.entity.LessonContent;

@Repository
public interface LessonContentRepository extends JpaRepository<LessonContent, Long> {
    boolean existsByTitleIgnoreCase(String title);
    boolean existsByTitleIgnoreCaseAndIdNot(String title, Long id);
}