package com.linhdv.efms_core_service.dto.settings.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Payload cập nhật thông tin Người dùng")
public class UpdateUserRequest {

    @NotBlank
    @Size(max = 255)
    @Schema(description = "Họ và tên", example = "Nguyễn Văn Admin Updated")
    private String name;

    @Email
    @NotBlank
    @Size(max = 255)
    @Schema(description = "Email liên hệ", example = "admin_new@efms.vn")
    private String email;

    @NotBlank
    @Size(max = 50)
    @Schema(description = "Vai trò (ADMIN, ACCOUNTANT, VIEWER)", example = "ACCOUNTANT")
    private String role;
}
