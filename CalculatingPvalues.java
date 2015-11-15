import java.io.IOException;
import java.util.ArrayList;

public class CalculatingPvalues {
	private ArrayList<Object> predict_result;
	private ArrayList<Double> CpGi_fit; // CpG適應函數
	
	private static int location;
	PSO pso = new PSO();
	
	public ArrayList<Object> CalcPvalNB(ArrayList<Object> inputarray, double prob, double p_value_limit, String sequence, int seednum, double w1, double w2, double w3) throws IOException {
		predict_result = new ArrayList<Object>();
		CpGi_fit = new ArrayList<Double>();
		
		int cluster_location[][];
		ArrayList<Object> coincidence = new ArrayList<Object>();
		int[] lineStr = (int[]) inputarray.get(0);
		
		cluster_location = new int[inputarray.size()][lineStr.length];
		for (int i = 0; i < cluster_location.length; i++) {
			lineStr = (int[]) inputarray.get(i);
			for (int j = 0; j < cluster_location[i].length; j++) {
				cluster_location[i][j] = lineStr[j];
			}
		}
		for (int i = 0; i < cluster_location.length; i++) {
			location = cluster_location[i][1];
			int CpGI_length = cluster_location[i][1] - cluster_location[i][0] + 1;
			String subsequence = sequence.substring(cluster_location[i][0], cluster_location[i][1] + 1);
			int CG_num = 0;
			int C = 0;
			int G = 0;
			for (int j = 0; j < subsequence.length(); j++) {
				if(subsequence.charAt(j) == 'c' || subsequence.charAt(j) == 'C'){
					C++;
				}else if(subsequence.charAt(j) == 'g' || subsequence.charAt(j) == 'G'){
					G++;
					if(j != 0){
						if(subsequence.charAt(j-1) == 'c' || subsequence.charAt(j-1) == 'C'){
							CG_num++;
						}
					}
				}
			}
			int variable1 = CpGI_length - (2 * CG_num);
			int variable2 = CG_num - 1;

			double p_value = CalculatingPvalues.GetNB(variable1, variable2, prob);
			double CG = (C + G) / (double) (subsequence.length());
			double OE = CG_num / ((C * G) / (double) (subsequence.length()));
			
			if (p_value <= p_value_limit && cluster_location[i][1] - cluster_location[i][0] + 1 > 50) {
				pso.runPSO(sequence, cluster_location[i][0], cluster_location[i][1],  predict_result, CpGi_fit, seednum, w1, w2, w3);
				coincidence.add(cluster_location[i].clone());
			}
		}
		return predict_result;
	}

	public static double GetNB(int variable1, int variable2, double prob) {
		double pval = 0;

		for (int i = 0; i < variable1; i++) {
			double ptemp = CalculatingPvalues.FactorialNB(i, variable2) + variable2 * Math.log(prob) + i * Math.log(1.0 - prob);
			ptemp = Math.exp(ptemp);
			pval += ptemp;
		}
		return pval;
	}

	public static double FactorialNB(int variable1, double variable2) {
		double stop = (double) variable1 + variable2 - 1;
		double temp1 = 0;
		double temp2 = 0;
		for (int i = variable1 + 1; i <= stop; i++) {
			temp1 += Math.log(i);
		}
		for (int i = 1; i <= variable2 - 1; i++) {
			temp2 += Math.log(i);
		}
		double temp = temp1 - temp2;
		return temp;
	}
	public static int getlocation(){
		return location;
	}

}
