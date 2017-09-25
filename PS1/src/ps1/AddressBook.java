package ps1;

import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;

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

  /**
   * Remove the entries with the ID.
   * <p>
   * If the ID doesn't exist, it will do nothing.
   * 
   * @param ID the id of the entry
   * 
   * @see Entry
   */
  public void RemoveEntry(int ID) {
    for (int i = 0; i < EntryList.size(); i++) {
      if (EntryList.get(i).getID() == ID)
        EntryList.remove(i);
    }
  }

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
        res.add(new Entry(e));
    }
    return res;
  }

  /**
   * Save the address book to a file using JSON style.
   *
   * @param filename the file name used for writing
   * 
   * @throws IOException If there's something wrong with the writing to the file.
   * @throws JsonIOException Fail to convert some entries to JSON.
   */
  public void SaveToFile(String filename) throws IOException, JsonIOException {
    JsonObject obj = new JsonObject();
    JsonArray entrylist = new JsonArray();
    for (Entry e : EntryList) {
      entrylist.add(e.toJSON());
    }
    obj.add("list", entrylist);
    try (FileWriter file = new FileWriter(filename)) {
      Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
      gson.toJson(obj, file);
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
   * @throws FileNotFoundException If this file is null, or invalid filename
   * @throws IOException If this file can't close properly
   * @throws JsonIOException If this file wasn't created by {@link SaveToFile} function.
   */
  public void ReadFromFile(String filename)
      throws FileNotFoundException, IOException, JsonSyntaxException, JsonIOException {
    try (FileReader file = new FileReader(filename)) {
      Gson gson = new GsonBuilder().create();
      JsonObject obj = gson.fromJson(file, JsonObject.class);
      JsonArray entrylist = obj.get("list").getAsJsonArray();
      for (JsonElement j : entrylist) {
        EntryList.add(Entry.toEntry(j.getAsJsonObject()));
      }
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj.getClass() != AddressBook.class)
      return false;
    if (this.hashCode() != ((AddressBook) obj).hashCode())
      return false;
    if (this.EntryList.size() != ((AddressBook) obj).EntryList.size())
      return false;
    for (int i = 0; i < this.EntryList.size(); i++)
      if (!this.EntryList.get(i).equals(((AddressBook) obj).EntryList.get(i)))
        return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 1;
    for (Entry e : EntryList) {
      hash = hash * 5 + e.hashCode();
    }
    return hash;
  }

  @Override
  public String toString() {
    String res = "";
    for (Entry e : EntryList) {
      res += e.toString() + "\n";
    }
    return res;
  }
}
