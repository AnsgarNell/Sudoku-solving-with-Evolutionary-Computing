/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package steps;

import evolution.Parameters;
import individual.Individual;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author InAns
 */
public class Evaluation {    
    
    public static double FitnessMean = 0.0;
    
    public static void evaluatePopulation(ArrayList<Individual> population)
    {
        double fitnessMean = 0.0;
        for (Individual e: population) 
        {
            // For each individual, calculate its fitness
            evaluate(e);
            fitnessMean += e.getFitness();
        }   
        FitnessMean = fitnessMean/population.size();
    }
    
    private static void evaluate(Individual individual)
    {     
        // The fitness for each individual will be the sum of how much numbers are repeated
        // in the grid's rows and columns
        int result = 0;
        int repeated = 0;
        Set<Integer> setNumbers = new HashSet<Integer>();
        
        // Check for each row how much numbers are repeated
        for(int i = 0; i < Parameters.GridCount; i++)
        {
            setNumbers.clear();
            for(int j = 0; j < Parameters.GridCount; j++)
            {
                // The set cannot contain duplicated numbers
                // As fixed numbers are negative, get the absolute value
                setNumbers.add(Math.abs(individual.getGrid()[i][j]));
            }
            // so it will contain 9 (or whatever) - duplicated numbers
            // or to express it in another way "duplicated numbers = 9 - setNumbers.size()"
            repeated = Parameters.GridCount - setNumbers.size();
            individual.getRowFitness()[i] = repeated;
            result = result + repeated;
        }
        
        // Do the same with the columns
        for(int j = 0; j < Parameters.GridCount; j++)
        {
            setNumbers.clear();
            for(int i = 0; i < Parameters.GridCount; i++)
            {
                setNumbers.add(Math.abs(individual.getGrid()[i][j]));
            }
            repeated = Parameters.GridCount - setNumbers.size();
            individual.getColumnFitness()[j] = repeated;
            result = result + repeated;
        }
        
        individual.setFitness(result);
    }
}
