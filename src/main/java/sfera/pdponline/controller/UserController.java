package sfera.pdponline.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sfera.pdponline.entity.Users;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.payload.request.AuthRegister;
import sfera.pdponline.security.CurrentUser;
import sfera.pdponline.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;


    @GetMapping("/me")
    public ResponseEntity<ApiResponse> getMe(@CurrentUser Users user){
        ApiResponse me = userService.getMe(user);
        return ResponseEntity.ok(me);
    }


    @PostMapping("/teacher-save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "This API is for Admin only. ",
        description = "Teacher data is stored through this API")
    public ResponseEntity<ApiResponse> saveTeacher(@RequestBody AuthRegister authRegister){
        ApiResponse teacher = userService.saveTeacher(authRegister);
        return ResponseEntity.ok(teacher);

    }

}
