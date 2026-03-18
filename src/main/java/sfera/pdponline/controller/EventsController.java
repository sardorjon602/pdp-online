package sfera.pdponline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.payload.request.RequestEvent;
import sfera.pdponline.service.EventsService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class EventsController {
    private final EventsService eventsService;

    @GetMapping("events/list")
    public ResponseEntity<ApiResponse> getAll() {
        ApiResponse response = eventsService.findAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("events/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Long id) {
        ApiResponse response = eventsService.findById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("events/save")
    public ResponseEntity<ApiResponse> save(@RequestBody RequestEvent requestEvent) {
        ApiResponse response = eventsService.save(requestEvent);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("events/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody RequestEvent requestEvent) {
        ApiResponse response = eventsService.update(id, requestEvent);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("events/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        ApiResponse response = eventsService.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}