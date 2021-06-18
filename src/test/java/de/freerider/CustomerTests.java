package de.freerider;
        
import de.freerider.model.Customer;
import de.freerider.model.Customer.Status;
import static org.junit.Assert.assertThrows;
        import org.junit.jupiter.api.AfterAll;
        import org.junit.jupiter.api.AfterEach;
        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.junit.jupiter.api.Assertions.assertFalse;
        import static org.junit.jupiter.api.Assertions.assertNotEquals;
        import static org.junit.jupiter.api.Assertions.assertNotNull;
        import static org.junit.jupiter.api.Assertions.assertNull;
        import static org.junit.jupiter.api.Assertions.assertTrue;
        import org.junit.jupiter.api.BeforeAll;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.springframework.boot.test.context.SpringBootTest;
        
@SpringBootTest
public class CustomerTests {
     public Customer mats;
     public Customer thomas;
        
        @BeforeEach
        public void initialize(){
        mats = new Customer();
        thomas = new Customer();
        
        }
        
        //ID-TESTS
        @Test
        void testIdNull() {
            assertNull(mats.getId());
            assertNull(thomas.getId());
            
        }
        @Test
        void testSetId() {
            assertTrue(mats.setId(("newId1")));
            assertTrue(thomas.setId(("newId1")));
            
        }
        @Test
        void testSetIdOnlyOnce() {
            mats.setId("newId1");
            thomas.setId("newId2");
            
            assertFalse(mats.setId("anotherNewId1"));
            assertFalse(thomas.setId("anotherNewId2"));
            
        }
        @Test
        void testResetId(){
            assertTrue(mats.setId(null));
            assertTrue(thomas.setId(null));
        }
        
        
        //NAME-TESTS
        @Test
        void testNamesInitial() {
            assertTrue(mats.getFirstName() == "");
            assertTrue(mats.getLastName() == "");
            assertTrue(thomas.getFirstName() == "");
            assertTrue(thomas.getLastName() == "");
            
        }@Test
        void testNamesSetNull() {
            mats.setFirstName(null);
            mats.setLastName(null);
            thomas.setFirstName(null);
            thomas.setLastName(null);
            
            assertTrue(mats.getFirstName() == "");
            assertTrue(mats.getLastName() == "");
            assertTrue(thomas.getFirstName() == "");
            assertTrue(thomas.getLastName() == "");
            
        }@Test
        void testSetNames() {
            mats.setFirstName("Matthias");
            mats.setLastName("Fischer");
            mats.setFirstName("Thomas");
            mats.setLastName("Wagner");
            
            assertTrue(mats.getFirstName() != "" || mats.getFirstName() != null);
            assertTrue(mats.getLastName() != "" || mats.getLastName() != null);
            assertTrue(thomas.getFirstName() != "" || thomas.getFirstName() != null);
            assertTrue(thomas.getLastName() != "" || thomas.getLastName() != null);
        }
        
        //CONTACT-TEST
        @Test
        void testContactsInital() {
            
            assertTrue(mats.getContact() == "");
            assertTrue(thomas.getContact() == "");
        }

        @Test
        void testContactsSetNull() {
            mats.setContact(null);
            thomas.setContact(null);
            
            assertTrue(mats.getContact()== "");
            assertTrue(thomas.getContact() == "");
            
        }@Test
        void testSetContact(){
            mats.setContact("0171");
            mats.setContact("0162");
            
            assertTrue(mats.getContact() != "" || mats.getContact() != null);
            assertTrue(thomas.getContact() != "" || thomas.getContact() != null);            
    
        }

        
        //STATUS-TESTS
        @Test
        void testStatusInital() {
            assertEquals(mats.getStatus(), Status.NEW);
            assertEquals(thomas.getStatus(), Status.NEW);
        }
        @Test
        void testSetStatus() {
            mats.setStatus(Status.ACTIVE);  
            thomas.setStatus(Status.ACTIVE);
            
            assertEquals(mats.getStatus(), Status.ACTIVE);
            assertEquals(thomas.getStatus(), Status.ACTIVE);            
            
        }
        

}


