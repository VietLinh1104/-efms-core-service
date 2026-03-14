package com.linhdv.efms_core_service.dto.settings.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@Schema(description = "Thông tin Người dùng")
public class UserResponse {

    @Schema(description = "ID Người dùng")
    private UUID id;

    @Schema(description = "Họ và tên")
    private String name;

    @Schema(description = "Email")
    private String email;

    @Schema(description = "Vai trò")
    private String role;

    @Schema(description = "Trạng thái hoạt động")
    private Boolean isActive;

    @Schema(description = "ID Công ty sở hữu")
    private UUID companyId;

    @Schema(description = "Tên Công ty")
    private String companyName;

    @Schema(description = "Ngày tạo")
    private Instant createdAt;
}
