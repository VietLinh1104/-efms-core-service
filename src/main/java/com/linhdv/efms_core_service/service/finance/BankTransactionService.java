package com.linhdv.efms_core_service.service.finance;

import com.linhdv.efms_core_service.dto.finance.request.CreateBankTransactionRequest;
import com.linhdv.efms_core_service.dto.finance.response.BankTransactionResponse;
import com.linhdv.efms_core_service.entity.BankAccount;
import com.linhdv.efms_core_service.entity.BankTransaction;
import com.linhdv.efms_core_service.repository.finance.BankTransactionRepository;
import com.linhdv.efms_core_service.wrapper.PagedResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BankTransactionService {

    private final BankTransactionRepository bankTransactionRepository;

    @Transactional(readOnly = true)
    public PagedResponse<BankTransactionResponse> search(UUID companyId, UUID accountId, String type, String status,
                                                         LocalDate fromDate, LocalDate toDate, int page, int size) {
        Page<BankTransaction> data = bankTransactionRepository.search(
                companyId, accountId, type, status, fromDate, toDate, PageRequest.of(page, size));
        List<BankTransactionResponse> content = data.getContent().stream().map(this::toResponse).toList();
        return PagedResponse.of(content, page, size, data.getTotalElements());
    }

    @Transactional(readOnly = true)
    public BankTransactionResponse getById(UUID id) {
        return toResponse(findOrThrow(id));
    }

    @Transactional
    public BankTransactionResponse create(CreateBankTransactionRequest req) {
        BankTransaction bt = new BankTransaction();
        BankAccount ba = new BankAccount(); ba.setId(req.getBankAccountId());
        
        bt.setBankAccount(ba);
        bt.setTransactionDate(req.getTransactionDate());
        bt.setDescription(req.getDescription());
        bt.setType(req.getType());
        bt.setAmount(req.getAmount());
        bt.setReference(req.getReference());
        bt.setIsReconciled(false);
        bt.setCreatedAt(Instant.now());

        return toResponse(bankTransactionRepository.save(bt));
    }

    @Transactional
    public void delete(UUID id) {
        BankTransaction bt = findOrThrow(id);
        if (Boolean.TRUE.equals(bt.getIsReconciled())) {
            throw new IllegalStateException("Không thể xoá giao dịch ngân hàng đã đối chiếu");
        }
        bankTransactionRepository.delete(bt);
    }

    // ── Helper ──────────────────────────────────────────────────
    private BankTransaction findOrThrow(UUID id) {
        return bankTransactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Giao dịch ngân hàng không tồn tại: " + id));
    }

    public BankTransactionResponse toResponse(BankTransaction bt) {
        return BankTransactionResponse.builder()
                .id(bt.getId())
                .bankAccountId(bt.getBankAccount().getId())
                .bankAccountName(bt.getBankAccount().getName())
                .transactionDate(bt.getTransactionDate())
                .description(bt.getDescription())
                .type(bt.getType())
                .amount(bt.getAmount())
                .reference(bt.getReference())
                .isReconciled(bt.getIsReconciled())
                .journalEntryId(bt.getJournalEntry() != null ? bt.getJournalEntry().getId() : null)
                .createdAt(bt.getCreatedAt())
                .build();
    }
}
