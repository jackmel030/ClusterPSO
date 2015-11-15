
import java.util.Random;

//初始化
public class Initial {
	//宣告參數
	private Random rand = new Random();
	int X1_MAX;
	int X1_MIN;
	int X2_MAX;
	int X2_MIN;
	
	//內建建構子
	public Initial(){
		rand.setSeed(13);
	}
	
	//自建建構子
	public Initial(int seed){
		rand.setSeed(seed);
	}


	
	public int[][] initialParticle(int[][] particle, double[][] velocity,int beginIndex, int endIndex, String subseq){
		X1_MAX = endIndex - beginIndex - 200;
		X1_MIN = beginIndex - beginIndex;
		X2_MAX = endIndex - beginIndex;
		X2_MIN = 200;
		for (int i = 0; i < particle.length; i++) {
			particle[i][0] = (int) (rand.nextDouble() * (X1_MAX - X1_MIN));
			velocity[i][0] = rand.nextDouble() * 8 - 4;
			particle[i][1] = (int) (rand.nextDouble() * (X2_MAX - particle[i][0]));
			velocity[i][1] = rand.nextDouble() * 8 - 4;
			if (particle[i][1] < X2_MIN)
				particle[i][1] = particle[i][1] + X2_MIN;

			if ((particle[i][0] + particle[i][1]) >= subseq.length())
				particle[i][1] = subseq.length() - particle[i][0];
		}
		return particle;
	}

}
