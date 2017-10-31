package addressbooktest;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import addressbook.Address;
import addressbook.AddressBook;
import addressbook.Contact;

public class AddressBookTest {

  @Test
  public void TestAddressBook(){
    AddressBook addrbook = new AddressBook();
    List<Contact> list = addrbook.search("abc");
    assertTrue(list.isEmpty());
    
    String firstName = " abc";
    String lastName = "def ";
    String phoneNumber = "+1(234)567-890";
    String emailAddress = "abc.def@123.com ";
    String note = "  abcd\nbcd\r\nabcd\r";
    Address addr = new Address(" abcd\n ", " New York ", " NY ", "+1-(234)-567", " USA ");
    Contact c = new Contact.Builder(firstName).lastName(lastName).postalAddress(addr)
        .phoneNumber(phoneNumber).emailAddress(emailAddress).note(note).build();
    addrbook.add(c);
    
    list = addrbook.search("abc");
    assertEquals(1, list.size());
    assertTrue(list.get(0).equals(c));
    
    list = addrbook.search("ab");
    assertTrue(list.isEmpty());
    
    addrbook.remove(c);
    list = addrbook.search("abc");
    assertTrue(list.isEmpty());
    
    addrbook.add(c);
    addrbook.clear();
    list = addrbook.search("abc");
    assertTrue(list.isEmpty());
    
    //should pass this null check
    addrbook.add(null);
    list = addrbook.search("abc");
    assertTrue(list.isEmpty());
    
    addrbook.remove(null);
  }
  
  @Test(expected = IOException.class)
  public void TestReadAddressBookFromFile() throws Exception{
    AddressBook addrbook = new AddressBook();
    addrbook.readAddressBookFromFile("");
  }
  
  @Test(expected = IOException.class)
  public void TestReadAddressBookFromNull() throws Exception{
    AddressBook addrbook = new AddressBook();
    addrbook.readAddressBookFromFile(null);
  }
  
  @Test(expected = IOException.class)
  public void TestWriteAddressBookToFile() throws Exception{
    AddressBook addrbook = new AddressBook();
    addrbook.writeAddressBookToFile("/invalid/filename");
  }
  
  @Test(expected = IOException.class)
  public void TestWriteAddressBookToNull() throws Exception{
    AddressBook addrbook = new AddressBook();
    addrbook.writeAddressBookToFile(null);
  }
  
  @Test
  public void TestAddressBookAndFile(){
    AddressBook addrbook = new AddressBook();
    
    String firstName = " abc";
    String lastName = "def ";
    String phoneNumber = "+1(234)567-890";
    String emailAddress = "abc.def@123.com ";
    String note = "  abcd\nbcd\r\nabcd\r";
    Address addr = new Address(" abcd\n ", " New York ", " NY ", "+1-(234)-567", " USA ");
    Contact c = new Contact.Builder(firstName).lastName(lastName).postalAddress(addr)
        .phoneNumber(phoneNumber).emailAddress(emailAddress).note(note).build();
    addrbook.add(c);
    
    try {
      addrbook.writeAddressBookToFile("output");
    } catch (IOException e) {
      fail("Unexpected Exception");
    }
    
    try {
      addrbook.readAddressBookFromFile("output");
    } catch (IOException e) {
      fail("Unexpected Exception");
    }
    List<Contact> list = addrbook.search("abc");
    assertEquals(2, list.size());
    assertTrue(list.get(0).equals(c));
    assertTrue(list.get(1).equals(c));
    
  }
}
