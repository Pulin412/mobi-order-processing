package com.mobi.inventory.unittest.servicelayer;

import com.mobi.inventory.dto.ProductDto;
import com.mobi.inventory.dto.ResponseDto;
import com.mobi.inventory.entity.Product;
import com.mobi.inventory.repository.InventoryRepository;
import com.mobi.inventory.service.InventoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductServiceUnitTest {

    @InjectMocks
    private InventoryService inventoryService;
    @MockBean
    private InventoryRepository inventoryRepository;
    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void getProductByIdTest() throws Exception {
        Optional<Product> optProduct = Optional.of( new Product("T-Shirt", "Polo T-shirt", 700.0, 799.0, 10.0, "FASHION"));
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
        System.out.println("###"+p.getProductDtoList());
        assertEquals(2, p.getProductDtoList().size());
        assertNotNull(p.getProductDtoList());
    }

    /*@Test
    public void removeAllProductTest() {
        doAnswer(inventoryRepository.deleteAll()).then(doNothing());
        ResponseDto p = inventoryService.removeAllProducts();
        System.out.println("###"+p.getProductDtoList());
        assertEquals(2, p.getProductDtoList().size());
        assertNotNull(p.getProductDtoList());
    }*/

    private List<Product> getListOfProduct(){
        List<Product> productList = new ArrayList<Product>();
        Product productOne = new Product("T-Shirt", "Polo T-shirt", 700.0, 799.0, 10.0, "FASHION");
        Product productTwo = new Product("T-Shirt", "Round neck T-shirt", 800.0, 899.0, 10.0, "FASHION");
        productList.add(productOne);
        productList.add(productTwo);
        return productList;
    }
    private List<ProductDto> getListOfProductDto(){
        List<ProductDto> productDtoList = new ArrayList<ProductDto>();
        ProductDto productOne = new ProductDto(100L,"T-Shirt", "Polo T-shirt", 700.0, 799.0, 10.0, "FASHION",2);
        ProductDto productTwo = new ProductDto(200L,"T-Shirt", "Round neck T-shirt", 800.0, 899.0, 10.0, "FASHION",3);
        productDtoList.add(productOne);
        productDtoList.add(productTwo);
        return productDtoList;
    }
}