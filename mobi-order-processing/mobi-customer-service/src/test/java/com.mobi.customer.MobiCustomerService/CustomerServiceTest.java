package com.mobi.customer.MobiCustomerService;

import com.mobi.customer.MobiCustomerService.entity.Customer;
import com.mobi.customer.MobiCustomerService.exception.RecordNotFoundException;
import com.mobi.customer.MobiCustomerService.model.CustomerDto;
import com.mobi.customer.MobiCustomerService.model.ResponseDto;
import com.mobi.customer.MobiCustomerService.repository.CustomerRepository;
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
public class CustomerServiceTest {

  List<Customer> customerList = new ArrayList<>();

  @InjectMocks private CustomerService customerService;

  @Mock private CustomerRepository customerRepository;

  @Spy private CustomerServiceUtil customerServiceUtil = new CustomerServiceUtil(new ModelMapper());

  @Test
  public void testupdateCustomer() {
    Long id = 1L;
    /* FOR MOCKING REPO */
    Customer customer = new Customer(1L, "Anand", "Pune", "anand@gmail.com", 1234L);
    Optional<Customer> customerOptional = Optional.of(customer);
    Mockito.when(customerRepository.findById(id)).thenReturn(customerOptional);
    Mockito.when(customerRepository.save(Mockito.any())).thenReturn(null);
    /*MOCKIN REPO ENDS */

    /* TEST REQUEST */
    CustomerDto customerDto = new CustomerDto("Anand", "Pune", "anand@gmail.com", 1234L);
    ResponseDto expectedResponse = new ResponseDto("", HttpStatus.OK.toString(), null);
    expectedResponse.setMessage(CustomerServiceConstants.CUSTOMER_RECORDS_UPDATED);
    List<CustomerDto> customerDtoList = new ArrayList<>();
    customerDtoList.add(customerDto);
    expectedResponse.setCustomerDtoList(customerDtoList);
    expectedResponse.setMessage(CustomerServiceConstants.CUSTOMER_RECORDS_UPDATED);

    ResponseDto actualResponse = customerService.updateCustomer(id, customerDto);

    Assert.assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void testcreateCustomer() throws RecordNotFoundException {
    List<CustomerDto> customerDtoList = new ArrayList<>();
    ResponseDto expectedResponse = new ResponseDto("", HttpStatus.OK.toString(), null);

    /* FOR MOCKING REPO */
    Customer customer = new Customer(1L, "Anand", "Pune", "anand@gmail.com", 1234L);
    Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customer);

    /* REPO Mock Ends*/
    CustomerDto customerDto = customerServiceUtil.convertToDto(customer);
    customerDtoList.add(customerDto);
    expectedResponse.setCustomerDtoList(customerDtoList);
    expectedResponse.setMessage("New customer created Successfully");
    ResponseDto actualResponse = customerService.createCustomer(customerDto);

    Assert.assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void testgetAllCustomer() {
    Customer customer = new Customer(1L, "Anand", "Pune", "anand@gmail.com", 1234L);
    Customer customer2 = new Customer(1L, "ABC", "Pune", "anand@gmail.com", 1234L);
    List<Customer> customerList = new ArrayList<>();
    customerList.add(customer);
    customerList.add(customer2);
    Mockito.when(customerRepository.findAll()).thenReturn(customerList);

    ResponseDto expectedResponse = new ResponseDto("", HttpStatus.OK.toString(), null);
    List<CustomerDto> customerDtoList =
        customerServiceUtil.customerListToCustomerDtoList(customerList);
    expectedResponse.setCustomerDtoList(customerDtoList);
    expectedResponse.setMessage(CustomerServiceConstants.CUSTOMER_LIST);
    ResponseDto actualResponse = customerService.getAllCustomers();

    Assert.assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void testgetCustomerById() {
    Customer customer = new Customer(1L, "Anand", "Pune", "anand@gmail.com", 1234L);
    Optional<Customer> customerOptional = Optional.of(customer);
    Mockito.when(customerRepository.findById(1L)).thenReturn(customerOptional);

    ResponseDto expectedResponse = new ResponseDto("", HttpStatus.OK.toString(), null);
    CustomerDto customerDto = new CustomerDto("Anand", "Pune", "anand@gmail.com", 1234L);
    List<CustomerDto> customerDtoList = new ArrayList<>();
    customerDtoList.add(customerServiceUtil.convertToDto(customer));
    expectedResponse.setMessage(CustomerServiceConstants.CUSTOMER_LIST);
    expectedResponse.setCustomerDtoList(customerDtoList);

    ResponseDto actualResponse = customerService.getCustomerById(1L);

    Assert.assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void testdeleteCustomerById() throws RecordNotFoundException {
    Customer customer = new Customer(1L, "Anand", "Pune", "anand@gmail.com", 1234L);
    Optional<Customer> customerOptional = Optional.of(customer);
    Mockito.when(customerRepository.findById(1L)).thenReturn(customerOptional);
    Mockito.doNothing().when(customerRepository).deleteById(1L);

    CustomerDto customerDto = new CustomerDto("Anand", "Pune", "anand@gmail.com", 1234L);
    ResponseDto expectedResponse = new ResponseDto("", HttpStatus.OK.toString(), null);
    List<CustomerDto> customerDtoList = new ArrayList<>();
    customerDtoList.add(customerServiceUtil.convertToDto(customer));
    expectedResponse.setMessage("Customer Deleted Successfully...!!");
    expectedResponse.setCustomerDtoList(customerDtoList);

    ResponseDto actualResponse = customerService.deleteCustomerById(1L);
    Assert.assertEquals(expectedResponse, actualResponse);
  }
}
