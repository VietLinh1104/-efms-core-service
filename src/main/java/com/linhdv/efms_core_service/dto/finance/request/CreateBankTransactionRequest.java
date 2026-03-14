package com.linhdv.efms_core_service.dto.finance.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Schema(description = "Payload tạo giao dịch ngân hàng (thủ công)")
public class CreateBankTransactionRequest {

    @NotNull
    @Schema(description = "UUID tài khoản ngân hàng")
    private UUID bankAccountId;

    @NotNull
    @Schema(description = "Ngày giao dịch", example = "2025-01-15")
    private LocalDate transactionDate;

    @Schema(description = "Diễn giải / Nội dung CK", example = "KH Nguyen Van A chuyen khoan")
    private String description;

    @NotBlank
    @Size(max = 10)
    @Schema(description = "Loại giao dịch (in / out)", example = "in")
    private String type;

    @NotNull
    @DecimalMin("0.01")
    @Schema(description = "Số tiền giao dịch", example = "1000000.00")
    private BigDecimal amount;

    @Size(max = 255)
    @Schema(description = "Tham chiếu (Mã GD NH, Reference ID)", example = "FT25015000012345")
    private String reference;
}
