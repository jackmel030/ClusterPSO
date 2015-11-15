import java.util.ArrayList;

public class setthresholddist {

	public int get_threshold_dist(int CG_num, int percentile_limit, ArrayList<Object> inputarray) {

		int dist_n[];
		dist_n = new int[CG_num];
		for (int i = 0; i < dist_n.length; i++) {
			int[] CG = (int[]) inputarray.get(i);
//			String[] lineStr = ((String) inputarray.get(i)).split(",");
			if (i == 0) {
//				dist_n[i] = Integer.valueOf(lineStr[0]);
				dist_n[i] = CG[0];
			} else {
//				String[] lineStr1 = ((String) inputarray.get(i - 1)).split(",");
				int[] CG1 = (int[]) inputarray.get(i-1);
//				dist_n[i] = Integer.valueOf(lineStr[0]) - Integer.valueOf(lineStr1[1]);
				dist_n[i] = CG[0] - CG1[1];
			}
		}
		
		// sort
		dist_n = setthresholddist.sort(dist_n);
		
		for (int j = 0; j < dist_n.length; j++) {
			double Percentile = 100 * j / dist_n.length;
			if (Percentile >= percentile_limit) {
				return dist_n[j];
			}
		}
		return dist_n[dist_n.length - 1];
	}

	public static int[] sort(int[] input) {
		for (int j = 0; j < input.length; j++) {
			for (int k = j + 1; k < input.length; k++) {
				if (input[k] < input[j]) {
					int temp = input[j];
					input[j] = input[k];
					input[k] = temp;
				}
			}
		}
		return input;
	}
}
