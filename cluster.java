
import java.util.ArrayList;

public class cluster {
	private int Dt;

	public cluster() {
		Dt = 44;
	}

	public cluster(int Dt) {
		this.Dt = Dt;
	}

	public ArrayList<Object> clusterCpG(ArrayList<Object> inputarray) {
		int[] lineStr1;
		int[] lineStr2;
		ArrayList<Object> cluster = new ArrayList<Object>();
		int distance;
		int[] temp = new int[2];
		boolean result = false;
		for (int i = 0; i < inputarray.size() - 1; i++) {
			lineStr1 = (int[]) inputarray.get(i+1);
			lineStr2 = (int[]) inputarray.get(i);
			distance = lineStr1[0] - lineStr2[1];
			if (distance <= Dt) {
				if (result == false) {
					temp[0] = lineStr2[0];
				}
				temp[1] = lineStr1[1];
				result = true;
			} else if (distance > Dt && result == true) {
				result = false;
				cluster.add(temp.clone());
			}
		}
		if (result == true) {
			cluster.add(temp.clone());
		}
//		temp[0] = 63309;
//		temp[1] = 63839;
//		cluster.add(temp.clone());
		return cluster;
	}
	
	public int[][] Getrange(ArrayList<Object> clusters_result, ArrayList<Object> CpG_Coordinates){
		int[][] particle_range = new int[clusters_result.size()][4];
		for(int i=0; i<clusters_result.size(); i++){
			int[] lineStr = (int[]) clusters_result.get(i);
			for(int j=1; j<CpG_Coordinates.size()-1; j++){
				int[] lineStr1 = (int[]) CpG_Coordinates.get(j-1);
				int[] lineStr2 = (int[]) CpG_Coordinates.get(j);
				int[] lineStr3 = (int[]) CpG_Coordinates.get(j+1);
				
				if(lineStr2[0] == (lineStr[0])){
					particle_range[i][0] = lineStr1[1] + (lineStr2[0] - lineStr1[1]) / 2;
					particle_range[i][1] = lineStr2[0];
				}
				if(lineStr2[1] == (lineStr[1])){
					particle_range[i][2] = lineStr2[1];
					particle_range[i][3] = lineStr2[1] + (lineStr3[0] - lineStr2[1])/2;
					break;
				}
			}
		}
		return particle_range;
	}

}
