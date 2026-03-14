package com.linhdv.efms_core_service.dto.report.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Một dòng dữ liệu trong báo cáo tài chính (hỗ trợ phân cấp cây)")
public class ReportRowResponse {

    @Schema(description = "Mã/Tên mục báo cáo (vd: 111, Tiền mặt)", example = "Tiền mặt")
    private String name;

    @Schema(description = "Giá trị số tiền", example = "10000000.00")
    private BigDecimal amount;

    @Schema(description = "Mức độ thò thụt (Cấp độ cha con) để in lề", example = "1")
    private int level;

    @Schema(description = "Đánh dấu nếu đây là dòng tổng cộng", example = "false")
    private boolean isTotal;

    @Schema(description = "Danh sách dòng con (nếu có)")
    private List<ReportRowResponse> children;
}
