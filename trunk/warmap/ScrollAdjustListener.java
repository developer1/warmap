/*
 * ScrollAdjustListener.java
 *
 * Created on May 2, 2005, 3:08 PM
 */

package warmap;

import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Ryan Clark
 */
public class ScrollAdjustListener implements AdjustmentListener
{
  JButton[] button;
  int count;
  
  public void SetUpdate(int c, JButton[] b)
  { 
    count = c; 
    button = b;
  }
  
  public void ScrollAdjustListener()
  { }
  
  //repaint the points if a scrollbar moves
  public void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) 
  {
    for(int i=0;i<count;i++)
      button[i].repaint();
  }
  
}
