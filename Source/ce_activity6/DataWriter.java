/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce_activity6;

import evolution.Parameters;
import individual.Individual;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author GANEARAN
 */
public class DataWriter {
    
    public static String testName = "Sudoku";
    public static String directory = "results/";
    public static String fileNameFitness = "Fitness_results";
    public static String fileNameGenerations = "Generations_results";
    public static String fileNameDataResults = "Data_results";
    public static String fileNameGraphicResults = "Graphic_results";

    public static void addTest() 
    {
        try 
        {
            // Write to the results file
            FileWriter outFile = new FileWriter(fileNameFitness, true);
            PrintWriter out = new PrintWriter(outFile);

            // Write text to file
            out.print(Parameters.getParameters() + ";");
            out.close();
            
            // Write to the generations file
            outFile = new FileWriter(fileNameGenerations, true);
            out = new PrintWriter(outFile);

            // Write text to file
            out.print(Parameters.getParameters() + ";");
            out.close();
            
            // Write to the data file
            outFile = new FileWriter(fileNameDataResults, true);
            out = new PrintWriter(outFile);

            // Write text to file
            out.println(Parameters.getParameters());
            out.close();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    static void createFile() 
    {
        try 
        {
            directory += testName + "/";
            fileNameFitness = directory + fileNameFitness + "_" + testName + ".txt";
            fileNameGenerations = directory + fileNameGenerations + "_" + testName + ".txt";
            fileNameDataResults = directory + fileNameDataResults + "_" + testName + ".txt";
            fileNameGraphicResults = directory + fileNameGraphicResults + "_" + testName + ".jpg";
            
            final File file = new File(fileNameFitness);
            final File parent_directory = file.getParentFile();

            if (null != parent_directory)
            {
                parent_directory.mkdirs();
            }
            
            // Create the fitness results file
            FileWriter outFile = new FileWriter(fileNameFitness);
            PrintWriter out = new PrintWriter(outFile);

            // Write text to file
            out.print(";");
            for(int i = 0; i < Parameters.ExecutionsCount; i++)
            {
                out.print(i + ";");
            }
            out.println();
            out.close();
            
            // Create the generations results file
            outFile = new FileWriter(fileNameGenerations);
            out = new PrintWriter(outFile);

            // Write text to file
            out.print(";");
            for(int i = 0; i < Parameters.ExecutionsCount; i++)
            {
                out.print(i + ";");
            }
            out.println();
            out.close();
            
            // Create the results file
            new FileWriter(fileNameDataResults);
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void writeExecutionResults(int bestMinFitness, int generation, Individual individual, int execution) 
    {
        try 
        {
            // Write to the fitness results file
            FileWriter outFile = new FileWriter(fileNameFitness, true);
            PrintWriter out = new PrintWriter(outFile);

            // Write text to file
            out.print(bestMinFitness + ";");
            out.close();
            
            // Write to the generations results file
            outFile = new FileWriter(fileNameGenerations, true);
            out = new PrintWriter(outFile);

            // Write text to file
            out.print(generation + ";");
            out.close();
            
            // Write to the results file
            outFile = new FileWriter(fileNameDataResults, true);
            out = new PrintWriter(outFile);

            // Write text to file
            out.println("Execution: " + execution);
            out.print(individual);
            out.close();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
