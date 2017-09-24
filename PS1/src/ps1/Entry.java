package ps1;

import org.json.JSONException;
import org.json.JSONObject;

//TODO add java doc
public class Entry {
  private String name;
  private String postal;
  private String phone;
  private String email;
  private String note;

  public Entry(String Name) {
    name = Name;
  }

  public void setPostal(String Postal) throws IllegalArgumentException {
    //TODO check valid postal
    postal = Postal;
  }

  public void setPhone(String Phone) throws IllegalArgumentException {
    //TODO check valid phone
    phone = Phone;
  }

  public void setEmail(String Email) throws IllegalArgumentException {
    //TODO check valid email
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

  public String toString() {
    return name;
  }
}
