package com.wsulbaran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {
    private final CustomerRespository customerRespository;

    public  Main(CustomerRespository customerRespository) {
        this.customerRespository = customerRespository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping()
    public List<Customer> getCustomer (){
        return  customerRespository.findAll();
    }

    record NewCustomer (String name, String email, Integer age) {}

    @PostMapping()
    public void  addCustomer (@RequestBody NewCustomer request) {
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);

        customerRespository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer (@PathVariable("customerId") Integer id) {
        customerRespository.deleteById(id);
    }

    record UpdateCustomer (String name, String email, Integer age) {}
    @PutMapping("{customerId}")
    public void  updateCustomer (@PathVariable("customerId") Integer id, @RequestBody UpdateCustomer request) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);

        customerRespository.save(customer);
    }
}
