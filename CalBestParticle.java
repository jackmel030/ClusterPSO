import java.text.DecimalFormat;

public class CalBestParticle {
	   /*參數宣告*/ 
	   private double gBset = -10000000.0;
	   private double[] pBset;
	   private int[] gBestx;
	   private int[][] pBestx;
	   private int[][] particle;
	   DecimalFormat df=new DecimalFormat("#.####");
	   /*建構子*/	
	   public CalBestParticle(){

	   }
	   
	   /*評估GBEST及PBEST*/
	   public void calBest(double[] fitness, int[][] particle, int[][] pbestx, int[] gbestx, double[] pbest, double gbest, int moveTime, int beginIndex, int endIndex){
		    this.particle = particle;
		    pBestx = pbestx;
		    gBestx = gbestx;
		    pBset = pbest;
		    gBset = gbest;
		    
		    if(moveTime==0){
		    	for(int i=0; i<particle.length; i++){
		    		pBset[i] = fitness[i];
					for(int j=0; j<particle[0].length; j++) {
						pBestx[i][j] = particle[i][j];
					}
					if(fitness[i]>gBset){
						gBset = fitness[i];
						for(int j=0; j<particle[0].length; j++) {
							gBestx[j] = particle[i][j];
						}
					}
		    	}
		    }else{
				for(int i=0; i<particle.length; i++) {
					if(fitness[i] > pBset[i]) {
						pBset[i] = fitness[i];
						for(int j=0; j<particle[0].length; j++) {
							pBestx[i][j] = particle[i][j];
						}
					}
					if(fitness[i]> gBset){
						gBset = fitness[i];
						for(int j=0; j<particle[0].length; j++) {
							gBestx[j] = particle[i][j];
						}
					}
				}
		    }
//		    System.out.println("gbest" + " = (" + (gBestx[0]) + ", " + (gBestx[1]) + ");\tgbest_fit = " + df.format(gBset));
//		    for(int i=0; i<particle.length; i++){
//				System.out.println("pbest" + (i+1) + " = (" + pBestx[i][0] + ", " + pBestx[i][1] + ");\tpbest_fit" + (i+1) + " = " + df.format(pBset[i]));
//			}
	   }
	   
	   /*回傳粒子*/
	   public int[][] getParticle(){
		   return particle;
	   }
	   
	   /*回傳PBEST適應函數值*/
	   public double[] getPbest(){
		   return pBset;
	   }
	   
	   /*回傳PBEST粒子*/
	   public int[][] getPbestx(){
		   return pBestx;
	   }
	   
	   /*回傳GBEST適應函數值*/
	   public double getGbest(){
		   return gBset;
	   }
	   
	   /*回傳GBEST粒子*/
	   public int[] getGbestx(){
		   return gBestx;
	   }
}
