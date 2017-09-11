/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package steps;

import evolution.Parameters;
import individual.Individual;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author InAns
 */
public class Mutation {
    
    public static void mutatePopulation(ArrayList<Individual> offspring)
    {
        for (Individual e: offspring) 
        {
            mutate(e);     
        }
    }  
    
    private static void mutate(Individual e) 
    {
        // For each individual, we go through the 9 3x3 subgrids,
        // and mutate each separately

        // Row counter for subgrids
        for(int i = 0; i < Parameters.GridSize; i++)
        {
            // Column counter for subgrids
            for(int j = 0; j < Parameters.GridSize; j++)
            {
                mutateSubGrid(i, j, e);              
            }
        }
    }

    private static void mutateSubGrid(int row, int column, Individual e) 
    {
        Random random = new Random();
        
        double probability = random.nextDouble();

        // Check if mutation should happen
        while(probability <= Parameters.MutationRate)
        {
            // Only swap mutation is feasible, so do it
            int swap1;
            int swap2;
            int position1Row;
            int position1Column;
            int position2Row;
            int position2Column; 

            do
            {
                do
                {
                    // Choose randomly both positions until they are different
                    swap1 = random.nextInt(Parameters.GridCount);
                    swap2 = random.nextInt(Parameters.GridCount);
                }while(swap1 == swap2);

                // Convert one-dimension position to bidimensional absolute (9x9) array position
                position1Row = (swap1 / Parameters.GridSize) + (row * Parameters.GridSize);
                position1Column = (swap1 % Parameters.GridSize) + (column * Parameters.GridSize);
                position2Row = (swap2 / Parameters.GridSize) + (row * Parameters.GridSize);
                position2Column = (swap2 % Parameters.GridSize) + (column * Parameters.GridSize);
            // If any of the positions are fixed, select others
            }while((e.getGrid()[position1Row][position1Column] < 0)||(e.getGrid()[position2Row][position2Column] < 0));

            // Swap positions contents
            int backup = e.getGrid()[position1Row][position1Column];                   
            e.getGrid()[position1Row][position1Column] = e.getGrid()[position2Row][position2Column];
            e.getGrid()[position2Row][position2Column] =  backup;
            
            // Get another number and keep mutating if necessary
            probability = random.nextDouble();
        }
    }

    public static void changeMutationProbability() 
    {
        // Calculate new mutation rate, avoiding it to be higher than 0.90
        Parameters.MutationRate = Math.min(Parameters.MutationRateBase/Evaluation.FitnessMean, 0.90);
    }
}
