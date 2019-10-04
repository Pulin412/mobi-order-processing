package com.mobi.inventory.integrationtest;

import com.mobi.demo.ProductDto;
import com.mobi.inventory.MobiInventoryServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MobiInventoryServiceApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest {

    @LocalServerPort
    private int port;

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/inventory/api/v1" + uri;
    }

    HttpHeaders headers = new HttpHeaders();
    TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Test
    public void retrieveProductsIntegrationTest() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, null);
        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/"), HttpMethod.GET, entity, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        //String expected = "{\"id\":1,\"name\":\"Rajesh Bhojwani\",\"description\":\"Class 10\"}";
    }

    @Test
    public void addProductIntegrationTest() throws Exception {
        headers.set(HttpHeaders.CONTENT_TYPE,"application/json");
        HttpEntity<List<ProductDto>> entity = new HttpEntity<>(mockProductDTO(), headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/"), HttpMethod.POST, entity, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        //String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
    }

    @Test
    public void updateProductIntegrationTest() throws Exception {
        headers.set(HttpHeaders.CONTENT_TYPE,"application/json");
        ProductDto productDtoOne = new ProductDto(1L,"Casual Shirt", "Casual shirt", 900.0, 999.0, 10.0, "FASHION",3);
        HttpEntity<ProductDto> entity = new HttpEntity<>(productDtoOne, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/"), HttpMethod.PUT, entity, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        //String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
    }

    @Test
    public void deleteProductIntegrationTest() throws Exception {
        headers.set(HttpHeaders.CONTENT_TYPE,"application/json");
        HttpEntity<List<ProductDto>> entity = new HttpEntity<>(mockProductDTO(), headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/"), HttpMethod.DELETE, entity, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        //String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
    }

    private List<ProductDto> mockProductDTO() {
        List<ProductDto> productDtoList = new ArrayList<>();
        ProductDto productOne = new ProductDto(201L,"Casual Shirt", "Casual shirt", 900.0, 999.0, 10.0, "FASHION",3);
        productDtoList.add(productOne);
        return productDtoList;
    }
}
