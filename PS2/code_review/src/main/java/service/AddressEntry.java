package service;

import org.json.JSONObject;

/**
 * Defines the contents of an address entry for a person.
 * @author aarnav
 */
public class AddressEntry {
  private final Long id;
  private Name name;
  private Address address;
  private PhoneNumber phoneNumber;
  private Email email;
  private Note note;

  /**
   * Constructor.
   * @param builder
   */
  public AddressEntry(EntryBuilder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.phoneNumber = builder.phoneNumber;
    this.address = builder.address;
    this.phoneNumber = builder.phoneNumber;
    this.email = builder.email;
    this.note = builder.note;
  }
  
  /**
   * Create a JSON object with all member instances of the objects of the member 
   * instances of the AddressEntries.
   * @return a JSON Object
   */
  public JSONObject jsonify() {
    JSONObject entryJson = new JSONObject();
    JSONObject nameJson = name.jsonify();
    addJSONData(nameJson, entryJson);
    JSONObject addressJson = address.jsonify();
    addJSONData(addressJson, entryJson);
    JSONObject phoneNumberJson = phoneNumber.jsonify();
    addJSONData(phoneNumberJson, entryJson);
    JSONObject emailJson = email.jsonify();
    addJSONData(emailJson, entryJson);
    JSONObject noteJson = note.jsonify();
    addJSONData(noteJson, entryJson);
    return entryJson;
  }
  
  /**
   * Return the count of matches found with a search term by comparing all its 
   * "searchable" contact properties.
   * @param searchTerm
   * @return number of matches
   */
  public Integer searchEntry(String searchTerm) {
    Integer count = 0;
    count += name.getSearchCount(searchTerm);
    count += address.getSearchCount(searchTerm);
    count += phoneNumber.getSearchCount(searchTerm);
    count += email.getSearchCount(searchTerm);
    count += note.getSearchCount(searchTerm);
    return count;
  }
  
  /**
   * Return the id associated with a contact.
   * @return
   */
  public Long getId() {
    return id;
  }
  
  /**
   * Return the name associated with a contact.
   * @return
   */
  public Name getName() {
    return name;
  }
  
  /**
   * Return the address associated with a contact.
   * @return
   */
  public Address getAddress() {
    return address;
  }
  
  /**
   * Return the phoneNumber associated with a contact.
   * @return
   */
  public PhoneNumber getPhoneNumber() {
    return phoneNumber;
  }
  
  /**
   * Return the email associated with a contact.
   * @return
   */
  public Email getEmail() {
    return email;
  }
  
  /**
   * Return the note associated with a contact.
   * @return
   */
  public Note getNote() {
    return note;
  }
  
  /**
   * Add the source POJO's jsonified object to a global destination JSON Object.
   * @param source
   * @param destination:
   */
  private void addJSONData(JSONObject source, JSONObject destination) {
    for (String key : source.keySet()) {
      String value = source.getString(key);
      destination.put(key, value);
    }
  }
  
  /**
   * Builder for the AddressEntry class that also ensures that each type of field is validated.
   * @author aarnav
   *
   */
  public static class EntryBuilder {
    private final Long id;
    private Name name;
    private Address address;
    private PhoneNumber phoneNumber;
    private Email email;
    private Note note;
    
    public EntryBuilder(Long id, String firstName, String lastName, String phoneNumber) {
      this.id = id;
      this.name = new Name(firstName, lastName);
      this.phoneNumber = new PhoneNumber(phoneNumber);
    }
    
    public EntryBuilder address(String address1, String address2, String city,
        String state, String country, String zip) {
      this.address = new Address.AddressBuilder()
          .address1(address1)
          .address2(address2)
          .city(city)
          .state(state)
          .country(country)
          .zip(zip)
          .build();
      return this;
    }
    
    public EntryBuilder email(String email) {
      this.email = new Email(email);
      return this;
    }
    
    public EntryBuilder note(String note) {
      this.note = new Note(note);
      return this;
    }
    
    public AddressEntry build() {
      AddressEntry addressEntry = new AddressEntry(this);
      return addressEntry;
    }
  }
}
