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
public class IndSortByProbability implements Comparator<Individual>{

    @Override
    public int compare(Individual o1, Individual o2) {
        Float difference = o1.getProbability() - o2.getProbability();
        if (difference > 0)
            return -1;
        else if (difference == 0)
            return 0;
        else
            return 1;
    }
}
