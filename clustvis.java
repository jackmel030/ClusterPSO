import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class clustvis {

	public void windowActivated(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowOpened(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowCloseing(WindowEvent e) {
		System.exit(0);
	}

	public void windowClosing(WindowEvent e) {
	}
	static JPanel Pane;
	// --------------------------------------------------------------------
	public static JTextArea TextArea1 = new JTextArea(22, 58);
	public static JTextArea TextArea2 = new JTextArea(7, 55);
	public static JTextField TextField1 = new JTextField();
	public static JTextField TextField2 = new JTextField();
	public static int grapicsy = 0;
	public static int Xgrend = 0;

	public static final JLabel Label6 = new JLabel(" ");// GC
	public static final JLabel Label7 = new JLabel(" ");
	public static final JLabel Label8 = new JLabel(" ");
	public static final JLabel Label9 = new JLabel(" ");
	public static final JLabel Label10 = new JLabel(" ");
	public static final JLabel Label11 = new JLabel(" ");
	public static final JLabel Label16 = new JLabel(" ");// OE
	public static final JLabel Label17 = new JLabel(" ");
	public static final JLabel Label18 = new JLabel(" ");
	public static final JLabel Label19 = new JLabel(" ");
	public static final JLabel Label20 = new JLabel(" ");
	public static final JLabel Label21 = new JLabel(" ");
	// --------------------GC OE 變數用---------------------------------------
	protected static double[] OE_sum; // 算出OE
	protected static int[] GC_sum; // 算出GC
	protected static int[] arr_GC_sum; // 算出GC平均
	protected static double[] arr_OE_sum; // 算出OE平均
	public static int gcnum = 0, oenum = 0;
	public static double GC, OE_ratio, CpG_observed, CpG_expected;
	public static double GC_number = 0;
	public static double C_SUM = 0, G_SUM = 0;
	public static String[] primegc;
	public static String[] primeoe;
	public static char C = 'C';
	static char G = 'G';
	public static int[][] cpgnumber = new int[4][3];
	public static int[][] CpGIlocation = new int[4][3];
	public static int CpGInum = 4;
	public static int po1 = 0, po2 = 0, po3 = 0, po4 = 0, po5 = 0;
	// public static int po6=0;
	public static int yyy = 0;
	public static int po1p = 0, out = 0;
	//存圖
	static JTextField jt1 = new JTextField("Default");
	static JLabel jl1 = new JLabel("picture name"); 
	public static String path="";
	// ---------------------------------------------------------------------
	public static void main(String[] args) {
		// Scanner a = new Scanner(System.in);
		// String s = a.nextLine();

		// -------假設islang區域---------------------------------------------------
		cpgnumber[0][0] = 100;
		cpgnumber[0][1] = 800;
		cpgnumber[1][0] = 2100;
		cpgnumber[1][1] = 3900;
		cpgnumber[2][0] = 3950;
		cpgnumber[2][1] = 5900;
		cpgnumber[3][0] = 7250;
		cpgnumber[3][1] = 7500;
		// cpgnumber[1][0]=5010; cpgnumber[1][1]=6000;
		// -----------------------------------------------------------------------
		CpGIlocation[0][0] = 100;
		CpGIlocation[0][1] = 800;
		CpGIlocation[1][0] = 2100;
		CpGIlocation[1][1] = 3900;
		CpGIlocation[2][0] = 3950;
		CpGIlocation[2][1] = 5900;
		CpGIlocation[3][0] = 7250;
		CpGIlocation[3][1] = 7500;

//		showvis(s, cpgnumber, CpGIlocation, CpGInum);

	}

	// -------總GC 含量-------------------------------------------------
	public static void findtheGC(String DNAsequence) {
		double sum = 0;
		for (int i = 0; i < DNAsequence.length(); i++) {
			if (DNAsequence.charAt(i) == C | DNAsequence.charAt(i) == G) {
				sum += 1;
			}
		}
		GC = sum / DNAsequence.length() * 100;
//		System.out.println("GC含量百分比=" + (int) GC);
		GC_sum[gcnum] = (int) GC;
		gcnum++;
	}

	public static double fiGC(String DNAsequence) {
		double sum = 0;
		for (int i = 0; i < DNAsequence.length(); i++) {
			if (DNAsequence.charAt(i) == C | DNAsequence.charAt(i) == G) {
				sum += 1;
			}
		}
		return GC = sum / DNAsequence.length() * 100;

	}

	// -------------------------------------------------------------
	public static void findtheOE(String seq) {
		GC_number = 0;
		C_SUM = 0;
		G_SUM = 0;
		for (int i = 0; i < seq.length(); i++){ // 算C各體
			if (seq.charAt(i) == C) {
				C_SUM += 1;
			}
		}
		for (int i = 0; i < seq.length(); i++){ // 算G各體
			if (seq.charAt(i) == G) {
				G_SUM += 1;
			}
		}
		StringBuffer supers1 = new StringBuffer(seq);
		int i = 0, j = 1;
		do {
			j = i + 1;
			String a = new String();
			if (j < supers1.length()) {
				a = a + supers1.charAt(i) + supers1.charAt(j);
			} else {
				a = a + supers1.charAt(i);
			}
			i++;
			if (a.equals("CG")) {
				GC_number = GC_number + 1;
			}
		} while (i < seq.length());

		CpG_observed = GC_number / seq.length(); // 關察值
		CpG_expected = (C_SUM / seq.length()) * (G_SUM / seq.length()); // 期望值
		OE_ratio = CpG_observed / CpG_expected; // oe值
		// System.out.println("OE_ratio含量="+OE_ratio);
		OE_sum[oenum] = OE_ratio;
		oenum++;

	}

	public static double fiOE(String seq) {
		GC_number = 0;
		C_SUM = 0;
		G_SUM = 0;
		for (int i = 0; i < seq.length(); i++){ // 算C各體
			if (seq.charAt(i) == C) {
				C_SUM += 1;
			}
		}
		for (int i = 0; i < seq.length(); i++){ // 算G各體
			if (seq.charAt(i) == G) {
				G_SUM += 1;
			}
		}
		StringBuffer supers1 = new StringBuffer(seq);
		int i = 0, j = 1;
		do {
			j = i + 1;
			String a = new String();
			if (j < supers1.length()) {
				a = a + supers1.charAt(i) + supers1.charAt(j);
			} else {
				a = a + supers1.charAt(i);
			}
			i++;
			if (a.equals("CG")) {
				GC_number = GC_number + 1;
			}
		} while (i < seq.length());

		CpG_observed = GC_number / seq.length(); // 關察值
		CpG_expected = (C_SUM / seq.length()) * (G_SUM / seq.length()); // 期望值
		OE_ratio = CpG_observed / CpG_expected; // oe值
		// System.out.println("OE_ratio含量="+OE_ratio);
		return (OE_ratio = CpG_observed / CpG_expected);

	}

	// ---------------------------------------------------------------
	@SuppressWarnings("deprecation")
	// 配合 f.show();
	public static void showvis(final String sequence, final int[][] cpgnumber, final double[][] cpgnumber_CGOE, final int[][] trueCpGIlocation, final int trueCpGInum) {
		
		int width = 1020;// 調大小 長
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
		
		// -----------------------------------------------------
		JPanel Panel = new JPanel();
		Panel.setLayout(null);
		Panel.setBorder(BorderFactory.createTitledBorder("Sequence"));
		Panel.setBounds(40, 80, 958, 600);
		f.add(Panel);

		JPanel Panel3 = new JPanel();
		Panel3.setBounds(330, 700, 650, 180);
		f.add(Panel3);
		JScrollPane bb = new JScrollPane(TextArea2);
		Panel3.add(bb);

		Panel.setOpaque(false);// 透明化
		Panel.setBorder(BorderFactory.createTitledBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED), "Sequence", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Courier New", Font.BOLD, 15), Color.blue));
		Panel3.setOpaque(false);// 透明化
		Panel3.setBorder(BorderFactory.createTitledBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED), "CpG island", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Courier New", Font.BOLD, 15), Color.blue));

		// ---------------------------------------

		TextField1.setBounds(50, 815, 55, 30);
		f.add(TextField1);
		TextField1.setText(String.valueOf(cpgnumber.length));

		TextField2.setBounds(50, 765, 55, 30);
		f.add(TextField2);
		TextField2.setText(String.valueOf(sequence.length()));

		JLabel Label4 = new JLabel("CpG island number");
		Label4.setBounds(50, 800, 150, 10);
		Label4.setForeground(Color.BLUE);
		f.add(Label4);

		JLabel Label5 = new JLabel("Sequence Length");
		Label5.setBounds(50, 750, 150, 10);
		Label5.setForeground(Color.BLUE);
		f.add(Label5);

		// -----在TextArea2印出CpGI位置--------------------------------------------
		for (int i = 0; i < cpgnumber.length; i++) {
			TextArea2.append((i + 1) + "\t" + cpgnumber[i][0] + "\t" + cpgnumber[i][1] + "\t" + (cpgnumber[i][1] - cpgnumber[i][0]) + "\t" + cpgnumber_CGOE[i][0] + "\t" + cpgnumber_CGOE[i][1]);
			TextArea2.append("\n");
		}

		// -----------------------畫圖----------------------------------------------
		//JPanel Pane = new JPanel() {
		Pane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				setBackground(Color.WHITE);
				super.paintComponent(g);
			}
			public void paint(Graphics g) { // 畫線
				int i = 0;
				int linestart_X = 55;
				int linestart_Y = 25;
				int PreCpGIstart_Y = linestart_Y + 2;
				int TrueCpGIstart_Y = linestart_Y - 2;
				
				grapicsy = 0; // Y軸往下
				while (i < (sequence.length() / 800) + 1) {
					
					g.setColor(Color.BLACK); // -----------畫橫--------
					if (sequence.length() < 800) {
						g.drawString("" + i, 10, linestart_Y + 5);
						g.drawLine(linestart_X, linestart_Y, linestart_X + (sequence.length() % 800), linestart_Y);
					} else if (sequence.length() > 801 && i < (sequence.length() / 800)) {
						g.drawString("" + ((i * 800) + 1), 10, linestart_Y + 5 + grapicsy);
						g.drawLine(linestart_X, linestart_Y + grapicsy, linestart_X + 800, linestart_Y + grapicsy);
					}
					if (i == (sequence.length() / 800) && sequence.length() > 801) {// 畫幾條
						g.drawString("" + ((i * 800) + 1), 10, linestart_Y + 5 + grapicsy);
						g.drawLine(linestart_X, linestart_Y + grapicsy, linestart_X + (sequence.length() % 800), linestart_Y + grapicsy);
						break;
					}
					grapicsy = grapicsy + 50; // -----------換行--------
					i++;
				}
				// ---------畫GC位置------ //50當第一個原點 850 當底 每條切800個點
				String a = new String();
				String b = new String();
				a = "";
				b = "";
				grapicsy = 0;
				int y = 0;
				int yr = 0;

				for (int x = 0; x < sequence.length() - 1; x++) {
					a = a + sequence.charAt(x);
					b = a + sequence.charAt(x + 1);
					if (b.equals("CG")) {
						g.drawLine(((y + linestart_X)), linestart_Y - 8 + yr, ((y + linestart_X)), linestart_Y + 7 + yr);
					}
					y = y + 1;
					if (x % 800 == 0 && x != 0) {
						yr = yr + 50;
						y = 0;
					}
					a = "";
					b = "";
				}
				// --------畫出cpgusland位置-----------------------------------
				for (int j = 0; j < cpgnumber.length; j++) {
					for (int n = (cpgnumber[j][0] / 800); n <= ((cpgnumber[j][0] + (cpgnumber[j][1] - cpgnumber[j][0])) / 800); n++) {
						g.setColor(Color.red);
						if (n == 0) {
							if (cpgnumber[j][1] >= 800 && cpgnumber[j][0] == 0) {
								g.drawLine(linestart_X + n, PreCpGIstart_Y, linestart_X +800, PreCpGIstart_Y);
							} else if (cpgnumber[j][1] >= 800) {
								g.setColor(Color.red);
								g.drawLine((linestart_X + cpgnumber[j][0]), PreCpGIstart_Y, linestart_X + 800, PreCpGIstart_Y);
							} else {
								g.drawLine((50 + cpgnumber[j][0]), PreCpGIstart_Y, 50 + (cpgnumber[j][1] % 800), PreCpGIstart_Y);
							}
							g.drawString("CpG island" + (j + 1), 50 + (cpgnumber[j][0] - 800 * n), (PreCpGIstart_Y + 20 + (50 * n)));
						} else if (n == (((cpgnumber[j][0] + (cpgnumber[j][1] - cpgnumber[j][0])) / 800))) {
							if (cpgnumber[j][0] / 800 == n && n == (cpgnumber[j][0] + (cpgnumber[j][1] - cpgnumber[j][0])) / 800) {
								g.drawLine(linestart_X + (cpgnumber[j][0] % 800), (PreCpGIstart_Y + (50 * (n))), linestart_X + (cpgnumber[j][1] % 800), (PreCpGIstart_Y + (50 * (n))));
							} else {
								g.drawLine(linestart_X, (PreCpGIstart_Y + (50 * (n))), linestart_X + (cpgnumber[j][1] % 800), (PreCpGIstart_Y + (50 * (n))));
							}
							g.drawString("CpG island" + (j + 1), 50 + (cpgnumber[j][0] - 800 * n), (PreCpGIstart_Y + 20 + (50 * n)));
						} else {
							if (n == (cpgnumber[j][0] / 800)) {
								g.drawLine((linestart_X + (cpgnumber[j][0] % 800)), (PreCpGIstart_Y + (50 * (n))), linestart_X + 800, (PreCpGIstart_Y + (50 * (n))));
								g.drawString("CpG island" + (j + 1), 50 + (cpgnumber[j][0] - 800 * n), (PreCpGIstart_Y + 20 + (50 * n)));
							} else {
								g.drawLine(linestart_X, (PreCpGIstart_Y + (50 * (n))), linestart_X + 800, (PreCpGIstart_Y + (50 * (n))));
							}
						}
					}
				}
				
				// --------畫出真實cpgusland位置-----------------------------------
//				for (int j = 0; j < trueCpGInum; j++) {
//					for (int n = (trueCpGIlocation[j][0] / 800); n <= ((trueCpGIlocation[j][0] + (trueCpGIlocation[j][1] - trueCpGIlocation[j][0])) / 800); n++) {
//						g.setColor(Color.BLUE);
//						if (n == 0) {
//							if (trueCpGIlocation[j][1] >= 800 && trueCpGIlocation[j][0] == 0) {
//								g.drawLine(linestart_X + n, TrueCpGIstart_Y, linestart_X + 800, TrueCpGIstart_Y);
//							} else if (trueCpGIlocation[j][1] >= 800) {
//								// g.setColor(Color.BLUE);
//								g.drawLine((linestart_X + trueCpGIlocation[j][0]), TrueCpGIstart_Y, linestart_X + 800, TrueCpGIstart_Y);
//							} else {
//								g.drawLine((50 + trueCpGIlocation[j][0]), TrueCpGIstart_Y, 50 + (trueCpGIlocation[j][1] % 800), TrueCpGIstart_Y);
//							}
//							g.drawString("CpG island" + (j + 1), 50 + (trueCpGIlocation[j][0] - 800 * n), (TrueCpGIstart_Y - 10 + (50 * n)));
//						} else if (n == (((trueCpGIlocation[j][0] + (trueCpGIlocation[j][1] - trueCpGIlocation[j][0])) / 800))) {
//							if (trueCpGIlocation[j][0] / 800 == n && n == (trueCpGIlocation[j][0] + (trueCpGIlocation[j][1] - trueCpGIlocation[j][0])) / 800) {
//								g.drawLine(linestart_X + (trueCpGIlocation[j][0] % 800), (TrueCpGIstart_Y + (50 * (n))), linestart_X + (trueCpGIlocation[j][1] % 800), (TrueCpGIstart_Y + (50 * (n))));
//							} else {
//								g.drawLine(linestart_X, (TrueCpGIstart_Y + (50 * (n))), linestart_X + (trueCpGIlocation[j][1] % 800), (TrueCpGIstart_Y + (50 * (n))));
//							}
//							g.drawString("CpG island" + (j + 1), 50 + (trueCpGIlocation[j][0] - 800 * n), (TrueCpGIstart_Y - 10 + (50 * n)));
//						} else {
//							if (n == (trueCpGIlocation[j][0] / 800)) {
//								g.drawLine((linestart_X + (trueCpGIlocation[j][0] % 800)), (TrueCpGIstart_Y + (50 * (n))), linestart_X + 800, (TrueCpGIstart_Y + (50 * (n))));
//								g.drawString("CpG island" + (j + 1), 50 + (trueCpGIlocation[j][0] - 800 * n), (TrueCpGIstart_Y - 10 + (50 * n)));
//							} else {
//								g.drawLine(linestart_X, (TrueCpGIstart_Y + (50 * (n))), linestart_X + 800, (TrueCpGIstart_Y + (50 * (n))));
//							}
//						}
//					}
//				}
	
			}
		};
		
		Pane.setBackground(Color.red);
		Pane.setPreferredSize(new Dimension(940, (50 * sequence.length() / 800) + 50)); // 設大小
		
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
		//存圖檔
		JButton ttt= new JButton("save");
		ttt.setBounds(200, 800, 100, 40);
	
		jt1.setBounds(200, 770, 100, 25);
		jl1.setBounds(200, 745, 100, 25);
		jl1.setForeground(Color.BLUE);
		f.add(jl1);
		f.add(jt1);
		ttt.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				BufferedImage image = new BufferedImage(Pane.getWidth(), Pane.getHeight(), BufferedImage.TYPE_INT_RGB);
				 Graphics g=image.getGraphics();
				   g.setColor(Color.WHITE);
				   g.fillRect(0, 0, Pane.getWidth(), Pane.getHeight());
				Pane.paintAll(image.createGraphics());
				
					try {
						ImageIO.write(image, "jpg", new File(path+jt1.getText()+".jpg"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				
			}
			
		});
		
		f.add(ttt);
		
	}

}
