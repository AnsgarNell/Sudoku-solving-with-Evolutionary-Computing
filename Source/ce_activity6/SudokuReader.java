/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce_activity6;

import evolution.Parameters;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author InAns
 */
public class SudokuReader {
    
    public static void readSudoku(String name) 
    {
        try 
        {
            // Read the file again to save the data into arrays
            BufferedReader file = new BufferedReader(new FileReader(name));          
            String data = file.readLine();
            int i = 0;
            while (data != null)
            {
                // Split each row into an array
                String[] dataArray = data.split(" ");
                for(int j = 0; j < Parameters.GridCount; j++)
                {
                    int number = Integer.parseInt(dataArray[j]);
                    // We mark the fixed numbers transforming them into negative numbers
                    if(number > 0)
                    {
                        number = -number;
                    }
                    Parameters.Grid[i][j] = number;
                }
                data = file.readLine();
                i++;
            }
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(SudokuReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) 
        {
            Logger.getLogger(SudokuReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
