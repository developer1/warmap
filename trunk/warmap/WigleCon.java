package warmap;

import java.io.*;
import java.net.*;

/**
 *
 * @author Ryan Clark
 */
public class WigleCon 
{
  public static final String SERVER = "wigle.net";
  public static final String PATH_TO_QUERY = "/gps/gps/GPSDB/confirmquery/?";
  public static final int TIMEOUT=10000;    //we need a long timeout, this server
                                            //can get pretty busy
  
  //
  // Adds results of rectangular coordinate boundry into the PointDB database
  // returns number of entried added
  public int AddResults(PointDB PDB, int Offset, double LatRange1, 
          double LatRange2, double LongRange1, double LongRange2)
  {
    Socket s=null;
    String Results=null;
    String buffer=null;
    String Host=SERVER;
    String Location;
    OutputStreamWriter oo = null;
    InputStreamReader oi = null;
    BufferedReader br = null;
    Map m = new Map();
    int Count=0;
    
    Location = (PATH_TO_QUERY+
            "latrange1="+LatRange1+
            "&latrange2="+LatRange2+
            "&longrange1="+LongRange1+
            "&longrange2="+LongRange2+
            "&pagestart="+Offset);    
    
    try {                   //open socket and get page
      s=new Socket(Host,80);
      s.setSoTimeout(TIMEOUT);
      
      oi = new InputStreamReader( s.getInputStream());
      oo = new OutputStreamWriter( s.getOutputStream());
      br = new BufferedReader(oi);
      
      // Send HTTP request header for page, then read out
      // the HTML until the /HTML tag is detected; then close
      // the receiving socket.
      oo.write("GET " + Location + " HTTP/1.1\r\n");    // HTTP request 
      oo.write("HOST: " + Host + "\r\n");               // HOST name
      oo.write("\r\n");
      oo.flush();     // Force the three lines to be sent
      
      do {                        //start parsing the HTML
        buffer = br.readLine();   // Read a line of data from the remote host
        if(buffer.startsWith("<td>")) //throw out any other line that doesnt
        {                             //start with <td>
          PDB.AddAP(m.ParseDataLine(buffer));
          Count++;
        }
      } while( buffer.toUpperCase().indexOf("</HTML>") < 0 );
   
    s.close();  // Close the TCP connection
      
    } catch (IOException e) { return -1; }
    
    return Count;
  }
  
  
  /** Creates a new instance of WigleCon */
  public WigleCon() 
  {}
  
}
