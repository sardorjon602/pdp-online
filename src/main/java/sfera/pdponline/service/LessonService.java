package sfera.pdponline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sfera.pdponline.entity.Lesson;
import sfera.pdponline.entity.Modules;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.payload.request.RequestLesson;
import sfera.pdponline.payload.response.ResLesson;
import sfera.pdponline.repository.LessonRepository;
import sfera.pdponline.repository.ModulesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ModulesRepository modulesRepository;

    public ApiResponse findAll() {
        List<Lesson> all = lessonRepository.findAll();
        List<ResLesson> resLessons = new ArrayList<>();
        for (Lesson lesson : all) {
            ResLesson resLesson = ResLesson.builder()
                    .id(lesson.getId())
                    .title(lesson.getTitle())
                    .moduleName(lesson.getModule().getTitle())
                    .free(lesson.isFree())
                    .durationHour(lesson.getDurationHour())
                    .description(lesson.getDescription())
                    .build();
            resLessons.add(resLesson);
        }
        return ApiResponse.builder()
                .message("Success")
                .success(true)
                .status(HttpStatus.OK)
                .body(resLessons)
                .build();
    }

    public ApiResponse findById(Long id) {
        Optional<Lesson> optional = lessonRepository.findById(id);
        if (optional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Lesson not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Lesson lesson = optional.get();
        ResLesson resLesson = ResLesson.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .moduleName(lesson.getModule().getTitle())
                .free(lesson.isFree())
                .durationHour(lesson.getDurationHour())
                .description(lesson.getDescription())
                .build();
        return ApiResponse.builder()
                .message("Success")
                .success(true)
                .status(HttpStatus.OK)
                .body(resLesson)
                .build();
    }

    public ApiResponse save(RequestLesson requestLesson) {
        boolean exists = lessonRepository.existsByTitleIgnoreCase(requestLesson.getTitle());
        if (exists) {
            return ApiResponse.builder()
                    .message("Lesson already exists")
                    .success(false)
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        Optional<Modules> moduleOptional = modulesRepository.findById(requestLesson.getModuleId());
        if (moduleOptional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Module not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Lesson lesson = Lesson.builder()
                .title(requestLesson.getTitle())
                .module(moduleOptional.get())
                .free(requestLesson.isFree())
                .durationHour(requestLesson.getDurationHour())
                .description(requestLesson.getDescription())
                .build();
        lessonRepository.save(lesson);

        return ApiResponse.builder()
                .message("Lesson successfully saved")
                .success(true)
                .status(HttpStatus.CREATED)
                .build();
    }

    public ApiResponse update(Long id, RequestLesson requestLesson) {
        Optional<Lesson> optional = lessonRepository.findById(id);
        if (optional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Lesson not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        boolean titleExists = lessonRepository.existsByTitleIgnoreCaseAndIdNot(requestLesson.getTitle(), id);
        if (titleExists) {
            return ApiResponse.builder()
                    .message("Lesson with this title already exists")
                    .success(false)
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        Optional<Modules> moduleOptional = modulesRepository.findById(requestLesson.getModuleId());
        if (moduleOptional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Module not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Lesson toUpdate = optional.get();
        toUpdate.setTitle(requestLesson.getTitle());
        toUpdate.setModule(moduleOptional.get());
        toUpdate.setFree(requestLesson.isFree());
        toUpdate.setDurationHour(requestLesson.getDurationHour());
        toUpdate.setDescription(requestLesson.getDescription());
        lessonRepository.save(toUpdate);

        return ApiResponse.builder()
                .message("Lesson successfully updated")
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }

    public ApiResponse delete(Long id) {
        Optional<Lesson> optional = lessonRepository.findById(id);
        if (optional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Lesson not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        lessonRepository.delete(optional.get());
        return ApiResponse.builder()
                .message("Lesson successfully deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }
}