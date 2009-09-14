/*
 * PointDB.java
 *
 * Created on April 4, 2005, 10:11 PM
 */

package warmap;

import java.util.*;
import java.io.Serializable;


public class PointDB implements Serializable
{
  protected List m_APDB;
    
  /** Creates a new instance of PointDB */
  public PointDB() 
  {
    m_APDB = new LinkedList();    //our linked list of access points
  }  
  
  //
  // Add access point
  // returns AP entry on success, null on failure
  public Point AddAP(Point aAP)
  {
    boolean bResult;
    bResult = m_APDB.add(aAP);
    
    if(bResult == true) return aAP;   //successfully added point
    return null;                      //failure to add point
  }
  
  //
  // Get access point
  // returns AP on success, null on failure
  public Point GetAP(int nIndex)
  {
    if(nIndex > m_APDB.size()) return null; //this is out of range
    return ((Point) m_APDB.get(nIndex));
  }
  
  //
  // Get count of access points
  // returns number of entries
  public int GetCount()
  {
    return m_APDB.size();
  }
  
  //
  //sort the database
  //
  public void SortBook()
  {
    Collections.sort(m_APDB);
  }
}
