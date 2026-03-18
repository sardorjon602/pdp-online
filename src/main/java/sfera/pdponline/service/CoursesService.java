package sfera.pdponline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sfera.pdponline.entity.Courses;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.payload.request.RequestCourse;
import sfera.pdponline.payload.response.ResCourse;
import sfera.pdponline.repository.CoursesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoursesService {
    private final CoursesRepository coursesRepository;

    public ApiResponse findAll() {
        List<Courses> all = coursesRepository.findAll();
        List<ResCourse> resCourses = new ArrayList<>();
        for (Courses course : all) {
            ResCourse resCourse = ResCourse.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .price(course.getPrice())
                    .promoCod(course.getPromoCod())
                    .discountPrice(course.getDiscountPrice())
                    .level(course.getLevel())
                    .active(course.isActive())
                    .description(course.getDescription())
                    .build();
            resCourses.add(resCourse);
        }
        return ApiResponse.builder()
                .message("Success")
                .success(true)
                .status(HttpStatus.OK)
                .body(resCourses)
                .build();
    }

    public ApiResponse findById(Long id) {
        Optional<Courses> optional = coursesRepository.findById(id);
        if (optional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Course not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Courses course = optional.get();
        ResCourse resCourse = ResCourse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .price(course.getPrice())
                .promoCod(course.getPromoCod())
                .discountPrice(course.getDiscountPrice())
                .level(course.getLevel())
                .active(course.isActive())
                .description(course.getDescription())
                .build();
        return ApiResponse.builder()
                .message("Success")
                .success(true)
                .status(HttpStatus.OK)
                .body(resCourse)
                .build();
    }

    public ApiResponse save(RequestCourse requestCourse) {
        boolean exists = coursesRepository.existsByTitleIgnoreCase(requestCourse.getTitle());
        if (exists) {
            return ApiResponse.builder()
                    .message("Course already exists")
                    .success(false)
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
        Courses course = Courses.builder()
                .title(requestCourse.getTitle())
                .price(requestCourse.getPrice())
                .promoCod(requestCourse.getPromoCod())
                .discountPrice(requestCourse.getDiscountPrice())
                .level(requestCourse.getLevel())
                .active(requestCourse.isActive())
                .description(requestCourse.getDescription())
                .build();
        coursesRepository.save(course);
        return ApiResponse.builder()
                .message("Course successfully saved")
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }

    public ApiResponse update(Long id, RequestCourse requestCourse) {
        Optional<Courses> optional = coursesRepository.findById(id);
        if (optional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Course not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        boolean titleExists = coursesRepository.existsByTitleIgnoreCaseAndIdNot(requestCourse.getTitle(), id);
        if (titleExists) {
            return ApiResponse.builder()
                    .message("Course with this title already exists")
                    .success(false)
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        Courses toUpdate = optional.get();
        toUpdate.setTitle(requestCourse.getTitle());
        toUpdate.setPrice(requestCourse.getPrice());
        toUpdate.setPromoCod(requestCourse.getPromoCod());
        toUpdate.setDiscountPrice(requestCourse.getDiscountPrice());
        toUpdate.setLevel(requestCourse.getLevel());
        toUpdate.setActive(requestCourse.isActive());
        toUpdate.setDescription(requestCourse.getDescription());
        coursesRepository.save(toUpdate);

        return ApiResponse.builder()
                .message("Course successfully updated")
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }

    public ApiResponse delete(Long id) {
        Optional<Courses> optional = coursesRepository.findById(id);
        if (optional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Course not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        coursesRepository.delete(optional.get());
        return ApiResponse.builder()
                .message("Course successfully deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }
}