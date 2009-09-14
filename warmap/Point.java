/*
 * Point.java
 *
 * Created on April 4, 2005, 9:10 PM
 */

package warmap;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class Point implements Serializable, Comparable
{
  //Local access point variables
  private String MAC;         //MAC address
  private String SSID;        //SSID
  private String Name;        //Name
  private int Type;           //Type (1 BBS, 2 infra, 3 ad-hoc)
  private boolean FreeNet;    //Free network
  private boolean PayNet;     //Pay network
  private boolean WEP;        //WEP on or off
  private double Lat;         //Latitude
  private double Lon;         //Longitude
  private int LastUpdate;       //Last seen time
  private int Channel;        //Signal channel
  private int QOS;            //Quality of Service
  //private boolean Cloaked;    //Broadcast SSID on or off
  
  ///////////////////////////////////////////////////////
  // Accessor functions for access point variables
  ///////////////////////////////////////////////////////
  void SetMAC(String m_szMAC)
  { MAC = m_szMAC; }
  String GetMAC()
  { return MAC; }
  
  void SetSSID(String m_szSSID)
  { SSID = m_szSSID; }
  String GetSSID()
  { return SSID; }
  
  void SetName(String m_szName)
  { Name = m_szName; }
  String GetName()
  { return Name; }
  
  void SetType(int m_nType)
  { Type = m_nType; }
  int GetType()
  { return Type; }
  
  void SetFreeNet(boolean m_bFreeNet)
  { FreeNet = m_bFreeNet; }
  boolean GetFreeNet()
  { return FreeNet; }
  
  void SetPayNet(boolean m_bPayNet)
  { PayNet = m_bPayNet; }
  boolean GetPayNet()
  { return PayNet; }
  
  void SetWEP(boolean m_bWEP)
  { WEP = m_bWEP; }
  boolean GetWEP()
  { return WEP; }
  
  void SetLat(double m_dLat)
  { Lat = m_dLat; }
  double GetLat()
  { return Lat; }
  
  void SetLon(double m_dLon)
  { Lon = m_dLon; }
  double GetLon()
  { return Lon; }
  
  void SetLastUpdate(int m_nYYYYMMDDHHMMSS)
  { LastUpdate = m_nYYYYMMDDHHMMSS; }
  int GetLastUpdate()
  { return LastUpdate; }
  
  void SetChannel(int m_nChannel)
  { Channel = m_nChannel; }
  int GetChannel()
  { return Channel; }
  
  void SetQOS(int m_nQOS)
  { QOS = m_nQOS; }
  int GetQOS()
  { return QOS; }
  
  //void SetCloaked(boolean m_bCloaked)
  //{ Cloaked = m_bCloaked; }
  //boolean GetCloaked()
  //{ return Cloaked; }
  ///////////////////////////////////////////////////////
  
  
  public String toString()
  {    
    return ("SSID: "+SSID+"\nMAC: "+MAC+"\nName: "+Name+"\nType: "+Type+
            "\nFreenet: "+FreeNet+"\nPaynet: "+PayNet+"\nWEP: "+WEP+
            "\nLatitude: "+Lat+"\nLongitude: "+Lon+"\nLast Update: "+
            LastUpdate+"\nChannel: "+Channel+"\nQOS: "+QOS);
  }
  
  //
  //return only the MAC and SSID, to compress the display
  public String MACandSSID()
  {
    return (MAC+" - "+WEP+"\t- "+SSID);
  }
  
  
  // Default constructor
  public Point() 
  {
    //set variable defaults
    MAC = null;
    SSID = null;
    Name = null;
    Type = 0;
    FreeNet = false;
    PayNet = false;
    WEP = false;
    Lat = 0.0;
    Lon = 0.0;
    Channel = 0;
    QOS = 0;
    //Cloaked = false;
  }   

  public int compareTo(Object obj)
  {
    return this.MAC.compareTo(((Point) obj).MAC);
  }
}
