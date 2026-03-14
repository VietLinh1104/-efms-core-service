package com.linhdv.efms_core_service.dto.report.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Schema(description = "Báo cáo Bảng cân đối kế toán (Balance Sheet)")
public class BalanceSheetResponse {

    @Schema(description = "Tên công ty")
    private String companyName;

    @Schema(description = "Lập tới ngày")
    private LocalDate asOfDate;

    @Schema(description = "Tài sản (Assets)")
    private List<ReportRowResponse> assets;

    @Schema(description = "Nợ phải trả (Liabilities)")
    private List<ReportRowResponse> liabilities;

    @Schema(description = "Vốn chủ sở hữu (Equity)")
    private List<ReportRowResponse> equity;
}
