/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package steps;

import evolution.Parameters;
import individual.Individual;
import java.util.ArrayList;
import java.util.Collections;
import utils.IndSortByFitness;

/**
 *
 * @author InAns
 */
public class Replacement {
    
    public static void replace(ArrayList<Individual> population, ArrayList<Individual> offspring, ArrayList<Individual> parents)
    {
        // Sort population and offspring by their fitness
        Collections.sort(population, new IndSortByFitness());
        Collections.sort(offspring, new IndSortByFitness());  
        
        // Replace all individuals except the elite
        for(int i = 0; i < Parameters.IndividualsCount - Parameters.EliteCount; i++)
        {
            population.set(i + Parameters.EliteCount - 1, offspring.get(i));
        }       
    }    
}
