package com.linhdv.efms_core_service.repository.accounting;

import com.linhdv.efms_core_service.entity.FiscalPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FiscalPeriodRepository extends JpaRepository<FiscalPeriod, UUID> {

    List<FiscalPeriod> findByCompanyIdOrderByStartDateDesc(UUID companyId);

    /** Tìm kỳ kế toán đang mở chứa ngày được truyền vào */
    Optional<FiscalPeriod> findFirstByCompanyIdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            UUID companyId, String status, LocalDate date1, LocalDate date2);
}
