package addressbook;

import addressbook.Address;

/**
 * Container for various contact-related fields.
 * <p>
 * Instantiate using the Builder class.
 * 
 * @author Jai Lakhanpal
 * @version 1.0
 * @since 09-27-2017
 * @see Builder
 */
public class Contact {
	public String firstName;
    public String lastName;
    /** @see addressbook.Address */
    public Address postalAddress;
    public String phoneNumber;
    public String emailAddress;
    public String note;
    
    /**
     * Builder class used for instantiating Contact entries.
     * <p>
     * Only first name is required to build a Contact.
     * 
     * @author Jai Lakhanpal
     * @version 1.0
     * @since 09-27-2017
     */
    public static class Builder {
    		/** Required parameter */
    		public String firstName;
        
    		/** Optional parameter: "" by default */ 
    		public String lastName = "";
    		/** 
    		 * Optional parameter: "" by default 
    		 * @see addressbook.Address 
    		 */
    		public Address postalAddress = new Address("", "", "", "", "");
    		/** Optional parameter: "" by default */ 
    		public String phoneNumber = "";
    		/** Optional parameter: "" by default */ 
    		public String emailAddress = "";
    		/** Optional parameter: "" by default */ 
    		public String note = "";
    		
    		/**
    		 * Creates a Builder object using given first name.
    		 * <p>
    		 * Leading and ending white spaces will be trimmed.
    		 * 
    		 * @param firstName first name of contact
    		 */
    		public Builder(String firstName) {
    			this.firstName = firstName.trim();
    		}
    		
    		/**
    		 * Sets the contact's last name (optional).
    		 * <p>
    		 * Leading and ending white spaces will be trimmed.
    		 * 
    		 * @param s last name of contact
    		 * @return Builder object with updated last name
    		 */
    		public Builder lastName(String s) {
    			lastName = s.trim();
    			return this;
    		}
    		
    		/**
    		 * Sets the contact's address (optional).
    		 * 
    		 * @param a address of contact
    		 * @return Builder object with updated address
    		 * @see addressbook.Address
    		 */
    		public Builder postalAddress(Address a) {
    			postalAddress = a;
    			return this;
    		}
    		
    		/**
    		 * Sets the contact's phone number (optional).
    		 * <p>
    		 * '+', '-', '(', ')' will be removed.
    		 * Leading and ending white spaces will be trimmed.
    		 * 
    		 * @param s phone number of contact
    		 * @return Builder object with updated phone number
    		 */
    		public Builder phoneNumber(String s) {
    			phoneNumber = Address.removePhoneSymbols(s).trim();
    			return this;
    		}
    		
    		/**
    		 * Sets the contact's email address (optional).
    		 * <p>
    		 * Leading and ending white spaces will be trimmed.
    		 * 
    		 * @param s email address of contact
    		 * @return Builder object with updated email address
    		 */
    		public Builder emailAddress(String s) {
    			emailAddress = s.trim();
    			return this;
    		}
    		
    		/**
    		 * Sets the contact's note (optional).
    		 * <p>
    		 * Newline characters will be removed.
    		 * Leading and ending white spaces will be trimmed.
    		 * 
    		 * @param s note for contact
    		 * @return Builder object with updated note
    		 */
    		public Builder note(String s) {
    			note = Address.removeLineBreaksAndTrim(s);
    			return this;
    		}
    		
    		/**
    		 * Builds contact object.
    		 * <p>
    		 * Call on Builder object after setting
    		 * all optional parameters.
    		 * 
    		 * @return Contact object with fields from Builder object
    		 */
    		public Contact build() {
    			return new Contact(this);
    		}
    }
    
    private Contact(Builder builder) {
    		firstName = builder.firstName;
    		lastName = builder.lastName;
    		postalAddress = builder.postalAddress;
    		phoneNumber = builder.phoneNumber;
    		emailAddress = builder.emailAddress;
    		note = builder.note;
    }
    
    /**
     * Searches for exact matches to any of the Contact properties.
	 * <p>
	 * Calls {@link addressbook.Address#searchAddressFields(String s)}
	 * to search Contact's address field.
     * 
     * @param s query to search (input will be modified to match each property's formatting)
     * @return true if any of the Contact properties matches exactly to query
     */
    public boolean searchContactFields(String s) {
    		s = s.toLowerCase().trim();
    		return firstName.toLowerCase().equals(s)
    				|| lastName.toLowerCase().equals(s) 
    				|| postalAddress.searchAddressFields(s) 
    				|| emailAddress.toLowerCase().equals(s)
    				|| note.toLowerCase().equals(Address.removeLineBreaksAndTrim(s))
    				|| phoneNumber.equals(Address.removePhoneSymbols(s));
    }
    
    /**
     * Checks if two Contact objects are equal.
     * <p>
     * Two Contacts are equal if all Contact properties match exactly.
     * 
     * @param c Contact to compare
     * @return true if Contacts are equal
     */
    public boolean equals(Contact c) {
    		return firstName.toLowerCase().equals(c.firstName.toLowerCase())
    				&& lastName.toLowerCase().equals(c.lastName.toLowerCase()) 
    				&& postalAddress.equals(c.postalAddress) 
    				&& emailAddress.toLowerCase().equals(c.emailAddress.toLowerCase())
    				&& note.toLowerCase().equals(c.note.toLowerCase())
    				&& phoneNumber.equals(c.phoneNumber); 
    }
    
    /**
     * Returns a string with each Contact property on a separate line.
     */
    @Override
    public String toString() {
		return firstName + "\n"
				+ lastName + "\n"
				+ postalAddress.toString() + "\n"
				+ phoneNumber + "\n"
				+ emailAddress + "\n"
				+ note;
	}
}
