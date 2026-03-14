package com.linhdv.efms_core_service.dto.report.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Schema(description = "Bảng Kết quả kinh doanh (Profit & Loss)")
public class ProfitLossResponse {

    @Schema(description = "Tên công ty")
    private String companyName;

    @Schema(description = "Từ ngày")
    private LocalDate fromDate;

    @Schema(description = "Đến ngày")
    private LocalDate toDate;

    @Schema(description = "Doanh thu (Revenues)")
    private List<ReportRowResponse> revenues;

    @Schema(description = "Chi phí (Expenses)")
    private List<ReportRowResponse> expenses;

    @Schema(description = "Lợi nhuận ròng (Net Income)")
    private ReportRowResponse netIncome;
}
