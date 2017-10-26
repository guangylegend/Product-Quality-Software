package addressbook;

/**
 * Container for various address-related fields.
 * 
 * @author Jai Lakhanpal
 * @version 1.0
 * @since 09-27-2017
 */
public class Address {
	public String street;
	public String city;
	public String state;
	public String zip;
	public String country;
	
	/**
	 * Constructs an Address object.
	 * 
	 * @param street line breaks, leading and ending white spaces will be trimmed
	 * @param city leading an ending white spaces will be trimmed
	 * @param state leading an ending white spaces will be trimmed
	 * @param zip '+', '-', '(', ')' will be removed; leading and ending white spaces will be trimmed
	 * @param country leading an ending white spaces will be trimmed
	 * @return Address object with updated fields
	 */
	public Address(String street, String city, String state, String zip, String country) {
		this.street = removeLineBreaksAndTrim(street);
		this.city = city.trim();
		this.state = state.trim();
		this.zip = removePhoneSymbols(zip).trim();
		this.country = country.trim();
	}
	
	/**
     * Searches for exact matches to any of the Address properties.
     * 
     * @param s query to search (input will be modified to match each property's formatting)
     * @return true if any of the Address properties matches exactly to query
     */
	public boolean searchAddressFields(String s) {
		s = s.toLowerCase().trim();
		return street.toLowerCase().equals(removeLineBreaksAndTrim(s))
				|| city.toLowerCase().equals(s)
				|| state.toLowerCase().equals(s)
				|| zip.equals(removePhoneSymbols(s))
				|| country.toLowerCase().equals(s);
	}
	
	/**
     * Checks if two Address objects are equal.
     * <p>
     * Two Address objects are equal if all Address properties match exactly.
     * 
     * @param a Address to compare
     * @return true if Addresses are equal
     */
	public boolean equals(Address a) {
		return street.toLowerCase().equals(a.street.toLowerCase())
				&& city.toLowerCase().equals(a.city.toLowerCase())
				&& state.toLowerCase().equals(a.state.toLowerCase())
				&& zip.equals(a.zip)
				&& country.toLowerCase().equals(a.country.toLowerCase());
	}
	
	/**
     * Returns a string with each Address property on a separate line.
     */
	@Override
	public String toString() {
		return street + "\n"
				+ city + "\n"
				+ state + "\n"
				+ zip + "\n"
				+ country;
	}
	
	/**
	 * Removes '+', '-', '(', ')' symbols from a given string.
	 * 
	 * @param s string to modify
	 * @return string with symbols removed
	 */
	public static String removePhoneSymbols(String s) {
		return s.replaceAll("[\\-\\+\\(\\)]", "");
	}
	
	/**
	 * Removes line breaks, leading and ending white spaces from a given string.
	 * 
	 * @param s string to modify
	 * @return string with symbols removed
	 */
	public static String removeLineBreaksAndTrim(String s) {
		return s.replaceAll("\\r\\n|\\r|\\n", " ").trim();
	}
}
