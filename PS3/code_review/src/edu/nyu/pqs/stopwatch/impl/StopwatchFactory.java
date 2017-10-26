package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import edu.nyu.pqs.stopwatch.api.Stopwatch;

/**
 * The StopwatchFactory is a thread-safe factory class for Stopwatch objects.
 * It maintains references to all created Stopwatch objects and provides a
 * convenient method for getting a list of those objects.
 *
 */
public class StopwatchFactory {

  private static ConcurrentHashMap<String, Stopwatch> stopwatchMap
      = new ConcurrentHashMap<String, Stopwatch>();
  private static Object mutex = new Object();

  /**
   * Creates and returns a new Stopwatch object
   * @param id The identifier of the new object
   * @return The new Stopwatch object
   * @throws IllegalArgumentException if <code>id</code> is empty, null, or
   *     already taken.
   */
  public static Stopwatch getStopwatch(String id) throws IllegalArgumentException {
    // replace this return statement with correct code
    if (id == null) {
      throw new IllegalArgumentException("Exception: id is null");
    }
    if (id.equals("")) {
      throw new IllegalArgumentException("Exception: id is empty");
    }
    if (stopwatchMap.containsKey(id)) {
      throw new IllegalArgumentException("Exception: id is already taken");
    }
    synchronized (mutex) {
      StopwatchImpl stopwatch = new StopwatchImpl(id);
      stopwatchMap.put(id, stopwatch);
      return stopwatch;
    }
  }

  /**
   * Returns a list of all created stopwatches
   * @return a List of al creates Stopwatch objects.  Returns an empty
   * list if no Stopwatches have been created.
   */
  public static List<Stopwatch> getStopwatches() {
    List<Stopwatch> allStopwatches = new ArrayList<Stopwatch>(stopwatchMap.values());
    return allStopwatches;
  }
}
