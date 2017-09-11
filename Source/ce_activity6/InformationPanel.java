/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ce_activity6;

/**
 *
 * @author InAns
 */
public interface InformationPanel {

    void writeInfo(String text);
    void writeError(String text);
    void writeExecutionBestFitness(double value, int execution);
    void writeFitnessChange(double value, int execution);
    void writeGeneration(int generation);
    void writePopulation(String text);
    void enableItems();
}
