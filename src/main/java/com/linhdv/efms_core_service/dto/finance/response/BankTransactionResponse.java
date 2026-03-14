package com.linhdv.efms_core_service.dto.finance.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@Schema(description = "Thông tin giao dịch ngân hàng")
public class BankTransactionResponse {

    @Schema(description = "ID giao dịch")
    private UUID id;

    @Schema(description = "ID tài khoản ngân hàng")
    private UUID bankAccountId;

    @Schema(description = "Tên tài khoản (ví dụ: Techcombank VND)")
    private String bankAccountName;

    @Schema(description = "Ngày giao dịch")
    private LocalDate transactionDate;

    @Schema(description = "Nội dung giao dịch")
    private String description;

    @Schema(description = "Loại (in / out)")
    private String type;

    @Schema(description = "Số tiền")
    private BigDecimal amount;

    @Schema(description = "Mã tham chiếu NH")
    private String reference;

    @Schema(description = "Trạng thái đối chiếu (Đã khớp hay chưa)")
    private Boolean isReconciled;

    @Schema(description = "ID bút toán Kế toán (Journal Entry) liên kết nếu đã khớp")
    private UUID journalEntryId;

    @Schema(description = "Ngày nạp dữ liệu")
    private Instant createdAt;
}
