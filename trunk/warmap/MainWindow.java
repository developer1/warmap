/*
 * MainWindow.java
 *
 * Created on April 28, 2005, 12:38 PM
 */

package warmap;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 *
 * @author Ryan Clark
 */
public class MainWindow extends javax.swing.JFrame
{
  PointDB APDB = new PointDB();   //point database
  Map m = new Map();              //map class
  JLabel MapIcon;              //the map image
  JButton[] point;                //point buttons
  int ZoomLevel=0;
  int ButtonSize=6;
  //int WiglePointsParsed=0;

  WigleCon wigle = new WigleCon();
  int results=0, temp_results=0, Offset=0; 
  
  ScrollAdjustListener ScrollListener = new ScrollAdjustListener();
  
  
  /** Creates new form MainWindow */
  public MainWindow()
  {
    initComponents();    

    ImageIcon Icon = new javax.swing.ImageIcon(getClass().getResource("/warmap/defaultmap.png"));
    MapPanel.removeAll();       //clear the area first
    MapIcon = new JLabel(Icon);
    MapPanel.add(MapIcon);
    MapIcon.setBounds(0,0,Icon.getIconWidth(),Icon.getIconHeight());
    MapPanel.setBounds(0,0,Icon.getIconWidth(),Icon.getIconHeight());
    
    //this is what gets the scroll bars to actually work
    MapPanel.setPreferredSize(new Dimension(Icon.getIconWidth(),Icon.getIconHeight()));
    
  }
  
  
  public JLabel SetMap(String szMapLocation)
  {
    MapPanel.removeAll();       //clear the area first
    ImageIcon Icon = new ImageIcon(szMapLocation);
    MapIcon = new JLabel(Icon);
    MapPanel.add(MapIcon);


    MapIcon.setBounds(0,0,Icon.getIconWidth(),Icon.getIconHeight());
    MapPanel.setBounds(0,0,Icon.getIconWidth(),Icon.getIconHeight());

    //this is what gets the scroll bars to actually work
    MapPanel.setPreferredSize(new Dimension(Icon.getIconWidth(),Icon.getIconHeight()));
    
    return MapIcon;
  }
  
  //shows how many access points have been downloaded
  public void Status(int Points)
  {
    StatusText.setText("Imported "+Points+" access points from WiGLE.net");
  }
 
  //put all the points in the MapPanel
  public void DrawPoints()
  {    
    Color Green = new java.awt.Color(0,255,0);
    Color Red = new java.awt.Color(255,0,0);
    
    int NumPoints = APDB.GetCount();
    int mapWidth = MapIcon.getWidth();
    int mapHeight = MapIcon.getHeight();

    if(NumPoints==0)
    {
      StatusText.setText("Error: no points in database");
    }
    
    //hide the panel until completed adding buttons
    MapIcon.setVisible(false);

    point = new JButton[NumPoints];
    for(int i=0;i<NumPoints;i++)
    {
      point[i] = new JButton();
      point[i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
      point[i].setPreferredSize(new java.awt.Dimension(6, 6));

      //make sure each point has an action listener
      point[i].addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent evt) { PointClicked(evt); }
      });

