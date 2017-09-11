/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution;

import ce_activity6.DataWriter;
import ce_activity6.InformationPanel;
import individual.Individual;
import java.util.ArrayList;
import java.util.Collections;
import steps.*;
import utils.IndSortByFitness;

/**
 *
 * @author mnellar
 */
public class Evolution extends Thread{
    
    // List containing all the individuals that form the population
    ArrayList<Individual> population = new ArrayList<Individual>();        
    ArrayList<Individual> offspring = new ArrayList<Individual>();;
    ArrayList<Individual> parents = new ArrayList<Individual>();;
    
    // Variables to keep track of the so far best individual's scores
    int minFitness;
    int bestMinFitness; 
    
    InformationPanel informationPanel;
       
    public Evolution(InformationPanel informationPanel)
    {
        this.informationPanel = informationPanel;
    }
    
    @Override
    public void run()
    {          
            // Check if all parameters are OK
            if(Parameters.ParentsCount > Parameters.IndividualsCount)
            {
                informationPanel.writeError("The number of parents can not be bigger than the population \nParentsNumber: " + Parameters.ParentsCount + " \nIndividualsNumber: " + Parameters.IndividualsCount);
            }
            else if(Parameters.ParentsCount % 2 != 0)
            {
                informationPanel.writeError("The number of parents must be an odd number \nParentsNumber: " + Parameters.ParentsCount);
            }
            else if(Parameters.OffspringCount % 2 != 0)
            {
                informationPanel.writeError("The number of parents must be an odd number \nParentsNumber: " + Parameters.ParentsCount);
            }
            else // Parameters are OK, proceed
            {         
                DataWriter.addTest();
                execute();
            }              
            end(); 
    }
    
    public void execute()
    {
        for(int execution = 0; execution < Parameters.ExecutionsCount; execution++)
        {
            initialize();          
            int generation = 0;
            while(generation < Parameters.MaxGenerations)
            {
                // Main actions
                evaluatePopulation();       
                ParentSelection.selectParents(population, parents);
                Recombination.recombinate(population, offspring, parents);
                Mutation.mutatePopulation(offspring);
                Evaluation.evaluatePopulation(offspring); 
                Replacement.replace(population, offspring, parents);

                if(bestMinFitness > minFitness)
                {
                    bestMinFitness = minFitness;
                    informationPanel.writeFitnessChange(bestMinFitness, generation);
                }   
                informationPanel.writeGeneration(generation);
                if(bestMinFitness == 0)
                {
                    break;
                }
                generation++;
            }
            informationPanel.writeExecutionBestFitness(bestMinFitness, execution);
            DataWriter.writeExecutionResults(bestMinFitness, generation, population.get(0), execution);
        }    
        informationPanel.writePopulation(printList(population));
    }
    
    private void evaluatePopulation()
    {
        Evaluation.evaluatePopulation(population);         
        calculateMinFitness(); 
        if(Parameters.DynamicMutationRate)
        {
            Mutation.changeMutationProbability();
        }
        informationPanel.writePopulation(printList(population));
    }
    
    private void calculateMinFitness() 
    {
        Collections.sort(population, new IndSortByFitness());
        minFitness = population.get(0).getFitness();
    }
    
    private void end()
    {   
        informationPanel.writePopulation(printList(population));
        informationPanel.writeInfo("Done"); 
        informationPanel.enableItems();
    }
    
    private String printList(ArrayList<Individual> list) 
    {
        String result = "";
        for(int i = 0; i < 4; i++)
        {
            result += list.get(i).toString();
        }
        return result;
    }

    private void initialize() 
    {
        // Clear previous data
        population.clear();
        offspring.clear();
        parents.clear();
        minFitness = 0;
        bestMinFitness = Integer.MAX_VALUE;
        
        Initialization.initializePopulation(population);   
    }
}

