package de.freerider.repository;

import de.freerider.model.Customer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 *
 * @author ykohn
 */
@SpringBootTest
public class CustomerRepositoryTest {
    
    @Autowired
            CrudRepository<Customer,String> customerRepository;
    
    
    //Two test Customers
    public Customer mats;
    public Customer thomas;
    
    public ArrayList<Customer> customers;
    public HashMap<String, Customer> allCustomers;
    
    //For resetting
    @BeforeEach
    public void initialize(){
        System.out.print("Re-initializing Customers\n");
        mats = new Customer(); //Mats won't have a custom ID
        thomas = new Customer(); //Thomas will have a custom ID
        customers = new ArrayList<>();
        
        thomas.setId("thomasId");
        customers.add(mats);
        customers.add(thomas);
        
    }
    
    //SAVE TESTS///////////////////
    @Test
    public void testSave() {
        System.out.println("save");
        CustomerRepository instance = new CustomerRepository();
        
        instance.save(thomas);
        long count1 = instance.count();
        Customer saveResult = instance.save(mats);
        long count2 = instance.count();
        Optional<Customer> findResult = instance.findById(mats.getId());
        
        //Regular Cases
        assertEquals(count1, count2 - 1);
        assertEquals(mats, saveResult);
        assertNotNull(mats.getId());
        assertEquals(instance.findById(mats.getId()), findResult);
        assertEquals(thomas.getId(), "thomasId");
        
        //Special Cases
        assertThrows(IllegalArgumentException.class, () -> {instance.save(null);});
        Customer c1 = new Customer();
        Customer c2 = new Customer();
        c1.setId("testId");
        c2.setId("testId");
        assertEquals(instance.save(c1), c1);
        assertEquals(instance.save(c2), c1); //c1 is replaced by c2 and c1 is returned
        
        
        
    }
    
    @Test
    public void testSaveAll() {
        System.out.println("saveAll");
        CustomerRepository instance = new CustomerRepository();
        
        long count1 = instance.count();
        List<Customer> saveResult = (List<Customer>) instance.saveAll(customers);
        long count2 = instance.count();
        
        //Regular Cases
        assertEquals(count1, count2 - 2);
        assertEquals(saveResult, (List<Customer>) instance.saveAll(customers));
        assertNotNull(saveResult.get(0).getId());
        
        //Special Cases
        assertThrows(IllegalArgumentException.class, () -> {instance.saveAll(null);});
        
    }
    
    //FIND TESTS///////////////////
    @Test
    public void testFindById() {
        System.out.println("findById");
        String id = "matsId";
        CustomerRepository instance = new CustomerRepository();
        instance.save(mats);
        
        Optional<Customer> expResult = Optional.ofNullable(instance.getAll().get(id));
        Optional<Customer> result = instance.findById(id);
        
        //Regular Cases
        assertEquals(expResult, result);
        
        //Special Cases
        assertThrows(IllegalArgumentException.class, () -> {instance.findById(null);});
        
    }
    
    @Test
    public void testExistsById() {
        System.out.println("existsById");
        String id = "thomasId";
        CustomerRepository instance = new CustomerRepository();
        instance.save(thomas);
        
        boolean expResult = true;
        boolean result = instance.existsById(id);
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        CustomerRepository instance = new CustomerRepository();
        allCustomers = instance.getAll();
        
        ArrayList<Customer> expResult = new ArrayList<>();
        allCustomers.forEach((key, value) -> expResult.add(value));
        List<Customer> result1 = (List<Customer>) instance.findAll();
        instance.save(new Customer());
        List<Customer> result2 = (List<Customer>) instance.findAll();
        
        //Regular Cases
        assertEquals(expResult, result1);
        assertEquals(result1.size() + 1, result2.size());
        
        
    }
    
    
    @Test
    public void testFindAllById() {
        System.out.println("findAllById");
        mats.setId("matsId");
        Iterable<String> ids = Arrays.asList("matsId", "thomasId");
        CustomerRepository instance = new CustomerRepository();
        instance.save(mats);
        instance.save(thomas);
        
        Iterable<Customer> expResult = Arrays.asList(mats, thomas);
        Iterable<Customer> result = instance.findAllById(ids);
        
        //Regular Cases
        assertEquals(expResult, result);
        
        //Special Cases
        assertThrows(IllegalArgumentException.class, () -> {instance.findAllById(null);});

    }
    
    //COUNT TEST///////////////////
    @Test
    public void testCount() {
        System.out.println("count");
        CustomerRepository instance = new CustomerRepository();
        instance.save(mats);
        
        long expResult = 1L;
        long result = instance.count();
        
        //Regular Cases
        assertEquals(expResult, result);
        
    }
    
    //DELETE TESTS///////////////////
    @Test
    public void testDeleteById() {
        System.out.println("deleteById");
        String id = "thomasId";
        CustomerRepository instance = new CustomerRepository();
        instance.save(thomas);
        
        long initCount = instance.count();
        instance.deleteById(id);
        long latterCount= instance.count();
        
        //Regular Cases
        assertEquals(initCount - 1, latterCount);
        
        //Special Cases
        assertThrows(IllegalArgumentException.class, () -> {instance.deleteById(null);});

        
    }
    
    @Test
    public void testDelete() {
        System.out.println("delete");
        CustomerRepository instance = new CustomerRepository();
        instance.save(mats);
        
        long initCount = instance.count();
        instance.delete(mats);
        long latterCount= instance.count();
        
        //Regular Cases
        assertEquals(initCount - 1, latterCount);
        
        //Special Cases
        assertThrows(IllegalArgumentException.class, () -> {instance.delete(null);});

        
    }
    
    @Test
    public void testDeleteAllById() {
        System.out.println("deleteAllById");
        Iterable<? extends String> ids = Arrays.asList("matsId", "thomasId");
        CustomerRepository instance = new CustomerRepository();
        mats.setId("matsId");
        instance.save(mats);
        instance.save(thomas);
        
        long initCount = instance.count();
        instance.deleteAllById(ids);
        long latterCount= instance.count();
        
        //Regular Cases
        assertEquals(initCount - 2, latterCount);
        
        //Special Cases
        assertThrows(IllegalArgumentException.class, () -> {instance.deleteAllById(null);});

        
    }
    
    @Test
    public void testDeleteAll_Iterable() {
        System.out.println("deleteAll");
        Iterable<? extends Customer> entities = customers;
        CustomerRepository instance = new CustomerRepository();
        instance.save(mats);
        instance.save(thomas);
        
        long initCount = instance.count();
        instance.deleteAll(entities);
        long latterCount = instance.count();
        
        //Regular Cases
        assertEquals(initCount - customers.size(), latterCount);
        
        //Special Cases
        assertThrows(IllegalArgumentException.class, () -> {instance.deleteAll(null);});

    }
    
    @Test
    public void testDeleteAll_0args() {
        System.out.println("deleteAll");
        CustomerRepository instance = new CustomerRepository();
        instance.deleteAll();
        
        //Regular Cases
        assertEquals(instance.count(), 0);
        
    }
    
}
