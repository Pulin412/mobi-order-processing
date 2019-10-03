package com.mobi.inventory.service;

import com.mobi.inventory.dto.ProductDto;
import com.mobi.inventory.dto.ResponseDto;
import com.mobi.inventory.entity.Product;
import com.mobi.inventory.repository.InventoryRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Inventory service.
 */
@Service
public class InventoryService {

    private static Logger logger = LoggerFactory.getLogger(InventoryService.class);

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Gets product by id.
     *
     * @param productId the product id
     * @return the product by id
     */
    public ResponseDto getProductById(String productId) {
        logger.info("Entered into getProductById with {}", productId);
        Optional<Product> product = inventoryRepository.findById(Long.valueOf(productId));
        ResponseDto responseDto = new ResponseDto("", HttpStatus.OK.toString(), null);
        if (product.isPresent()) {
            List<Product> productList = new ArrayList<>();
            productList.add(product.get());
            logger.info("Requested product found");
            responseDto.setMessage("Product Found");
            responseDto.setProductDtoList(productListToProductDtoList(productList));
        } else {
            logger.info("Requested product not found");
            responseDto.setMessage("Product not found");
        }
        return responseDto;
    }

    /**
     * Gets product by name.
     *
     * @param name the name
     * @return the product by name
     */
    public ResponseDto getProductByName(String name) {
        logger.info("Entered into getProductByName with {}", name);
        List<Product> productList = inventoryRepository.findByName(name);
        ResponseDto responseDto = new ResponseDto("", HttpStatus.OK.toString(), null);
        if (productList.isEmpty()) {
            logger.info("Requested product not found");
            responseDto.setMessage("Product not found");
        } else {
            logger.info("Requested product found");
            responseDto.setMessage("Product found");
            responseDto.setProductDtoList(productListToProductDtoList(productList));
        }
        return responseDto;
    }

    /**
     * Gets all products.
     *
     * @return the all products
     */
    public ResponseDto getAllProducts() {
        logger.info("Entered into getAllProduct");
        List<Product> productList = inventoryRepository.findAll();
        return new ResponseDto("All products", HttpStatus.OK.toString(), productListToProductDtoList(productList));
    }

    /**
     * Add list of products response dto.
     *
     * @param productDtoList the product dto list
     * @return the response dto
     */
    public ResponseDto addListOfProducts(List<ProductDto> productDtoList) {
        logger.info("Entered into addListOfProducts");
        List<Product> productList1 = inventoryRepository.saveAll(productDtoListToProductList(productDtoList));
        return new ResponseDto("Added products", HttpStatus.OK.toString(), productListToProductDtoList(productList1));
    }

    /**
     * Remove product response dto.
     *
     * @param id the id
     * @return the response dto
     */
    public ResponseDto removeProduct(String id) {
        logger.info("Entered into removeProduct");
        Optional<Product> product = inventoryRepository.findById(Long.valueOf(id));
        ResponseDto responseDto = new ResponseDto("", HttpStatus.OK.toString(), null);
        if (product.isPresent()) {
            inventoryRepository.delete(product.get());
            List<ProductDto> productDtoList = new ArrayList<>();
            productDtoList.add(convertToDto(product.get()));
            responseDto.setMessage("Deleted the product successfully");
            responseDto.setProductDtoList(productDtoList);
        } else {
            responseDto.setMessage("Product not found");
        }
        return responseDto;
    }

    /**
     * Remove all products response dto.
     *
     * @return the response dto
     */
    public ResponseDto removeAllProducts() {
        logger.info("Entered into removeAllProducts");
        inventoryRepository.deleteAll();
        return new ResponseDto("Removed all products successfully", HttpStatus.OK.toString(), null);
    }

    /**
     * Update product response dto.
     *
     * @param productDto the product dto
     * @return the response dto
     */
    public ResponseDto updateProduct(ProductDto productDto) {
        logger.info("Entered into updateProduct with productDto {}", productDto);
        List<Product> productList = new ArrayList<>();
        Optional<Product> tempProduct = inventoryRepository.findById(convertToEntity(productDto).getProductId());
        ResponseDto responseDto = new ResponseDto("", HttpStatus.OK.toString(), null);
        if (tempProduct.isPresent()) {
            Product product = tempProduct.get();
            if (productDto.getCategory() != null)
                product.setCategory(productDto.getCategory());
            if (productDto.getBasePrice() != null)
                product.setBasePrice(productDto.getBasePrice());
            if (productDto.getDescription() != null)
                product.setDescription(productDto.getDescription());
            if (productDto.getMrp() != null)
                product.setMrp(productDto.getMrp());
            if (productDto.getName() != null)
                product.setName(productDto.getName());
            if (productDto.getTax() != null)
                product.setTax(productDto.getTax());
            if (productDto.getQuantity() != null)
                product.setQuantity(productDto.getQuantity());
            inventoryRepository.save(product);
            productList.add(product);
            logger.info("Updated the product");
            responseDto.setMessage("Updated the product");
            responseDto.setProductDtoList(productListToProductDtoList(productList));
        } else {
            logger.info("Product not found");
            responseDto.setMessage("Product not found");
        }
        return responseDto;
    }

    private List<ProductDto> productListToProductDtoList(List<Product> productList) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : productList) {
            productDtoList.add(convertToDto(product));
        }
        return productDtoList;
    }

    private List<Product> productDtoListToProductList(List<ProductDto> productDtoList) {
        List<Product> productList = new ArrayList<>();
        for (ProductDto productDto : productDtoList) {
            productList.add(convertToEntity(productDto));
        }
        return productList;
    }

    private ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    private Product convertToEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }

}
