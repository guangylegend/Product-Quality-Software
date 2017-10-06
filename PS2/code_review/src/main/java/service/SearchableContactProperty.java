package service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A basic (abstract) contact property that enables jsonification of the derived class' POJO
 * and counting the number of times a searchTerm is present in it.
 * @author aarnav
 */
public abstract class SearchableContactProperty {
  private Map<String, Integer> searchTerms;
  
  public SearchableContactProperty() {
    searchTerms = new HashMap<String, Integer>();
  }
  
  /**
   * Abstract method to add the member variables to be added in SearchTerms.
   */
  public abstract void populateSearchTerms();
  
  /**
   * Creates a JSON Object from the derived class' POJO.
   * @return
   */
  public JSONObject jsonify() {
    Gson gson = new GsonBuilder().create();
    String json = gson.toJson(this);
    return new JSONObject(json);
  }
  
  /**
   * Returns the number of times a searchTerm appears in the derived address property.
   * @param searchTerm: the term to be searched
   * @return the number of hits
   */
  public Integer getSearchCount(String searchTerm) {
    populateSearchTerms();
    return searchTerms.get(searchTerm);
  }
  
  /**
   * Adds the words appearing in an address contact property to a HashMap 
   * in order to later be used to find a searchTerm match.
   * @param sentence
   */
  public void addWords(String sentence) {
    if (sentence.isEmpty()) {
      return;
    }
    String[] words = sentence.split("\\.");
    for (String word : words) {
      Integer count = searchTerms.containsKey(word) ? searchTerms.get(word) : 0;
      searchTerms.put(word, count + 1);
    }
  }
}
