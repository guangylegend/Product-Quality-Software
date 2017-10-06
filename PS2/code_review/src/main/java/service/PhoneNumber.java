package service;

/**
 * A contact's phone number.
 * @author aarnav
 *
 */
public class PhoneNumber extends SearchableContactProperty{
  private static final int MAX_PHONE_LENGTH = 15;
  private static final int MIN_PHONE_LENGTH = 10;
  private String phoneNumber;

  /**
   * Remove any extra whitespace characters and validate the phone number
   * @param phoneNumber
   */
  public PhoneNumber(String phoneNumber) {
    super();
    phoneNumber.trim().replaceAll("\\s+", " ");
    validatePhoneNumber(phoneNumber);
    this.phoneNumber = phoneNumber;
  }
  
  /**
   * Adding the memer variable whose values should be considered in the search operation.
   */
  @Override
  public void populateSearchTerms() {
    addWords(phoneNumber);
  }  
  
  /**
   * A contact on a phone's addressBook must have a phone number.
   * Do not allow a blank phone number or a phone number of lengt outside reasonable range.
   * @param phoneNumber: a contact's phone number
   */
  private void validatePhoneNumber(String phoneNumber) {
    if (phoneNumber.isEmpty()) {
      throw new NullPointerException();
    }
    if (phoneNumber.length() > MAX_PHONE_LENGTH
        || phoneNumber.length() < MIN_PHONE_LENGTH) {
      throw new IllegalArgumentException();
    }
  }
}
