
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class PSO {
	
	// ---------------------------
	public static int CpG_minlength = 200; // CpG最低長度限制
	public static int CpG_maxlength = 2000; // CpG最高長度限制
	public static double CpG_content; // CpG percent 55/100
	public static double CpG_oe; // CpG的OE值
	public static int CpG_merge = 100; // CpG合併
	public static int CpG_popsize;

	// ------------------------------
	/* 參數宣告 */
	private int moveTime; // 起始迭代
	public static int STOP_MOVE = 100; // 迭代次數
	public static int parNum = 300; // 粒子數
	private int parDim = 2; // CpG個數
	private double chaos; // chaos
	private String subsequence; // 儲存DNA子字串

	/* 宣告PSO參數 */
	private int[][] particle;
	private double[][] velocity;
	private double[] fitness;
	private int[][] pbestx;
	private int[] gbestx;
	private double[] pbest;
	private double gbest;
	DecimalFormat df=new DecimalFormat("#.##");
	DecimalFormat dff=new DecimalFormat("#.####");

	public void runPSO(String sequence, int start, int end, ArrayList<Object> predict_result, ArrayList<Double> CpGi_fit, int seednum, double w1, double w2, double w3) {
		/* CpG 相關類別初始 */
		Initial ini = new Initial(seednum);
		Fitness fit = new Fitness();
		CalBestParticle cal = new CalBestParticle();
//		chaos = rand.nextDouble();

		/* 列印相關設定 */
//		System.out.println("執行程式:PSO_CpG island");
//		System.out.println("迭代次數:" + STOP_MOVE);
//		System.out.println("parNum數:" + parNum);

		int beginIndex;
		int endIndex;
		

		/* 設定PSO參數 */
		particle = new int[parNum][parDim * 2];
		velocity = new double[parNum][parDim * 2];
		fitness = new double[parNum];
		pbestx = new int[parNum][parDim * 2];
		gbestx = new int[parDim * 2];
		pbest = new double[parNum];
		gbest = 0;

		/* Setting particle search range-start */
		if (start - 200 < 0)
			beginIndex = 0;
		else
			beginIndex = start - 200;

		/* Setting particle search range-end */
		if (end + 200 > sequence.length())
			endIndex = sequence.length();
		else
			endIndex = end + 200;

		int range = endIndex - beginIndex;
		subsequence = sequence.substring(beginIndex, endIndex);

		moveTime = 0;
//		System.out.println("beginIndex\t" + beginIndex + "\tendIndex\t" + endIndex);
		/* initial */
		particle = ini.initialParticle(particle, velocity, beginIndex, endIndex, subsequence);
		/* Calculate fitness */
		
		fitness = fit.fitness(particle.clone(), subsequence, predict_result, CpGi_fit, beginIndex, range, w1, w2, w3);
		int[][] g = new int[predict_result.size()][2];
		/* 讀取CpGisland的起始點及終止點 */
		for (int i = 0; i < predict_result.size(); i++) {
			int[] f = ((int[]) predict_result.get(i));
			double x = CpGi_fit.get(i);
		}
		/* Update pbest & gbest */
		cal.calBest(fitness, particle, pbestx, gbestx, pbest, gbest, moveTime, beginIndex, endIndex);

		while (moveTime < STOP_MOVE) {
			update(cal.getPbestx(), cal.getGbestx(), cal.getPbest(), cal.getGbest(), particle, velocity, subsequence, moveTime, STOP_MOVE, beginIndex, endIndex, seednum);
			particle = getNewpar(); // 取得粒子
			velocity = getVel(); // 取得速度
			fitness = fit.fitness(particle, subsequence, predict_result, CpGi_fit, beginIndex, range, w1, w2, w3); // 計算適應函數值
			for (int i = 0; i < predict_result.size(); i++) {
				int[] f = ((int[]) predict_result.get(i));
				double x = CpGi_fit.get(i);
			}

			cal.calBest(fitness, particle, pbestx, gbestx, pbest, gbest, moveTime, beginIndex, endIndex); // 計算個體最佳值(pBest)和群體最佳值(gBest)
			moveTime++;
		}


		
//		for (int i = 0; i < predict_result.size(); i++) {
//			int[] CG = (int[]) predict_result.get(i);
//			System.out.println(CG[0] + "\t" + CG[1]);
//		}
	}

	private double[][] getVel() {
		return velocity;
	}

	private int[][] getNewpar() {
		return particle;
	}

	public void update(int[][] pbestx, int[] gbestx, double[] pbset, double gbest, int[][] particle, double[][] velocity,
			String sequence, int moveTime, int STOP_MOVE, int beginIndex, int endIndex, int seednum) {
		Random rand = new Random();
		rand.setSeed(seednum);
		// 宣告參數
		this.particle = particle;
		this.velocity = velocity;

		// double W = 0.9-((0.9-0.4)/STOP_MOVE*moveTime); //大黑
		double W = (0.9 - 0.4) * (STOP_MOVE - moveTime) / STOP_MOVE + 0.4;

//		double C1 = 2.5 - moveTime * 0.02;
//		double C2 = 0.5 + moveTime * 0.02;
		double C1 = 2;
		double C2 = 2;

		int V_MAX = 4;
		int V_MIN = -4;

		int X1_MAX = endIndex - beginIndex - 200;
		int X1_MIN = beginIndex - beginIndex;
		int X2_MAX = endIndex - beginIndex;
		int X2_MIN = 200;

		// 更新粒子及速度
		for (int i = 0; i < particle.length; i++) {
			for (int j = 0; j < particle[0].length; j++) {
				
				double rand1 = rand.nextDouble();
				double rand2 = rand.nextDouble();
				double velocity_old = velocity[i][j];
//				chaos = 4 * chaos * (1 - chaos);
				velocity[i][j] = W * velocity[i][j] + rand1 * C1 * (pbestx[i][j] - particle[i][j]) + rand2 * C2 * (gbestx[j] - particle[i][j]);
//				if(j==0)
//					System.out.println(df.format(velocity[i][j]) + " = " + W + " * " + df.format(velocity_old) + " + " + df.format(rand1) + " * 2 * (" + pbestx[i][j] + " - " + particle[i][j] + ") + " + df.format(rand2) + " * 2 * (" + (gbestx[j]) + " - " + (particle[i][j]) + ")");
//				else
//					System.out.println(df.format(velocity[i][j]) + " = " + W + " * " + df.format(velocity_old) + " + " + df.format(rand1) + " * 2 * (" + pbestx[i][1] + " - " + particle[i][j] + ") + " + df.format(rand2) + " * 2 * (" + gbestx[1] + " - " + particle[i][j] + ")");

				// 速度限制
				
			}
			
		}
		for (int i = 0; i < particle.length; i++) {
			for (int j = 0; j < particle[0].length; j++) {
				if (velocity[i][j] >= V_MAX) {
					velocity[i][j] = V_MAX;
				} else if (velocity[i][j] <= V_MIN) {
					velocity[i][j] = V_MIN;
				}
				particle[i][j] = particle[i][j] + (int) Math.round(velocity[i][j]);
			}
		}

		for (int i = 0; i < particle.length; i++) {
			// 限制起始位置
			if (particle[i][0] <= X1_MIN)
				particle[i][0] = X1_MIN;
			else if (particle[i][0] >= X1_MAX)
				particle[i][0] = X1_MAX;
			// 限制CpG長度
			if (particle[i][1] >= X2_MAX - particle[i][0])
				particle[i][1] = X2_MAX - particle[i][0];
			else if (particle[i][1] < X2_MIN){
				particle[i][1] = X2_MIN;
			}
			// 限制起始點
			if ((particle[i][0] + particle[i][1]) >= sequence.length())
				particle[i][1] = sequence.length() - particle[i][0];
		}
	}
}
// -----------------------------------------------

