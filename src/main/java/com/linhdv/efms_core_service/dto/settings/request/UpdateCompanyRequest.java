package com.linhdv.efms_core_service.dto.settings.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Payload cập nhật thông tin công ty")
public class UpdateCompanyRequest {

    @NotBlank
    @Size(max = 255)
    @Schema(description = "Tên công ty", example = "Công ty Cổ phần EFMS")
    private String name;

    @Size(max = 50)
    @Schema(description = "Mã số thuế", example = "0101234567")
    private String taxCode;

    @Schema(description = "Địa chỉ trụ sở chính")
    private String address;

    @NotBlank
    @Size(max = 3)
    @Schema(description = "Tiền tệ hạch toán gốc (ví dụ: VND)", example = "VND")
    private String currency;
}
