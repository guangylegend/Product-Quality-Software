package service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Create and transact on an addressbook which can be stored in a file.
 * @author aarnav
 *
 */
public class AddressBook {
  private static final Logger LOGGER = Logger.getLogger(AddressBook.class.getName());
  private Map<Long, AddressEntry> addressBook;
  private Map<Integer, AddressEntry> searchedAddresses;
  private String fileName;
  private FileWriter writer;
  private Long latestId;
  
  /**
   * Initialize an empty address book and initialize member variables.
   * @param fileName: the fileName containing the relative path along 
   * with the file name and extension
   */
  public AddressBook(String fileName) {
    this.addressBook = new HashMap<Long, AddressEntry>();
    this.fileName = fileName;
    this.searchedAddresses = new TreeMap<Integer, AddressEntry>();
    this.writer = null;
    createEmptyAddressBook();
    readAddressBook();      // in order to initialize the latestId to 0
  }
  
  /**
   * Create an empty address book and wipe out any existing entries.
   * Also assign a writer object to the file.
   */
  public void createEmptyAddressBook() {
    try {
      writer = new FileWriter(fileName, false); // force overwriting & deletion of old file content
    } catch(IOException e) {
      LOGGER.fine("IO exception while creating an empty address book file");
      e.printStackTrace();;
    }
  }
  
  /**
   * Read the current state of the addressBook residing on the file. If there were 
   * any entries loaded in the memory
   * and then modified, the changes will be lost. However, if there were new entries 
   * added in the addressBook in memory, then will survive the load from disk.
   * @return A JSON object showing the current contents on the file along with any newly 
   * added but uncommitted entries.
   */
  public JSONObject readAddressBook() {
    String jsonString = null;
    try {
      jsonString = new String(Files.readAllBytes(Paths.get(fileName)));
    } catch (IOException e) {
      e.printStackTrace();
    }  
    JSONObject latestJSON = new JSONObject(jsonString);
    try {
      JSONArray items = latestJSON.getJSONArray("items");
      for (int i = 0; i < items.length(); i++) {
        JSONObject entry = items.getJSONObject(i);
        addressBook.put(entry.getLong("id"),getAddressEntryFromJson(entry, entry.getLong("id")));      
      }
      latestId = latestJSON.getLong("latest_id");
      latestId++;
    } catch(JSONException e) {
      latestId = 0l;  // Starting from scratch
      LOGGER.info("Blank address book found; set latestId = 0");
    }
    return latestJSON;
  }
  
  /**
   * Adds a new entry based on a JSONObject coming from a business API into an in-memory Map.
   * Does NOT commit it into the file yet.
   * @param entry: a JSON object containing all the AddressEntry based fields
   */
  public void addEntry(JSONObject entry) {
    readAddressBook();  // retrieve the latest copy of the address book    
    try {
      Long id = latestId++;
      addressBook.put(id, getAddressEntryFromJson(entry, id));      
    } catch(JSONException e) {
      LOGGER.severe("JSONException found");
      throw e;
    }
  }
  
  /**
   * Remove an entry from the in-memory address book based on the id. 
   * Do nothing if a matching entry was not found.
   * @param id: id of the AddressBook entry
   */
  public void removeEntry(Long id) {
    readAddressBook();  // retrieve the latest copy of the address book
    addressBook.remove(id);
  }
  
  /**
   * Search the entire addressBook by all the address entry properties.
   * Return the matching entries sorted by the frequency of the searchTerm found within the entry.
   * @param searchTerm: The term to be searched
   * @return All matching entries sorted in the order of frequency of matches in the entry properties.
   */
  public JSONObject searchEntry(String searchTerm) {
    readAddressBook();
    Integer count = 0;
    for (AddressEntry addressEntry : addressBook.values()) {
      if ((count = addressEntry.searchEntry(searchTerm)) > 0) {
        searchedAddresses.put(count, addressEntry);
      }
    }
    JSONObject searchedAddressEntries = populateJSONFromAddressEntries(searchedAddresses.values());
    return searchedAddressEntries;
  }
  
  /**
   * Save the in-memory addressBook to a file by overwriting all its contents.
   */
  public void saveToFile() {
    JSONObject overWritingJSON = populateJSONFromAddressEntries(addressBook.values());
    overWritingJSON.put("latest_id", latestId);
    try {
      writer.write(overWritingJSON.toString());
    } catch (IOException e) {
      LOGGER.severe("Unable to write JSON on file");
      e.printStackTrace();
    }
  }
  
  /**
   * Read a JSON file and create an AddressEntry object using the the JSON fields.
   * @param jsonEntry: a JSON Object containing an address entry
   * @param id: the id associated with the addressEntry
   * @return A constructed AddressEntry from the supplied AddressEntry and id
   */
  private AddressEntry getAddressEntryFromJson(JSONObject jsonEntry, Long id) {
    return new AddressEntry.EntryBuilder(
    id, 
    jsonEntry.getString("firstName"), 
    jsonEntry.getString("lastName"), 
    jsonEntry.getString("phoneNumber"))
    .address(jsonEntry.getString("address1"), 
        jsonEntry.getString("address2"), 
        jsonEntry.getString("city"), 
        jsonEntry.getString("state"), 
        jsonEntry.getString("country"), 
        jsonEntry.getString("zip"))
    .email(jsonEntry.getString("email"))
    .note(jsonEntry.getString("note"))
    .build();
  }
  
  /**
   * Read a collection of addressEntries and create a JSON object containing all entries.
   * @param addressEntries
   * @return
   */
  private JSONObject populateJSONFromAddressEntries(Collection<AddressEntry> addressEntries) {
    JSONObject jsonifiedAddressEntries = new JSONObject();
    JSONArray items = new JSONArray();
    for (AddressEntry addressEntry : addressEntries) {
      JSONObject jsonAddressEntry = addressEntry.jsonify();
      items.put(jsonAddressEntry);
    }
    jsonifiedAddressEntries.put("items", items);
    return jsonifiedAddressEntries;
  }
}
