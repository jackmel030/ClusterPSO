import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

public class EstimateAcc {
	public static String CpGfilename = "(Hum)seq_cpg_islands.md";
	public static String filepath = "";
	private static double ACC;
	private static double SN;
	private static double SP;
	private static double CC;
	private static double PC;
	private static int TP;
	private static int TN;
	private static int FP;
	private static int FN;	
	public static JTextArea TextArea1 = new JTextArea(22, 58);
	public static JTextArea TextArea2 = new JTextArea(7, 55);
	public static JTextField TextField1 = new JTextField();
	public static JTextField TextField2 = new JTextField();
	public static int grapicsy = 0;
	public static int Xgrend = 0;

	/* Acc建構子 */
	public EstimateAcc() {
	
	}
//	10282	10539


	public static void main(String[] args) throws IOException {
		int[] t = new int[]{10098, 10209, 10543, 10598};
		int[] p = new int[]{10282, 10539};
		int[] tt = new int[4];
		int[] pp = new int[2];
		for(int i=0; i<t.length; i++){
			tt[i] = t[i] - t[0];
			System.out.println(tt[i]);
		}
		for(int i=0; i<p.length; i++){
			pp[i] = p[i] - t[0];
			System.out.println(pp[i]);
		}
		int sequencelen = t[3] - t[0] + 1;
		
		int[] CC_f = new int[sequencelen];
		int[] TC_f = new int[sequencelen];

		for (int j = 0; j < sequencelen; j++) {
			CC_f[j] = 0;
			TC_f[j] = 0;
		}
		// --把真實數值轉為0跟1--------------
		for (int k = tt[1]; k < tt[2]+1; k++) {
			TC_f[k] = 1;
		}
		
		// --把預測數值轉為0跟1--------------
		for (int k = pp[0]; k < pp[1]+1; k++) {
			CC_f[k] = 1;
		}
		TP = 0;
		TN = 0;
		FP = 0;
		FN = 0;

		for (int j = 0; j < sequencelen; j++) {
			if (TC_f[j] == 0 && CC_f[j] == 0) {
				TN = TN + 1;
			} else if (TC_f[j] == 0 && CC_f[j] == 1) {
				FP = FP + 1;
			} else if (TC_f[j] == 1 && CC_f[j] == 0) {
				FN = FN + 1;
			} else if (TC_f[j] == 1 && CC_f[j] == 1) {
				TP = TP + 1;
			}
		}
		System.out.println("TP: " + TP + "\tTN: " + TN + "\tFP: " + FP + "\tFN: " + FN);
		
		int width = 1900;// 調大小 長
		int height = 940;// 寬
		int x = 0;
		int y = 0;
		// ------------視窗定義-------
		JFrame f = new JFrame("CpG Island Prediction"); // 主視窗定義
		f.setBounds(x, y, width, height); // 顯示視窗大小
		f.setLayout(null); // <---父視窗不使用版面管理員
		f.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});

		// -------------改成WINDOWS 的變大縮小(右上的小圖)
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowssLookAndFeel");
			SwingUtilities.updateComponentTreeUI(f);
		} catch (Exception e) {

		}
		JPanel Panel = new JPanel();
		Panel.setLayout(null);
		Panel.setBorder(BorderFactory.createTitledBorder("Sequence"));
		Panel.setBounds(40, 80, 1800, 600);
		f.add(Panel);

		
		JPanel Pane = new JPanel() {
			public void paint(Graphics g) {
				String[] name = new String[]{"cluster", "PSO", "PSORL", "CPSO", "CPSORL", "clusterPSO", "true", "sequence"};
				int[][] aaa = new int[][]{
						{10256,	10525},
						{10223,	10500},
						{10223,	10500},
						{10278,	10540},
						{10282,	10539},
						{10248,	10558},
						{10209,	10543},
						{10098,	10698},

						};

				int i = 0;
				int linestart_X = 55;
				int linestart_Y = 25;
				int PreCpGIstart_Y = linestart_Y + 2;
				int TrueCpGIstart_Y = linestart_Y - 2;
				g.setColor(Color.BLACK);
				
//				
				for(int j=0; j<aaa.length; j++){
					g.drawString(name[j], 10, linestart_Y + 5 + grapicsy);
					g.drawLine(aaa[j][0] - aaa[7][0] + 100, linestart_Y + grapicsy, aaa[j][1] - aaa[7][0] + 100, linestart_Y + grapicsy);
					grapicsy = grapicsy + 25;
				}
				
			}
		};
