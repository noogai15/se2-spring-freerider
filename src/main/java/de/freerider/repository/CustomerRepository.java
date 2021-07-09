package de.freerider.repository;
import de.freerider.datamodel.Customer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.stereotype.Component;


@Component
public class CustomerRepository implements CrudRepository<Customer, String> {
    //
    private final IDGenerator idGen = new IDGenerator( "C", IDGenerator.IDTYPE.NUM, 6 );
    private final  HashMap<String, Customer> customers = new HashMap<>();
    
    @Override
    public <S extends Customer> S save(S entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entitiy can't be null");
        }
        
        String newID = idGen.nextId();
        
        if (entity.getId() == null || entity.getId().isEmpty()) {
            while(existsById(newID)) {
                newID = idGen.nextId();
            }
            entity.setId(newID);
        }
        
        if (existsById(entity.getId())){
            Customer old = customers.get(entity.getId()); //Customer to be replaced
            delete(old); //delete that Customer
            customers.put(entity.getId(), entity); //Put in new Customer with same Id as the old one
            return (S) old; //return the old Customer
        }
        
        customers.put(entity.getId(), entity);
        return entity;
    }
    
    @Override
    public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
        if (entities == null) {
            throw new IllegalArgumentException("Entities can't be null");
        }
        
        for (S entity : entities) {
            if(entity != null){
                save(entity);
            }
        }
        return entities;
    }
    
    @Override
    public Optional<Customer> findById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID can't be null");
        }
        Optional<Customer> customer = Optional.ofNullable(customers.get(id));
        return customer;
    }
    
    @Override
    public boolean existsById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID can't be null");
        }
        return customers.containsKey(id);
    }
    
    @Override
    public Iterable<Customer> findAll() {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        customers.forEach((key, value) -> allCustomers.add(value));
        return allCustomers;
    }
    
    @Override
    public Iterable<Customer> findAllById(Iterable<String> ids) {
        if (ids == null) {
            throw new IllegalArgumentException("IDs can't be null");
        }
        ArrayList<Customer> c = new ArrayList<>();
        
        for (String id: ids) {
            if(!id.trim().equals("")){
                Optional<Customer> optional = findById(id);
                c.add(optional.get());
            }
        }
        return c;
    }
    
    @Override
    public long count() {
        return customers.size();
    }
    
    @Override
    public void deleteById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID can't be null");
        }
        customers.remove(id);
        
    }
    
    @Override
    public void delete(Customer entity) {
        if (entity == null || entity.getId() == null) {
            throw new IllegalArgumentException("Entity can't be null");
        }
        customers.remove(entity.getId());
    }
    
    @Override
    public void deleteAllById(Iterable<? extends String> ids) {
        if (ids == null) {
            throw new IllegalArgumentException("IDs can't be null");
        }
        for (String id: ids) {
            deleteById(id);
        }
    }
    
    @Override
    public void deleteAll(Iterable<? extends Customer> entities) {
        if (entities == null) {
            throw new IllegalArgumentException("Entities can't be null");
        }
        for(Customer customer: entities) {
            delete(customer);
        }
    }
    
    @Override
    public void deleteAll() {
        customers.clear();
    }
    
    public HashMap<String, Customer> getAll() {
        return customers;
    }
    
    
    
}
