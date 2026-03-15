package sfera.pdponline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sfera.pdponline.entity.Lesson;
import sfera.pdponline.entity.LessonContent;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.payload.request.RequestLessonContent;
import sfera.pdponline.payload.response.ResLessonContent;
import sfera.pdponline.repository.LessonContentRepository;
import sfera.pdponline.repository.LessonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonContentService {
    private final LessonContentRepository lessonContentRepository;
    private final LessonRepository lessonRepository;

    public ApiResponse findAll() {
        List<LessonContent> all = lessonContentRepository.findAll();
        List<ResLessonContent> resList = new ArrayList<>();
        for (LessonContent lc : all) {
            ResLessonContent res = ResLessonContent.builder()
                    .id(lc.getId())
                    .title(lc.getTitle())
                    .lessonName(lc.getLesson().getTitle())
                    .file(lc.getFile())
                    .lessonCount(lc.getLessonCount())
                    .build();
            resList.add(res);
        }
        return ApiResponse.builder()
                .message("Success")
                .success(true)
                .status(HttpStatus.OK)
                .body(resList)
                .build();
    }

    public ApiResponse findById(Long id) {
        Optional<LessonContent> optional = lessonContentRepository.findById(id);
        if (optional.isEmpty()) {
            return ApiResponse.builder()
                    .message("LessonContent not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        LessonContent lc = optional.get();
        ResLessonContent res = ResLessonContent.builder()
                .id(lc.getId())
                .title(lc.getTitle())
                .lessonName(lc.getLesson().getTitle())
                .file(lc.getFile())
                .lessonCount(lc.getLessonCount())
                .build();
        return ApiResponse.builder()
                .message("Success")
                .success(true)
                .status(HttpStatus.OK)
                .body(res)
                .build();
    }

    public ApiResponse save(RequestLessonContent request) {
        boolean exists = lessonContentRepository.existsByTitleIgnoreCase(request.getTitle());
        if (exists) {
            return ApiResponse.builder()
                    .message("LessonContent already exists")
                    .success(false)
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        Optional<Lesson> lessonOptional = lessonRepository.findById(request.getLessonId());
        if (lessonOptional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Lesson not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        LessonContent lc = LessonContent.builder()
                .title(request.getTitle())
                .lesson(lessonOptional.get())
                .file(request.getFile())
                .lessonCount(request.getLessonCount())
                .build();
        lessonContentRepository.save(lc);
        return ApiResponse.builder()
                .message("LessonContent successfully saved")
                .success(true)
                .status(HttpStatus.CREATED)
                .build();
    }

    public ApiResponse update(Long id, RequestLessonContent request) {
        Optional<LessonContent> optional = lessonContentRepository.findById(id);
        if (optional.isEmpty()) {
            return ApiResponse.builder()
                    .message("LessonContent not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        boolean titleExists = lessonContentRepository.existsByTitleIgnoreCaseAndIdNot(request.getTitle(), id);
        if (titleExists) {
            return ApiResponse.builder()
                    .message("LessonContent with this title already exists")
                    .success(false)
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        Optional<Lesson> lessonOptional = lessonRepository.findById(request.getLessonId());
        if (lessonOptional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Lesson not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        LessonContent toUpdate = optional.get();
        toUpdate.setTitle(request.getTitle());
        toUpdate.setLesson(lessonOptional.get());
        toUpdate.setFile(request.getFile());
        toUpdate.setLessonCount(request.getLessonCount());
        lessonContentRepository.save(toUpdate);

        return ApiResponse.builder()
                .message("LessonContent successfully updated")
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }

    public ApiResponse delete(Long id) {
        Optional<LessonContent> optional = lessonContentRepository.findById(id);
        if (optional.isEmpty()) {
            return ApiResponse.builder()
                    .message("LessonContent not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        lessonContentRepository.delete(optional.get());
        return ApiResponse.builder()
                .message("LessonContent successfully deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }
}