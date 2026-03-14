package com.linhdv.efms_core_service.controller.settings;

import com.linhdv.efms_core_service.dto.common.ApiResponse;
import com.linhdv.efms_core_service.dto.settings.request.CreateUserRequest;
import com.linhdv.efms_core_service.dto.settings.request.UpdateUserRequest;
import com.linhdv.efms_core_service.dto.settings.response.UserResponse;
import com.linhdv.efms_core_service.service.settings.UserService;
import com.linhdv.efms_core_service.wrapper.PagedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/settings/users")
@RequiredArgsConstructor
@Tag(name = "Settings: Users", description = "Quản lý Người dùng hệ thống")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Danh sách người dùng (phân trang)")
    public ResponseEntity<ApiResponse<PagedResponse<UserResponse>>> listUsers(
            @RequestParam UUID companyId,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(ApiResponse.success(userService.searchUsers(companyId, search, page, size)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Chi tiết người dùng")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(userService.getUserById(id)));
    }

    @PostMapping
    @Operation(summary = "Tạo người dùng mới")
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody CreateUserRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo người dùng thành công", userService.createUser(req)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Cập nhật thông tin người dùng")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @PathVariable UUID id, @Valid @RequestBody UpdateUserRequest req) {
        return ResponseEntity.ok(ApiResponse.success("Cập nhật thành công", userService.updateUser(id, req)));
    }

    @PatchMapping("/{id}/toggle-active")
    @Operation(summary = "Bật/tắt trạng thái hoạt động của tài khoản")
    public ResponseEntity<ApiResponse<UserResponse>> toggleActive(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(userService.toggleActive(id)));
    }

    @PatchMapping("/{id}/reset-password")
    @Operation(summary = "Đặt lại mật khẩu người dùng")
    public ResponseEntity<ApiResponse<Void>> resetPassword(
            @PathVariable UUID id, @RequestParam String newPassword) {
        userService.resetPassword(id, newPassword);
        return ResponseEntity.ok(ApiResponse.success("Đặt lại mật khẩu thành công"));
    }
}