//		Pane.setBackground(Color.red);
//		Pane.setPreferredSize(new Dimension(940, (50 * 8000000 / 800) + 50)); // 設大小
		JScrollPane scr = new JScrollPane(Pane); // 加入捲軸
		scr.getVerticalScrollBar().setUnitIncrement(50); // 直
		scr.getHorizontalScrollBar().setUnitIncrement(50); // 橫
		Pane.setOpaque(false);// 透明化
		scr.setBounds(Panel.getBounds());
		scr.setLocation(0, 0);
		Panel.add(scr);

		JLabel LabeGC = new JLabel(new ImageIcon("CPGpre.gif"));
		LabeGC.setBounds(270, 20, 450, 50);
		f.add(LabeGC);

		JLabel Label = new JLabel(new ImageIcon("2.png")); // 連接背景圖檔
		Label.setBounds(0, 0, 1024, 950);
		f.add(Label);
		// -------視窗顯示---------------------------------------------
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.show();
	}

	/* 計算預測正確率 */
	public static void Estimate(String subfilename, ArrayList<Object> clusters_result, ArrayList<Object> prediction_CGOE,String path)throws IOException {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(4);
		int[][] prediction_result = new int[clusters_result.size()][4];
		double[][] prediction_CGOE_result = new double[prediction_CGOE.size()][2];
		for (int i = 0; i < clusters_result.size(); i++) {
			int[] lineStr = (int[]) clusters_result.get(i);
			double[] CGOE = (double[]) prediction_CGOE.get(i);
			prediction_result[i][0] = lineStr[0];
			prediction_result[i][1] = lineStr[1];
			prediction_CGOE_result[i][0] = CGOE[0];
			prediction_CGOE_result[i][1] = CGOE[1];
//			System.out.println("predict island " + (i + 1) + "\t" + prediction_result[i][0] + "\t" + prediction_result[i][1] + "\t" + nf.format(prediction_CGOE_result[i][0]) + "\t" + nf.format(prediction_CGOE_result[i][1]));
		}
		
//		System.out.println(prediction_result.length);

		ReadData read = new ReadData(path);
		FileReader fr = new FileReader(filepath + CpGfilename);
		BufferedReader br = new BufferedReader(fr);
		int trueCpGInum = 0;
		int[][] trueCpGIlocation = new int[3000][2];// 預估500條內cpg
		String lineStr;
		
		/* 抓出正確的CpG ISLAND */
		String sequence = read.readSquence(subfilename);
		while ((lineStr = br.readLine()) != null) { // cpgdata[6] 指抓到的 CPG
			String[] true_CpG_data = new String[9];
			true_CpG_data = lineStr.split("\t");
			if (true_CpG_data[4].equals(subfilename.substring(0, 11)) && true_CpG_data[8].equals("relaxed")) {
				trueCpGIlocation[trueCpGInum][0] = Integer.parseInt(true_CpG_data[5]);
				trueCpGIlocation[trueCpGInum][1] = Integer.parseInt(true_CpG_data[6]);
//				System.out.println("true island " + (trueCpGInum + 1) + "\t" + trueCpGIlocation[trueCpGInum][0] + "\t" + trueCpGIlocation[trueCpGInum][1]);
				trueCpGInum = trueCpGInum + 1;
			}
		}
//		System.out.println();
			
			// -把預測數值轉為0跟1---------------
			int[] CC_f = new int[sequence.length()];
			int[] TC_f = new int[sequence.length() + 1000];

			for (int j = 0; j < sequence.length(); j++) {
				CC_f[j] = 0;
				TC_f[j] = 0;
			}
			// --把預測數值轉為0跟1--------------
			for (int j = 0; j < prediction_result.length; j++) {
				for (int k = prediction_result[j][0]; k < prediction_result[j][1] + 1; k++) {
					CC_f[k] = 1;
				}
			}
			// --把真實數值轉為0跟1--------------
			for (int j = 0; j < trueCpGInum; j++) {
				for (int k = trueCpGIlocation[j][0]; k < trueCpGIlocation[j][1] + 1; k++) {
					TC_f[k] = 1;
				}
			}

			/* 計算敏感度,特異度及預測正確率 */
			TP = 0;
			TN = 0;
			FP = 0;
			FN = 0;

			for (int j = 0; j < sequence.length(); j++) {
				if (TC_f[j] == 0 && CC_f[j] == 0) {
					TN = TN + 1;
				} else if (TC_f[j] == 0 && CC_f[j] == 1) {
					FP = FP + 1;
				} else if (TC_f[j] == 1 && CC_f[j] == 0) {
					FN = FN + 1;
				} else if (TC_f[j] == 1 && CC_f[j] == 1) {
					TP = TP + 1;
				}
			}
//			System.out.println("TP: " + TP + "\tTN: " + TN + "\tFP: " + FP + "\tFN: " + FN);
			// ------------------------------------

			SN = (double) TP / (TP + FN);
			SP = (double) TN / (TN + FP);
			ACC = (double) (TP + TN) / (TP + FP + TN + FN);
			PC = (double) TP / (TP + FN + FP);
			CC = (double) ((double) TP * TN-(double) FP * FN)/ (Math.sqrt((double)(TP + FN) * (TP + FP) * (TN + FN) * (TN + FP)));
			System.out.println("SN:\t" + SN + "\tSP:\t" + SP + "\tACC:\t" + ACC + "\tPC:\t" + PC + "\tCC:\t" + CC);
			clustvis.showvis(sequence, prediction_result, prediction_CGOE_result, trueCpGIlocation, trueCpGInum);
		}
	

	public static double getACC(){
		return ACC;
	}
	public static double getSN(){
		return SN;
	}
	public static double getSP(){
		return SP;
	}
	public static double getPC(){
		return PC;
	}
	public static double getCC(){
		return CC;
	}
	public int getTP(){
		return TP;
	}
	public int getTN(){
		return TN;
	}
	public int getFP(){
		return FP;
	}
	public int getFN(){
		return FN;
	}
	
}
