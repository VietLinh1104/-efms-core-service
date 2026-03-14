package com.linhdv.efms_core_service.dto.settings.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@Schema(description = "Thông tin Công ty")
public class CompanyResponse {

    @Schema(description = "Mã Công ty (Tenant ID)")
    private UUID id;

    @Schema(description = "Tên Công ty", example = "Công ty Cổ phần EFMS")
    private String name;

    @Schema(description = "Mã số thuế")
    private String taxCode;

    @Schema(description = "Địa chỉ trụ sở")
    private String address;

    @Schema(description = "Đồng tiền sử dụng mặc định")
    private String currency;

    @Schema(description = "Đang hoạt động")
    private Boolean isActive;

    @Schema(description = "Ngày tạo")
    private Instant createdAt;
}