      //draw each point
      MapIcon.add(point[i]);
      point[i].setBounds(
              (m.GetYPos(APDB.GetAP(i).GetLon(),0,mapWidth)-(ButtonSize/2)),
              (m.GetXPos(APDB.GetAP(i).GetLat(),0,mapHeight)-(ButtonSize/2)),
              ButtonSize, ButtonSize);  //button size
      if(!APDB.GetAP(i).GetWEP())
        point[i].setBackground(Green);
      else
        point[i].setBackground(Red);
    }

    MapIcon.setVisible(true);
  }
  
  
  //save points function
  public int ExportDB(String szFilename)
  {
    ObjectOutputStream output;
    
    try                             //open the file
      { output = new ObjectOutputStream(new FileOutputStream(szFilename)); }
      catch (IOException ioException)
      { StatusText.setText("Unable to open file"); return -1; }
    
    try                             //write the data
      { output.writeObject(APDB); }
    catch(IOException ioException)
      { StatusText.setText("Unable to write to file"); }
    
    try                             //close the file
      { output.close(); }
      catch (IOException ioException)
      { StatusText.setText("Error closing file."); }
    return 0;    
  }
  
  
  public PointDB ImportDB(String szFileName)
  {
    ObjectInputStream input=null;
    PointDB pdb = new PointDB();
    
    try                                   //open the file
      { input = new ObjectInputStream(new FileInputStream(szFileName)); }
      catch (IOException ioException)
      { StatusText.setText("Error opening file file."); }
    
    try                                   //read the file
    {
      while(true)
      { pdb = (PointDB) input.readObject(); }
    }
    catch(EOFException endOfFileException)
    {
      StatusText.setText("Successfully imported database "+szFileName);
    }
    catch(IOException ioException)
    {
      StatusText.setText("Error importing database, exiting");
      return null;
    }
    catch(ClassNotFoundException classNotFoundException)
    {
      StatusText.setText("Unable to create object, exiting");
      return null;
    }
    
    try                     //close the file
      { input.close(); }
      catch (IOException ioException)
      {
        StatusText.setText("Error closing file.");
        return null;
      }
    return pdb;
  }
  
  
  //void AddWiglePointParsed()
  //{
  //  StatusText.setText("Downloading points from WiGLE.net: "+(++WiglePointsParsed));
  //}
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FileChooser = new javax.swing.JFileChooser();
        AbootDialog = new javax.swing.JDialog();
        jTextPane1 = new javax.swing.JTextPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        MapScroll = new javax.swing.JScrollPane();
        MapPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        InfoText = new javax.swing.JTextArea();
        ButtonBar = new javax.swing.JToolBar();
        OpenMapButton = new javax.swing.JButton();
        GetPointsButton = new javax.swing.JButton();
        ImportFromFileButton = new javax.swing.JButton();
        SavePointsButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        ZoomIn = new javax.swing.JButton();
        ZoomOut = new javax.swing.JButton();
        StatusText = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMap = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        importPoints = new javax.swing.JMenuItem();
        exportPoints = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        FileChooser.setOpaque(true);

        AbootDialog.setTitle("Aboot");
        AbootDialog.setAlwaysOnTop(true);
        AbootDialog.setBackground(new java.awt.Color(204, 204, 255));
        AbootDialog.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        AbootDialog.setModal(true);
        AbootDialog.setResizable(false);
        AbootDialog.getContentPane().setLayout(new java.awt.FlowLayout());

        jTextPane1.setBackground(new java.awt.Color(204, 204, 255));
        jTextPane1.setEditable(false);
        jTextPane1.setText("This project by:\nRyan Clark\n\nProfessor Wheeler\nCET431 - Spring 2005\n");
        AbootDialog.getContentPane().add(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Wireless AP Mapping");
        setName("MainWindow"); // NOI18N

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setDividerSize(6);
        jSplitPane1.setOneTouchExpandable(true);

        MapScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        MapScroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        MapScroll.setPreferredSize(new java.awt.Dimension(1200, 1200));

        MapPanel.setLayout(null);
        MapScroll.setViewportView(MapPanel);

        jSplitPane1.setRightComponent(MapScroll);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        InfoText.setBackground(new java.awt.Color(204, 204, 255));
        InfoText.setEditable(false);
        InfoText.setDisabledTextColor(new java.awt.Color(0, 0, 51));
        jScrollPane1.setViewportView(InfoText);

        jPanel1.add(jScrollPane1);

        jSplitPane1.setLeftComponent(jPanel1);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        ButtonBar.setFloatable(false);

        OpenMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/warmap/Icon/Open_Icon.png"))); // NOI18N
        OpenMapButton.setText("Open Map");
        OpenMapButton.setToolTipText("Open Map");
        OpenMapButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 2, 1, 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        OpenMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenMapButtonActionPerformed(evt);
            }
        });
        ButtonBar.add(OpenMapButton);

        GetPointsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/warmap/Icon/WiGLE_Icon.png"))); // NOI18N
        GetPointsButton.setText("Download APs");
        GetPointsButton.setToolTipText("Download Access Points From Wigle.net");
        GetPointsButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 2, 1, 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        GetPointsButton.setEnabled(false);
        GetPointsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GetPointsButtonActionPerformed(evt);
            }
        });
        ButtonBar.add(GetPointsButton);

        ImportFromFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/warmap/Icon/Import_Icon.png"))); // NOI18N
        ImportFromFileButton.setText("Import Points");
        ImportFromFileButton.setToolTipText("Import Points From File");
        ImportFromFileButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 2, 1, 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        ImportFromFileButton.setEnabled(false);
        ImportFromFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportFromFileButtonActionPerformed(evt);
            }
        });
        ButtonBar.add(ImportFromFileButton);

        SavePointsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/warmap/Icon/Save_Icon.png"))); // NOI18N
        SavePointsButton.setText("Export Points");
        SavePointsButton.setToolTipText("Save Points To File");
        SavePointsButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 2, 1, 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        SavePointsButton.setEnabled(false);
        SavePointsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SavePointsButtonActionPerformed(evt);
            }
        });
        ButtonBar.add(SavePointsButton);
        ButtonBar.add(jSeparator3);

        jLabel1.setText("Zoom");
        ButtonBar.add(jLabel1);

        ZoomIn.setText("+");
        ZoomIn.setEnabled(false);
        ZoomIn.setPreferredSize(new java.awt.Dimension(20, 10));
        ZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZoomInActionPerformed(evt);
            }
        });
        ButtonBar.add(ZoomIn);

        ZoomOut.setText("-");
        ZoomOut.setEnabled(false);
        ZoomOut.setPreferredSize(new java.awt.Dimension(20, 10));
        ZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZoomOutActionPerformed(evt);
            }
        });
        ButtonBar.add(ZoomOut);

        getContentPane().add(ButtonBar, java.awt.BorderLayout.NORTH);

        StatusText.setBackground(new java.awt.Color(212, 208, 200));
        StatusText.setEditable(false);
        StatusText.setText("Status");
        getContentPane().add(StatusText, java.awt.BorderLayout.SOUTH);

        fileMenu.setText("File");

        openMap.setText("Open map");
        openMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMapActionPerformed(evt);
            }
        });
        fileMenu.add(openMap);
        fileMenu.add(jSeparator2);

        importPoints.setText("Import points");
        importPoints.setEnabled(false);
        importPoints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importPointsActionPerformed(evt);
            }
        });
        fileMenu.add(importPoints);

        exportPoints.setText("Export points");
        exportPoints.setEnabled(false);
        exportPoints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportPointsActionPerformed(evt);
            }
        });
        fileMenu.add(exportPoints);
        fileMenu.add(jSeparator1);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText("Help");

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-665)/2, (screenSize.height-506)/2, 665, 506);
    }// </editor-fold>//GEN-END:initComponents

  private void exportPointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportPointsActionPerformed
    SavePointsButtonActionPerformed(evt);
  }//GEN-LAST:event_exportPointsActionPerformed

  private void importPointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importPointsActionPerformed
    ImportFromFileButtonActionPerformed(evt);
  }//GEN-LAST:event_importPointsActionPerformed

  private void openMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMapActionPerformed
    OpenMapButtonActionPerformed(evt);
  }//GEN-LAST:event_openMapActionPerformed

  private void ZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZoomOutActionPerformed
    if(ZoomLevel>0)
    {
      if(ZoomLevel<1)
        ZoomOut.setEnabled(false);
      
      MapPanel.removeAll();       //clear the map, cant use any of it
      MapIcon = SetMap(m.GetImage(--ZoomLevel));
      DrawPoints();
    }
    ZoomIn.setEnabled(true);
  }//GEN-LAST:event_ZoomOutActionPerformed

  private void ZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZoomInActionPerformed
    if(ZoomLevel<3)
    {
      //if(ZoomLevel>1)
      //  ZoomIn.setEnabled(false);

      MapPanel.removeAll();       //clear the map, cant use any of it
      MapIcon = SetMap(m.GetImage(++ZoomLevel));
      DrawPoints();
    }
    ZoomOut.setEnabled(true);
  }//GEN-LAST:event_ZoomInActionPerformed

  private void SavePointsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SavePointsButtonActionPerformed
    if (FileChooser.showSaveDialog(ImportFromFileButton)==JFileChooser.CANCEL_OPTION) return;
    File fname = FileChooser.getSelectedFile();
    String szFileName = fname.getAbsoluteFile().toString();
    
    if(!szFileName.endsWith(".wgp"))
      szFileName.concat(".wgp");
    
    if(ExportDB(szFileName)!=0)
      StatusText.setText("Error exporting points to file.");
    else
      StatusText.setText("Successfully exported points to "+szFileName);
  }//GEN-LAST:event_SavePointsButtonActionPerformed

  //repaint all the points
  public void RepaintPoints()
  {
      for(int i=0;i<APDB.GetCount();i++)
      point[i].repaint();
  }
  
  //if a point was clicked
  private void PointClicked(java.awt.event.ActionEvent evt) 
  {
    Color Selected = new java.awt.Color(0,0,255);
    JButton b;
    b=(JButton) evt.getSource();
    
    for(int i=0;i<APDB.GetCount();i++)
    {
      if(b==point[i])
      {
        InfoText.setText(m.toString()+"\n"+APDB.GetCount()+" points"+
                "\n-----------------------------------\n"+APDB.GetAP(i).toString());
        StatusText.setText(APDB.GetAP(i).GetMAC()+" - "+APDB.GetAP(i).GetSSID());
        point[i].setBackground(Selected);
        point[i].repaint();
      }
    }
  }
  
  private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
    //AbootDialog.setPreferredSize(new Dimension(140,120));
    AbootDialog.setSize(140,130);
    AbootDialog.setVisible(true);
  }//GEN-LAST:event_aboutMenuItemActionPerformed

  private void GetPointsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GetPointsButtonActionPerformed
    int count=0;
    int timeout_count=0;
    StatusText.setText("Downloading points - ");

    do {
      temp_results = wigle.AddResults(APDB,Offset,
              m.GetUpperLeftX(),m.GetLowerRightX(),     //longitude
              m.GetUpperLeftY(),m.GetLowerRightY());    //latitude
      
      if(temp_results==-1)
      {
        if(timeout_count>2)
          break;
        timeout_count++;
      }
      results+=temp_results;
      if(temp_results==1000) Offset+=1000;
      count+=temp_results;
      
      StatusText.setText(StatusText.getText()+count+", ");
      StatusText.repaint();
    } while ((temp_results==1000)&&(timeout_count<3));
    StatusText.setText("Downloaded "+count+" points from WiGLE.net");
    if(results==-1)
      StatusText.setText("Error, could not download points.  Possible network problem.");
    DrawPoints();
    SavePointsButton.setEnabled(true);
    exportPoints.setEnabled(true);
    
    InfoText.setText(m.toString()+"\n"+APDB.GetCount()+" points");
  }//GEN-LAST:event_GetPointsButtonActionPerformed

  private void ImportFromFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportFromFileButtonActionPerformed
    if (FileChooser.showOpenDialog(ImportFromFileButton)==JFileChooser.CANCEL_OPTION) return;
    File fname = FileChooser.getSelectedFile();
    String szFileName = fname.getAbsoluteFile().toString();
    
    if(szFileName.endsWith(".wgp"))
      StatusText.setText("Imported "+fname.getAbsoluteFile());
    else
    {
      StatusText.setText("Failed to open file, only open files that end in \".wgp\"");
      return;
    }
    
    APDB = ImportDB(szFileName);
    
    DrawPoints();
    SavePointsButton.setEnabled(true);
    exportPoints.setEnabled(true);
    
    InfoText.setText(m.toString()+"\n"+APDB.GetCount()+" points");
  }//GEN-LAST:event_ImportFromFileButtonActionPerformed

  private void OpenMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenMapButtonActionPerformed
    if (FileChooser.showOpenDialog(OpenMapButton)==JFileChooser.CANCEL_OPTION)
        return;
    File fname = FileChooser.getSelectedFile();
    String szFileName = fname.getAbsoluteFile().toString();
    
    if(szFileName.endsWith(".mappack"))
      StatusText.setText("Imported "+fname.getAbsoluteFile());
    else
    {
      StatusText.setText("Failed to open file, only open files that end in \".mappack\"");
      return;
    }
    
    //put the map into its class
    m.ImportMap(fname.getAbsoluteFile().toString(),fname.getPath().toString());
    
    //output map info to side frame
    InfoText.setText(m.toString()+"\n"+APDB.GetCount()+" points");

    ZoomLevel=0;
    MapIcon = SetMap(m.GetImage(ZoomLevel));
    
    GetPointsButton.setEnabled(true);
    ZoomIn.setEnabled(true);
    ImportFromFileButton.setEnabled(true);
    importPoints.setEnabled(true);
    SavePointsButton.setEnabled(false);
    exportPoints.setEnabled(false);
  }//GEN-LAST:event_OpenMapButtonActionPerformed
  
  private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
    System.exit(0);
  }//GEN-LAST:event_exitMenuItemActionPerformed
  
  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new MainWindow().setVisible(true);
      }
    });
  }
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AbootDialog;
    private javax.swing.JToolBar ButtonBar;
    private javax.swing.JFileChooser FileChooser;
    private javax.swing.JButton GetPointsButton;
    private javax.swing.JButton ImportFromFileButton;
    private javax.swing.JTextArea InfoText;
    private javax.swing.JPanel MapPanel;
    private javax.swing.JScrollPane MapScroll;
    private javax.swing.JButton OpenMapButton;
    private javax.swing.JButton SavePointsButton;
    private javax.swing.JTextArea StatusText;
    private javax.swing.JButton ZoomIn;
    private javax.swing.JButton ZoomOut;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenuItem exportPoints;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem importPoints;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMap;
    // End of variables declaration//GEN-END:variables
  
}
