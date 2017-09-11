/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package steps;

import evolution.Parameters;
import individual.Individual;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import utils.IndSortByInverseFitness;
import utils.IndSortByProbability;

/**
 *
 * @author InAns
 */
public class ParentSelection {

    public static void selectParents(ArrayList<Individual> population, ArrayList<Individual> parents) 
    {
        parents.clear();
        
        // Apply ranking
        setProbabilities(population);
        
        Collections.sort(population, new IndSortByProbability());
        // Create array a containing the sum of probabilities for each i
        Float[] a = new Float[Parameters.IndividualsCount];
        Float probabilitiesSum = new Float(0);
        for(int i = 0; i < Parameters.IndividualsCount; i++)
        {
            probabilitiesSum = probabilitiesSum + population.get(i).getProbability();
            a[i] = probabilitiesSum;
        }

        // Apply roulette wheel algorithm
        Random random = new Random();

        int current_member = 0;
        while(current_member < Parameters.ParentsCount)
        {
            double r = random.nextDouble();
            int i = 0;
            while((a[i] < r) && ( i < Parameters.IndividualsCount - 1))
            {
                i++;
            }
            parents.add(population.get(i));
            current_member++;
        }
        Collections.sort(parents, new IndSortByProbability());
    }    
    
    private static void setProbabilities(ArrayList<Individual> population)
    {
        // The probabilities are allways set with Ranking Selection
        Collections.sort(population, new IndSortByInverseFitness());
        Float s = new Float(1.5);
        for(int i = 0; i < Parameters.IndividualsCount; i++)
        { 
            // For each individual, its fitness and probability are stored
            Individual individual = population.get(i);
            Float probability = new Float(0);
            probability = (2 - s)/Parameters.IndividualsCount + (2*i*(s-1))/(Parameters.IndividualsCount*(Parameters.IndividualsCount-1));
            individual.setProbability(probability);
        }   
    }
}
