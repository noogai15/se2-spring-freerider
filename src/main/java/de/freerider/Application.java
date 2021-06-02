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
        
        //Create Customer List and ID List
        ArrayList<Customer> cList = new ArrayList<>(Arrays.asList(c1, c2, c3, c4 ,c5));
        ArrayList<String> idList = new ArrayList<>();

        //Saving the List of new Customers
        customerManager.saveAll(cList);
        
        //Double-check Customers and add their newly given IDs to the ID List
        System.err.println("Customers:");
        for (Customer customer : cList) {
            System.err.println(customer.getId() + ": " + customer.getFirstName());
            idList.add(customer.getId());
        }
        
        //Get the newly added Customers by their newly given IDs
        Iterable<Customer> newCList = customerManager.findAllById(idList);
        
        //Overwrite newCList (to test findAll)
        newCList = customerManager.findAll();
        
        //Just to test existsByID; should only print "true"
        for (Customer customer: newCList) {
            System.out.println(Boolean.toString(customerManager.existsById(customer.getId())));
        }

        //Testing the different delete methods, re-adding after each and counting in-between
        //Counts should be changing between 5's and 0's
        System.out.println(customerManager.count());
        
        customerManager.deleteAll(newCList); { 
        
        System.out.println(customerManager.count());
        
        customerManager.saveAll(cList);

        System.out.println(customerManager.count());
        
        customerManager.deleteAllById(idList);

        System.out.println(customerManager.count());
        
        customerManager.saveAll(cList); 

        System.out.println(customerManager.count());
        
        customerManager.deleteAll();

        System.out.println(customerManager.count());
        
        //Testing if duplicates happen; should still be 5
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