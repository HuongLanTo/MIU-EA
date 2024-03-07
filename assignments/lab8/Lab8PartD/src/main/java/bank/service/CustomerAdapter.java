package bank.service;

import bank.domain.Customer;

public class CustomerAdapter {
    public static Customer getCustomerFromCustomerResponse(CustomerResponse customerResponse){
        Customer customer = new Customer();
        customer.setName(customerResponse.getName());
        return customer;
    }
    public static CustomerResponse getCustomerResponseFromCustomer(Customer customer){
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setName(customer.getName());
        return customerResponse;
    }
}
