package ps1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;


/**
 * This is an implementation of a contact entry. It contains the following attributes:
 * <ul>
 * <li>Id: int
 * <li>Name: String
 * <li>Postal Address: String
 * <li>Phone Number: String
 * <li>Email Address: String
 * <li>Note: String
 * </ul>
 * <p>
 * Attribute Name is required for an entry, while others are optional. Id is auto incremental and is
 * the discriminative tag for an Entry.
 * 
 * @author Guang Yang
 * @version 1.0
 */
public class Entry {
  private static int SID = 0;
  private int id;
  private String name;
  private String postal;
  private String phone;
  private String email;
  private String note;

  /**
   * Create an entry and specify the name of contact.
   *
   * @param Name the name of the contact
   * 
   */
  public Entry(String Name) {
    SID++;
    id = SID;
    name = Name;
  }

  /**
   * Create an entry as the deep copy of another entry.
   *
   * @param e the entry being copyed
   * 
   */
  public Entry(Entry e) {
    id = e.id;
    name = e.name;
    postal = e.postal;
    phone = e.phone;
    email = e.email;
    note = e.note;
  }

  /**
   * Set the postal address for this entry.
   * 
   * @param Postal the postal address of the contact
   * 
   */
  public void setPostal(String Postal) {
    postal = Postal;
  }

  /**
   * Set the phone for this entry.
   * <p>
   * A valid phone number should be 10 consecutive digits.
   * 
   * @param Phone the phone of the contact
   * 
   * @throws IllegalArgumentException If the input Phone isn't a valid phone number
   * 
   */
  public void setPhone(String Phone) throws IllegalArgumentException {
    Pattern p = Pattern.compile("[0-9]{10}");
    Matcher m = p.matcher(Phone);
    if (!m.matches())
      throw new IllegalArgumentException("invalid phone number");
    else
      phone = Phone;
  }

  /**
   * Set the email address for this entry.
   * 
   * @param Email the email address of the contact
   * 
   * @throws IllegalArgumentException If the input Email isn't a valid email address
   * 
   */
  public void setEmail(String Email) throws IllegalArgumentException {
    Pattern p = Pattern.compile(
        "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})");
    Matcher m = p.matcher(Email);
    if (!m.matches())
      throw new IllegalArgumentException("invalid email address");
    else
      email = Email;
  }

  /**
   * Set the note for this entry.
   * 
   * @param Note the note of the contact
   * 
   */
  public void setNote(String Note) {
    note = Note;
  }

  /**
   * Transform an entry into a Json object for writing.
   * 
   * @return JsonObject after transforming
   * 
   */
  public JsonObject toJSON() {
    JsonObject obj = new JsonObject();
    obj.addProperty("name", name);
    obj.addProperty("postal", postal);
    obj.addProperty("phone", phone);
    obj.addProperty("email", email);
    obj.addProperty("note", note);
    return obj;
  }

  /**
   * Parse a JsonObject into an Entry.
   * 
   * @param obj JsonObject for parsing
   * @return Entry after parsing
   * 
   * @throws IllegalArgumentException If the syntax of parameters don't fit the requirement of an
   *         entry.
   * @throws JsonSyntaxException If the syntax of Json can't be parsed into an entry.
   * 
   */
  public static Entry toEntry(JsonObject obj) throws IllegalArgumentException, JsonSyntaxException {
    JsonPrimitive readobj = obj.getAsJsonPrimitive("name");
    if (readobj == null)
      throw new JsonSyntaxException("parse error");
    String Name = readobj.getAsString();
    Entry e = new Entry(Name);
    readobj = obj.getAsJsonPrimitive("postal");
    if (readobj != null) {
      if (!readobj.isString())
        throw new JsonSyntaxException("parse error");
      else
        e.setPostal(readobj.getAsString());
    }
    readobj = obj.getAsJsonPrimitive("phone");
    if (readobj != null) {
      if (!readobj.isString())
        throw new JsonSyntaxException("parse error");
      else
        e.setPhone(readobj.getAsString());
    }
    readobj = obj.getAsJsonPrimitive("email");
    if (readobj != null) {
      if (!readobj.isString())
        throw new JsonSyntaxException("parse error");
      else
        e.setEmail(readobj.getAsString());
    }
    readobj = obj.getAsJsonPrimitive("note");
    if (readobj != null) {
      if (!readobj.isString())
        throw new JsonSyntaxException("parse error");
      else
        e.setNote(readobj.getAsString());
    }
    return e;
  }

  /**
   * Search a key in any of the fields.
   * 
   * @param key the string for search
   * @return whether this entry contains the key in any of the fields
   * 
   */
  public boolean search(String key) {
    if (name.contains(key))
      return true;
    else if (postal != null && postal.contains(key))
      return true;
    else if (phone != null && phone.contains(key))
      return true;
    else if (email != null && email.contains(key))
      return true;
    else if (note != null && note.contains(key))
      return true;
    return false;
  }

  /**
   * Return the discriminative specifier ID for an entry.
   *
   * @return the id of this entry
   * 
   */
  public int getID() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (obj == this)
      return true;
    if (!(obj instanceof Entry))
      return false;
    Entry e = (Entry) obj;
    if (name.equals(e.name) && (postal == null ? e.postal == null : postal.equals(e.postal))
        && (phone == null ? e.phone == null : phone.equals(e.phone))
        && (email == null ? e.email == null : email.equals(e.email))
        && (note == null ? e.note == null : note.equals(e.note)))
      return true;
    else
      return false;
  }


  @Override
  public int hashCode() {
    int hash = 17;
    hash = 31 * hash + id;
    hash = 31 * hash + name.hashCode();
    hash = 31 * hash + (postal == null ? 0 : postal.hashCode());
    hash = 31 * hash + (phone == null ? 0 : phone.hashCode());
    hash = 31 * hash + (email == null ? 0 : email.hashCode());
    hash = 31 * hash + (note == null ? 0 : note.hashCode());
    return hash;
  }

  /**
   * Returns the string representation of this entry.
   * <p>
   * It will has the following format:
   * <ul>
   * <li>name: xxx
   * <li>postal: xxx
   * <li>phone: xxx
   * <li>email: xxx
   * <li>note: xxx
   * </ul>
   * <p>
   * If any of the fields is null, it will display "Not Set".
   */
  @Override
  public String toString() {
    String s = "";
    s += "name: " + name + "\n";
    s += "postal: " + (postal == null ? "Not Set" : postal) + "\n";
    s += "phone: " + (phone == null ? "Not Set" : phone) + "\n";
    s += "email: " + (postal == null ? "Not Set" : email) + "\n";
    s += "note: " + (note == null ? "Not Set" : note) + "\n";
    return s;
  }
}
