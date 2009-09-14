/*
 * Input.java
 *
 * Created on July 23, 2004, 1:47 PM
 */

/**
 *
 * @author  Administrator32
 */

package warmap;

import java.io.*;
import javax.swing.*;


public class Input {

    /** Creates a new instance of Input */
    public Input() {
    }

public static int GUIInputInt()
{
String s; // the buffer
int nResult=0;

for(;;) // A while !
    {
    try {
        s = JOptionPane.showInputDialog(null,"Enter an integer.");
        nResult = Integer.parseInt(s);
        }


    catch(NumberFormatException e)
        {
        System.out.print("Bad input, please enter an integer> ");
        continue;   // Ask for the input again.
        }

  
    return nResult; // We'return done

    }
}


public static int InputInt()
{
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String s; // the buffer
int nResult=0;

for(;;) // A while !
    {
    try {
        s = br.readLine(); // Read a line of text from console
        nResult = Integer.parseInt(s);
        }


    catch(NumberFormatException e)
        {
        System.out.print("Bad input, please enter an integer> ");
        continue;   // Ask for the input again.
        }

    catch(IOException e)
        {
        System.out.println("Error reading console, aborting.");
        System.exit(-1); // Puke, exit program. 
        }
     // No exception was thrown, the value is OK

    return nResult; // We'return done

    }

}


public static double InputDouble()
{
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String s; // the buffer
double dResult=0;

for(;;) // A while !
    {
    try {
        s = br.readLine(); // Read a line of text from console
        dResult = Double.parseDouble(s);
        }


    catch(NumberFormatException e)
        {
        System.out.print("Bad input, please enter a double> ");
        continue;   // Ask for the input again.
        }

    catch(IOException e)
        {
        System.out.println("Error reading console, aborting.");
        System.exit(-1); // Puke, exit program. 
        }
     // No exception was thrown, the value is OK

    return dResult; // We'return done

    }

}


public static String InputString()
{
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String s=null; // the buffer
int nResult=0;

for(;;) // A while !
    {
    try {
        s = br.readLine(); // Read a line of text from console
        }



    catch(IOException e)
        {
        System.out.println("Error reading console, aborting.");
        System.exit(-1); // Puke, exit program. 
        }

return s;
    }

}




}
