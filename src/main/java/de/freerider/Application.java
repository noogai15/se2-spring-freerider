package de.freerider;

import de.freerider.model.Customer;
import de.freerider.repository.CrudRepository;
import de.freerider.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
        CrudRepository<Customer, String> customerManager = new CustomerRepository();
        
        //Create Customers
        Customer c1 = new Customer("Mühlbauer", "Thomas", "0171");
        Customer c2 = new Customer("Schmid", "Lisa", "0162");
        Customer c3 = new Customer("Wolf", "Aldric", "0161");
        Customer c4 = new Customer("Bohn", "Alf", "0133");
        Customer c5 = new Customer("Scholz", "Elke", "0178");
        
        //
        ArrayList<Customer> cList = new ArrayList<>(Arrays.asList(c1, c2, c3, c4 ,c5));
        ArrayList<String> idList = new ArrayList<>();
        
        
        
        //TEST save / saveAll
        customerManager.saveAll(cList);
        
        System.err.println("Customers:");
        for (Customer customer : cList) {
            System.err.println(customer.getId() + ": " + customer.getFirstName());
            idList.add(customer.getId());
        }
        
        
        
        
        //TEST findAllById / findById
        Iterable<Customer> testList = customerManager.findAllById(idList);
        
        //TEST existsById (should only print true)
        for (Customer customer: testList) {
            System.out.println(Boolean.toString(customerManager.existsById(customer.getId())));
        }
        //TEST count: Should be 5
        System.out.println(customerManager.count());
        
        
        
        
        //TEST deletes---------
        customerManager.deleteAll(testList); { //delete and deleteAll
        
        //TEST count: Should be 0
        System.out.println(customerManager.count());

        customerManager.saveAll(cList); //Re-add and TEST findAll
        
        //TEST count: Should be 5
        System.out.println(customerManager.count());
        
        customerManager.deleteAllById(idList); //deletebyId and deleteAllById
        
        //TEST count: Should be 0
        System.out.println(customerManager.count());
        
        customerManager.saveAll(cList); //Re-add and TEST findAll
        
        //TEST count: Should be 5
        System.out.println(customerManager.count());
        
        customerManager.deleteAll(); //deleteAll
        
        //TEST count: Should be 0
        System.out.println(customerManager.count());
        //TEST deletes---------
        
        //TEST for if duplicates happen; should still be 5
        customerManager.saveAll(cList);
        customerManager.saveAll(cList);
        System.out.println(customerManager.count());
        
    }
        
    }
    
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
            
        };
    }
    
}