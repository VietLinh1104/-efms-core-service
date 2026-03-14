package com.linhdv.efms_core_service.dto.finance.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@Schema(description = "Báo cáo tổng hợp đối chiếu số dư (Ngân hàng vs Hệ thống)")
public class ReconciliationSummaryResponse {

    @Schema(description = "Ngày tính toán số dư", example = "2025-01-31")
    private LocalDate asOfDate;

    @Schema(description = "Số dư hiện tại trên Bank (theo import)")
    private BigDecimal bankBalance;

    @Schema(description = "Số dư trên hệ thống (Sổ cái - GL)")
    private BigDecimal systemBalance;

    @Schema(description = "Tổng tiền các giao dịch chưa bị đối chiếu (Unreconciled) trên Bank")
    private BigDecimal unreconciledBankTransactions;

    @Schema(description = "Tổng tiền các bút toán Hệ thống chưa được đối chiếu (Unreconciled System entries)")
    private BigDecimal unreconciledSystemEntries;

    @Schema(description = "Độ chênh lệch (bankBalance - systemBalance)")
    private BigDecimal difference;
}
