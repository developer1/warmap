/*
 * Map.java
 *
 * Created on April 4, 2005, 9:10 PM
 */

package warmap;

/**
 *
 * @author Ryan Clark
 */
public class Map {
  private String Location;
  private double UpperLeftX;
  private double UpperLeftY;
  private double LowerRightX;
  private double LowerRightY;
  private String[] Image;
  
  ///////////////////////////////////////////////////////
  // Accessor functions for map variables
  ///////////////////////////////////////////////////////
  void SetLocation(String temp_Location)
  { Location = temp_Location; }
  String GetLocation()
  { return Location; }
  
  void SetUpperLeftX(double temp_ULX)
  { UpperLeftX = temp_ULX; }
  double GetUpperLeftX()
  { return UpperLeftX; }
  
  void SetUpperLeftY(double temp_ULY)
  { UpperLeftY = temp_ULY; }
  double GetUpperLeftY()
  { return UpperLeftY; }
  
  void SetLowerRightX(double temp_LRX)
  { LowerRightX = temp_LRX; }
  double GetLowerRightX()
  { return LowerRightX; }
  
  void SetLowerRightY(double temp_LRY)
  { LowerRightY = temp_LRY; }
  double GetLowerRightY()
  { return LowerRightY; }
  
  void SetImage(String temp_Image, int nImageNum)
  { Image[nImageNum] = temp_Image; }
  String GetImage(int nImageNum)
  { return Image[nImageNum]; }
  ///////////////////////////////////////////////////////
  
  //
  // Get X position of GPS position on the map
  // returns integer of position
  //
  int GetXPos(double m_fGPSXPos, int m_nMinX, int m_nMaxX)
  {
    double NewPos;

    NewPos=(((m_nMaxX-m_nMinX)*(m_fGPSXPos-UpperLeftX))/(LowerRightX-UpperLeftX));
    if(NewPos%(int)NewPos>0.5)
      NewPos++;
    return ((int)NewPos);
  }
  
  //
  // Get Y position of GPS position on the map
  // returns integer of position
  //
  int GetYPos(double m_fGPSYPos, int m_nMinY, int m_nMaxY)
  {
    double NewPos;

    NewPos=(((m_nMaxY-m_nMinY)*(m_fGPSYPos-UpperLeftY))/(LowerRightY-UpperLeftY));
    if(NewPos%(int)NewPos>0.5)
      NewPos++;
    return ((int)NewPos);
  }

