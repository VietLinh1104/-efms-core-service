package com.linhdv.efms_core_service.service.finance;

import com.linhdv.efms_core_service.dto.finance.request.ReconcileMatchRequest;
import com.linhdv.efms_core_service.dto.finance.response.BankTransactionResponse;
import com.linhdv.efms_core_service.dto.finance.response.ReconciliationSummaryResponse;
import com.linhdv.efms_core_service.entity.BankTransaction;
import com.linhdv.efms_core_service.entity.JournalEntry;
import com.linhdv.efms_core_service.repository.accounting.JournalEntryRepository;
import com.linhdv.efms_core_service.repository.finance.BankTransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReconciliationService {

    private final BankTransactionRepository bankTransactionRepository;
    private final JournalEntryRepository journalEntryRepository;
    private final BankTransactionService bankTransactionService;

    @Transactional(readOnly = true)
    public List<BankTransactionResponse> getUnreconciledTransactions(UUID accountId) {
        return bankTransactionRepository.findByBankAccount_IdAndIsReconciledFalseOrderByTransactionDateAsc(accountId)
                .stream()
                .map(bankTransactionService::toResponse)
                .toList();
    }

    @Transactional
    public BankTransactionResponse match(ReconcileMatchRequest req) {
        BankTransaction bt = bankTransactionRepository.findById(req.getBankTransactionId())
                .orElseThrow(() -> new EntityNotFoundException("Bank transaction not found"));
        JournalEntry je = journalEntryRepository.findById(req.getJournalEntryId())
                .orElseThrow(() -> new EntityNotFoundException("Journal entry not found"));

        if (!"posted".equals(je.getStatus())) {
            throw new IllegalStateException("Journal entry phải ở trạng thái posted mới có thể đối chiếu");
        }

        bt.setJournalEntry(je);
        bt.setIsReconciled(true);
        return bankTransactionService.toResponse(bankTransactionRepository.save(bt));
    }

    @Transactional
    public BankTransactionResponse unmatch(UUID id) {
        BankTransaction bt = bankTransactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bank transaction not found"));

        bt.setJournalEntry(null);
        bt.setIsReconciled(false);
        return bankTransactionService.toResponse(bankTransactionRepository.save(bt));
    }

    @Transactional
    public List<BankTransactionResponse> autoMatch(UUID accountId) {
        // Tương lai: Logic matching tự động (rule-based) so sánh số tiền, ngày, nội dung.
        // Tạm thời trả về list rỗng
        return List.of();
    }

    @Transactional(readOnly = true)
    public ReconciliationSummaryResponse getSummary(UUID accountId) {
        // Lấy số dư: TODO - dùng AccountBalance or Payment sum
        return ReconciliationSummaryResponse.builder()
                .asOfDate(LocalDate.now())
                .bankBalance(BigDecimal.ZERO)
                .systemBalance(BigDecimal.ZERO)
                .unreconciledBankTransactions(BigDecimal.ZERO)
                .unreconciledSystemEntries(BigDecimal.ZERO)
                .difference(BigDecimal.ZERO)
                .build();
    }
}
