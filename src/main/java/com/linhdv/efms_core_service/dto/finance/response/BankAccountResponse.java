package com.linhdv.efms_core_service.dto.finance.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@Schema(description = "Thông tin tài khoản ngân hàng")
public class BankAccountResponse {

    @Schema(description = "ID Tài khoản")
    private UUID id;

    @Schema(description = "Tên nhận diện", example = "Techcombank VND")
    private String name;

    @Schema(description = "Tên ngân hàng")
    private String bankName;

    @Schema(description = "Số tài khoản")
    private String accountNumber;

    @Schema(description = "Loại (checking / savings)")
    private String type;

    @Schema(description = "Mã tiền tệ", example = "VND")
    private String currencyCode;

    @Schema(description = "Số dư đầu kỳ")
    private BigDecimal openingBalance;

    @Schema(description = "Trạng thái hoạt động")
    private Boolean isActive;

    @Schema(description = "ID Tài khoản Kế toán")
    private UUID glAccountId;

    @Schema(description = "Mã Tài khoản Kế toán")
    private String glAccountCode;

    @Schema(description = "Thời gian tạo")
    private Instant createdAt;
}