  //
  // Parses an HTML data line generated from Wigle.net
  // returns a Point entry
  public static Point ParseDataLine(String szLine) throws NullPointerException
  {
    //split this line into different strings
    Point p=new Point();
    String szArray[]=null;
    String szArray2[]=null;
    
    for(int i=0;i<szLine.length();i++)
    {
      try { 
        szArray=szLine.split("<td>");
      } catch (Exception e) {}
    }
    
    for(int i=1;i<szArray.length;i++)
    {
      szArray2=szArray[i].split("</td>");     //take out the </td>'s
      switch (i)
      {
        case 1:   //MAC
          p.SetMAC(szArray2[0]);
          break;
        case 2:   //SSID
          if(szArray2[0].contains("&nbsp;")) break;
          p.SetSSID(szArray2[0]);
          break;
        case 3:   //Comment
          break;
        case 4:   //Name
          if(szArray2[0].contains("&nbsp;")) break;
          p.SetName(szArray[0]);
          break;
        case 5:   //Type (1 BBS, 2 infra, 3 ad-hoc)
          if(szArray2[0].contains("BSS")) p.SetType(1);
          else if(szArray2[0].contains("inf")) p.SetType(2);
          else if(szArray2[0].contains("hoc")) p.SetType(3);
          else p.SetType(0);
          break;
        case 6:   //Freenet
          if(szArray2[0].contains("Y")) p.SetFreeNet(true);
          else p.SetFreeNet(false);
          break;
        case 7:   //Paynet
          if(szArray2[0].contains("Y")) p.SetPayNet(true);
          else p.SetPayNet(false);
          break;
        case 8:   //FirstTime
          break;
        case 9:   //Flags
          break;
        case 10:  //WEP
          if(szArray2[0].contains("Y")) p.SetWEP(true);
          else p.SetWEP(false);
          break;
        case 11:  //Longitude
          try {
          p.SetLat(Double.parseDouble(szArray2[0]));
          } catch (NumberFormatException e) { }
          break;
        case 12:  //Latitude
          try {
          p.SetLon(Double.parseDouble(szArray2[0]));
          } catch (NumberFormatException e) { }
          break;
        ////////////////////////////////////////////////////
          
        case 13:  //DHCP
          break;
        case 14:  //Last Update
          try {
            p.SetLastUpdate(Integer.parseInt(szArray2[0]));
          } catch (NumberFormatException e) {}
          break;
        case 15:  //Channel
          if(szArray2[0]=="&nbsp;") break;
          else if(szArray2[0]==" ") break;
          else if(szArray2[0]=="") break;
          try {
            p.SetChannel(Integer.parseInt(szArray2[0]));
          } catch (NumberFormatException e) { }
          break;
        case 16:  //Active
          break;
        case 17:  //BNCInterval
          break;
        case 18:  //QoS
          try {
            p.SetQOS(Integer.parseInt(szArray2[0]));
          } catch (NumberFormatException e) { }
          break;
        case 19:  //UserFound
          break;
        default:  //None of the above
          break;
      }
    }
    return p;
  }
  
  
  //
  // Imports a HTML file generated by Wigle.net for parsing
  // returns the number of points added to the databse
  public static int ImportHTMLPoints(String szFileName, PointDB pDB)
  {
    FIO file = new FIO(szFileName);
    String a=null;
    
    //put the file contents into the string
    try
    {
      a=file.readText();
    }
    catch (Exception e){ System.out.println("Unable to read file"); return -1; }
    
    //split the string into several different strings, each line has its own string
    String line[]=null;
    try { line = a.split("\n"); }
    catch (Exception e) { System.out.println("Invalid string"); return -2; }
    
    
    //parse each line and look for data
    int count=0;
    try
    {
      for(int i=0;i<line.length;i++)
      {
        if(line[i].startsWith("<td>"))
        {
          pDB.AddAP(ParseDataLine(line[i]));
          count++;
        }
      }
    } catch (Exception e) { System.out.println("Null pointer, blah!"); return -3; }
    return count;
  }
  
  
  //
  // Opens a .mappack file and imports it's data
  public void ImportMap(String szFileName, String szDirectory)
  {
    FIO file = new FIO(szFileName);
    String a=null;
    String szData[]=null;
    
    //put the file contents into the string
    try
    {
      a=file.readText();
    }
    catch (Exception e){ System.out.println("Unable to read file"); return; }
    
    //split the string into several different strings, each line has its own string
    String line[]=null;
    try { line = a.split("\n"); }
    catch (Exception e) { System.out.println("Invalid string"); return; }
    
    //parse each line and look for data
    int count=0;
    try
    {
      for(int i=0;i<line.length;i++)
      {
        if(line[i].startsWith("longName="))
          Location = line[i].substring(9);
        else if(line[i].startsWith("image"))
          Image[count++] = szDirectory.substring(0,szDirectory.length()-16) + line[i].substring(22);
        else if(line[i].startsWith("upperLeftX="))
          UpperLeftX = Double.parseDouble(line[i].substring(11,19));
        else if(line[i].startsWith("upperLeftY="))
          UpperLeftY = Double.parseDouble(line[i].substring(11,19));
        else if(line[i].startsWith("lowerRightX="))
          LowerRightX = Double.parseDouble(line[i].substring(12,20));
        else if(line[i].startsWith("lowerRightY="))
          LowerRightY = Double.parseDouble(line[i].substring(12,20));
      }
    } catch (Exception e) { System.out.println("Null pointer, blah!"); return; }
  }
  
  
  public String toString()
  {
    return ("Location\n  "+Location+
            "\nLatitude\n  "+UpperLeftX+" to "+LowerRightX+
            "\nLongitude\n  "+UpperLeftY+" to "+LowerRightY);
  }
  
  /** Creates a new instance of Map */
  public Map() 
  {
    Image = new String[4];
  }
    
}
