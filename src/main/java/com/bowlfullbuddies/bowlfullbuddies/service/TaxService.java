package com.bowlfullbuddies.bowlfullbuddies.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Tax;
import com.bowlfullbuddies.bowlfullbuddies.repository.TaxRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaxService {

    private final TaxRepository taxRepository;

    public List<Tax> findAll() {
        return taxRepository.findAll();
    }

    public Tax findById(Long id) {
        return taxRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tax not found"));
    }

    public Tax save(Tax tax) {
        return taxRepository.save(tax);
    }

    public void delete(Long id) {
        taxRepository.deleteById(id);
    }
}
