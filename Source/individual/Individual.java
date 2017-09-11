/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package individual;

import evolution.Parameters;

/**
 *
 * @author mnellar
 */
public class Individual{
    
    private int fitness;
    // We need probability for Ranking Selection
    private Float probability;
    private int[][] grid;
    // This variables contain the fitness for each row or column
    private int[] rowFitness;
    private int[] columnFitness;
    // This variables contain the sum of the above fitness in groups of three
    private int[] rowSubGridFitness;
    private int[] columnSubGridFitness;
    
    public Individual()
    {
        this.grid = new int[Parameters.GridCount][Parameters.GridCount];
        rowFitness = new int[Parameters.GridCount];
        columnFitness = new int[Parameters.GridCount];
        rowSubGridFitness = new int[Parameters.GridSize];
        columnSubGridFitness = new int[Parameters.GridSize];
    }
    
    public Individual(int[][] grid)
    {
        this();

        // Copy the original grid content
        this.grid = new int[Parameters.GridCount][Parameters.GridCount];
        for(int i = 0; i < Parameters.GridCount; i++)
        {
            System.arraycopy(grid[i], 0, this.grid[i], 0, Parameters.GridCount);
        }    
    }
    
    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) 
    {
        this.fitness = fitness;
        
        int result;
        // Calculate the subgrids fitness sum by rows
        for(int i = 0; i < Parameters.GridSize; i++)
        {
            result = 0;
            for(int j = 0 ; j < Parameters.GridSize; j++)
            {
                result = result + rowFitness[(i * Parameters.GridSize) + j];
            }
            rowSubGridFitness[i] = result;
        }
        
        // Do the same for the columns
        for(int j = 0; j < Parameters.GridSize; j++)
        {
            result = 0;
            for(int i = 0 ; i < Parameters.GridSize; i++)
            {
                result = result + columnFitness[(j * Parameters.GridSize) + i];
            }
            columnSubGridFitness[j] = result;
        }
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    } 

    public int[] getRowFitness() {
        return rowFitness;
    }

    public int[] getColumnFitness() {
        return columnFitness;
    }

    public void setRowFitness(int[] rowFitness) {
        this.rowFitness = rowFitness;
    }

    public void setColumnFitness(int[] columnFitness) {
        this.columnFitness = columnFitness;
    }

    public int[] getRowSubGridFitness() {
        return rowSubGridFitness;
    }

    public int[] getColumnSubGridFitness() {
        return columnSubGridFitness;
    }

    public Float getProbability() {
        return probability;
    }

    public void setProbability(Float probability) {
        this.probability = probability;
    }
  
    @Override
    public String toString()
    {
        String result = "Fitness: " + fitness + "\n";
        for(int i = 0; i < Parameters.GridCount; i++)
        {
            for(int j = 0; j < Parameters.GridCount; j++)
            {
                result += Math.abs(grid[i][j]) + " ";
            }
            result += "\n";
        }
        return result;
    } 
}


