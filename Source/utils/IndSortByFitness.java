/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import individual.Individual;
import java.util.Comparator;

/**
 *
 * @author mnellar
 */
public class IndSortByFitness implements Comparator<Individual>{

    @Override
    public int compare(Individual o1, Individual o2) {
        double difference = o1.getFitness() - o2.getFitness();
        if (difference > 0)
            return 1;
        else if (difference == 0)
            return 0;
        else
            return -1;
    }
}