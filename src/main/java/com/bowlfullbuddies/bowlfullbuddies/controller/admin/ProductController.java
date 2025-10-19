package com.bowlfullbuddies.bowlfullbuddies.controller.admin;

import com.bowlfullbuddies.bowlfullbuddies.dto.ProductRequestDTO;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Product;
import com.bowlfullbuddies.bowlfullbuddies.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ✅ Create Product with Images
    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<Product> addProduct(
            @RequestPart("productData") ProductRequestDTO dto,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        Product savedProduct = productService.addProduct(dto, images);
        return ResponseEntity.ok(savedProduct);
    }

    // ✅ Get All Products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // ✅ Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Get Single Product
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // ✅ Update Product (optional - similar structure to POST)
    @PutMapping(value = "/{id}", consumes = { "multipart/form-data" })
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestPart("productData") ProductRequestDTO dto,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        Product updatedProduct = productService.updateProduct(id, dto, images);
        return ResponseEntity.ok(updatedProduct);
    }
}
