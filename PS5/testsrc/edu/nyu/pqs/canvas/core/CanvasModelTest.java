package edu.nyu.pqs.canvas.core;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import edu.nyu.pqs.canvas.content.CanvasContent;
import edu.nyu.pqs.canvas.content.CanvasLine;
import edu.nyu.pqs.canvas.gui.CanvasListener;

public class CanvasModelTest {
  private CanvasModel model;
  private CanvasContent content;

  @Mock
  private CanvasListener listener;

  @Before
  public void setUp() {
    model = CanvasModel.getInstance();
    MockitoAnnotations.initMocks(this);
    model.registerListener(listener);
    content = new CanvasLine(0, 0, 15, 20, "Red");
  }

  @After
  public void tearDown() {
    model.getListenersForTest().clear();
    model.clear();
  }

  @Test
  public void startTest() {
    model.start();
    assertEquals(model.getListenersForTest().size(), 2);
    assertEquals(model.getListenersForTest().get(model.getListenersForTest().size() - 1).getId(),
        0);
  }

  @Test
  public void createCanvasTest() {
    model.start();
    model.createCanvas();
    model.createCanvas();
    model.createCanvas();
    assertEquals(model.getListenersForTest().size(), 5);
    assertEquals(model.getListenersForTest().get(model.getListenersForTest().size() - 1).getId(),
        4);
  }

  @Test
  public void addContentTest() {
    model.addContent(content);
    assertEquals(model.getContent().size(), 1);
    assertEquals(model.getContent().get(0), content);
    Mockito.verify(listener, Mockito.times(1)).eventRepaint();
  }

  @Test
  public void revertTest() {
    model.revert();
    assertEquals(model.getContent().size(), 0);
    
    model.addContent(content);
    model.addContent(content);
    model.revert();
    assertEquals(model.getContent().size(), 1);
    Mockito.verify(listener, Mockito.times(3)).eventRepaint();
  }

  @Test
  public void clearTest() {
    model.clear();
    assertEquals(model.getContent().size(), 0);
    Mockito.verify(listener, Mockito.times(1)).eventRepaint();
  }
}
