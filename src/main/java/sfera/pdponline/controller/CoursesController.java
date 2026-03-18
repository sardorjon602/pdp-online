package sfera.pdponline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.payload.request.RequestCourse;
import sfera.pdponline.service.CoursesService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class CoursesController {
    private final CoursesService coursesService;

    @GetMapping("courses/list")
    public ResponseEntity<ApiResponse> getAll() {
        ApiResponse response = coursesService.findAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("courses/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Long id) {
        ApiResponse response = coursesService.findById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("courses/save")
    public ResponseEntity<ApiResponse> save(@RequestBody RequestCourse requestCourse) {
        ApiResponse response = coursesService.save(requestCourse);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("courses/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody RequestCourse requestCourse) {
        ApiResponse response = coursesService.update(id, requestCourse);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("courses/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        ApiResponse response = coursesService.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}