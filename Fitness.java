

import java.text.DecimalFormat;
import java.util.ArrayList;


public class Fitness {

	/*宣告變數*/
	public static double CpG_content;        //CpG內容限制比      參數
	public static double CpG_oe;			   //CpG獲得與期望限制比 參數
	private double CpGLength;		   //CpG長度 參數
	
	
	private double[] fitness;		   //適應值函數		
	private int C, G, CpG; 			   //C,G,A,T及CpG個數
	
	
	private double CGpercent;
	private double OE;
	
	private boolean isCpG;
	//private List <Object> CpGi1;
	DecimalFormat df=new DecimalFormat("#.####");
	
	/*內建參數設定建構子*/
	public Fitness(){
		//CpG_content = 0.5;
		//CpG_oe = 0.6;
	}
	
	/*自建參數建構子*/
	public  Fitness(int CpG_minlength, int CpG_maxlength, double CpG_content, double CpG_oe){
		//this.CpG_content = CpG_content;
		//this.CpG_oe = CpG_oe;
	}
	
	/*計算每顆粒子的適應函數值並判斷是否為CpGisland，是則將其儲存*/
	public double[] fitness(int[][] particle, String subsequence, ArrayList <Object> CpGi, ArrayList <Double> CpGi_fit, int min, int range, double w1, double w2, double w3){
		
		fitness = new double[particle.length];
		for(int i=0; i<particle.length; i++){
			/*計算CpG_island適應函數值*/
			
			fitness[i] = calCpGfit(subsequence.substring(particle[i][0], particle[i][0] + particle[i][1]), range, w1, w2, w3);
			/*儲存CpG_island*/
			
			int[] CpG = new int[2];
			CpG[0] = particle[i][0]+min;
			CpG[1] = particle[i][1];
			if (getisCpG() == true){
//				CpGi.add(CpG.clone());
				storeCpG(fitness[i], particle[i].clone(), CpGi, CpGi_fit, min);
			}
				
		}
		return fitness;
	}
	/*計算子字串的適應函數值*/
	public double calCpGfit(String subsquence, int range, double w1, double w2, double w3){
		double fitness;
		C = 0; 
		G = 0;
		CpG = 0;
		
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
		CGpercent = getCG_Content(subsquence);
		OE = getCpG_OE(subsquence);
		CpGLength = getlength(subsquence, range);
		fitness = CGpercent * w1 + OE * w2 + CpGLength * w3;
		
		if(CGpercent >= CpG_content && OE >= CpG_oe && subsquence.length() >= 200){
			isCpG = true;
			//System.out.println(CpG_oe);
		}
		else{
			isCpG = false;
			//System.out.println(CpG_content);
		}
		return fitness;
	 }

	/*取得CG百分比(CG%)分數*/
	public double getCG_Content(String subsquence) {
		if ((C * G) == 0 || subsquence.length() == 0) // C跟G要一起存在
			return 0;
		else
			return ((C + G) / (double) (subsquence.length()));
	}
	
	
	/*取得CpG_OE分數*/
	public double getCpG_OE(String subsquence) {
		if ((C * G) == 0 || subsquence.length() == 0)
			return 0;
		else{
			double ObsCpG = CpG / (double) subsquence.length();
			double ExpCpG = C / (double) subsquence.length() * G / (double) subsquence.length();
			return (double) (ObsCpG / ExpCpG);
		}
	}
	
	public double getlength(String subsquence, int range) {
		if (subsquence.length() >= 200) // C跟G要一起存在
			return (subsquence.length()/(double)range);
		else
			return 0;
	}
	public int getisC(){
		return C;
	}
	public int getisG(){
		return G;
	}
	public int getisCPG(){
		return CpG;
	}
	public boolean getisCpG(){
		return isCpG;
	}
	public double getCG_Content() {
		return CGpercent;
	}
	public double getCpG_OE() {
		return OE;
	}
	public double getlength() {
		return CpGLength;
	}

	/*紀錄CpG_island*/
	public void storeCpG(double fit, int[] particle, ArrayList <Object> CpGi, ArrayList <Double> CpGi_fit, int min){
		int[] CpG;
		double FIT = 0;
		/*調整回真實之index*/
		particle[0] += min;
		int[] temp = new int[2];
		if(CpGi.size() == 0){
			CpGi.add(particle.clone());
			CpGi_fit.add(fit);
		}else{
			boolean newCpGi = true;
			for(int i=0; i<CpGi.size(); i++){
				CpG = (int[]) CpGi.get(i);
				FIT = (double) CpGi_fit.get(i);
				/*判斷是否overlap，有則以較高fitness取代低者*/
				if(CpG[0] <= particle[0] && (CpG[0] + CpG[1]) >= particle[0]){
					if(fit > FIT){
						CpGi.remove(i);
						CpGi_fit.remove(i);
						CpGi.add(particle.clone());
						CpGi_fit.add(fit);
					}
					newCpGi = false;
					break;
				}else if(particle[0] <= CpG[0]&&(particle[0] + particle[1]) >= CpG[0]){
					if(fit > FIT){
						CpGi.remove(i);
						CpGi_fit.remove(i);
						CpGi.add(particle.clone());
						CpGi_fit.add(fit);
					}
					newCpGi = false;
					break;
				}
			}
			/*沒有overlap則儲存*/
			if(newCpGi){
				CpGi.add(particle.clone());
				CpGi_fit.add(fit);
			}
			
		}
	}
	
}
