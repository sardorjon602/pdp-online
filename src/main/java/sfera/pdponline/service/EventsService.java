package sfera.pdponline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sfera.pdponline.entity.Events;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.payload.request.RequestEvent;
import sfera.pdponline.payload.response.ResEvent;
import sfera.pdponline.repository.EventsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventsService {
    private final EventsRepository eventsRepository;

    public ApiResponse findAll() {
        List<Events> all = eventsRepository.findAll();
        List<ResEvent> resEvents = new ArrayList<>();
        for (Events event : all) {
            ResEvent resEvent = ResEvent.builder()
                    .id(event.getId())
                    .title(event.getTitle())
                    .date(event.getDate())
                    .file(event.getFile())
                    .build();
            resEvents.add(resEvent);
        }
        return ApiResponse.builder()
                .message("Success")
                .success(true)
                .status(HttpStatus.OK)
                .body(resEvents)
                .build();
    }

    public ApiResponse findById(Long id) {
        Optional<Events> optional = eventsRepository.findById(id);
        if (optional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Event not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Events event = optional.get();
        ResEvent resEvent = ResEvent.builder()
                .id(event.getId())
                .title(event.getTitle())
                .date(event.getDate())
                .file(event.getFile())
                .build();
        return ApiResponse.builder()
                .message("Success")
                .success(true)
                .status(HttpStatus.OK)
                .body(resEvent)
                .build();
    }

    public ApiResponse save(RequestEvent requestEvent) {
        boolean exists = eventsRepository.existsByTitleIgnoreCase(requestEvent.getTitle());
        if (exists) {
            return ApiResponse.builder()
                    .message("Event already exists")
                    .success(false)
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        Events event = Events.builder()
                .title(requestEvent.getTitle())
                .date(requestEvent.getDate())
                .file(requestEvent.getFile())
                .build();
        eventsRepository.save(event);
        return ApiResponse.builder()
                .message("Event successfully saved")
                .success(true)
                .status(HttpStatus.CREATED)
                .build();
    }

    public ApiResponse update(Long id, RequestEvent requestEvent) {
        Optional<Events> optional = eventsRepository.findById(id);
        if (optional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Event not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        boolean titleExists = eventsRepository.existsByTitleIgnoreCaseAndIdNot(requestEvent.getTitle(), id);
        if (titleExists) {
            return ApiResponse.builder()
                    .message("Event with this title already exists")
                    .success(false)
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        Events toUpdate = optional.get();
        toUpdate.setTitle(requestEvent.getTitle());
        toUpdate.setDate(requestEvent.getDate());
        toUpdate.setFile(requestEvent.getFile());
        eventsRepository.save(toUpdate);

        return ApiResponse.builder()
                .message("Event successfully updated")
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }

    public ApiResponse delete(Long id) {
        Optional<Events> optional = eventsRepository.findById(id);
        if (optional.isEmpty()) {
            return ApiResponse.builder()
                    .message("Event not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        eventsRepository.delete(optional.get());
        return ApiResponse.builder()
                .message("Event successfully deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }
}