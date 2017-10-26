package edu.nyu.pqs.stopwatch.impl;

import edu.nyu.pqs.stopwatch.api.Stopwatch;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the Stopwatch interface, and is thread-safe.
 * It supports the typical operations of a physical stopwatch: start, stop, restart,
 * and the recording of laps (times intervals). Also, it can be asked for a list of
 * all the lap times that have been recorded using that stopwatch (if the stopwatch
 * has just been started and stopped once, then the list is of size one and contains
 * the elapsed time).
 */
public class StopwatchImpl implements Stopwatch {

  private final String id;
  private List<Long> lapTimes = new ArrayList<Long>();
  private long timer = 0;
  private enum State {
    START, RUNNING, STOPPED
  }
  private State state;

  private Object mutex = new Object();

  /**
   * Constructor of StopwatchImpl class.
   * It assigns id to the object field, and changes the state to be START.
   * @param id  stopwatch id to be assigned
   */
  public StopwatchImpl(String id) {
    this.id = id;
    state = State.START;
  }

  private long getCurrentTime() {
    return System.currentTimeMillis();
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void start() {
    synchronized (mutex) {
      if (state == State.RUNNING) {
        throw new IllegalStateException("Exception: the stopwatch is already running");
      }
      timer = getCurrentTime();
      state = State.RUNNING;
    }
  }

  @Override
  public void lap() {
    synchronized (mutex) {
      if (state != State.RUNNING) {
        throw new IllegalStateException("Exception: the stopwatch isn't running");
      }
      long currentTime = getCurrentTime();
      lapTimes.add(currentTime - timer);
      timer = currentTime;
    }
  }

  @Override
  public void stop() {
    synchronized (mutex) {
      if (state != State.RUNNING) {
        throw new IllegalStateException("Exception: the stopwatch isn't running");
      }
      lap();
      state = State.STOPPED;
    }
  }

  @Override
  public void reset() {
    synchronized (mutex) {
      if (state == State.RUNNING) {
        stop();
      }
      lapTimes.clear();
      timer = 0;
    }
  }

  @Override
  public List<Long> getLapTimes() {
    synchronized (mutex) {
      return lapTimes;
    }
  }

  @Override
  public String toString() {
    synchronized (mutex) {
      return "id: " + getId() + ", lap times: " + lapTimes.toString();
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof StopwatchImpl)) {
      return false;
    }
    Stopwatch sw = (StopwatchImpl) obj;
    return this.getId().equals(sw.getId());
  }

  @Override
  public int hashCode() {
    return 37 + 43 * id.hashCode();
  }
}
