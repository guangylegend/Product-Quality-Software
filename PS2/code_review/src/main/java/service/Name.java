package service;

public class Name extends SearchableContactProperty{
  private static final int MAX_NAME_SIZE = 60;
  private String firstName;
  private String lastName;

  /**
   * Validate that the firstName is not empty and that the names are of reasonable length.
   * @param firstName
   * @param lastName
   */
  public Name(String firstName, String lastName) {
    super();
    firstName.trim().replaceAll("\\s+", " ");
    assert(!firstName.isEmpty());
    validateName(firstName);
    this.firstName = firstName;
    lastName.trim().replaceAll("\\s+", " ");
    validateName(lastName);
    this.lastName = lastName;
  }
  
  /**
   * Fields to enable search on.
   */
  @Override
  public void populateSearchTerms() {
    addWords(firstName);
    addWords(lastName);
  }
  
  /**
   * Make sure that the names are of reasonable length.
   * @param name
   */
  private void validateName(String name) {
    if (name.length() > MAX_NAME_SIZE) {
      throw new IllegalArgumentException();
    }
  }
}
