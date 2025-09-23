package com.bowlfullbuddies.bowlfullbuddies.controller.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Tax;
import com.bowlfullbuddies.bowlfullbuddies.service.TaxService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/tax")
@CrossOrigin(origins = "http://localhost:3000") // allow React frontend
@RequiredArgsConstructor
public class TaxController {

    private final TaxService taxService;

    @GetMapping
    public ResponseEntity<List<Tax>> getAllTaxes() {
        return ResponseEntity.ok(taxService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tax> getTaxById(@PathVariable Long id) {
        return ResponseEntity.ok(taxService.findById(id));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Tax> createTax(@RequestBody Tax tax) {
        return ResponseEntity.ok(taxService.save(tax));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tax> updateTax(@PathVariable Long id, @RequestBody Tax tax) {
        tax.setId(id);
        return ResponseEntity.ok(taxService.save(tax));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTax(@PathVariable Long id) {
        taxService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
