package com.bowlfullbuddies.bowlfullbuddies.service;

import com.bowlfullbuddies.bowlfullbuddies.dto.ProductRequestDTO;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.*;
import com.bowlfullbuddies.bowlfullbuddies.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductSubCategoryRepository productSubCategoryRepository;
    private final BrandRepository brandRepository;
    private final TaxRepository taxRepository;
    private final ProductImagesRepository productImagesRepository;

    /**
     * ✅ Create new product with all details and image URLs
     */
    public Product addProduct(ProductRequestDTO dto, List<MultipartFile> images) {
        Product product = new Product();

        // ✅ Basic fields
        product.setProductName(dto.getProductname());
        product.setHsnCode(dto.getHsnCode());
        product.setBarCode(dto.getBarCode());
        product.setModelNo(dto.getModelNo());
        product.setSerialNo(dto.getSerialNo());
        product.setDescription(dto.getDescription());
        product.setQuantity(dto.getQuantity());
        product.setMinimumStock(dto.getMinimumStock());
        product.setCostPrice(dto.getCostPrice());
        product.setMrp(dto.getMrp());
        product.setMeasurementUnit(dto.getMeasurementUnit());

        // ✅ Sizes & Colors
        product.setSizes(dto.getSizes());
        product.setColors(dto.getColors());

        // ✅ Relationships
        if (dto.getBrandId() != null) {
            Brand brand = brandRepository.findById(dto.getBrandId())
                    .orElseThrow(() -> new RuntimeException("Invalid brand ID"));
            product.setBrand(brand);
        }

        if (dto.getTaxId() != null) {
            Tax tax = taxRepository.findById(dto.getTaxId())
                    .orElseThrow(() -> new RuntimeException("Invalid tax ID"));
            product.setTax(tax);
        }

        if (dto.getProductSubCategoryId() != null) {
            ProductSubCategory subCategory = productSubCategoryRepository.findById(dto.getProductSubCategoryId())
                    .orElseThrow(() -> new RuntimeException("Subcategory not found"));
            product.setProductSubCategory(subCategory);
        }

        // ✅ Save product first (so it gets an ID)
        Product savedProduct = productRepository.save(product);

        // ✅ Save image URLs if provided
        if (images != null && !images.isEmpty()) {
            try {
                // ✅ Create uploads directory once
                Path uploadDir = Paths.get("uploads");
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                // ✅ Save each image
                for (MultipartFile file : images) {
                    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                    Path filePath = uploadDir.resolve(fileName);
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                    // ✅ Create ProductImages entry
                    ProductImages productImage = new ProductImages();
                    productImage.setProduct(savedProduct);
                    productImage.setImageUrl("/uploads/" + fileName);

                    productImagesRepository.save(productImage);
                }

            } catch (IOException e) {
                throw new RuntimeException("Failed to save images", e);
            }
        }

        return savedProduct;
    }

    /**
     * ✅ Get all products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * ✅ Delete product by ID
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * ✅ Get product by ID
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    /**
     * ✅ Update product and images
     */
    public Product updateProduct(Long id, ProductRequestDTO dto, List<MultipartFile> images) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // ✅ Basic updates
        existing.setProductName(dto.getProductname());
        existing.setHsnCode(dto.getHsnCode());
        existing.setBarCode(dto.getBarCode());
        existing.setModelNo(dto.getModelNo());
        existing.setSerialNo(dto.getSerialNo());
        existing.setDescription(dto.getDescription());
        existing.setQuantity(dto.getQuantity());
        existing.setMinimumStock(dto.getMinimumStock());
        existing.setCostPrice(dto.getCostPrice());
        existing.setMrp(dto.getMrp());
        existing.setMeasurementUnit(dto.getMeasurementUnit());
        existing.setSizes(dto.getSizes());
        existing.setColors(dto.getColors());

        // ✅ Relationships
        if (dto.getBrandId() != null) {
            existing.setBrand(brandRepository.findById(dto.getBrandId())
                    .orElseThrow(() -> new RuntimeException("Brand not found")));
        }

        if (dto.getTaxId() != null) {
            existing.setTax(taxRepository.findById(dto.getTaxId())
                    .orElseThrow(() -> new RuntimeException("Tax not found")));
        }

        if (dto.getProductSubCategoryId() != null) {
            existing.setProductSubCategory(productSubCategoryRepository.findById(dto.getProductSubCategoryId())
                    .orElseThrow(() -> new RuntimeException("Subcategory not found")));
        }

        // ✅ Replace images if new ones provided
        if (images != null && !images.isEmpty()) {
            productImagesRepository.deleteAll(existing.getProductImages());
            existing.getProductImages().clear();

            for (MultipartFile file : images) {
                try {
                    ProductImages productImage = new ProductImages();
                    productImage.setProduct(existing);
                    productImage.setImageUrl("/uploads/" + file.getOriginalFilename());

                    productImagesRepository.save(productImage);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to save updated image", e);
                }
            }
        }

        return productRepository.save(existing);
    }
}
