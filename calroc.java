import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class calroc {

	/*Acc建構子*/
	
	public calroc(){//計算roc用的
		
	}

	public static void main(String[] args) throws IOException
	{ 
//	 String dataname = "NT_028395.3";			//原始檔案名稱
//	 String CpGfilename = "(Hum)seq_cpg_islands.md";
	// String CpGfilename = "seq_cpg_islands.md";
//	 String filepath = "D:\\javaword\\test\\data\\";
		
		 int[][] p;
		 p = new int[3][2];//--
		 
		 p	[0]	[0]=	35555	;	p	[0]	[1]=	35777	;
		 p	[1]	[0]=	45915	;	p	[1]	[1]=	46049	;
		 p	[2]	[0]=	46307	;	p	[2]	[1]=	46446	;

		 System.out.println(p.length);

//		Estimate(dataname, p,647850);//---
		
	}
	/*計算預測正確率*/
	public static void Estimate(String subfilename,ArrayList<Object> inputarray, int seqlength) throws IOException{
		int[][] CpGi = new int[inputarray.size()][2];
		for (int i = 0; i < inputarray.size(); i++) {
//			String[] lineStr = ((String) inputarray.get(i)).split(",");
			int[] lineStr = (int[]) inputarray.get(i);
			CpGi[i][0] = lineStr[0];
			CpGi[i][1] = lineStr[1];
		}
		String lineStr;
		
	    final File tm = new File("D:\\程式\\CpGI_cluster-一正\\CpGI\\Result\\true.txt");
	    PrintWriter pw;
	    pw = new PrintWriter(new FileWriter(tm));
	    
//	    final File fm = new File("D:\\程式\\CpGI_cluster-一正\\CpGI\\Result\\prediction.txt");
//	    PrintWriter fw;
//	    fw = new PrintWriter(new FileWriter(fm));
		
		FileReader fr = new FileReader("D:\\程式\\CpGI_cluster-一正\\CpGI\\data\\(Hum)seq_cpg_islands.md");
		BufferedReader br = new BufferedReader(fr);
	int r=0;
	int[][] CpG = new int[CpGi.length][2];
	int[][] CpG_true = new int[500][2];//預估500條內cpg
	CpG = CpGi;
	
		/*抓出正確的CpG ISLAND*/
		//-----------------------------------
		while((lineStr = br.readLine()) != null)                 //cpgdata[6] 指抓到的 CPG END 數\
		{                                                        //cpgdata[5] 指抓到的 CPG START 數
			String[] cpgdata= new String[9];
			cpgdata = lineStr.split("\t");
			if(cpgdata[4].equals(subfilename.substring(0, 11)) && cpgdata[8].equals("relaxed"))
			{
			  CpG_true[r][0]=Integer.parseInt(cpgdata[5]);
			  CpG_true[r][1]=Integer.parseInt(cpgdata[6]);
			  r=r+1;
		
		     }//--迴圈end
		}
	//	 System.out.println("CpGi.length="+CpGi.length); 
		//---印出遇測到的-------------------------------
		for(int i=0; i<CpGi.length; i++)
		{
	//	   System.out.println("CpG_start="+CpG[i][0]); 
	//	   System.out.println("CpG_end="+CpG[i][1]); 				
		}
        //-把預測數值轉為0跟1---------------
		int[] CC_f = new int[seqlength];
		int ccnum=0;
		for(int i=0; i<CpGi.length; i++)
		{

			if(i==0)
			{
	  //      System.out.println("CpG[r][0]-0)"+(CpG[i][0]-0));
	          for(int x=0;x<(CpG[i][0]-0);x++)
	          {
	           CC_f[ccnum]=0;
	           ccnum=ccnum+1;
	           }
	  //      System.out.println("CpG[f]-CpG[r]"+(CpG[i][1]-CpG[i][0])); 
	          for(int x=0;x<(CpG[i][1]-CpG[i][0]);x++)
	          {
	         	CC_f[ccnum]=1;
	        	ccnum=ccnum+1;
	          }					
			}
			else if(i<CpGi.length-1)
			{

		 //    System.out.println("CpG[f]-CpG[r])"+(CpG[i][0]-CpG[i-1][1]));		   
				 for(int x=0;x<(CpG[i][0]-CpG[i-1][1]);x++)
	             {
		         CC_f[ccnum]=0;
		         ccnum=ccnum+1;	
	             }
		 //     System.out.println("CpG[f]-CpG[r])"+(CpG[i][1]-CpG[i][0]));	
	             for(int x=0;x<(CpG[i][1]-CpG[i][0]);x++)
	             {
	             CC_f[ccnum]=1;
	             ccnum=ccnum+1;
	             }  
	        }
			else
			{
		//	System.out.println("CpG[f]-CpG[r])"+(CpG[i][0]-CpG[i-1][1]));	
		    for(int x=0;x<(CpG[i][0]-CpG[i-1][1]);x++)
		    {
	        CC_f[ccnum]=0;
	        ccnum=ccnum+1;
		    }
		  //  System.out.println("CpG[f]-CpG[r])"+(CpG[i][1]-CpG[i][0]));	
		    for(int x=0;x<(CpG[i][1]-CpG[i][0]);x++)
		    {
	    	CC_f[ccnum]=1;
        	ccnum=ccnum+1;
		    }	  
	//		System.out.println("seqlength-CpG[r])"+(seqlength-CpG[i][1]));	
	        for(int x=0;x<(seqlength-CpG[i][1]);x++)
	        {
		      CC_f[ccnum]=0;
		      ccnum=ccnum+1;
	        }
	        }	
		  
			
		}
	
		 System.out.println("ccnum="+ccnum);	
	//	 System.out.println("r="+r);	
		 int tcnum=0;
		 int[] TC_f = new int[seqlength];
	    //--把真實數值轉為0跟1--------------
			for(int i=0; i<r; i++)
			{
				if(i==0)
				{
		        //System.out.println("CpG_true[r][0]-0)"+(CpG_true[i][0]-0));
		        for(int x=0;x<(CpG_true[i][0]-0);x++)
		        {
			        TC_f[tcnum]=0;
			        tcnum=tcnum+1;
		        }
		     //   System.out.println("CpG_true[f]-CpG_true[r]"+(CpG_true[i][1]-CpG_true[i][0])); 
		        for(int x=0;x<(CpG_true[i][1]-CpG_true[i][0]);x++)
		        {
		        	 TC_f[tcnum]=1;
				     tcnum=tcnum+1;
		        }
				}
				else if(i<r-1)
				{
			 //   System.out.println("CpG_true[f]-CpG_true[r])"+(CpG_true[i][0]-CpG_true[i-1][1]));	
		        for(int x=0;x<(CpG_true[i][0]-CpG_true[i-1][1]);x++)
		        {
		        	   TC_f[tcnum]=0;
				       tcnum=tcnum+1;
		        }
			//    System.out.println("CpG_true[f]-CpG_true[r])"+(CpG_true[i][1]-CpG_true[i][0]));	
		        for(int x=0;x<(CpG_true[i][1]-CpG_true[i][0]);x++)
		        {
		        TC_f[tcnum]=1;
		        tcnum=tcnum+1;
		        }}
				else
				{
				//System.out.println("CpG_true[f]-CpG_true[r])"+(CpG_true[i][0]-CpG_true[i-1][1]));	
			    for(int x=0;x<(CpG_true[i][0]-CpG_true[i-1][1]);x++)
			    {
			    	  TC_f[tcnum]=0;
				      tcnum=tcnum+1;
			    }
			   // System.out.println("CpG_true[f]-CpG_true[r])"+(CpG_true[i][1]-CpG_true[i][0]));	
			    for(int x=0;x<(CpG_true[i][1]-CpG_true[i][0]);x++)
			    {
			    TC_f[tcnum]=1;
		        tcnum=tcnum+1;
			    }	  
			//	System.out.println("seqlength-CpG_true["+r+"])"+(seqlength-CpG_true[i][1]));	

				for(int x=0;x<(seqlength-CpG_true[i][1]);x++)
		          {
		           TC_f[tcnum]=0;
			       tcnum=tcnum+1;
		          }
				}			
			}
			 System.out.println("tcnum="+tcnum);
			 
	        //輸出真實		 
			 
		for (int i = 0; i < TC_f.length; i++) {
			if (CC_f[i] == 1) {
				if (Math.random() < 0.05) {
					pw.println(TC_f[i] + " " + 5);
				} else {
					pw.println(TC_f[i] + " " + 6);
				}
			} else {
				pw.println(TC_f[i] + " " + 1);
			}
		}
			 	 pw.close();  
			 
//			//輸出預測
//					for(int i=0; i<CC_f.length; i++)
//					{	  
//						fw.println();
//					}
//				 	 fw.close();   

	}
}

