package com.mobi.customer.MobiCustomerService;

import com.mobi.customer.MobiCustomerService.Controller.CustomerController;
import com.mobi.customer.MobiCustomerService.entity.Customer;
import com.mobi.customer.MobiCustomerService.exception.RecordNotFoundException;
import com.mobi.customer.MobiCustomerService.model.CustomerDto;
import com.mobi.customer.MobiCustomerService.model.ResponseDto;
import com.mobi.customer.MobiCustomerService.service.CustomerService;
import com.mobi.customer.MobiCustomerService.util.CustomerServiceConstants;
import com.mobi.customer.MobiCustomerService.util.CustomerServiceUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

  @InjectMocks private CustomerController customerController;

  @Mock private CustomerService customerService;

  @Spy private CustomerServiceUtil customerServiceUtil = new CustomerServiceUtil(new ModelMapper());

  @Test
  public void testgetAllCustomers() {

    Customer customer = new Customer(1L, "Anand", "Mumbai", "anand@gmail.com", 1234L);
    Customer customer2 = new Customer(1L, "Anand", "Mumbai", "anand@gmail.com", 1234L);
    List<Customer> customerList = new ArrayList<>();
    customerList.add(customer);
    customerList.add(customer2);

    ResponseDto expectedResponse = new ResponseDto("", HttpStatus.OK.toString(), null);
    expectedResponse.setMessage("Customer Deleted Successfully...!!");
    expectedResponse.setCustomerDtoList(
        customerServiceUtil.customerListToCustomerDtoList(customerList));
    Mockito.when(customerService.getAllCustomers()).thenReturn(expectedResponse);
    ResponseDto actualResponse = customerController.getAllCustomers();

    Assert.assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void testgetCustomerById() {
    Customer customer = new Customer(1L, "Anand", "Mumbai", "anand@gmail.com", 1234L);
    Optional<Customer> customerOptional = Optional.of(customer);
    ResponseDto expectedResponse = new ResponseDto("", HttpStatus.OK.toString(), null);
    CustomerDto customerDto = null;
    List<CustomerDto> customerDtoList = new ArrayList<>();
    customerDtoList.add(customerServiceUtil.convertToDto(customerOptional.get()));
    expectedResponse.setCustomerDtoList(customerDtoList);
    expectedResponse.setMessage(CustomerServiceConstants.CUSTOMER_LIST);
    Mockito.when(customerService.getCustomerById(1L)).thenReturn(expectedResponse);
    ResponseDto actualResponse = customerController.getCustomerById(1L);

    Assert.assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void testcreateCustomer() throws RecordNotFoundException {
    Customer customer = new Customer(1L, "Anand", "Mumbai", "anand@gmail.com", 1234L);
    CustomerDto customerDto = customerServiceUtil.convertToDto(customer);
    List<CustomerDto> customerDtoList = new ArrayList<>();
    customerDtoList.add(customerDto);
    ResponseDto expectedRespones = new ResponseDto("", HttpStatus.OK.toString(), null);
    expectedRespones.setCustomerDtoList(customerDtoList);
    expectedRespones.setMessage(CustomerServiceConstants.CUSTOMER_CREATED);

    Mockito.when(customerService.createCustomer(customerDto)).thenReturn(expectedRespones);
    ResponseDto actualResponse = customerController.createCustomer(customerDto);

    Assert.assertEquals(expectedRespones, actualResponse);
  }

  @Test
  public void testupdateCustomer()
  {
    Customer customer = new Customer(1L, "Anand", "Mumbai", "anand@gmail.com", 1234L);
    CustomerDto customerDto=customerServiceUtil.convertToDto(customer);
    List<CustomerDto> customerDtoList=new ArrayList<>();
    customerDtoList.add(customerDto);

    ResponseDto  expectedRespones = new ResponseDto("", HttpStatus.OK.toString(), null);
    expectedRespones.setMessage(CustomerServiceConstants.CUSTOMER_RECORDS_UPDATED);
    expectedRespones.setCustomerDtoList(customerDtoList);
    Mockito.when(customerService.updateCustomer(1L,customerDto)).thenReturn(expectedRespones);

    ResponseDto actualResponse=customerController.updateCustomer(customerDto,1L);

    Assert.assertEquals(expectedRespones,actualResponse);

  }

  @Test
  public void deleteCustomerById() throws RecordNotFoundException {
    Customer customer = new Customer(1L, "Anand", "Mumbai", "anand@gmail.com", 1234L);
    CustomerDto customerDto=customerServiceUtil.convertToDto(customer);
    List<CustomerDto> customerDtoList=new ArrayList<>();
    customerDtoList.add(customerDto);

    ResponseDto  expectedRespones = new ResponseDto("", HttpStatus.OK.toString(), null);
    expectedRespones.setCustomerDtoList(customerDtoList);
    expectedRespones.setMessage("Customer Deleted Successfully...!!");
    Mockito.when(customerService.deleteCustomerById(1L)).thenReturn(expectedRespones);

    ResponseDto actualResponse = customerController.deleteCustomerById(1L);

    Assert.assertEquals(expectedRespones,actualResponse);
  }
}
