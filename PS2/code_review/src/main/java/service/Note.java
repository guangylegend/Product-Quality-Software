package service;

/**
 * A note added about a contact in plain text format.
 * @author aarnav
 *
 */
public class Note extends SearchableContactProperty{
  private static final int MAX_NOTE_SIZE = 80;
  private String note;
  
  /**
   * Remove any extra whitespace characters and validate the length of the note.
   * @param note
   */
  public Note(String note) {
    super();
    note.trim().replaceAll("\\s+", " ");
    validateNote(note);
    this.note = note;
  }
  
  /**
   * Adding the member variable whose values are to be considered for searchTerm match.
   */
  @Override
  public void populateSearchTerms() {
    addWords(note);
  }
  
  /**
   * Make sure that the length of the note is less than the limit.
   * We intend to allow a blank note.
   * @param note: A text containing any additional notes about the contact
   */
  private void validateNote(String note) {
    if (note.length() > MAX_NOTE_SIZE) {
      throw new IllegalArgumentException();
    }
  }
  
  
}
