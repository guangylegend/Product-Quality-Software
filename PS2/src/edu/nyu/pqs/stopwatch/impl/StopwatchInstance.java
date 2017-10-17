package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import edu.nyu.pqs.stopwatch.api.Stopwatch;

public class StopwatchInstance implements Stopwatch {
  private List<Long> lapTime;
  private final String id;
  private Long startTime;
  private Long elapsedTime;
  private boolean isRunning;
  private final Object sync = new Object();

  public StopwatchInstance(String watchId) {
    lapTime = new CopyOnWriteArrayList<Long>();
    id = watchId;
    startTime = 0L;
    elapsedTime = 0L;
    isRunning = false;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void start() {
    synchronized (sync) {
      if (isRunning) {
        throw new IllegalStateException();
      }
      startTime = System.currentTimeMillis();
      isRunning = true;
    }
  }

  @Override
  public void lap() {
    synchronized (sync) {
      if (!isRunning) {
        throw new IllegalStateException();
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

  @Override
  public void stop() {
    synchronized (sync) {
      if (!isRunning) {
        throw new IllegalStateException();
      }
      elapsedTime += System.currentTimeMillis() - startTime;
      isRunning = false;
    }
  }

  @Override
  public void reset() {
    synchronized (sync) {
      startTime = 0L;
      elapsedTime = 0L;
      isRunning = false;
      lapTime.clear();
    }
  }

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
  public int hashCode() {
    return 0;
  }

  @Override
  public String toString() {
    return null;
  }

  @Override
  public boolean equals(Object obj) {
    return false;
  }
}
