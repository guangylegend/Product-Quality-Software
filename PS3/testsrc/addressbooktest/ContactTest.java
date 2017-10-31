package addressbooktest;

import static org.junit.Assert.*;
import org.junit.Test;
import addressbook.Address;
import addressbook.Contact;

public class ContactTest {
  @Test
  public void TestContact() {
    String firstName = " abc";
    String lastName = "def ";
    String phoneNumber = "+1(234)567-890";
    String emailAddress = "abc.def@123.com ";
    String note = "  abcd\nbcd\r\nabcd\r";
    Address addr = new Address(" abcd\n ", " New York ", " NY ", "+1-(234)-567", " USA ");
    Contact c = new Contact.Builder(firstName).lastName(lastName).postalAddress(addr)
        .phoneNumber(phoneNumber).emailAddress(emailAddress).note(note).build();
    assertEquals("abc", c.firstName);
    assertEquals("def", c.lastName);
    assertEquals("1234567890", c.phoneNumber);
    assertEquals("abc.def@123.com", c.emailAddress);
    assertEquals("abcd bcd abcd", c.note);
    assertEquals(addr, c.postalAddress);
    
  }

  @Test
  public void TestSearchContactFields() {
    String firstName = " abc";
    String lastName = "def ";
    String phoneNumber = "+1(234)567-890";
    String emailAddress = "abc.def@123.com ";
    String note = "  abcd\nbcd\r\nabcd\r";
    Address addr = new Address(" abcd\n ", " New York ", " NY ", "+1-(234)-567", " USA ");
    Contact c = new Contact.Builder(firstName).lastName(lastName).postalAddress(addr)
        .phoneNumber(phoneNumber).emailAddress(emailAddress).note(note).build();
    assertTrue(c.searchContactFields("aBc"));
    assertTrue(c.searchContactFields("Def"));
    assertTrue(c.searchContactFields("1234567890 "));
    assertTrue(c.searchContactFields("abcd"));
    assertTrue(c.searchContactFields("abc.def@123.com"));
    assertTrue(c.searchContactFields("abcd bcd abcd"));
    assertFalse(c.searchContactFields("NJ "));
    
    //should pass this null check
    assertFalse(c.searchContactFields(null));
  }

  @Test
  public void TestEquals() {
    String firstName = " abc";
    String lastName = "def ";
    String phoneNumber = "+1(234)567-890";
    String emailAddress = "abc.def@123.com ";
    String note = "  abcd\nbcd\r\nabcd\r";
    Address addr = new Address(" abcd\n ", " New York ", " NY ", "+1-(234)-567", " USA ");
    Address addrn = new Address(" abc", " New York ", " NY ", "+1-(234)-567", " USA ");
    Contact c1 = new Contact.Builder(firstName).lastName(lastName).postalAddress(addr)
        .phoneNumber(phoneNumber).emailAddress(emailAddress).note(note).build();
    Contact c2 = new Contact.Builder(firstName).lastName(lastName).postalAddress(addr)
        .phoneNumber(phoneNumber).emailAddress(emailAddress).note(note).build();
    Contact c3 = new Contact.Builder(firstName + "a").lastName(lastName).postalAddress(addr)
        .phoneNumber(phoneNumber).emailAddress(emailAddress).note(note).build();
    Contact c4 = new Contact.Builder(firstName).lastName(lastName + "b").postalAddress(addr)
        .phoneNumber(phoneNumber).emailAddress(emailAddress).note(note).build();
    Contact c5 = new Contact.Builder(firstName).lastName(lastName).postalAddress(addrn)
        .phoneNumber(phoneNumber).emailAddress(emailAddress).note(note).build();
    Contact c6 = new Contact.Builder(firstName).lastName(lastName).postalAddress(addr)
        .phoneNumber(phoneNumber + "0").emailAddress(emailAddress).note(note).build();
    Contact c7 = new Contact.Builder(firstName).lastName(lastName).postalAddress(addr)
        .phoneNumber(phoneNumber).emailAddress("c" + emailAddress).note(note).build();
    Contact c8 = new Contact.Builder(firstName).lastName(lastName).postalAddress(addr)
        .phoneNumber(phoneNumber).emailAddress(emailAddress).note(note + " d").build();
    assertTrue(c1.equals(c2));
    assertFalse(c1.equals(c3));
    assertFalse(c1.equals(c4));
    assertFalse(c1.equals(c5));
    assertFalse(c1.equals(c6));
    assertFalse(c1.equals(c7));
    assertFalse(c1.equals(c8));
    
    //should pass this null and empty check
    assertFalse(c1.equals(null));
    assertFalse(c1.equals(""));
  }

  @Test
  public void TestToString() {
    String firstName = " abc";
    String lastName = "def ";
    String phoneNumber = "+1(234)567-890";
    String emailAddress = "abc.def@123.com ";
    String note = "  abcd\nbcd\r\nabcd\r";
    Address addr = new Address(" abcd\n ", " New York ", " NY ", "+1-(234)-567", " USA ");
    Contact c = new Contact.Builder(firstName).lastName(lastName).postalAddress(addr)
        .phoneNumber(phoneNumber).emailAddress(emailAddress).note(note).build();
    assertEquals("abc\n" + "def\n" + "abcd\nNew York\nNY\n1234567\nUSA\n" + "1234567890\n"
        + "abc.def@123.com\n" + "abcd bcd abcd", c.toString());
  }
}
