package service;

/**
 * A contact's address.
 * @author aarnav
 */
public class Address extends SearchableContactProperty{
  public static final int ADDRESS_LIMIT = 35;
  public static final int ZIP_LIMIT = 12;
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String country;
  private String zip;
  
  /**
   * Constructor.
   * @param builder
   */
  Address(AddressBuilder builder) {
    super();
    this.address1 = builder.address1;
    this.address2 = builder.address2;
    this.city = builder.city;    
    this.state = builder.state;   
    this.country = builder.country; 
    this.zip = builder.zip;
  }

  /**
   * Member fields to allow search operation on.
   */
  @Override
  public void populateSearchTerms() {
    addWords(address1);
    addWords(address2);
    addWords(city);
    addWords(state);
    addWords(country);
    addWords(zip);    
  }

  /**
   * Builder class to help build the address and simultaneously validate the various fields.
   * @author aarnav
   *
   */
  public static class AddressBuilder {
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String zip;
    
    public AddressBuilder() {
    }
    
    public AddressBuilder address1(String address1) {
      if (address1.length() > ADDRESS_LIMIT) {
        throw new IllegalArgumentException();
      }
      this.address1 = address1.trim().replaceAll("\\s+", " ");
      return this;
    }
    
    public AddressBuilder address2(String address2) {
      if (address2.length() > ADDRESS_LIMIT) {
        throw new IllegalArgumentException();
      }
      this.address2 = address2.trim().replaceAll("\\s+", " ");;
      return this;
    }
    
    public AddressBuilder city(String city) {
      if (city.length() > ADDRESS_LIMIT) {
        throw new IllegalArgumentException();
      }
      this.city = city.trim().replaceAll("\\s+", " ");;
      return this;
    }
    
    public AddressBuilder state(String state) {
      if (state.length() > ADDRESS_LIMIT) {
        throw new IllegalArgumentException();
      }
      this.state = state.trim().replaceAll("\\s+", " ");;
      return this;
    }
    
    
    public AddressBuilder country(String country) {
      if (country.length() > ADDRESS_LIMIT) {
        throw new IllegalArgumentException();
      }
      this.country = country.trim().replaceAll("\\s+", " ");;
      return this;
    }
    
    public AddressBuilder zip(String zip) {
      if (zip.length() > ZIP_LIMIT) {
        throw new IllegalArgumentException();
      }
      this.zip = zip.trim().replaceAll("\\s+", " ");;
      return this;
    }
    
    public Address build() {
      Address address = new Address(this);
      return address;
    }
  }
}
