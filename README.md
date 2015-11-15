# ClusterPSO

Software instruction
ClusterPSO version 1 was presented in March 15th, 2014, this program is a free software.

Please report the bugs or suggest for any improvements of current software to the authors. (Author: Yi-Cheng Chiang, E-mail: a09210917@yahoo.com.tw)

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

Input parameters
To run the program, input the command "java -jar CpGI_fat.jar contig_sequence data_path threshold_dt p-value population generation" 

1.	contig_sequence is the contig sequence which is defined as 0 or 1. If user want to predict CpG islands from contig sequence that user will set 0, otherwise set 1.
2.	data_path is the data set file pathway and name (only accept .txt extension, and the input does not have the file extension).	For the supplementary test data set, use "D:\CpG_jar\NT_113952.1.fasta".
3.	threshold_dt is the distance threshold between adjacent CpG dinucleotides within array.
4.	p-value is the significance level (e.g. 0.01) for the cluster, which indicates a potential for the presence of island within the detected cluster.
5.	population is the population size of particle swarm optimization.
6.	generation is the generation number of particle swarm optimization.

Example for ClusterPSO v1 implementation:
java -jar CpGI_fat.jar 0 D:\CpG_jar\NT_113952.1.fasta 65 0.01 300 100
The example shows the implementation of the ClusterPSO on contig sequence of 0 using supplementary test data set placed in "D:\CpG_jar\NT_113952.1.fasta", with the distance threshold set as 65%, significance level at 0.01, total test by 300 population and 100 generation in particle swarm optimization. 

