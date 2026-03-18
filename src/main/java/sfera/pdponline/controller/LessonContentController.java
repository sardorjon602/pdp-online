package sfera.pdponline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.payload.request.RequestLessonContent;
import sfera.pdponline.service.LessonContentService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LessonContentController {
    private final LessonContentService lessonContentService;

    @GetMapping("lesson-content/list")
    public ResponseEntity<ApiResponse> getAll() {
        ApiResponse response = lessonContentService.findAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("lesson-content/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Long id) {
        ApiResponse response = lessonContentService.findById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("lesson-content/save")
    public ResponseEntity<ApiResponse> save(@RequestBody RequestLessonContent request) {
        ApiResponse response = lessonContentService.save(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("lesson-content/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody RequestLessonContent request) {
        ApiResponse response = lessonContentService.update(id, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("lesson-content/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        ApiResponse response = lessonContentService.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}