
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class CpGcluster {

	private int seednum; // 亂數種子78,151
//	private Random rand = new Random();
	public static int percentile_limit ;//參數
	public static double p_value_limit ;//參數
	private ArrayList<Object> CpG_Coordinates = new ArrayList<Object>();
	private ArrayList<Object> clusters_fragment = new ArrayList<Object>();	//CpG起始及終止位置
	private static ArrayList<Object> clusters_result = new ArrayList<Object>();
	private ArrayList<Object> prediction_CGOE = new ArrayList<Object>();
//	static ;
	//uiparameter
	String path="";
	
	@SuppressWarnings("unused")
	private static int location;
	
	static Thread runner = new Thread(){
		public void run(){
			int count = 0;
			while(1==1){
				count++;
				try {
					sleep(1000);
					location = CalculatingPvalues.getlocation();
//					System.out.println("sec "+count+"=\t"+location);	
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};
	
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, InterruptedException {
		
		final File tm = new File("D:\\程式\\CpGI_cluster-一正\\CpGI\\Result\\ClusterPSO-NT_028395.3-時間數據.txt");
	    PrintWriter pw;
	    pw = new PrintWriter(new FileWriter(tm));
		/*時間設定參數*/
		long startTime;			//啟始時間(毫秒)
		long stopTime;			//結束時間(毫秒)
		long sumTime;			//總共花費的時間(秒)	
		long allsec_d;
		long hour;
		long min;
		long sec;
		/* 啟始時間(毫秒) */
		runner.start();
		for (int i = 690; i < 691; i++) {
			startTime = System.currentTimeMillis();
			/* 執行CpGClust */
//			System.out.println("start clusterPSO");
			//new CpGcluster(i);
			/* CpGClust結束時間(毫秒) */
			stopTime = System.currentTimeMillis();
			sumTime = stopTime - startTime;
			allsec_d = sumTime / 1000;
			hour = allsec_d / 3600;
			min = allsec_d / 60;
			if (min >= 60)
				min = min % 60;
			sec = allsec_d % 60;

			double acc = EstimateAcc.getACC();
			double sn = EstimateAcc.getSN();
			double sp = EstimateAcc.getSP();
			double pc = EstimateAcc.getPC();
			double cc = EstimateAcc.getCC();
			int CS = getCS();
			pw.println(acc + "\t" + sn + "\t" + sp + "\t" + pc + "\t" + cc + "\t\t" + allsec_d);
			
//			System.out.println(hour + "時" + min + "分" + sec + "秒");
			System.out.println("end\t" + i + "\n");
		}
		pw.close();
		runner.stop();
		
	}
	public static int getCS(){
		int CS = clusters_result.size();
		return CS;
	}

	@SuppressWarnings("unchecked")
	public CpGcluster(int times,String path) throws IOException, InterruptedException {
		this.path=path;
		double w1 = 0.5;
		double w2 = 0.25;
		double w3 = 0.25;
		int seed[] = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997, 1009, 1013, 1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069, 1087, 1091, 1093, 1097, 1103, 1109, 1117, 1123, 1129, 1151, 1153, 1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223, 1229, 1231, 1237, 1249, 1259, 1277, 1279, 1283, 1289, 1291, 1297, 1301, 1303, 1307, 1319, 1321, 1327, 1361, 1367, 1373, 1381, 1399, 1409, 1423, 1427, 1429, 1433, 1439, 1447, 1451, 1453, 1459, 1471, 1481, 1483, 1487, 1489, 1493, 1499, 1511, 1523, 1531, 1543, 1549, 1553, 1559, 1567, 1571, 1579, 1583, 1597, 1601, 1607, 1609, 1613, 1619, 1621, 1627, 1637, 1657, 1663, 1667, 1669, 1693, 1697, 1699, 1709, 1721, 1723, 1733, 1741, 1747, 1753, 1759, 1777, 1783, 1787, 1789, 1801, 1811, 1823, 1831, 1847, 1861, 1867, 1871, 1873, 1877, 1879, 1889, 1901, 1907, 1913, 1931, 1933, 1949, 1951, 1973, 1979, 1987, 1993, 1997, 1999, 2003, 2011, 2017, 2027, 2029, 2039, 2053, 2063, 2069, 2081, 2083, 2087, 2089, 2099, 2111, 2113, 2129, 2131, 2137, 2141, 2143, 2153, 2161, 2179, 2203, 2207, 2213, 2221, 2237, 2239, 2243, 2251, 2267, 2269, 2273, 2281, 2287, 2293, 2297, 2309, 2311, 2333, 2339, 2341, 2347, 2351, 2357, 2371, 2377, 2381, 2383, 2389, 2393, 2399, 2411, 2417, 2423, 2437, 2441, 2447, 2459, 2467, 2473, 2477, 2503, 2521, 2531, 2539, 2543, 2549, 2551, 2557, 2579, 2591, 2593, 2609, 2617, 2621, 2633, 2647, 2657, 2659, 2663, 2671, 2677, 2683, 2687, 2689, 2693, 2699, 2707, 2711, 2713, 2719, 2729, 2731, 2741, 2749, 2753, 2767, 2777, 2789, 2791, 2797, 2801, 2803, 2819, 2833, 2837, 2843, 2851, 2857, 2861, 2879, 2887, 2897, 2903, 2909, 2917, 2927, 2939, 2953, 2957, 2963, 2969, 2971, 2999, 3001, 3011, 3019, 3023, 3037, 3041, 3049, 3061, 3067, 3079, 3083, 3089, 3109, 3119, 3121, 3137, 3163, 3167, 3169, 3181, 3187, 3191, 3203, 3209, 3217, 3221, 3229, 3251, 3253, 3257, 3259, 3271, 3299, 3301, 3307, 3313, 3319, 3323, 3329, 3331, 3343, 3347, 3359, 3361, 3371, 3373, 3389, 3391, 3407, 3413, 3433, 3449, 3457, 3461, 3463, 3467, 3469, 3491, 3499, 3511, 3517, 3527, 3529, 3533, 3539, 3541, 3547, 3557, 3559, 3571, 3581, 3583, 3593, 3607, 3613, 3617, 3623, 3631, 3637, 3643, 3659, 3671, 3673, 3677, 3691, 3697, 3701, 3709, 3719, 3727, 3733, 3739, 3761, 3767, 3769, 3779, 3793, 3797, 3803, 3821, 3823, 3833, 3847, 3851, 3853, 3863, 3877, 3881, 3889, 3907, 3911, 3917, 3919, 3923, 3929, 3931, 3943, 3947, 3967, 3989, 4001, 4003, 4007, 4013, 4019, 4021, 4027, 4049, 4051, 4057, 4073, 4079, 4091, 4093, 4099, 4111, 4127, 4129, 4133, 4139, 4153, 4157, 4159, 4177, 4201, 4211, 4217, 4219, 4229, 4231, 4241, 4243, 4253, 4259, 4261, 4271, 4273, 4283, 4289, 4297, 4327, 4337, 4339, 4349, 4357, 4363, 4373, 4391, 4397, 4409, 4421, 4423, 4441, 4447, 4451, 4457, 4463, 4481, 4483, 4493, 4507, 4513, 4517, 4519, 4523, 4547, 4549, 4561, 4567, 4583, 4591, 4597, 4603, 4621, 4637, 4639, 4643, 4649, 4651, 4657, 4663, 4673, 4679, 4691, 4703, 4721, 4723, 4729, 4733, 4751, 4759, 4783, 4787, 4789, 4793, 4799, 4801, 4813, 4817, 4831, 4861, 4871, 4877, 4889, 4903, 4909, 4919, 4931, 4933, 4937, 4943, 4951, 4957, 4967, 4969, 4973, 4987, 4993, 4999, 5003, 5009, 5011, 5021, 5023, 5039, 5051, 5059, 5077, 5081, 5087, 5099, 5101, 5107, 5113, 5119, 5147, 5153, 5167, 5171, 5179, 5189, 5197, 5209, 5227, 5231, 5233, 5237, 5261, 5273, 5279, 5281, 5297, 5303, 5309, 5323, 5333, 5347, 5351, 5381, 5387, 5393, 5399, 5407, 5413, 5417, 5419, 5431, 5437, 5441, 5443, 5449, 5471, 5477, 5479, 5483, 5501, 5503, 5507, 5519, 5521, 5527, 5531, 5557, 5563, 5569, 5573, 5581, 5591, 5623, 5639, 5641, 5647, 5651, 5653, 5657, 5659, 5669, 5683, 5689, 5693, 5701, 5711, 5717, 5737, 5741, 5743, 5749, 5779, 5783, 5791, 5801, 5807, 5813, 5821, 5827, 5839, 5843, 5849, 5851, 5857, 5861, 5867, 5869, 5879, 5881, 5897, 5903, 5923, 5927, 5939, 5953, 5981, 5987, 6007, 6011, 6029, 6037, 6043, 6047, 6053, 6067, 6073, 6079, 6089, 6091, 6101, 6113, 6121, 6131, 6133, 6143, 6151, 6163, 6173, 6197, 6199, 6203, 6211, 6217, 6221, 6229, 6247, 6257, 6263, 6269, 6271, 6277, 6287, 6299, 6301, 6311, 6317, 6323, 6329, 6337, 6343, 6353, 6359, 6361, 6367, 6373, 6379, 6389, 6397, 6421, 6427, 6449, 6451, 6469, 6473, 6481, 6491, 6521, 6529, 6547, 6551, 6553, 6563, 6569, 6571, 6577, 6581, 6599, 6607, 6619, 6637, 6653, 6659, 6661, 6673, 6679, 6689, 6691, 6701, 6703, 6709, 6719, 6733, 6737, 6761, 6763, 6779, 6781, 6791, 6793, 6803, 6823, 6827, 6829, 6833, 6841, 6857, 6863, 6869, 6871, 6883, 6899, 6907, 6911, 6917, 6947, 6949, 6959, 6961, 6967, 6971, 6977, 6983, 6991, 6997, 7001, 7013, 7019, 7027, 7039, 7043, 7057, 7069, 7079, 7103, 7109, 7121, 7127, 7129, 7151, 7159, 7177, 7187, 7193, 7207, 7211, 7213, 7219, 7229, 7237, 7243, 7247, 7253, 7283, 7297, 7307, 7309, 7321, 7331, 7333, 7349, 7351, 7369, 7393, 7411, 7417, 7433, 7451, 7457, 7459, 7477, 7481, 7487, 7489, 7499, 7507, 7517, 7523, 7529, 7537, 7541, 7547, 7549, 7559, 7561, 7573, 7577, 7583, 7589, 7591, 7603, 7607, 7621, 7639, 7643, 7649, 7669, 7673, 7681, 7687, 7691, 7699, 7703, 7717, 7723, 7727, 7741, 7753, 7757, 7759, 7789, 7793, 7817, 7823, 7829, 7841, 7853, 7867, 7873, 7877, 7879, 7883, 7901, 7907, 7919};
//		for (int tt=0 ; tt<4; tt++){
//		for (int t=0 ; t<20; t++){
			CpG_Coordinates = new ArrayList<Object>();
			clusters_fragment = new ArrayList<Object>();
			clusters_result = new ArrayList<Object>();
			int sequence_length;
			int CG_num = 0;								//CpG個數
			sequence_length = 0;
			seednum = seed[times];
			
			//################### 讀取檔案 ###############################################
			//ReadData read = new ReadData();				//宣告read
			ReadData read = new ReadData(path);				//自訂
			String subfilename;						//宣告檔案名稱陣列	
			subfilename = read.read_filename();		//取得將要執行的檔案名稱
			
			// ###################	read sequence	######################################
			String sequence = read.readSquence(subfilename); // 讀取序列
			sequence_length = sequence.length(); // 多個檔案使用
			String[] lineStr = sequence.split("CG"); // 計算CG_num
			CG_num = (lineStr.length - 1);

			// ###################	Getting CpG Coordinates	############################
			int[] temp = new int[2];
			CpG_Coordinates = CpGcluster.GetCoords(sequence);
			for(int i=0; i<CpG_Coordinates.size(); i++){
				temp = (int[]) CpG_Coordinates.get(i);
			}

			// ###################	Setting threshold distance to $d	#######################
			setthresholddist getthreshold = new setthresholddist();
			int dist_threshold = getthreshold.get_threshold_dist(CG_num, percentile_limit, (ArrayList) CpG_Coordinates.clone());
//			System.out.println("dist_threshold = " + dist_threshold);

			// ###################	Detecting CpG clusters	################################# //少於dist_threshold都結合 文獻的
			cluster cluster = new cluster(dist_threshold);
			clusters_fragment = cluster.clusterCpG((ArrayList) CpG_Coordinates.clone());
			
			// ###################	Calculating P-values	###################################
			double prob = (CG_num / (double) (sequence_length - (CG_num + 1))); // Probability to find a CpG
//			System.out.println("Probability to find a CpG = " + prob + "\nCG_num = " + CG_num + "\nsequence_length = " + sequence_length);
			CalculatingPvalues P_values = new CalculatingPvalues();
			clusters_result = P_values.CalcPvalNB(clusters_fragment, prob, p_value_limit, sequence, seednum, w1, w2, w3);
			
			sort(clusters_result);
			overlap(clusters_result);
			for(int i=0; i<clusters_result.size(); i++){
				int[] CG = (int[]) clusters_result.get(i);
//				System.out.println(CG[0] + "\t" + CG[1]);
			}

			// ###################	Calculating CG & OE	###################################
			for(int i=0; i<clusters_result.size(); i++){
				int[] CG = (int[]) clusters_result.get(i);
				String subsquence = sequence.substring(CG[0], CG[1]);
				double[] CGOE = CpGcluster.calCGOE(subsquence);
				prediction_CGOE.add(CGOE.clone());
			}
			
			// ###################	Calculating ACC SN SP CC PC	###################################
			System.out.print(w1 + "\t" + w2 +"\t" + w3 + "\t");
			EstimateAcc.Estimate(subfilename, (ArrayList) clusters_result.clone(), (ArrayList) prediction_CGOE.clone(),path);
			// calroc.Estimate(subfilename[i], (ArrayList) clusters_result.clone(), sequence_length);
//			w3 = w3 -0.05;
			
//		}
//		w2 = w2 -0.05;
//		w3 = 1;
//		}
	}
	
	public static double[] calCGOE (String subsquence){
		double[] CGOE = new double[2];
		int C = 0;
		int G = 0;
		int CpG = 0;
		for(int i=0; i<subsquence.length(); i++){
			if (subsquence.charAt(i) == 'c' || subsquence.charAt(i) == 'C') {
				C++;
			} else if (subsquence.charAt(i) == 'g' || subsquence.charAt(i) == 'G') {
				G++;
				if (i != 0) {
					if (subsquence.charAt(i - 1) == 'c' || subsquence.charAt(i - 1) == 'C') {
						CpG++;
					}
				}
			}
		}
		CGOE[0] = (C + G) / (double) (subsquence.length());
		double ObsCpG = CpG / (double) subsquence.length();
		double ExpCpG = C / (double) subsquence.length() * G / (double) subsquence.length();
		CGOE[1] = (double) (ObsCpG / ExpCpG);
		
		return CGOE;
	}
	
	public static ArrayList<Object> GetCoords(String sequence){
		int CG_num = 0;
		int[] temp = new int[2];
		ArrayList<Object> cod = new ArrayList<Object>();
		for (int j = 0; j < sequence.length() - 1; j++) {
			if (sequence.charAt(j) == 'c' || sequence.charAt(j) == 'C') {
				if (sequence.charAt(j + 1) == 'g' || sequence.charAt(j + 1) == 'G') {
					temp[0] = j ;
					temp[1] = j + 1 ;
					cod.add(temp.clone());
					CG_num++;
				}
			}
		}
		return cod;
	}
	public void sort(ArrayList<Object> CpGi) {
		int[][] g = new int[CpGi.size()][2];
		/* 讀取CpGisland的起始點及終止點 */
		for (int i = 0; i < CpGi.size(); i++) {
			int[] f = ((int[]) CpGi.get(i));
			g[i][0] = f[0]; // 起始點
			g[i][1] = f[0] + f[1]; // 終止點
		}

		/* 排序 */
		for (int i = 0; i < CpGi.size() - 1; i++) {
			for (int j = 0; j < CpGi.size() - 1; j++) {
				if (g[j][0] > g[j + 1][0]) {
					int tempFit = g[j][0];
					g[j][0] = g[j + 1][0];
					g[j + 1][0] = tempFit;
					int tempLen = g[j][1];
					g[j][1] = g[j + 1][1];
					g[j + 1][1] = tempLen;
				}
			}
		}

		/* 將陣列取代回List */
		for (int i = 0; i < g.length; i++) {
			CpGi.set(i, g[i]);
		}

		/* 結合距離100以內之CpGisland */
		int[] getCG_A = null, getCG_B;
		for (int i = 0; i < (CpGi.size() - 1); i++) {
			getCG_A = (int[]) CpGi.get(i);
			getCG_B = (int[]) CpGi.get(i + 1);
			if (getCG_B[0] - getCG_A[1] < 100) {
				getCG_A[1] = getCG_B[1];
				CpGi.set(i, getCG_A);
				CpGi.remove(i + 1);
			}
		}
	}
	
	public void overlap(ArrayList<Object> CpGi) {
		int[][] q = new int[clusters_result.size()][2];
		for (int j = 0; j < clusters_result.size(); j++) {
			int[] CG = (int[]) clusters_result.get(j);
			q[j][0] = CG[0];
			q[j][1] = CG[1];
			int ss = 0;
			if (j != 0) {
				if ((q[j][0] - q[j - 1][1]) < 0) {
					q[j - 1][0] = q[j - 1][0];
					q[j - 1][1] = q[j][1];
					ss = j;
					clusters_result.remove(j);
					j = (ss - 1);
				}
			}
		}
	}
	


}
