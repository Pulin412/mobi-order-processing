package com.mobi.inventory.service;

import com.mobi.inventory.dto.ProductDto;
import com.mobi.inventory.dto.ResponseDto;
import com.mobi.inventory.entity.Product;
import com.mobi.inventory.repository.InventoryRepository;
import org.modelmapper.ModelMapper;
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
        Optional<Product> product = inventoryRepository.findById(Long.valueOf(productId));
        ResponseDto responseDto = new ResponseDto("", HttpStatus.OK.toString(), null);
        if (product.isPresent()) {
            List<Product> productList = new ArrayList<>();
            productList.add(product.get());
            responseDto.setMessage("Product Found");
            responseDto.setProductDtoList(productListToProductDtoList(productList));
        } else {
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
        List<Product> productList = inventoryRepository.findByName(name);
        ResponseDto responseDto = new ResponseDto("", HttpStatus.OK.toString(), null);
        if (productList.isEmpty()) {
            responseDto.setMessage("Product not found");
        } else {
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
        List<Product> productList = new ArrayList<>();
        Optional<Product> tempProduct = inventoryRepository.findById(convertToEntity(productDto).getProductId());
        ResponseDto responseDto = new ResponseDto("", HttpStatus.OK.toString(), null);
        if (tempProduct.isPresent()) {
            Product product = tempProduct.get();
            if(productDto.getCategory() != null)
                product.setCategory(productDto.getCategory());
            if(productDto.getBasePrice() != null)
                product.setBasePrice(productDto.getBasePrice());
            if(productDto.getDescription() != null)
                product.setDescription(productDto.getDescription());
            if(productDto.getMrp() != null)
                product.setMrp(productDto.getMrp());
            if(productDto.getName() != null)
                product.setName(productDto.getName());
            if(productDto.getTax() != null)
                product.setTax(productDto.getTax());
            if(productDto.getQuantity() != null)
                product.setQuantity(productDto.getQuantity());
            inventoryRepository.save(product);
            productList.add(product);
            responseDto.setMessage("Updated the product");
            responseDto.setProductDtoList(productListToProductDtoList(productList));
        } else {
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
