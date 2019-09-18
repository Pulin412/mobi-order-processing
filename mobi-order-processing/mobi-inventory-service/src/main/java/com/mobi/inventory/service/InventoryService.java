package com.mobi.inventory.service;

import com.mobi.inventory.dto.ProductDto;
import com.mobi.inventory.dto.ResponseDto;
import com.mobi.inventory.entity.Product;
import com.mobi.inventory.repository.InventoryRepository;
import com.mobi.inventory.utils.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseDto getProductById(String productId) {
        Optional<Product> product = inventoryRepository.findById(Long.valueOf(productId));
        if (product.isPresent()) {
            List<Product> productList = new ArrayList<>();
            productList.add(product.get());
            return new ResponseDto("Product found", HttpStatus.OK.toString(), productListToProductDtoList(productList));
        } else {
            return new ResponseDto("Product not found", HttpStatus.OK.toString(), null);
        }
    }

    public ResponseDto getProductByName(String name) {
        List<Product> productList = inventoryRepository.findByName(name);
        if (productList.isEmpty()) {
            return new ResponseDto("Product not found", HttpStatus.OK.toString(), null);
        } else {
            return new ResponseDto("Found the product", HttpStatus.OK.toString(), productListToProductDtoList(productList));
        }
    }

    public ResponseDto addProduct(ProductDto productDto) {
        Product product = convertToEntity(productDto);
        product.setCategory(Category.valueOf(product.getCategory()).getKey());
        Product save = inventoryRepository.save(product);
        List<Product> productList = new ArrayList<>();
        productList.add(save);
        return new ResponseDto("Added the product", HttpStatus.OK.toString(), productListToProductDtoList(productList));

    }

    public ResponseDto getAllProducts() {
        List<Product> productList = inventoryRepository.findAll();
        return new ResponseDto("All products", HttpStatus.OK.toString(), productListToProductDtoList(productList));
    }

    public ResponseDto addListOfProducts(List<ProductDto> productDtoList) {
        List<Product> productList1 = inventoryRepository.saveAll(productDtoListToProductList(productDtoList));
        return new ResponseDto("Added products", HttpStatus.OK.toString(), productListToProductDtoList(productList1));
    }

    public ResponseDto removeProduct(String id) {
        Optional<Product> product = inventoryRepository.findById(Long.valueOf(id));
        if (product.isPresent()) {
            inventoryRepository.delete(product.get());
            List<ProductDto> productDtoList = new ArrayList<>();
            productDtoList.add(convertToDto(product.get()));
            return new ResponseDto("Deleted the product successfully", HttpStatus.OK.toString(), productDtoList);
        } else {
            return new ResponseDto("Product not found", HttpStatus.OK.toString(), null);
        }
    }

    public ResponseDto removeAllProducts() {
        inventoryRepository.deleteAll();
        return new ResponseDto("Removed all products successfully", HttpStatus.OK.toString(), null);
    }

    public ResponseDto updateProduct(ProductDto productDto) {
        List<Product> productList = new ArrayList<>();
        Optional<Product> tempProduct = inventoryRepository.findById(convertToEntity(productDto).getProductId());
        if (tempProduct.isPresent()) {
            inventoryRepository.delete(tempProduct.get());
            inventoryRepository.save(tempProduct.get());
            productList.add(tempProduct.get());
            return new ResponseDto("Update the product", HttpStatus.OK.toString(), productListToProductDtoList(productList));
        } else {
            return new ResponseDto("Product not found", HttpStatus.OK.toString(), null);
        }
    }

    public ResponseDto initializeDB() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("T Shirt", "Cotton shirt", 500.0, 800.0, 15.0, Category.FASHION.getKey()));
        productList.add(new Product("iphone 11", "Smart phone", 100000.0, 120000.0, 11.0, Category.ELECTRONICS.getKey()));
        productList.add(new Product("OnePlus 7T", "Smart phone", 50000.0, 56000.0, 4.0, Category.ELECTRONICS.getKey()));
        productList.add(new Product("Craft Papers", "A4 craft papers", 150000.0, 170000.0, 5.0, Category.ARTS.getKey()));
        productList.add(new Product("Alienware", "Dell gaming laptop", 500.0, 800.0, 7.0, Category.COMPUTERS.getKey()));
        productList.add(new Product("T Shirt", "Cotton Shirt", 500.0, 800.0, 15.0, Category.FASHION.getKey()));
        productList.add(new Product("Protein Powder", "5lb whey protein", 6000.0, 8000.0, 23.0, Category.HEALTH.getKey()));
        productList.add(new Product("Car cover", "Car protective cover for SUV", 2500.0, 2800.0, 20.0, Category.AUTOMOTIVE.getKey()));
        addListOfProducts(productListToProductDtoList(productList));
        return new ResponseDto("Added sample products to DB", HttpStatus.OK.toString(), null);
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
