package sfera.pdponline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.payload.request.RequestLessonContent;
import sfera.pdponline.service.LessonContentService;

@RestController
@RequestMapping("/lesson-content")
@RequiredArgsConstructor
public class LessonContentController {
    private final LessonContentService lessonContentService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getAll() {
        ApiResponse response = lessonContentService.findAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Long id) {
        ApiResponse response = lessonContentService.findById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(@RequestBody RequestLessonContent request) {
        ApiResponse response = lessonContentService.save(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody RequestLessonContent request) {
        ApiResponse response = lessonContentService.update(id, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        ApiResponse response = lessonContentService.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}