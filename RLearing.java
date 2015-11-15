//import java.util.ArrayList;
//import java.util.List;
//
public class RLearing {
//	
//	private int count = 1;
//	private int newLen = 0;
//	private int extendLen = 10;
//	private double CpG_content = 0.5;
//	private double CpG_oe = 0.6;
//	
//	/*RLearing建構子*/
//	public void Rlearing(){
//		
	}
//	
//	/*強化學習*/
//	public void rlearing(int CpGi_th, ArrayList<Object> CpGi, String subsequence, int range){
//		Fitness fit= new Fitness();
//		/*讀取*/
//		int[][]p = new int [CpGi.size()][2];
//		int[] improve = new int[2];
//		int[] g = (int[]) CpGi.get(CpGi_th);
//		improve[0] = g[0];
//		improve[1] = g[1];
////		for(int i=0; i<CpGi.size(); i++){
////			int[] g = (int[]) CpGi.get(i);
////			p[i][0] = g[0];
////			p[i][1] = g[1];
////		}
//
////		System.out.println("CpGsize"+"\t"+CpGi.size());
//		/*強化開始*/
//		for (int i = 0; i < CpGi.size(); i++) {
//			fit.calCpGfit(sequence.substring(p[i][0], p[i][1]), range);
//			if ((fit.getCpG_Length() > minlength) && (fit.getCpG_Length() < maxlength)) {
//				while ((fit.getCG_Content(sequence.substring(p[i][0], p[i][1])) >= CpG_content) && (fit.getCpG_OE(sequence.substring(p[i][0], p[i][1])) >= CpG_oe)) {
//					System.out.println(i+"\t"+count);
////					System.out.println(i+"\t"+count+"\t"+(fit.getCG_Content(sequence.substring(p[i][0], p[i][1]))+"\t"+fit.getCpG_OE(sequence.substring(p[i][0], p[i][1])))+"\t"+sequence.substring(p[i][0], p[i][1]).length());
//					/*暫存*/
//					int tmp = p[i][1];
//					int tmp2 = p[i][0];
//					/* 延長 */
//					p[i][0] = p[i][0] - (newLen + extendLen);
//					if (p[i][0] < 0) {
//						p[i][0] = tmp2;
//					}
//					p[i][1] = p[i][1] + (newLen + extendLen);
//					if (p[i][1] > sequence.length()) {
//						p[i][1] = tmp;
//					}
//					/* 測試 */
//					fit.calCpGfit(sequence.substring(p[i][0], p[i][1]), range);
//					if ((fit.getCpG_Length() > maxlength)
//							|| (fit.getCG_Content(sequence.substring(p[i][0],
//									p[i][1])) < CpG_content)
//							|| (fit.getCpG_OE(sequence.substring(p[i][0],
//									p[i][1])) < CpG_oe)) {
//						p[i][0] = tmp2;
//						p[i][1] = tmp;
//						count--;
//						break;
//					}
//					count++;
//				}
//				newLen = extendLen*(count-1);
//				if(newLen<0){
//					newLen = 0;
//				}
//			}
//			/*存回*/
//			CpGi.set(i, p[i]);
//		}
//	}
//	
//}
