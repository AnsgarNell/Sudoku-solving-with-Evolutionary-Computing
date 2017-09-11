/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package steps;

import evolution.Parameters;
import individual.Individual;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author InAns
 */
public class Initialization{
    
    public static void initializePopulation(ArrayList<Individual> population)
    {     
        for(int i = 0; i < Parameters.IndividualsCount; i++)
        {        
            Individual individual = new Individual(Parameters.Grid);
            initializeNumbers(individual);
            population.add(individual); 
        }
    }  

    private static void initializeNumbers(Individual individual) 
    {
        // For each individual, we go through the 9 3x3 subgrids,
        // and initialize each separately

        // Row counter for subgrids
        for(int i = 0; i < Parameters.GridSize; i++)
        {
            // Column counter for subgrids
            for(int j = 0; j < Parameters.GridSize; j++)
            {
                initializeSubGrid(i, j, individual);              
            }
        }        
    }

    private static void initializeSubGrid(int row, int column, Individual individual) 
    {
        // First we make a set with available numbers, discarding the fixed ones
        Set<Integer> setNumbers = new HashSet<Integer>((Arrays.asList(Parameters.SET_VALUES)));
        for(int i = 0; i < Parameters.GridSize; i++)
        {
            for(int j = 0; j < Parameters.GridSize; j++)
            {
                int subGridRow = row * Parameters.GridSize + i;
                int subGridColumn = column * Parameters.GridSize + j;
                if(individual.getGrid()[subGridRow][subGridColumn] < 0)
                {
                    setNumbers.remove(Math.abs(individual.getGrid()[subGridRow][subGridColumn]));
                }
            }
        }
        Random random = new Random();
        for(int i = 0; i < Parameters.GridSize; i++)
        {
            for(int j = 0; j < Parameters.GridSize; j++)
            {
                int subGridRow = row * Parameters.GridSize + i;
                int subGridColumn = column * Parameters.GridSize + j;
                if(individual.getGrid()[subGridRow][subGridColumn] == 0)
                {
                    int position = random.nextInt(setNumbers.size());
                    int number = (Integer) setNumbers.toArray()[position];
                    setNumbers.remove(number);
                    individual.getGrid()[subGridRow][subGridColumn] = number;
                }
            }
        }
    }
}
