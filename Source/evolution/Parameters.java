/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution;

import ce_activity6.DataWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author InAns
 */
public class Parameters{
    
    // Evolutionary data, self-explaining
    public static int IndividualsCount = 1000; 
    public static int EliteCount = 10;
    public static int OffspringCount = IndividualsCount - EliteCount;
    public static int ParentsCount = 500;
    public static int MaxGenerations = 1000;
    public static int ExecutionsCount = 100;
    public static double MutationRate = 0.15;
    public static double MutationRateBase = 2.0;
    public static boolean DynamicMutationRate = true;
     
    // Sudoku data
    // Subgrid size
    public static int GridSize = 3;
    
    // Big grid size
    public static int GridCount = GridSize * GridSize;
    
    // Grid that contains original puzzle, with fixed numbers
    public static int[][] Grid = new int[GridCount][GridCount];
    
    public static final Integer[] SET_VALUES = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    
    public static void readParameters(String fileName) 
    {
        try 
        {
            InputStream input = new FileInputStream(fileName); 
            Properties properties = new Properties();
            properties.load(input);
            IndividualsCount = Integer.parseInt(properties.getProperty("IndividualsCount"));         
            ParentsCount = Integer.parseInt(properties.getProperty("ParentsCount"));
            OffspringCount = IndividualsCount - EliteCount;
            MaxGenerations = Integer.parseInt(properties.getProperty("MaxGenerations"));
            ExecutionsCount = Integer.parseInt(properties.getProperty("ExecutionsCount"));
            DynamicMutationRate = Boolean.parseBoolean(properties.getProperty("DynamicMutationRate"));
            MutationRate = Float.parseFloat(properties.getProperty("MutationRate")); 
            MutationRateBase = Float.parseFloat(properties.getProperty("MutationRateBase")); 
            DataWriter.testName = properties.getProperty("TestName");
        }
        catch (IOException ex) 
        {
            Logger.getLogger(Parameters.class.getName()).log(Level.SEVERE, null, ex);
        }               
    }

    public static String getParameters() 
    {
        return DataWriter.testName + "_" + IndividualsCount; 
    }
}
