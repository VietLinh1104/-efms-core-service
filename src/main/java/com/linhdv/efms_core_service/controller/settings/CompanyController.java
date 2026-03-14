package com.linhdv.efms_core_service.controller.settings;

import com.linhdv.efms_core_service.dto.common.ApiResponse;
import com.linhdv.efms_core_service.dto.settings.request.UpdateCompanyRequest;
import com.linhdv.efms_core_service.dto.settings.response.CompanyResponse;
import com.linhdv.efms_core_service.service.settings.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/settings/company")
@RequiredArgsConstructor
@Tag(name = "Settings: Company", description = "Quản lý thông tin Công ty")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    @Operation(summary = "Lấy thông tin công ty")
    public ResponseEntity<ApiResponse<CompanyResponse>> getCompany(@RequestParam UUID id) {
        return ResponseEntity.ok(ApiResponse.success(companyService.getCompany(id)));
    }

    @PutMapping
    @Operation(summary = "Cập nhật thông tin công ty")
    public ResponseEntity<ApiResponse<CompanyResponse>> updateCompany(
            @RequestParam UUID id, @Valid @RequestBody UpdateCompanyRequest req) {
        return ResponseEntity.ok(ApiResponse.success("Cập nhật thông tin công ty thành công", companyService.updateCompany(id, req)));
    }
}
