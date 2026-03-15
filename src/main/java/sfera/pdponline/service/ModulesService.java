package sfera.pdponline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sfera.pdponline.entity.Courses;
import sfera.pdponline.entity.Modules;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.payload.request.RequestModule;
import sfera.pdponline.payload.response.ResModule;
import sfera.pdponline.repository.CoursesRepository;
import sfera.pdponline.repository.ModulesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModulesService {
    private final ModulesRepository modulesRepository;
    private final CoursesRepository coursesRepository;

    public ApiResponse findAll() {
        List<Modules> all = modulesRepository.findAll();
        List<ResModule> resModules = new ArrayList<>();
        for (Modules module : all) {
            ResModule resModule = ResModule.builder()
                    .id(module.getId())
                    .title(module.getTitle())
                    .courseName(module.getCourses().getTitle())
                    .build();
            resModules.add(resModule);
        }
        return ApiResponse.builder()
                .message("Success")
                .success(true)
                .status(HttpStatus.OK)
                .body(resModules)
                .build();
    }

    public ApiResponse findById(Long id) {
        Optional<Modules> optional = modulesRepository.findById(id);
        if (optional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Module not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Modules module = optional.get();
        ResModule resModule = ResModule.builder()
                .id(module.getId())
                .title(module.getTitle())
                .courseName(module.getCourses().getTitle())
                .build();
        return ApiResponse.builder()
                .message("Success")
                .success(true)
                .status(HttpStatus.OK)
                .body(resModule)
                .build();
    }

    public ApiResponse save(RequestModule requestModule) {
        boolean exists = modulesRepository.existsByTitleIgnoreCase(requestModule.getTitle());
        if (exists) {
            return ApiResponse.builder()
                    .message("Module already exists")
                    .success(false)
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        Optional<Courses> courseOptional = coursesRepository.findById(requestModule.getCourseId());
        if (courseOptional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Course not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Modules module = Modules.builder()
                .title(requestModule.getTitle())
                .courses(courseOptional.get())
                .build();
        modulesRepository.save(module);
        return ApiResponse.builder()
                .message("Module successfully saved")
                .success(true)
                .status(HttpStatus.CREATED)
                .build();
    }

    public ApiResponse update(Long id, RequestModule requestModule) {
        Optional<Modules> optional = modulesRepository.findById(id);
        if (optional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Module not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        boolean titleExists = modulesRepository.existsByTitleIgnoreCaseAndIdNot(requestModule.getTitle(), id);
        if (titleExists) {
            return ApiResponse.builder()
                    .message("Module with this title already exists")
                    .success(false)
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        Optional<Courses> courseOptional = coursesRepository.findById(requestModule.getCourseId());
        if (courseOptional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Course not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Modules toUpdate = optional.get();
        toUpdate.setTitle(requestModule.getTitle());
        toUpdate.setCourses(courseOptional.get());
        modulesRepository.save(toUpdate);

        return ApiResponse.builder()
                .message("Module successfully updated")
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }

    public ApiResponse delete(Long id) {
        Optional<Modules> optional = modulesRepository.findById(id);
        if (optional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Module not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        modulesRepository.delete(optional.get());
        return ApiResponse.builder()
                .message("Module successfully deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }
}