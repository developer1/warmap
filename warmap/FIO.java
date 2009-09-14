package warmap;

import java.io.*;

public class FIO 
{
  private File szFile;
  private ObjectOutputStream output;
  private ObjectInputStream input;
  public static final boolean IN = false;
  public static final boolean OUT = true;
  
  //
  // Returns the contents of the file in a string
  public String readText()  throws IOException
  {
    String FileContents = ""; 
    FileReader FR = new FileReader(szFile); 
    
    int size = (int) szFile.length(); 
    char[] buf = new char[size];        //create character buffer
    
    FR.read(buf, 0, size);              //read the file into the buffer
    FileContents = new String(buf);     //convert character buffer to string
    FR.close();                         //close the file
    return FileContents;                //return the new string
  }
  
  
//
// Open file function, used for saving and importing a book
//    int io specifies going in or out (OUT=0, IN=1)
//
  public void OpenFile(boolean io, String File)
  {
    if(io==OUT)         //for opening a file for output
    {
      try
      { output = new ObjectOutputStream(new FileOutputStream(File)); }
      catch (IOException ioException)
      { System.err.println("Error opening file."); }
    }
    else                //for opening a file for input
    {
      try
      { input = new ObjectInputStream(new FileInputStream(File)); }
      catch (IOException ioException)
      { System.err.println("Error opening file file."); }
    }  
  }
  
  
//
// Close file function
//    int io specifies going in or out (OUT=0, IN=1)
//
  public void CloseFile(boolean io)
  {
    if(io==OUT)           //for closing a file that was used for output
    {
      try
      {
        if(output!=null)
          output.close();
      }
      catch (IOException ioException)
      { System.err.println("Error closing file."); }
    }
    else                  //for closing a file that was used for input
    {
      try
      {
        if(input!=null)
          input.close();
      }
      catch (IOException ioException)
      { System.err.println("Error closing file."); }
    }
  }
  
  //
// Save entire database to file
//
  public void SaveDB(PointDB PDB)
  {
    String szFileName;
    
    System.out.println("\n\n\nOutputting to file...");
    System.out.print("Enter file to save to: ");
    szFileName = Input.InputString();
    OpenFile(OUT, szFileName);                       //open file
    try
    { output.writeObject(PDB); }
    catch(IOException ioException)
    {
      System.out.println("Error writing to file, try again.");
      return;
    }
    CloseFile(OUT);                      //close file
  }
  

  public PointDB ReadDBFile()
  {
    PointDB PDB;
    PDB = new PointDB();
    try
    {
      while(true)
      { PDB = (PointDB) input.readObject(); }
    }
    catch(EOFException endOfFileException)
    {
      System.out.println("Successfully imported database");
      return PDB;
    }
    catch(IOException ioException)
    {
      System.err.println("Error importing database, exiting");
      return null;
    }
    catch(ClassNotFoundException classNotFoundException)
    {
      System.err.println("Unable to create object, exiting");
      return null;
    }
  }
  
//
// Open a database file
//
  public PointDB OpenDB()
  {
    PointDB PDB;
    String szFileName;
    
    System.out.println("\n\n\nImporting from file...");
    System.out.print("Enter name of file to open: ");
    szFileName = Input.InputString();
    OpenFile(IN,szFileName);
    PDB = ReadDBFile();
    CloseFile(IN);
    return PDB;
  }
  
  
  /** Creates a new instance of FIO */
  public FIO(String m_szFileName) 
  {
    szFile = new File(m_szFileName);
  }
  
}
