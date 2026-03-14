package com.linhdv.efms_core_service.dto.report.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Schema(description = "Báo cáo Lưu chuyển tiền tệ (Cash Flow Statement)")
public class CashFlowResponse {

    @Schema(description = "Tên công ty")
    private String companyName;

    @Schema(description = "Từ ngày")
    private LocalDate fromDate;

    @Schema(description = "Đến ngày")
    private LocalDate toDate;

    @Schema(description = "Tiền thuần từ hoạt động kinh doanh (Operating Activities)")
    private List<ReportRowResponse> operatingActivities;

    @Schema(description = "Tiền thuần từ hoạt động đầu tư (Investing Activities)")
    private List<ReportRowResponse> investingActivities;

    @Schema(description = "Tiền thuần từ hoạt động tài chính (Financing Activities)")
    private List<ReportRowResponse> financingActivities;

    @Schema(description = "Tổng lưu chuyển tiền thuần trong kỳ")
    private ReportRowResponse netCashFlow;

    @Schema(description = "Tiền và tương đương đầu kỳ")
    private ReportRowResponse openingCash;

    @Schema(description = "Tiền và tương đương cuối kỳ")
    private ReportRowResponse closingCash;
}
