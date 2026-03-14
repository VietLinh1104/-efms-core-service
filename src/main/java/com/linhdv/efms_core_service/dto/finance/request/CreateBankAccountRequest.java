package com.linhdv.efms_core_service.dto.finance.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Schema(description = "Payload tạo/cập nhật tài khoản ngân hàng")
public class CreateBankAccountRequest {

    @NotBlank
    @Size(max = 255)
    @Schema(description = "Tên nhận diện tài khoản", example = "Techcombank VND")
    private String name;

    @Size(max = 255)
    @Schema(description = "Tên ngân hàng", example = "Techcombank")
    private String bankName;

    @Size(max = 100)
    @Schema(description = "Số tài khoản", example = "1903123456789")
    private String accountNumber;

    @NotBlank
    @Size(max = 20)
    @Schema(description = "Loại tài khoản (checking / savings)", example = "checking")
    private String type;

    @NotBlank
    @Size(max = 3)
    @Schema(description = "Mã tiền tệ", example = "VND")
    private String currencyCode;

    @Schema(description = "Số dư đầu kỳ", example = "0.00")
    private BigDecimal openingBalance = BigDecimal.ZERO;

    @Schema(description = "UUID Tài khoản Kế toán tương ứng (1121..)")
    private UUID glAccountId;

    @NotNull
    @Schema(description = "UUID Công ty")
    private UUID companyId;
}
