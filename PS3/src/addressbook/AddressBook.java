package addressbook;

import addressbook.Address;
import addressbook.Contact;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides an AddressBook object for 
 * storage and operations of Contact entries.
 * 
 * @author Jai Lakhanpal
 * @version 1.0
 * @since 09-27-2017 
 */
public class AddressBook {
	private List<Contact> contactList = new ArrayList<Contact>();
	
	/**
	 * Adds an entry to the AddressBook.
	 * 
	 * @param contact entry to be added
	 * @see addressbook.Contact
	 */
	public void add(Contact contact) {
		contactList.add(contact);
	}
	
	/**
	 * Removes an entry from the AddressBook.
	 *  
	 * @param contact entry to removed
	 * @see addressbook.Contact
	 */
	public void remove(Contact contact) {
		contactList.remove(contact);
	}
	
	/**
	 * Searches for entries in the AddressBook
	 * by any of the Contact properties.
	 * <p>
	 * Calls {@link addressbook.Contact#searchContactFields(String s)}
	 * for each entry in the AddressBook.
	 * 
	 * @param s query to search
	 * @return list of all entries matching the query
	 */
	public List<Contact> search(String s) {
		List<Contact> contactMatchList = new ArrayList<Contact>();
		for (Contact c : contactList) {
			if (c.searchContactFields(s)) {
				contactMatchList.add(c);
			}
		}
		return contactMatchList;
	}
	
	/**
	 * Writes the entries in the AddressBook to a file.
	 * <p>
	 * Method will print a '#' followed by each entry.
	 * <p>
	 * Method will not clear AddressBook after writing to file.
	 * 
	 * @param filename name of file to write to
	 * @throws IOException
	 * @see addressbook.Contact#toString()
	 */
	public void writeAddressBookToFile(String filename) throws IOException {
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
	    for (Contact c : contactList) {
	    		writer.println("#");
	    		writer.println(c.toString());
	    }
	    writer.close();
	}
	
	/**
	 * Reads entries from a text file into the AddressBook.
	 * <p>
	 * File to be read must be in the same format as an
	 * output file from {@link #writeAddressBookToFile(String)}.
	 * <p>
	 * Will not clear AddressBook before reading in entries.
	 * 
	 * @param filename name of file to read from
	 * @throws IOException
	 */
	public void readAddressBookFromFile(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = br.readLine();
		while (line != null) {
			if (line.equals("#")) {
				String firstName = br.readLine();
				String lastName = br.readLine();			
				String street = br.readLine();
				String city = br.readLine();
				String state = br.readLine();
				String zip = br.readLine();
				String country = br.readLine();
				String phoneNumber = br.readLine();
				String emailAddress = br.readLine();
				String note = br.readLine();
				Address a = new Address(street, city, state, zip, country);
				Contact c = new Contact.Builder(firstName).lastName(lastName)
						.postalAddress(a).phoneNumber(phoneNumber)
						.emailAddress(emailAddress).note(note).build();
				contactList.add(c);
			}
			line = br.readLine();
		}
		br.close();
	}
	
	/**
	 * Clears all entries in the AddressBook.
	 */
	public void clear() {
		contactList.clear();
	}
}
