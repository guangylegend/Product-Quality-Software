package ps1;

public class Test {
  public static void main(String[] args) throws Exception {
    AddressBook book = new AddressBook();
    book.AddEntry("asdasd", null, "6462092365", "xxx@xxx.com", "asd");
    book.AddEntry("dsadsa", null, "6462092366", "xxx@xxx.online", "dsa");
    book.RemoveEntry(book.SearchEntry("asd").get(0).getID());
    book.SaveToFile("test");
    book.ReadFromFile("test");
    System.out.println(book);
  }
}
