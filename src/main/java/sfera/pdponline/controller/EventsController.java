package sfera.pdponline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.payload.request.RequestEvent;
import sfera.pdponline.service.EventsService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventsController {
    private final EventsService eventsService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getAll() {
        ApiResponse response = eventsService.findAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Long id) {
        ApiResponse response = eventsService.findById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(@RequestBody RequestEvent requestEvent) {
        ApiResponse response = eventsService.save(requestEvent);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody RequestEvent requestEvent) {
        ApiResponse response = eventsService.update(id, requestEvent);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        ApiResponse response = eventsService.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}