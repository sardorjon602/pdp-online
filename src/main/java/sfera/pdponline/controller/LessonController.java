package sfera.pdponline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.payload.request.RequestLesson;
import sfera.pdponline.service.LessonService;

@RestController
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getAll() {
        ApiResponse response = lessonService.findAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Long id) {
        ApiResponse response = lessonService.findById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(@RequestBody RequestLesson requestLesson) {
        ApiResponse response = lessonService.save(requestLesson);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody RequestLesson requestLesson) {
        ApiResponse response = lessonService.update(id, requestLesson);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        ApiResponse response = lessonService.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}