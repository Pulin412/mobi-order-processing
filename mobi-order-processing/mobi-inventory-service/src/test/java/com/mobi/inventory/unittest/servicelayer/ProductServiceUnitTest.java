package com.mobi.inventory.unittest.servicelayer;

import com.mobi.inventory.dto.ProductDto;
import com.mobi.inventory.dto.ResponseDto;
import com.mobi.inventory.entity.Product;
import com.mobi.inventory.repository.InventoryRepository;
import com.mobi.inventory.service.InventoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
public class ProductServiceUnitTest {

    @InjectMocks
    private InventoryService inventoryService;
    @Mock
    private InventoryRepository inventoryRepository;
    @Spy
    private ModelMapper modelMapper = new ModelMapper();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProductByIdTest() throws Exception {
        Optional<Product> optProduct = Optional.of(new Product("T-Shirt", "Polo T-shirt", 700.0, 799.0, 10.0, "FASHION"));
        when(inventoryRepository.findById(1L)).thenReturn(optProduct);
        final ResponseDto p = inventoryService.getProductById(1L);
        assertEquals(1, p.getProductDtoList().size());
        assertNotNull(p.getProductDtoList());
    }

    @Test
    public void getProductByNameTest() throws Exception {
        when(inventoryRepository.findByName("T-Shirt")).thenReturn(getListOfProduct());
        final ResponseDto p = inventoryService.getProductByName("T-Shirt");
        assertEquals(2, p.getProductDtoList().size());
        assertNotNull(p.getProductDtoList());
    }

    @Test
    public void getAllProductsTest() {
        when(inventoryRepository.findAll()).thenReturn(getListOfProduct());
        ResponseDto p = inventoryService.getAllProducts();
        assertEquals(2, p.getProductDtoList().size());
        assertNotNull(p.getProductDtoList());
    }

    @Test
    public void addListOfProductTest() {
        when(inventoryRepository.saveAll(getListOfProduct())).thenReturn(getListOfProduct());
        ResponseDto p = inventoryService.addListOfProducts(getListOfProductDto());
        assertEquals(2, p.getProductDtoList().size());
        assertNotNull(p.getProductDtoList());
    }

    @Test
    public void removeAProductTest() {
        Optional<Product> optProduct = Optional.of(new Product("T-Shirt", "Polo T-shirt", 700.0, 799.0, 10.0, "FASHION"));
        when(inventoryRepository.findById(1L)).thenReturn(optProduct);
        Mockito.doNothing().when(inventoryRepository).delete(optProduct.get());
        ResponseDto p = inventoryService.removeProduct(1L);
        assertEquals("Deleted the product successfully", p.getMessage());
        assertNotNull(p.getProductDtoList());
    }

    @Test
    public void removeAllProductTest() {
        Mockito.doNothing().when(inventoryRepository).deleteAll();
        ResponseDto p = inventoryService.removeAllProducts();
        assertEquals("Removed all products successfully", p.getMessage());
        assertNull(p.getProductDtoList());
    }

    @Test
    public void updateProductTest() {
        Optional<Product> optProduct = Optional.of(new Product("T-Shirt", "Polo T-shirt", 700.0, 799.0, 10.0, "FASHION"));
        when(inventoryRepository.findById(Mockito.anyLong())).thenReturn(optProduct);
        when(inventoryRepository.save(optProduct.get())).thenReturn(optProduct.get());
        ProductDto productDto = new ProductDto(3L, "T-Shirt", "Round neck T-shirt", 800.0, 899.0, 10.0, "FASHION", 3);
        ResponseDto p = inventoryService.updateProduct(productDto);
        assertEquals("Updated the product", p.getMessage());
        assertNotNull(p.getProductDtoList());
    }

    @Test
    public void updateProductNotFoundTest() {
        Optional<Product> optProduct = Optional.of(new Product("T-Shirt", "Polo T-shirt", 700.0, 799.0, 10.0, "FASHION"));
        when(inventoryRepository.findById(1L)).thenReturn(optProduct);
        when(inventoryRepository.save(optProduct.get())).thenReturn(optProduct.get());
        ProductDto productDto = new ProductDto(3L, "T-Shirt", "Round neck T-shirt", 800.0, 899.0, 10.0, "FASHION", 3);
        ResponseDto p = inventoryService.updateProduct(productDto);
        assertEquals("Product not found", p.getMessage());
        assertNull(p.getProductDtoList());
    }

    private List<Product> getListOfProduct() {
        List<Product> productList = new ArrayList<Product>();
        Product productOne = new Product("T-Shirt", "Polo T-shirt", 700.0, 799.0, 10.0, "FASHION");
        productOne.setProductId(1L);
        Product productTwo = new Product("T-Shirt", "Round neck T-shirt", 800.0, 899.0, 10.0, "FASHION");
        productTwo.setProductId(2L);
        productList.add(productOne);
        productList.add(productTwo);
        return productList;
    }

    private List<ProductDto> getListOfProductDto() {
        List<ProductDto> productDtoList = new ArrayList<>();
        ProductDto productDtoOne = new ProductDto(100L, "T-Shirt", "Polo T-shirt", 700.0, 799.0, 10.0, "FASHION", 2);
        ProductDto productDtoTwo = new ProductDto(200L, "T-Shirt", "Round neck T-shirt", 800.0, 899.0, 10.0, "FASHION", 3);
        productDtoList.add(productDtoOne);
        productDtoList.add(productDtoTwo);
        return productDtoList;
    }
}