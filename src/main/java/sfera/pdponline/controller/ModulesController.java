package sfera.pdponline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.payload.request.RequestModule;
import sfera.pdponline.service.ModulesService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class ModulesController {
    private final ModulesService modulesService;

    @GetMapping("module/list")
    public ResponseEntity<ApiResponse> getAll() {
        ApiResponse response = modulesService.findAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("module/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Long id) {
        ApiResponse response = modulesService.findById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("module/save")
    public ResponseEntity<ApiResponse> save(@RequestBody RequestModule requestModule) {
        ApiResponse response = modulesService.save(requestModule);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("module/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody RequestModule requestModule) {
        ApiResponse response = modulesService.update(id, requestModule);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("module/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        ApiResponse response = modulesService.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}