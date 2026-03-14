package com.linhdv.efms_core_service.service.settings;

import com.linhdv.efms_core_service.dto.settings.request.UpdateCompanyRequest;
import com.linhdv.efms_core_service.dto.settings.response.CompanyResponse;
import com.linhdv.efms_core_service.entity.Company;
import com.linhdv.efms_core_service.repository.settings.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    public CompanyResponse getCompany(UUID id) {
        return toResponse(findOrThrow(id));
    }

    @Transactional
    public CompanyResponse updateCompany(UUID id, UpdateCompanyRequest req) {
        Company company = findOrThrow(id);
        
        company.setName(req.getName());
        company.setTaxCode(req.getTaxCode());
        company.setAddress(req.getAddress());
        company.setCurrency(req.getCurrency());
        
        return toResponse(companyRepository.save(company));
    }

    private Company findOrThrow(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy thông tin công ty: " + id));
    }

    private CompanyResponse toResponse(Company c) {
        return CompanyResponse.builder()
                .id(c.getId())
                .name(c.getName())
                .taxCode(c.getTaxCode())
                .address(c.getAddress())
                .currency(c.getCurrency())
                .isActive(c.getIsActive())
                .createdAt(c.getCreatedAt())
                .build();
    }
}
