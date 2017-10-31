package addressbooktest;

import static org.junit.Assert.*;
import org.junit.Test;
import addressbook.Address;

public class AddressTest {
  @Test
  public void testRemovePhoneSymbols() {
    String s = "+1-(234)-567";
    assertEquals("1234567", Address.removePhoneSymbols(s));
    
    //should pass this null check
    assertNull(Address.removePhoneSymbols(null));
  }

  @Test
  public void testRemoveLineBreaksAndTrim() {
    String s = "  abcd\nbcd\r\nabcd\r";
    assertEquals("abcd bcd abcd", Address.removeLineBreaksAndTrim(s));
    
    //should pass this null check
    assertNull(Address.removeLineBreaksAndTrim(null));
  }

  @Test
  public void testAddress() {
    Address addr = new Address(" abcd\n ", " New York ", " NY ", "+1-(234)-567", " USA ");
    assertEquals("abcd", addr.street);
    assertEquals("New York", addr.city);
    assertEquals("NY", addr.state);
    assertEquals("1234567", addr.zip);
    assertEquals("USA", addr.country);
  }

  @Test
  public void TestToString() {
    Address addr = new Address(" abcd\n ", " New York ", " NY ", "+1-(234)-567", " USA ");
    assertEquals("abcd\nNew York\nNY\n1234567\nUSA", addr.toString());
  }
  
  @Test
  public void TestSearchAddressFields() {
    Address addr = new Address(" abcd\n ", " New York ", " NY ", "+1-(234)-567", " USA ");
    assertTrue(addr.searchAddressFields(" Abcd"));
    assertTrue(addr.searchAddressFields("NEW YORK"));
    assertTrue(addr.searchAddressFields("ny "));
    assertTrue(addr.searchAddressFields("1234567"));
    assertTrue(addr.searchAddressFields(" usa "));
    assertFalse(addr.searchAddressFields("Nj "));
    
    //should pass this null check
    assertFalse(addr.searchAddressFields(null));
  }
  
  @Test
  public void TestEquals(){
    Address addr1 = new Address(" abcd\n ", " New York ", " NY ", "+1-(234)-567", " USA ");
    Address addr2 = new Address(" abcd\n ", " New York ", " NY ", "+1-(234)-567", " USA ");
    Address addr3 = new Address("", " New York ", " NY ", "+1-(234)-567", " USA ");
    Address addr4 = new Address(" abcd\n ", "", " NY ", "+1-(234)-567", " USA ");
    Address addr5 = new Address(" abcd\n ", " New York ", "", "+1-(234)-567", " USA ");
    Address addr6 = new Address(" abcd\n ", " New York ", " NY ", "", " USA ");
    Address addr7 = new Address(" abcd\n ", " New York ", " NY ", "+1-(234)-567", "");
    assertTrue(addr1.equals(addr2));
    assertFalse(addr1.equals(addr3));
    assertFalse(addr1.equals(addr4));
    assertFalse(addr1.equals(addr5));
    assertFalse(addr1.equals(addr6));
    assertFalse(addr1.equals(addr7));
    
  //should pass this null and empty check
    assertFalse(addr1.equals(null));
    assertFalse(addr1.equals(""));
  }
}
