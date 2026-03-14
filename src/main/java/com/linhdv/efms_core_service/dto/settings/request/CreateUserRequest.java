package com.linhdv.efms_core_service.dto.settings.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Payload Thêm mới Người dùng")
public class CreateUserRequest {

    @NotBlank
    @Size(max = 255)
    @Schema(description = "Họ và tên", example = "Nguyễn Văn Admin")
    private String name;

    @Email
    @NotBlank
    @Size(max = 255)
    @Schema(description = "Email đăng nhập", example = "admin@efms.vn")
    private String email;

    @NotBlank
    @Size(min = 6, max = 255)
    @Schema(description = "Mật khẩu gốc (Chưa mã hóa)", example = "123456")
    private String password;

    @NotBlank
    @Size(max = 50)
    @Schema(description = "Vai trò (ADMIN, ACCOUNTANT, VIEWER)", example = "ADMIN")
    private String role;

    @NotNull
    @Schema(description = "UUID Công ty (Organization) quản lý user này")
    private UUID companyId;
}
