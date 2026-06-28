package com.irfan.fintrack.service;

import com.irfan.fintrack.model.Transaction;
import com.irfan.fintrack.model.User;
import com.irfan.fintrack.repository.TransactionRepository;
import com.irfan.fintrack.repository.UserRepository;
import com.irfan.fintrack.request.TransactionCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found."));
    }

    public List<Transaction> getTransactionsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        return user.getTransactions();
    }

    public Transaction createTransaction(TransactionCreateRequest req) {

        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found."));

        Transaction transaction = Transaction.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .amount(req.getAmount())
                .type(req.getType())
                .date(req.getDate() == null ? LocalDate.now() : req.getDate())
                .user(user)
                .build();

        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, Transaction updated) {

        Transaction existing = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found."));

        if (updated.getTitle() != null)
            existing.setTitle(updated.getTitle());

        if (updated.getDescription() != null)
            existing.setDescription(updated.getDescription());

        if (updated.getAmount() > 0)
            existing.setAmount(updated.getAmount());

        if (updated.getType() != null)
            existing.setType(updated.getType());

        if (updated.getDate() != null)
            existing.setDate(updated.getDate());

        return transactionRepository.save(existing);
    }

    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction not found.");
        }

        transactionRepository.deleteById(id);
    }
}