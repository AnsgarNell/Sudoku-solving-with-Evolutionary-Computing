<div id="globalWrapper">

# Computación Evolutiva - Activity No. 6

## Ansgar Mikel Nell Artolazabal

# <a class="toc" name="toc-Section-1">1</a> Introduction

<div class="Unindented">A Sudoku is a puzzle pastime where a <span class="formula">9 × 9</span> grid is presented divided in smaller <span class="formula">3 × 3</span> grids. Most of this grids spaces are blank, and others are filled with numbers between 1-9\. The objective is to fill the blank squares so that there are no repeated numbers neither in the <span class="formula">3 × 3</span> grids nor in the <span class="formula">9 × 9</span> grids columns nor rows, as it can be seen in the Fig. [1↓](#fig:Start-puzzle-(a)).</div>

<div class="Indented">

<div class="float"><a class="Label" name="fig:Start-puzzle-(a)"></a>

<div class="multifigure"><span lang="es"><span class="float">

<div class="figure">

<div class="PlainVisible"><span lang="es">![figure Blank.png](img/Blank.png)</span>  
<span lang="es">

<div class="caption">(a) </div>

</span></div>

</div>

</span><span class="hfill"></span><span class="float">

<div class="figure"><span lang="es">![figure Filled.png](img/Filled.png)

<div class="caption">(b) </div>

</span></div>

</span></span>

<div class="caption">Figure 1 Start puzzle (a) and resolved (b) Sudoku game</div>

</div>

</div>

</div>

<div class="Indented">Finding the solution of a Sudoku game is a constraint satisfaction problem (CSP).</div>

<div class="Indented">The problem can be considered in some way as a mixing between the traveling salesman problem, where the shortest distance between a given list of cities must be found permuting the route that will be taken. A similar action must be done in the Sudoku’s <span class="formula">3 × 3</span> grid, rearranging the 9 numbers, thus in this case there is no function to be calculated. On the other hand, it recalls the eight queens problem, which have to be placed in a chess table avoiding eating each other. In a similar way, the Sudoku row and columns can’t have the same number two or more times.</div>

# <a class="toc" name="toc-Section-2">2</a> Constraint handling

<div class="Unindented">Resolving a CSP with Evolutionary Algorithms (EA) requires transforming the original problem into a constrained optimization problem (COP). There are several ways for achieving this. The next list shows the most common ones <span class="bibcites">[[<span class="bib-index">1</span>](#biblio-1)]</span>:</div>

1.  A function is applied to the result so that infeasible solutions are penalized and their fitness is reduced. Usually the penalization is proportional to the number of violated constraints.
2.  “Repair” a unfeasible solution returning a feasible one.
3.  Ensure the feasibility of all solutions via a specific representation for the problem, as initialization, mutation and recombination.
4.  Use a “decoder” from genotypes to phenotypes so that the last ones are always feasible.

<div class="Unindented">The penalty function that will be used counts how many numbers are repeated in each row and column, or, to express it in another way, how many numbers are missing. Mathematically it can be expressed like <span class="bibcites">[[<span class="bib-index">4</span>](#biblio-4)]</span>:\begin_inset Separator latexpar\end_inset</div>

<div class="Indented">

<div class="center">

<div class="formula"><a class="eqnumber" name="eq:1">(1)</a> <span class="environment"><span class="arrayrow"><span class="arraycell align-r">_f_(_x_) = <span class="limits"><sup class="limit">9</sup><span class="limit">⎲</span><span class="limit">⎳</span><sub class="limit">_i_ = 1</sub></span>_g_<sub>_i_</sub>(_x_) + <span class="limits"><sup class="limit">9</sup><span class="limit">⎲</span><span class="limit">⎳</span><sub class="limit">_j_ = 1</sub></span>_h_<sub>_j_</sub>(_x_)</span> </span><span class="arrayrow"><span class="arraycell align-r"></span> </span><span class="arrayrow"><span class="arraycell align-r">_g_<sub>_i_</sub>(_x_) = |_x_<sub>_i_</sub>|,  _h_<sub>_j_</sub>(_x_) = |_x_<sub>_j_</sub>|</span></span></span></div>

</div>

</div>

<div class="Indented">In this case <span class="formula">|.|</span> indicates the number of same numerals in a row or column.</div>

# <a class="toc" name="toc-Section-3">3</a> Evolutionary Algorithm selection

<div class="Unindented">This problem is well suited to be resolved using Genetic Algorithms, as we want to find an optimal solution searching in a solution space <span class="bibcites">[[<span class="bib-index">6</span>](#biblio-6)]</span>. In this kind of EA the genes of the individuals evolve similarly to how evolution works in nature, selecting the fittest parents which will create offspring individuals. This will mutate with some probability optimizing their solution, so that every generation will be closer to the exact solution.</div>

## <a class="toc" name="toc-Subsection-3.1">3.1</a> Representation

<div class="Unindented">The genes chosen for this problem will represent the <span class="formula">9 × 9</span> through an integer array, although virtually the grid is divided into smaller <span class="formula">3 × 3</span> grids, so there will be 9 <span class="formula">3 × 3</span> arrays. Each of them will be a permutation representation as this is the operation that will create new different solutions.</div>

<div class="Indented">There will be two ways for accessing the grid: an absolute one, where the used form is [9][9], and another one, where each sub-grid will be located in a [3][3] position in the grid, as can be seen in Fig. [2↓](#fig:-sub-arrays).</div>

<div class="Indented">

<div class="float"><a class="Label" name="fig:-sub-arrays"></a>

<div class="figure">

<div class="center">![figure 3x3_subarrays.png](img/3x3_subarrays.png)</div>

<div class="caption">Figure 2 <span class="formula">3 × 3</span> sub-arrays</div>

</div>

</div>

</div>

<div class="Indented">To maintain the fixed numbers that are given as input to the puzzle through mutation, recombination and initialization, they are converted into negative values. Therefor calculations are made with absolute values.</div>

## <a class="toc" name="toc-Subsection-3.2">3.2</a> Evaluation

<div class="Unindented">As showed in formula [1↑](#eq:1), the evaluation will count how many numbers are repeated in each row and column, and the sum will be the individual’s fitness. Logically, if a solution is found there will be no duplicates and the fitness will sum zero, so this is an inverse fitness optimization problem where the main goal will be to reach zero.</div>

## <a class="toc" name="toc-Subsection-3.3">3.3</a> Recombination

<div class="Unindented">There are four different recombination operators for permutation representations that have been used so far <span class="bibcites">[[<span class="bib-index">1</span>](#biblio-1)]</span>:</div>

1.  Partially Mapped Crossover
2.  Edge Crossover
3.  Order Crossover
4.  Cycle Crossover

<div class="Unindented">The two last ones are not applicable for the Sudoku problem because the order is not relevant, nor it is the absolute position. On the other hand, it has been verified that cycle crossover has worst properties of locality <span class="bibcites">[[<span class="bib-index">2</span>](#biblio-2)]</span>. Sudoku represents a special case from a permutation point of view, due to the fact that preserving order or relative position does not have better results because of the interdependency between sub-grids. Therefor the permutation operator introduced by Sato <span class="bibcites">[[<span class="bib-index">3</span>](#biblio-3)]</span> will be used, where crossover is done based on rows for the first child and based on columns for the second. In each case, the fittest row or column from both parents will be selected preserving highly fit results, as it can be seen in Fig. [3↓](#fig:Sato's-row-and). It must be noted that this rows and columns refer to the sub-grids, meaning that each position is a <span class="formula">3 × 3</span> grid, and the related fitness is the sum of fitness in groups of three rows or columns.</div>

<div class="Indented">

<div class="float"><a class="Label" name="fig:Sato's-row-and"></a>

<div class="figure">

<div class="center">![figure Crossover.png](img/Crossover.png)</div>

<div class="caption">Figure 3 Sato’s row and columns fitness based crossover (taken from <span class="bibcites">[[<span class="bib-index">4</span>](#biblio-4)]</span>)</div>

</div>

</div>

</div>

## <a class="toc" name="toc-Subsection-3.4">3.4</a> Mutation

<div class="Unindented">There are also four mutation types which can be selected when permutation is used: Swap, insert, scramble and inversion. For the current problem, the only feasible option is the swap operator as it is the only one that does not alter significantly the so far obtained results, because it changes only two positions.</div>

<div class="Indented">It must be noted that the mutation probability will affect each <span class="formula">3 × 3</span> grid separately, so for an individual the total probability of having a change in its numbers is nine times bigger.</div>

## <a class="toc" name="toc-Subsection-3.5">3.5</a> Parent Selection

<div class="Unindented">The simplest parent selection method is the fitness proportional selection. However, this approach suffers from some problems like premature convergence or more sensitivity to outlying fitness values which can cause that a very fit individual dominates the intermediate population and reduces diversity <span class="bibcites">[[<span class="bib-index">5</span>](#biblio-5)]</span>, so the alternative ranking selection will be used, in order to distribute selection probability more adequately.</div>

<div class="Indented">The selection probability will be implemented with the roulette wheel algorithm. Tournament selection is dismissed because there is no problem to have knowledge of the entire population.</div>

## <a class="toc" name="toc-Subsection-3.6">3.6</a> Replacement

<div class="Unindented">For survival selection, elitism will be used to preserve the current fittest members, but any other individual will be replaced by the offspring using age-based replacement.</div>

# <a class="toc" name="toc-Section-4">4</a> Parameters

## <a class="toc" name="toc-Subsection-4.1">4.1</a> Mutation rate, dynamic vs. fixed

<div class="Unindented">Tests have been made to determine if a dynamic mutation rate (MR) approach, based on the main population fitness, is better than having a fixed rate that does not vary along the execution generations. The formula that has been used for changing the mutation rate is a simple one, which selects the minimum between 0.90 and a constant divided by the mean fitness. This minimum has been introduced to avoid endless loops in case that the mutation rate goes above 1.0.</div>

<div class="Indented">

<div class="formula"><a class="eqnumber" name="eq-2">(2)</a> _ρ_’ = _min_[0.90,  _α__n_ ⁄ <span class="limits"><sup class="limit">_n_</sup><span class="limit">⎲</span><span class="limit">⎳</span><sub class="limit">_i_ = 0</sub></span>_f_(_x_<sub>_i_</sub>)]</div>

</div>

<div class="Indented">where <span class="formula">_f_(_x_<sub>_i_</sub>)</span> stands for the fitness of the individual i.</div>

<div class="Indented">Different values for <span class="formula">_α_</span> have been tried: 1, 2, 3, 4, 5, 6, 7, 8, 9 and 10\. For the fixed MR, values 0.05, 0.10, 0.15, 0.20, 0.25, 0.30 and 0.40 have been used. However, the performance obtained with 0.40 was so poor that it has been discarded in order to not alter the graphics scale.</div>

<div class="Indented">The obtained results show clearly that the best results are obtained using a fixed MR, as it can be seen in the next analysis. The data were obtained after performing 50 executions for each type of MR, with a 3000 members population, 500 parents through 1000 generations.</div>

### <a class="toc" name="toc-Subsubsection-4.1.1">4.1.1</a> Fitness

<div class="Unindented">In the next figures the obtained fitness is shown, being zero the best value.</div>

### <a class="toc" name="toc-Subsubsection--1"></a>MBF

<div class="Unindented">

<div class="float"><a class="Label" name="Figure-4"></a>

<div class="figure">

<div class="center">![figure MBF.png](img/MBF.png)</div>

<div class="caption">Figure 4 Mean Best Fitness (MBF) results for different mutation rates</div>

</div>

</div>

</div>

<div class="Indented">There are two groups that can be considered as the best. One is the dynamic MR between values 5-8, with 7 as the best, and the second one is located on the right, including fixed MR 0.15-0.30, being the best the last one globally. Nevertheless, the dynamic group can be considered better than the fixed group.</div>

### <a class="toc" name="toc-Subsubsection--2"></a>SR

<div class="Unindented">

<div class="float"><a class="Label" name="Figure-5"></a>

<div class="figure">

<div class="center">![figure SR.png](img/SR.png)</div>

<div class="caption">Figure 5 Success Rate (SR) results for different mutation rates</div>

</div>

</div>

</div>

<div class="Indented">The success rate measure results match the previous ones, having the fixed MR with 0.30 as its best member. Again, the dynamic group has better performance than the fixed group.</div>

### <a class="toc" name="toc-Subsubsection-4.1.2">4.1.2</a> Generations

<div class="Unindented">The next analysis is done based on how much generations have passed before finding the solution only when it is found.</div>

### <a class="toc" name="toc-Subsubsection--3"></a>AES

<div class="Unindented">

<div class="float"><a class="Label" name="Figure-6"></a>

<div class="figure">

<div class="center">![figure AES.png](img/AES.png)</div>

<div class="caption">Figure 6 Average number of Evaluations to a Solution (AES) measured in generations for different MRs</div>

</div>

</div>

</div>

<div class="Indented">The AES shows different results than before. However, it must be noted that this measure is only taken over the successful runs. Therefor, those executions that have less SR can have better AES results if they get the solution quickly in the few times they find it.</div>

### <a class="toc" name="toc-Subsubsection--4"></a>Peak generations

<div class="Unindented">

<div class="float"><a class="Label" name="Figure-7"></a>

<div class="figure">

<div class="center">![figure Peak.png](img/Peak.png)</div>

<div class="caption">Figure 7 Peak generations measure for different MRs</div>

</div>

</div>

</div>

<div class="Indented">Again, the group that showed best behaviour in the fitness analysis is the worst in speed.</div>

<div class="Indented">Based on the obtained results, it can be concluded that the best choice would be to have a fixed mutation rate of 0.30, followed very closely by the dynamic MR with 7 as its <span class="formula">_α_</span> constant value.</div>

## <a class="toc" name="toc-Subsection-4.2">4.2</a> Population

<div class="Unindented">To study how the population count affects to the results, some test have been made with 100, 500, 1000, 2000, 5000, 10000 and 20000 individuals populations. Again, 50 executions through 1000 generations have been done, using the fixed mutation rate of 0.30 obtained in the previous section. This time, the parents count used was 50 for the 1000 individuals population, 250 for 500 and 500 parents above that number. The execution time for each test has increased with the population, as can be seen in the next table:</div>

<div class="Indented">

<div class="float"><a class="Label" name="Table-1"></a>

<div class="table">

<div class="center">

<table>

<tbody>

<tr>

<td align="center" valign="top">Population count</td>

<td align="center" valign="top">Execution time (in min)</td>

</tr>

<tr>

<td align="center" valign="top">100</td>

<td align="center" valign="top">3</td>

</tr>

<tr>

<td align="center" valign="top">500</td>

<td align="center" valign="top">6</td>

</tr>

<tr>

<td align="center" valign="top">1000</td>

<td align="center" valign="top">10</td>

</tr>

<tr>

<td align="center" valign="top">2000</td>

<td align="center" valign="top">15</td>

</tr>

<tr>

<td align="center" valign="top">5000</td>

<td align="center" valign="top">22</td>

</tr>

<tr>

<td align="center" valign="top">10000</td>

<td align="center" valign="top">28</td>

</tr>

<tr>

<td align="center" valign="top">20000</td>

<td align="center" valign="top">39</td>

</tr>

</tbody>

</table>

</div>

<div class="caption">Table 1 Execution times for different population count</div>

</div>

</div>

</div>

<div class="Indented">It can be seen that the needed time increases as the population gets bigger, but there is little difference between the 5000 population and the 10000 one, compared with the 20000 individuals population. This can be explained considering that as the program breaks the search if a solution is found, when the success rate increases, less time is needed for that execution. This, together with the similar SR for the biggest populations (46% for 10000 and 48% for 20000) explain this data.</div>

### <a class="toc" name="toc-Subsubsection-4.2.1">4.2.1</a> Fitness

<div class="Unindented">The next section analyzes how the population count affects to the obtained mean fitness and success rate.</div>

### <a class="toc" name="toc-Subsubsection--5"></a>MBF

<div class="Unindented">

<div class="float"><a class="Label" name="Figure-8"></a>

<div class="figure">

<div class="center">![figure MBF_Population.png](img/MBF_Population.png)</div>

<div class="caption">Figure 8 Mean Best Fitness (MBF) results for different populations</div>

</div>

</div>

</div>

<div class="Indented">The graphic shows the expected behaviour, that is, the MBF is smaller as the population grows. So, the best execution is obtained with a 20000 members population. It is noticeable that the difference between the 500 and 1000 individuals populations is very slight, despite being twice bigger.</div>

### <a class="toc" name="toc-Subsubsection--6"></a>SR

<div class="Unindented">

<div class="float"><a class="Label" name="Figure-9"></a>

<div class="figure">

<div class="center">![figure SR_Population.png](img/SR_Population.png)</div>

<div class="caption">Figure 9 Success Rate (SR) results for different populations</div>

</div>

</div>

</div>

<div class="Indented">The success rate measure results match the previous ones. Both the 10000 and the 20000 are above 90% success rate (92% and 96% respectively).</div>

### <a class="toc" name="toc-Subsubsection-4.2.2">4.2.2</a> Generations

<div class="Unindented">Next the influence of the population count is measured in order to know how it affects the speed of finding a solution.</div>

### <a class="toc" name="toc-Subsubsection--7"></a>AES

<div class="Unindented">

<div class="float"><a class="Label" name="Figure-10"></a>

<div class="figure">

<div class="center">![figure AES_Population.png](img/AES_Population.png)</div>

<div class="caption">Figure 10 Average number of Evaluations to a Solution (AES) measured in generations for different populations</div>

</div>

</div>

</div>

<div class="Indented">This time the best member has also the quickest results, needing in average slightly more than 119 generations to find the solution. This, together with its success rate makes it a quick and reliable configuration.</div>

### <a class="toc" name="toc-Subsubsection--8"></a>Peak generations

<div class="Unindented">

<div class="float"><a class="Label" name="Figure-11"></a>

<div class="figure">

<div class="center">![figure Peak_Population.png](img/Peak_Population.png)</div>

<div class="caption">Figure 11 Peak generations measure for different populations</div>

</div>

</div>

</div>

<div class="Indented">Coherently with the other graphics, the quickest of all executions has been the 20000 members option needing only 27 generations to solve the puzzle.</div>

<div class="Indented">In conclusion the best option, despite its global execution time from 39 minutes, is the biggest population as it has the maximum probability to find the Sudoku puzzle solution through various executions.</div>

# <a class="toc" name="toc-Section-5">5</a> Program Structure

<div class="Unindented">The Sudoku solving application has been developed in Java using the NetBeans IDE<span class="FootOuter"><span class="SupFootMarker"> [A] </span><span class="HoverFoot"><span class="SupFootMarker"> [A] </span>https://netbeans.org/</span></span>. It has a simple interface where the current and previous executions results can be monitored. It also allows to select the parameters configuration file and start the test choosing which Sudoku puzzle must be solved.</div>

<div class="Indented">For output, a name must be given to the current test, and all data will be written in a folder with the same name. This folder contains three files:</div>

*   Data_results__test_name_.txt: this file contains the solution obtained in each execution, and the achieved fitness.
*   Fitness_results__test_name_.txt: here the fitness results for each execution can be found, in CSV format.
*   Generations_results__test_name_.txt: the same as above, but measured in generations needed to find the solution (or maximum allowed generations if none was found).

<div class="Unindented">The program is divided into several packages and classes according to their function as explained next.</div>

## <a class="toc" name="toc-Subsection-5.1">5.1</a> ce_activity6

<div class="Unindented">This package contains those classes used for input/output actions:</div>

### <a class="toc" name="toc-Subsubsection--9"></a>CE_Activity6_Population

<div class="Unindented">This class consists of a simple window where the desired best members data is shown.</div>

### <a class="toc" name="toc-Subsubsection--10"></a>DataWriter

<div class="Unindented">The three results files are created and populated with this class, as its name suggests.</div>

### <a class="toc" name="toc-Subsubsection--11"></a>InformationPanel

<div class="Unindented">An interface class to share information between different classes.</div>

### <a class="toc" name="toc-Subsubsection--12"></a>MainWindow

<div class="Unindented">This class contains the main window of the application.</div>

### <a class="toc" name="toc-Subsubsection--13"></a>SudokuReader

<div class="Unindented">Used to read the Sudoku puzzle from a text file.</div>

## <a class="toc" name="toc-Subsection-5.2">5.2</a> evolution

<div class="Unindented">This is the main package, where the evolution motor resides. It is composed by two classes:</div>

### <a class="toc" name="toc-Subsubsection--14"></a>Evolution

<div class="Unindented">The main evolutionary algorithm is located here, with the usual steps:</div>

*   Initialization
*   Evaluation
*   Parent selection
*   Recombination
*   Mutation
*   Replacement

### <a class="toc" name="toc-Subsubsection--15"></a>Parameters

<div class="Unindented">This class reads the parameters from the configuration file, and stores them to be used by other classes.</div>

## <a class="toc" name="toc-Subsection-5.3">5.3</a> individual

<div class="Unindented">It contains only a class, with the same name.</div>

### <a class="toc" name="toc-Subsubsection--16"></a>Individual

<div class="Unindented">This is the representation of each individual member. The most important variables are a 9x9 array to store the individual’s solution, and an integer to represent its fitness.</div>

## <a class="toc" name="toc-Subsection-5.4">5.4</a> steps

<div class="Unindented">This package extends every step from the evolutionary algorithm described above:</div>

### <a class="toc" name="toc-Subsubsection--17"></a>Initialization

### <a class="toc" name="toc-Subsubsection--18"></a>Evaluation

### <a class="toc" name="toc-Subsubsection--19"></a>ParentSelection

### <a class="toc" name="toc-Subsubsection--20"></a>Recombination

### <a class="toc" name="toc-Subsubsection--21"></a>Mutation

### <a class="toc" name="toc-Subsubsection--22"></a>Replacement

## <a class="toc" name="toc-Subsection-5.5">5.5</a> utils

<div class="Unindented">Here we can find three auxiliary classes that order lists based on their fitness or probability:</div>

### <a class="toc" name="toc-Subsubsection--23"></a>IndSortByFitness

### <a class="toc" name="toc-Subsubsection--24"></a>IndSortByInverseFitness

### <a class="toc" name="toc-Subsubsection--25"></a>IndSortByProbability

# <a class="toc" name="toc-Section-6">6</a> Conclusions

<div class="Unindented">Starting with a CSP, it has been transformed into a COP through constraint handling. After studying different representation, mutation rates and recombination techniques, an efficient approximation has been obtained where a optimum parameters setting allows solving different Sudoku puzzles with an acceptable performance.</div>

<div class="Indented">

# References

<span class="entry">[<a class="biblioentry" name="biblio-1"><span class="bib-index">1</span></a>]</span> <span class="bib-authors">Agoston E. Eiben, J. E. Smith</span>. _<span class="bib-title">Introduction to Evolutionary Computing</span>_. <span class="bib-publisher">SpringerVerlag</span>, <span class="bib-year">2003</span>.

<span class="entry">[<a class="biblioentry" name="biblio-2"><span class="bib-index">2</span></a>]</span> <span class="bib-authors">Edgar Galván-López, Michael O'Neill</span>. <span class="bib-title">On the effects of locality in a permutation problem: the Sudoku puzzle</span>. _<span class="bib-booktitle">Proceedings of the 5th international conference on Computational Intelligence and Games</span>_:<span class="bib-pages">80—87</span>, <span class="bib-year">2009</span>. URL [<span class="bib-url">http://dl.acm.org.ezproxy.uned.es/citation.cfm?id=1719293.1719316</span>](http://dl.acm.org.ezproxy.uned.es/citation.cfm?id=1719293.1719316).

<span class="entry">[<a class="biblioentry" name="biblio-3"><span class="bib-index">3</span></a>]</span> <span class="bib-authors">Y. Sato, H. Inoue</span>. <span class="bib-title">Solving Sudoku with genetic operations that preserve building blocks</span>. _<span class="bib-booktitle">Computational Intelligence and Games (CIG), 2010 IEEE Symposium on</span>_:<span class="bib-pages">23-29</span>, <span class="bib-year">Aug.</span>.

<span class="entry">[<a class="biblioentry" name="biblio-4"><span class="bib-index">4</span></a>]</span> <span class="bib-authors">Yuji Sato, Naohiro Hasegawa, Mikiko Sato</span>. <span class="bib-title">Acceleration of genetic algorithms for sudoku solution on many-core processors</span>. _<span class="bib-booktitle">Proceedings of the 13th annual conference companion on Genetic and evolutionary computation</span>_:<span class="bib-pages">407—414</span>, <span class="bib-year">2011</span>. URL [<span class="bib-url">http://doi.acm.org.ezproxy.uned.es/10.1145/2001858.2002027</span>](http://doi.acm.org.ezproxy.uned.es/10.1145/2001858.2002027).

<span class="entry">[<a class="biblioentry" name="biblio-5"><span class="bib-index">5</span></a>]</span> <span class="bib-authors">Artem Sokolov, Darrell Whitley</span>. <span class="bib-title">Unbiased tournament selection</span>. _<span class="bib-booktitle">Proceedings of the 2005 conference on Genetic and evolutionary computation</span>_:<span class="bib-pages">1131—1138</span>, <span class="bib-year">2005</span>. URL [<span class="bib-url">http://doi.acm.org.ezproxy.uned.es/10.1145/1068009.1068198</span>](http://doi.acm.org.ezproxy.uned.es/10.1145/1068009.1068198).

<span class="entry">[<a class="biblioentry" name="biblio-6"><span class="bib-index">6</span></a>]</span> <span class="bib-authors">Scott M. Thede</span>. <span class="bib-title">An introduction to genetic algorithms</span>. _<span class="bib-journal">J. Comput. Sci. Coll.</span>_, <span class="bib-volume">20</span>(<span class="bib-number">1</span>):<span class="bib-pages">115—123</span>, <span class="bib-year">2004</span>. URL [<span class="bib-url">http://dl.acm.org.ezproxy.uned.es/citation.cfm?id=1040231.1040247</span>](http://dl.acm.org.ezproxy.uned.es/citation.cfm?id=1040231.1040247).

</div>

</div>