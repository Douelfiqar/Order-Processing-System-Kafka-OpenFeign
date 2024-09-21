package org.example.customerservice.service;

import jakarta.transaction.Transactional;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.example.customerservice.entitie.Customer;
import org.example.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(int id, Customer customerDetails) {
        Customer existingCustomer = getCustomerById(id);
        existingCustomer.setNom(customerDetails.getNom());
        existingCustomer.setLastName(customerDetails.getLastName());
        existingCustomer.setEmail(customerDetails.getEmail());
        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(int id) {
        Customer existingCustomer = getCustomerById(id);
        customerRepository.delete(existingCustomer);
    }
}
