/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package steps;

import evolution.Parameters;
import individual.Individual;
import java.util.ArrayList;

/**
 *
 * @author InAns
 */
public class Recombination {
    
    public static void recombinate(ArrayList<Individual> population, ArrayList<Individual> offspring, ArrayList<Individual> parents)
    {
        offspring.clear();

        int parent = 0;
        for(int i = 0; i < Parameters.OffspringCount; i=i+2)
        {           
            makeCrossOver(parents.get(parent), parents.get(parent + 1), offspring);
            parent = parent + 2;
            if(parent >= Parameters.ParentsCount)
            {
                parent = 0;
            }
        }
    }

    private static void makeCrossOver(Individual parent1, Individual parent2, ArrayList<Individual> offspring) 
    {
        // Create the first child, based on rows
        Individual child1 = new Individual();
        for(int i = 0; i < Parameters.GridSize; i++)
        {
            if(parent2.getRowSubGridFitness()[i] < parent1.getRowSubGridFitness()[i])
            {
                copyRowSubGrids(parent2, child1, i);
            }
            else
            {
                copyRowSubGrids(parent1, child1, i);
            }
        }
        offspring.add(child1);
        
        // Create the second child, based on columns
        Individual child2 = new Individual();
        for(int i = 0; i < Parameters.GridSize; i++)
        {
            if(parent2.getColumnSubGridFitness()[i] < parent1.getColumnSubGridFitness()[i])
            {
                copyColumnSubGrids(parent2, child2, i);
            }
            else
            {
                copyColumnSubGrids(parent1, child2, i);
            }
        }
        offspring.add(child2);
    }

    private static void copyRowSubGrids(Individual parent, Individual child, int row) 
    {
        int baseRow = row * Parameters.GridSize;
        for(int i = 0; i < Parameters.GridSize; i++)
        {
            for(int j = 0; j < Parameters.GridCount; j++)
            {
                child.getGrid()[baseRow + i][j] = parent.getGrid()[baseRow + i][j];
            }
        }
    }

    private static void copyColumnSubGrids(Individual parent, Individual child, int column) 
    {
        int baseColumn = column * Parameters.GridSize;
        for(int j = 0; j < Parameters.GridSize; j++)
        {
            for(int i = 0; i < Parameters.GridCount; i++)
            {
                child.getGrid()[i][baseColumn + j] = parent.getGrid()[i][baseColumn + j];
            }
        }
    }
}
