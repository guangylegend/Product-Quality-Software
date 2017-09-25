package ps1;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is an implementation of a contact entry. It contains the following attributes:
 * <ul>
 * <li>Name: String
 * <li>Postal Address: String
 * <li>Phone Number: String
 * <li>Email Address: String
 * <li>Note: String
 * </ul>
 * <p>
 * Attribute Name is required for an entry, while others are optional.
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
   * Create an entry and specify the name of contact.
   *
   * @param Entry
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
   * @throws IllegalArgument If the input Phone isn't a valid phone number
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
   * @throws IllegalArgument If the input Email isn't a valid email address
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

  public void setNote(String Note) {
    note = Note;
  }

  public JSONObject toJSON() throws JSONException {
    JSONObject obj = new JSONObject();
    obj.put("name", name);
    obj.put("postal", postal);
    obj.put("phone", phone);
    obj.put("email", email);
    obj.put("note", note);
    return obj;
  }

  public Entry toEntry(JSONObject obj) throws JSONException, IllegalArgumentException {
    Object readobj = obj.get("name");
    if (readobj == null || readobj.getClass() != String.class)
      throw new JSONException("parse error");
    String Name = (String) readobj;
    Entry e = new Entry(Name);
    readobj = obj.get("postal");
    if (readobj != null) {
      if (readobj.getClass() != String.class)
        throw new JSONException("parse error");
      else
        e.setPostal((String) readobj);
    }
    readobj = obj.get("phone");
    if (readobj != null) {
      if (readobj.getClass() != String.class)
        throw new JSONException("parse error");
      else
        e.setPhone((String) readobj);
    }
    readobj = obj.get("email");
    if (readobj != null) {
      if (readobj.getClass() != String.class)
        throw new JSONException("parse error");
      else
        e.setEmail((String) readobj);
    }
    readobj = obj.get("note");
    if (readobj != null) {
      if (readobj.getClass() != String.class)
        throw new JSONException("parse error");
      else
        e.setNote((String) readobj);
    }
    return e;
  }

  public boolean search(String key) {
    if (name.contains(key))
      return true;
    else if (postal.contains(key))
      return true;
    else if (phone.contains(key))
      return true;
    else if (email.contains(key))
      return true;
    else if (note.contains(key))
      return true;
    return false;
  }

  @Override
  public boolean equals(Object obj) {
    return obj.getClass() == Entry.class && this.hashCode() == ((Entry)obj).hashCode();
  }


  @Override
  public int hashCode() {
    return id;
  }

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
