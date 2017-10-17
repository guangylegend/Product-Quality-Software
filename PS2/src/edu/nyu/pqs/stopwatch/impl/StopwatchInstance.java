package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import edu.nyu.pqs.stopwatch.api.Stopwatch;

/**
 * A thread-safe stopwatch that can be used for timing laps.  The stopwatch
 * objects are created in the StopwatchFactory.  Different threads can
 * share a single stopwatch object and safely call any of the stopwatch methods.
 * 
 * @author Guang Yang
 */
public class StopwatchInstance implements Stopwatch {
  private List<Long> lapTime;
  private final String id;
  private Long startTime;
  private Long elapsedTime;
  private boolean isRunning;
  private final Object sync = new Object();


  /**
   * Create a new Stopwatch instance with a unique watchId.
   * 
   * @param watchId the specified id for this watch.
   */
  public StopwatchInstance(String watchId) {
    lapTime = new CopyOnWriteArrayList<Long>();
    id = watchId;
    startTime = 0L;
    elapsedTime = 0L;
    isRunning = false;
  }

  /**
   * {@inheritDoc} 
   */
  @Override
  public String getId() {
    return id;
  }

  /**
   * {@inheritDoc} 
   */
  @Override
  public void start() {
    synchronized (sync) {
      if (isRunning) {
        throw new IllegalStateException("Stopwatch is already running");
      }
      startTime = System.currentTimeMillis();
      isRunning = true;
    }
  }
  
  /**
   * {@inheritDoc} 
   */
  @Override
  public void lap() {
    synchronized (sync) {
      if (!isRunning) {
        throw new IllegalStateException("Stopwatch isn't running");
      }
      elapsedTime += System.currentTimeMillis() - startTime;
      if (!lapTime.isEmpty()) {
        lapTime.add(elapsedTime - lapTime.get(lapTime.size() - 1));
      } else {
        lapTime.add(elapsedTime);
      }
      startTime = System.currentTimeMillis();
    }
  }

  /**
   * {@inheritDoc} 
   */
  @Override
  public void stop() {
    synchronized (sync) {
      if (!isRunning) {
        throw new IllegalStateException("Stopwatch isn't running");
      }
      elapsedTime += System.currentTimeMillis() - startTime;
      isRunning = false;
    }
  }

  /**
   * {@inheritDoc} 
   */
  @Override
  public void reset() {
    synchronized (sync) {
      startTime = 0L;
      elapsedTime = 0L;
      isRunning = false;
      lapTime.clear();
    }
  }

  /**
   * {@inheritDoc} 
   */
  @Override
  public List<Long> getLapTimes() {
    synchronized (sync) {
      List<Long> list = new ArrayList<Long>(lapTime);
      if (!isRunning && elapsedTime > 0) {
        if (!lapTime.isEmpty()) {
          list.add(elapsedTime - lapTime.get(lapTime.size() - 1));
        } else {
          list.add(elapsedTime);
        }
      }
      return list;
    }
  }

  @Override
  public String toString() {
    return "Time: " + elapsedTime + "milliseconds.";
  }
}
