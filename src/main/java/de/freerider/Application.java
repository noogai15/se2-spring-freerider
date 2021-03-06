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
        Customer c1 = new Customer();
        c1.setFirstName("Thomas");
        c1.setLastName("Mühlbauer");
        c1.setContact("0171");
        
        Customer c2 = new Customer();
        c2.setFirstName("Lisa");
        c2.setLastName("Schmid");
        c2.setContact("0162");
        
        Customer c3 = new Customer();
        c3.setFirstName("Wolf");
        c3.setLastName("Aldric");
        c3.setContact("0161");
        
        Customer c4 = new Customer();
        c4.setFirstName("Bohn");
        c4.setLastName("Alf");
        c4.setContact("0133");        
        
        Customer c5 = new Customer();
        c5.setFirstName("Elke");
        c5.setLastName("Scholz");
        c5.setContact("0178");
        
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