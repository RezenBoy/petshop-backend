        package com.bowlfullbuddies.bowlfullbuddies.controller.user;

        import com.bowlfullbuddies.bowlfullbuddies.dto.ProductResponseDTO;
        import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Product;
        import com.bowlfullbuddies.bowlfullbuddies.entity.admin.ProductImages;
        import com.bowlfullbuddies.bowlfullbuddies.repository.ProductRepository;
        import lombok.RequiredArgsConstructor;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.stream.Collectors;

        @RestController
        @RequestMapping("/api/products")
        @CrossOrigin(origins = "http://localhost:3000")
        @RequiredArgsConstructor
        public class UserProductController {

        private final ProductRepository productRepository;

        @GetMapping
        public List<ProductResponseDTO> getAllProducts() {
                return productRepository.findAll()
                        .stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList());
        }

        @GetMapping("/{id}")
        public ProductResponseDTO getProductById(@PathVariable Long id) {
                Product product = productRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Product not found"));
                ProductResponseDTO response = convertToDTO(product);
                return response;
        }

        private ProductResponseDTO convertToDTO(Product p) {
                return new ProductResponseDTO(
                        p.getId(),
                        p.getProductName(),
                        p.getDescription(),
                        p.getMrp(),
                        p.getCostPrice(),
                        p.getQuantity(),
                        p.getBrand() != null ? p.getBrand().getBrandName() : null,
                        (p.getProductSubCategory() != null && p.getProductSubCategory().getProductCategory() != null)
                                ? p.getProductSubCategory().getProductCategory().getCategoryName()
                                : null,
                        p.getProductSubCategory() != null ? p.getProductSubCategory().getSubCategoryName() : null,
                        p.getSizes(),
                        (p.getColors() != null)
                                ? p.getColors().stream()
                                        .map(c -> c.getName() + " (" + c.getCode() + ")")
                                        .collect(Collectors.toList())
                                : List.of(),
                        (p.getProductImages() != null)
                                ? p.getProductImages().stream()
                                        .map(ProductImages::getImageUrl)
                                        .collect(Collectors.toList())
                                : List.of()

                );
        }
        }
