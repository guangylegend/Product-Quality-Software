package ps1;

import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is an implementation of a small address book. Each entry contains the following information:
 * <ul>
 * <li>Name
 * <li>Postal Address
 * <li>Phone Number
 * <li>Email Address
 * <li>Note
 * </ul>
 * <p>
 * This address book supports many methods including:
 * <ul>
 * <li>Add an entry to the address book
 * <li>Search entries by key in any of the fields
 * <li>Remove
 * <li>Save the address book to a file
 * <li>Read saved address book from a file
 * </ul>
 * 
 * @author Guang Yang
 * @version 1.0
 */
public class AddressBook {

  private List<Entry> EntryList;

  /**
   * Create a empty address book.
   */
  public AddressBook() {
    EntryList = new ArrayList<Entry>();
  }

  // TODO params requirements
  /**
   * Add an contact entry to the address book. The parameters contain Name, Postal Address, Phone
   * number, Email Address and small note. The Name is required, the others can be left null.
   *
   * @param Name name of the contact, required
   * @param Postal postal address of the contact, leave it null if not specified
   * @param Phone phone number of the contact, leave it null if not specified
   * @param Email email address of the contact, leave it null if not specified
   * @param Note note of the contact, leave it null if not specified
   * 
   * @throws IllegalArgumentException If Name is null, or Postal Address, Phone Number, Email
   *         Address has invalid format.
   */
  public void AddEntry(String Name, String Postal, String Phone, String Email, String Note)
      throws IllegalArgumentException {
    if (Name == null)
      throw new IllegalArgumentException("Name can't be empty");
    Entry e = new Entry(Name);
    if (Postal != null)
      e.setPostal(Postal);
    if (Phone != null)
      e.setPhone(Phone);
    if (Email != null)
      e.setEmail(Email);
    if (Note != null)
      e.setNote(Note);
    EntryList.add(e);
  }

  // TODO should be optimized
  /**
   * Remove the entries which contain the key in any of their fields.
   *
   * @param key the string used for removing
   * 
   * @see Entry
   */
  public void RemoveEntry(String key) {
    List<Entry> res = new ArrayList<Entry>();
    for (Entry e : EntryList) {
      if (e.search(key))
        res.remove(e);
    }
  }

  // TODO determine whether to return a copy or original item
  /**
   * Search the entries which contain the key in any of their fields, and return a list of them.
   *
   * @param key the string used for search
   * @return the result entries as a list
   * 
   * @see Entry
   */
  public List<Entry> SearchEntry(String key) {
    List<Entry> res = new ArrayList<Entry>();
    for (Entry e : EntryList) {
      if (e.search(key))
        res.add(e);
    }
    return res;
  }

  /**
   * Save the address book to a file using JSON style.
   *
   * @param filename the file name used for writing
   * 
   * @throws IOException If there's something wrong with the writing to the file.
   * @throws JSONException Fail to convert some entries to JSON.
   */
  public void SaveToFile(String filename) throws IOException, JSONException {
    JSONObject obj = new JSONObject();
    JSONArray entrylist = new JSONArray();
    for (Entry e : EntryList) {
      entrylist.put(e.toJSON());
    }
    obj.put("list", entrylist);
    try (FileWriter file = new FileWriter(filename)) {
      file.write(obj.toString());
    }
  }

  /**
   * Read saved address book from a file, if this file wasn't created by {@link SaveToFile}
   * function, it will throw an exception.
   * <p>
   * This method will append the contact list read from the file to the current address book, and
   * won't eliminate the redundancy.
   *
   * @param filename the file name used for reading
   * 
   * @throws FileNotFoundException If file is null, or invalid filename
   * @throws IOException If this file wasn't created by {@link SaveToFile} function or some other
   *         reading issues
   */
  public void ReadFromFile(String filename) throws FileNotFoundException, IOException {

  }

  /**
   * Return a string representation of the entries in the address book.
   * 
   * @return a string representation of the address book
   * 
   * @see Entry
   */
  public String toString() {
    String res = "";
    for (Entry e : EntryList) {
      res += e.toString();
    }
    return res;
  }
}
