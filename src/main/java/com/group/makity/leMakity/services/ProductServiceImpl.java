package com.group.makity.leMakity.services;

import com.group.makity.leMakity.dtos.ProductDTO;
import com.group.makity.leMakity.dtos.ProductHistoryDTO;
import com.group.makity.leMakity.entities.AppCategory;
import com.group.makity.leMakity.entities.Product;
import com.group.makity.leMakity.exceptions.CategoryNotFoundException;
import com.group.makity.leMakity.exceptions.ProductNotFoundException;
import com.group.makity.leMakity.mappers.ProductMapImpl;
import com.group.makity.leMakity.mappers.ProductMapper;
import com.group.makity.leMakity.repositories.AppCategoryRepository;
import com.group.makity.leMakity.repositories.AppUserRepository;
import com.group.makity.leMakity.repositories.ProductRepository;
import com.group.makity.leMakity.validator.ProductValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCT_NOT_FOUND = "Le produit n'existe pas";
    private static final String CATEGORY_NOT_FOUND = "La categorie n'existe pas";
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private AppCategoryRepository appCategoryRepository;
    private ProductMapImpl productMap;
    private AppUserRepository appUserRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, AppCategoryRepository appCategoryRepository, ProductMapImpl productMap, AppUserRepository appUserRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.appCategoryRepository = appCategoryRepository;
        this.productMap = productMap;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public ProductDTO findById(Long idProduct) throws ProductNotFoundException {
        Product product = productRepository.findById(idProduct).orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));
        ProductDTO productDTO = productMapper.toDto(product);
        return productDTO;
    }

    @Override
    public ProductDTO findByProductName(String productName) throws ProductNotFoundException {
        Product product = productRepository.findByProductName(productName).orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));
        ProductDTO productDTO = productMapper.toDto(product);
        return productDTO;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) throws CategoryNotFoundException, ProductNotFoundException {

        List<String> errors = ProductValidator.validate(productDTO);
        if (!errors.isEmpty()) {
            log.error("Article is not valid {}", productDTO);
            throw new ProductNotFoundException("Le produit n'est pas valide");
        }

        return ProductDTO.fromEntity(
                productRepository.save(
                        ProductDTO.toEntity(productDTO)
                )
        );
        /*Product product = productMap.toEntity(productDTO);
        // product.setCategory(appCategory);
        Product productSaved = productRepository.save(product);
        //  productSaved.setCategory(appCategory);
        ProductDTO productDTOSaved = productMap.toDto(productSaved);

         return productDTOSaved;*/
    }

    @Override
    public ProductDTO saveProductDB(ProductDTO productDTO) throws IOException, CategoryNotFoundException, ProductNotFoundException {
        return null;
    }

   /* @Override
    public ProductDTO saveProduct(ProductDTO productDTO) throws CategoryNotFoundException {

        //AppCategory appCategory = appCategoryRepository.findById(productDTO.getCategory().getIdCategory()).orElseThrow(() -> new CategoryNotFoundException(CATEGORY_NOT_FOUND));
        // AppCategory appCategory = appCategoryRepository.findById(productDTO.getIdCategory()).orElseThrow(() -> new CategoryNotFoundException(CATEGORY_NOT_FOUND));
        Product product = productMap.toEntity(productDTO);
        // product.setCategory(appCategory);
        Product productSaved = productRepository.save(product);
        //  productSaved.setCategory(appCategory);
        ProductDTO productDTOSaved = productMap.toDto(productSaved);

        return productDTOSaved;
    }*/

    @Override
    public ProductDTO saveProductD(Product product) throws IOException, CategoryNotFoundException, ProductNotFoundException {

       /* List<String> errors = ProductValidator.validate(productDTO);
        if (!errors.isEmpty()) {
            log.error("Article is not valid {}", productDTO);
            throw new ProductNotFoundException("Le produit n'est pas valide");
        }*/

       // Product product = productMap.toEntity(productDTO);
        Product productSaved = productRepository.save(product);
        ProductDTO productDTOSaved = productMap.toDto(productSaved);
        return productDTOSaved;

    }

  /*  @Override
    public ProductDTO saveProductDB(ProductDTO productDTO) throws IOException, CategoryNotFoundException, ProductNotFoundException {

        List<String> errors = ProductValidator.validate(productDTO);
        if (!errors.isEmpty()) {
            log.error("Article is not valid {}", productDTO);
            throw new ProductNotFoundException("Le produit n'est pas valide");
        }

        Product product = productMap.toEntity(productDTO);
        Product productSaved = productRepository.save(product);
        ProductDTO productDTOSaved = productMap.toDto(productSaved);
        return productDTOSaved;

    }*/

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) throws ProductNotFoundException, CategoryNotFoundException {

        //AppCategory appCategory = appCategoryRepository.findById(productDTO.getIdCategory()).orElseThrow(() -> new CategoryNotFoundException(CATEGORY_NOT_FOUND));
        Product product = productRepository.findById(productDTO.getIdProduct()).orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));
        if(product == null){
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND);
        }
        if(!Objects.equals(product.getProductName(), productDTO.getProductName())){
            product.setProductName(productDTO.getProductName());
        }
        if (!Objects.equals(product.getPrice(),productDTO.getPrice())){
            product.setPrice(productDTO.getPrice());
        }
       /* if (!Objects.equals(product.getCategory().getIdCategory(),productDTO.getIdCategory())){
            product.setCategory(appCategory);
        }*/
        if (!Objects.equals(product.getDescription(), productDTO.getDescription())){
            product.setDescription(productDTO.getDescription());
        }
        if (!Objects.equals(product.getImage(), productDTO.getImage())){
            product.setImage(productDTO.getImage());
        }
        if (!Objects.equals(product.getStockQuantity(), productDTO.getStockQuantity())){
            product.setStockQuantity(productDTO.getStockQuantity());
        }
        Product productSaved = productRepository.save(product);
        ProductDTO productDTOSaved = productMapper.toDto(productSaved);
        return productDTOSaved;
    }

    @Override
    public boolean deleteProduct(Long idProduct) {
        productRepository.deleteById(idProduct);
        return true;
    }

    @Override
    public List<ProductDTO> listProduct() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDto(product)).collect(Collectors.toList());
        return productDTOList;
    }

    @Override
    public ProductHistoryDTO listPageProduct(String keyword, int page, int size) {
        Page<Product> pageProduct = productRepository.searchProduct(keyword, PageRequest.of(page, size));
        ProductHistoryDTO productHistoryDTO = new ProductHistoryDTO();
        List<ProductDTO> productDTOS = pageProduct.getContent().stream().map(prod -> productMapper.toDto(prod)).collect(Collectors.toList());
        productHistoryDTO.setProductDTOS(productDTOS);
        productHistoryDTO.setPageSize(size);
        productHistoryDTO.setCurrentPage(page);
        productHistoryDTO.setTotalPages(pageProduct.getTotalPages());
        return productHistoryDTO;
    }

}
