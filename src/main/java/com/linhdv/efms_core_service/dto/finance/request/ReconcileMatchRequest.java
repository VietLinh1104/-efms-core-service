package com.linhdv.efms_core_service.dto.finance.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Payload đối chiếu: Khớp thủ công 1 giao dịch ngân hàng vào 1 chứng từ Hệ thống (Journal Entry)")
public class ReconcileMatchRequest {

    @NotNull
    @Schema(description = "UUID của Giao dịch Ngân hàng chưa đối chiếu")
    private UUID bankTransactionId;

    @NotNull
    @Schema(description = "UUID của Bút toán trên Hệ thống (Journal Entry) cần khớp")
    private UUID journalEntryId;
}
