package service;

/**
 * The email of a contact.
 * @author aarnav
 *
 */
public class Email extends SearchableContactProperty{
  private String email;
  
  /**
   * Remove any extra whitespaces and validate the email address.
   * @param email: a contact's email address
   */
  public Email(String email) {
    super();
    email.trim().replaceAll("\\s+", " ");
    validateEmailAddress(email);
    this.email = email;
  }
  
  /**
   * Adding the member variables to be considered in the search.
   */
  @Override
  public void populateSearchTerms() {
    addWords(email);
  }
  
  /**
   * Allow a blank email address but not an email with improper format.
   * @param email
   */
  private void validateEmailAddress(String email) {
    if (!email.isEmpty()) {
      String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      if (!email.matches(EMAIL_REGEX)) {
        throw new IllegalArgumentException();
      }
    }
  }
}
