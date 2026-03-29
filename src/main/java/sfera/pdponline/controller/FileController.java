package sfera.pdponline.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.payload.response.ResFile;
import sfera.pdponline.service.FileService;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        ApiResponse apiResponse = fileService.saveFile(file);
        return  ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable Long id) {
        ResFile attachment = fileService.getAttachment(id);
        return ResponseEntity.ok()
                .headers(attachment.getHeaders())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+attachment.getFillName())
                .body(attachment.getResource());
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<CompletableFuture<String>> upload(@RequestParam("file") MultipartFile file) {
        try {
            CompletableFuture<String> fileURL = fileService.uploadFile(file, file.getOriginalFilename());
            return ResponseEntity.ok(fileURL);
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CompletableFuture.completedFuture(e.getMessage()));
        }
    }
}
