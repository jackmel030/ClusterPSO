//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
public class Improve {
//	String dataname = "NT_080256.1";			//原始檔案名稱
//	String CpGfilename = "(Hum)seq_cpg_islands.md";
//	String filepath = "C:\\Users\\user\\Desktop\\程式\\CpGI_cluster-一正\\CpGI\\data\\";
////	int mean=(median+16);	//mean文獻平均長度
//	int mean=90;			//mean文獻平均長度
////	int mean=60;			//mean文獻平均長度
////	private int[][] extension;
//	
//	public static int[][]cpgnumber;
//	/*RLearing建構子*/
//	public void Improve(){
//	
//	}
//	
//	public ArrayList extension(ArrayList inputarray, String sequence) throws IOException{
//		int count = 1;
//		int newLen = 0;
//		int extendLen = 10;
//		Fitness fit = new Fitness(); 
//		
//		String[] lineStr = ((String) inputarray.get(0)).split(",");
//		int[][] extension = new int[inputarray.size()][2];
//		for(int i=0; i<inputarray.size(); i++){
//			lineStr = ((String) inputarray.get(i)).split(",");
//			extension[i][0] = Integer.valueOf(lineStr[0]);
//			extension[i][1] = Integer.valueOf(lineStr[1]);
//		}
//		
//		/*強化開始左延長*/
//		System.out.println("--Left_extension--");	
//		for(int i=0; i<extension.length; i++){
//			fit.calCpGfit(sequence.substring(extension[i][0], extension[i][1])+1);                      //第幾個cpgisland
//			while((fit.getCG_Content(sequence.substring(extension[i][0], extension[i][1]))>=0.5)
//					&&(fit.getCpG_OE(sequence.substring(extension[i][0], extension[i][1]))>=0.6)) //滿足gc 跟 oe 值
//			{   
//				/*暫存*/
//				int tmp_end =  extension[i][1];  //宣告2個新矩陣 先存放值
//				int tmp_start = extension[i][0];  // tmp結束   tmp2開始
//				if((tmp_end-tmp_start)>1500){		//大於1500不要延長
//					break;
//				}
//			
//				/*左延長*/
//				extension[i][0] = extension[i][0]- (newLen+extendLen);
//				if(extension[i][0]<0){
//					extension[i][0]=tmp_start;
//				}
//				if(i!=0){
//					if((extension[i][0]- extension[i-1][1])<0 ){
//						extension[i][0]=(extension[i-1][1]+1);
//						count=0;
//						break;
//					}	
//				}
//				/*測試*/
//				fit.calCpGfit(sequence.substring(extension[i][0], extension[i][1]));
//				if((fit.getCG_Content(sequence.substring(extension[i][0], extension[i][1]))<0.5) || (fit.getCpG_OE(sequence.substring(extension[i][0], extension[i][1]))<0.6)){  //長度 GC O/E 不符合 跳開
//					extension[i][0] = tmp_start;
//					extension[i][1] = tmp_end;
//					count--;
//					break;
//				}
//				count++;
//			}
//			newLen = extendLen*(count-1);
//			if(newLen<0){
//				newLen = 0;
//			}
//			
//			/*存回*/
//			newLen = 0;
//			count=1;
//		}
//		inputarray = new ArrayList();
//		for(int i=0; i<extension.length; i++){
//			inputarray.add(extension[i][0]+","+extension[i][1]);
//		}
//		System.out.println(inputarray);
//		return inputarray;
//		
//		
////		newLen = 0;
////		count=1;	
////		System.out.println("--Right_extension--");
////		/*強化開始右延長**/
////		for(int i=0; i<extension.length; i++){
////			fit.calCpGfit(sequence.substring(extension[i][0], extension[i][1]));                    //第幾個cpgisland
////			if((fit.getCpG_Length()>=mean)){   //是否大於設定長度
////				while((fit.getCG_Content(sequence.substring(extension[i][0], extension[i][1]))>=0.41)
////						&&(fit.getCpG_OE(sequence.substring(extension[i][0], extension[i][1]))>=0.6)) //滿足gc 跟 oe 值
////				{   
////				    int  tmp =  extension[i][1];  //宣告2個新矩陣 先存放值
////					int  tmp2 = extension[i][0];  // tmp結束   tmp2開始
////					if((tmp-tmp2)>1500){					//大於1500不要延長
////						break;
////					}
////					extension[i][1] = extension[i][1]+ (newLen+extendLen);
////					if( i==(extension.length-1)){
////						break;
////					}                    
////	                if(extension[i][1]>=extension[i+1][0]&& i<extension.length){
////	                	extension[i][1]=extension[i][1]-((newLen+extendLen));
////	                	break;
////	                }
////	                if(extension[i][1]>sequence.length()){
////	                	extension[i][1]=tmp;
////	                	break;	
////	                }
////	                /*測試*/
////	                fit.calCpGfit(sequence.substring(extension[i][0], extension[i][1]));
////					if((fit.getCG_Content(sequence.substring(extension[i][0], extension[i][1]))<0.41)		&&(fit.getCpG_OE(sequence.substring(extension[i][0], extension[i][1]))<=0.6)){  //長度 GC O/E 不符合 跳開
////						extension[i][0]=tmp2;
////						extension[i][1]=tmp;
////						count--;
////						break;
////					}
////					count++;
////				}
////				newLen = extendLen*(count-1);
////				if(newLen<0){newLen = 0;}
////			}
////			/*存回*/
////			newLen = 0;
////			count=1;
////		}	
////		EstimateAcc.Estimate(filepath, CpGfilename, dataname, extension,sequence.length(), 10);
//		
//	}
//	
//	
////	public void Terms(int[][] improve_CpGI, String sequence) throws IOException{
////		int hhh=0;
////		for(int i=0; i<improve_CpGI.length; i++){
////			if((improve_CpGI[i][1]-improve_CpGI[i][0])>200){
////				hhh=hhh+1;
////			}
////		}  
////		int[][]pp1 = new int [hhh][2];
////		hhh=0;
////		for(int i=0; i<improve_CpGI.length; i++){
////			if((improve_CpGI[i][1]-improve_CpGI[i][0])>200){
////				pp1[hhh][0]=improve_CpGI[i][0];
////				pp1[hhh][1]=improve_CpGI[i][1];
////				hhh=hhh+1;
////			}
////		}		
////		System.out.println(hhh+"---EnD--");
////		EstimateAcc.Estimate(filepath, CpGfilename, dataname, pp1,sequence.length(), 10);
////		clustvis.showvis(sequence,pp1);
////	}
}		
//
//
