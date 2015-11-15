

import java.util.ArrayList;

public class CpGIdentify{
	/*CpG island definition*/
	private int LENGTH;
	private double GC_CONTENT;
	private double OE_RATIO;
	
	
	/*Constructor*/
	public CpGIdentify(){
		LENGTH = 10;
		GC_CONTENT = 0.5;
		OE_RATIO = 0.6;
	}
	public CpGIdentify(int LENGTH, double GC_CONTENT, double OE_RATIO){
		this.LENGTH = LENGTH;
		this.GC_CONTENT = GC_CONTENT;
		this.OE_RATIO = OE_RATIO;
	}
	
	
	/*CpG island identification*/
	public void identifyCpGI(ArrayList<Object> array, String Sequence){
//		array.add("47226,48632");
//		System.out.println(Sequence.substring(48218, 48633));
		for(int i=0; i<array.size(); i++){
			int A= 0, T = 0, C = 0, G = 0, CpG = 0;
			double OE_ratio = 0;
			double GC_content = 0;
			int[] lineStr = (int[]) array.get(i);
//			String[] lineStr = ((String) array.get(i)).split(",");
			String subsquence = Sequence.substring(lineStr[0], lineStr[1]+1);
			int length = subsquence.length();
			for(int j=0; j<subsquence.length(); j++){
				if(subsquence.charAt(j) == 'a' || subsquence.charAt(j) == 'A'){
				    A++;
				}else if(subsquence.charAt(j) == 't' || subsquence.charAt(j) == 'T'){
					T++;
				}else if(subsquence.charAt(j) == 'c' || subsquence.charAt(j) == 'C'){
					C++;
				}else if(subsquence.charAt(j) == 'g' || subsquence.charAt(j) == 'G'){
					G++;
					if(j != 0){
						if(subsquence.charAt(j-1) == 'c' || subsquence.charAt(j-1) == 'C'){
							CpG++;
						}
					}
				}
			}
			GC_content = (double)(C + G) / (double)length;
			if(C * G == 0){
				OE_ratio = 0;
			}else{
				OE_ratio = (double)(CpG * length) / (double)(C * G);
			}
//			if(i == array.size()-1){	
//				System.out.println(GC_content+"   .................     "+OE_ratio);
//			}
			
			if(GC_content < GC_CONTENT || OE_ratio < OE_RATIO || subsquence.length() < LENGTH){
				array.remove(i);
				i--;
			}
			
		}
	}
}
