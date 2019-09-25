package com.mobi.inventory.unittest.controllerlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobi.inventory.MobiInventoryServiceApplication;
import com.mobi.inventory.controller.InventoryController;
import com.mobi.inventory.dto.ProductDto;
import com.mobi.inventory.dto.ResponseDto;
import com.mobi.inventory.entity.Product;
import com.mobi.inventory.service.InventoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@WebMvcTest(value = InventoryController.class)
@ContextConfiguration(classes = MobiInventoryServiceApplication.class)
//@SpringBootTest(classes = MobiInventoryServiceApplication.class)
public class ProductControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    String baseUri = "/inventory/api/v1";

    @Test
    public void retrieveProductsTest() throws Exception {
        ResponseDto resDto = new ResponseDto("OK", "200", mockProductDTOList());
        Mockito.when(inventoryService.getAllProducts()).thenReturn(resDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(baseUri+ "/");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    public void retrieveProductByIdTest() throws Exception {
        ResponseDto resDto = new ResponseDto("OK", "200", mockProductDTOList());
        Mockito.when(inventoryService.getProductById(1L)).thenReturn(resDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(baseUri+ "/product/{id}",1L);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    public void retrieveProductByNameTest() throws Exception {
        ResponseDto resDto = new ResponseDto("OK", "200", mockProductDTOList());
        Mockito.when(inventoryService.getProductByName("T-Shirt")).thenReturn(resDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(baseUri+ "/productByName/{name}","T-Shirt");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    public void addProductsTest() throws Exception {
        ResponseDto resDto = new ResponseDto("OK", "200", mockProductDTOList());
        Mockito.when(inventoryService.addListOfProducts(mockProductDTOList())).thenReturn(resDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(baseUri+ "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockProduct()));
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    public void removeProductByIdTest() throws Exception {
        ResponseDto resDto = new ResponseDto("OK", "200", mockProductDTOList());
        Mockito.when(inventoryService.removeProduct("1")).thenReturn(resDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(baseUri+ "/{id}","1");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    public void removeProductsTest() throws Exception {
        ResponseDto resDto = new ResponseDto("OK", "200", mockProductDTOList());
        Mockito.when(inventoryService.removeAllProducts()).thenReturn(resDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(baseUri+ "/");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    public void updateProductsTest() throws Exception {
        ResponseDto resDto = new ResponseDto("OK", "200", mockProductDTOList());
        Mockito.when(inventoryService.updateProduct(mockProductDTO())).thenReturn(resDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(baseUri+ "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockProduct()));
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        assertNotNull(result.getResponse().getContentAsString());
    }

    private List<ProductDto> mockProductDTOList() {
        List<ProductDto> productDtoList = new ArrayList<>();
        ProductDto productOne = new ProductDto(1L,"T-Shirt", "Polo T-shirt", 700.0, 799.0, 10.0, "FASHION",2);
        productDtoList.add(productOne);
        return productDtoList;
    }
    private List<Product> mockProduct() {
        List<Product> productList = new ArrayList<>();
        Product productOne = new Product("T-Shirt", "Polo T-shirt", 700.0, 799.0, 10.0, "FASHION");
        productList.add(productOne);
        return productList;
    }

    private ProductDto mockProductDTO() {
        ProductDto productDto = new ProductDto(1L,"T-Shirt", "Polo T-shirt", 700.0, 799.0, 10.0, "FASHION",2);
        return productDto;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
