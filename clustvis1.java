import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;



public class clustvis1 {

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

	// --------------------------------------------------------------------
	public static JTextArea TextArea1 = new JTextArea(22, 58);
	public static JTextArea TextArea2 = new JTextArea(7, 55);
	
	public static JTextField TextField1 = new JTextField();
	public static JTextField TextField2 = new JTextField();
	public static int grapicsy = 0;
	public static int Xgrend = 0;
	
	private static JTable table;
	private static DefaultTableModel tableModel;
	
	
	
	public static String[] columnNames = {"Number", "Start", "End", "Length", "CG", "OE", "see sequence"};
	
	public static Object[][] data = {
		{"1", new Integer(10256), new Integer(10538), new Integer(283), new Double(0.5674), new Double(0.6267), new String("see sequence")},
		{"2", new Integer(25890), new Integer(28591), new Integer(2702), new Double(0.642), new Double(1.1644), new String("see sequence")},
		{"3", new Integer(36767), new Integer(37028), new Integer(262), new Double(0.5402), new Double(0.6356), new String("see sequence")},
		{"4", new Integer(37541), new Integer(37864), new Integer(324), new Double(0.548), new Double(1.0013), new String("see sequence")},
		{"5", new Integer(65488), new Integer(65749), new Integer(262), new Double(0.5019), new Double(0.816), new String("see sequence")},
		{"6", new Integer(72168), new Integer(73712), new Integer(1545), new Double(0.6218), new Double(1.2267), new String("see sequence")},
		{"7", new Integer(128811), new Integer(129586), new Integer(776), new Double(0.6929), new Double(0.7488), new String("see sequence")},
		{"8", new Integer(129921), new Integer(130152), new Integer(232), new Double(0.645), new Double(0.6), new String("see sequence")},
		{"9", new Integer(131785), new Integer(132003), new Integer(219), new Double(0.633), new Double(0.6117), new String("see sequence")},
		{"10", new Integer(163393), new Integer(163619), new Integer(227), new Double(0.5752), new Double(0.7604), new String("see sequence")},
		{"11", new Integer(164088), new Integer(164472), new Integer(385), new Double(0.5), new Double(0.6667), new String("see sequence")},
		{"12", new Integer(164835), new Integer(165068), new Integer(234), new Double(0.5665), new Double(0.6052), new String("see sequence")},
		{"13", new Integer(167551), new Integer(167834), new Integer(284), new Double(0.5866), new Double(0.6993), new String("see sequence")},
		{"14", new Integer(168204), new Integer(168427), new Integer(224), new Double(0.5022), new Double(0.7913), new String("see sequence")},
		{"15", new Integer(182184), new Integer(182448), new Integer(265), new Double(0.5), new Double(0.6073), new String("see sequence")},
	};
	
	
	public static int[][] preCpGI = new int[][]{
		{10256, 10538}, {25890, 28591}, {36767, 37028}, {37541, 37864}, {65488, 65749}, {72168, 73712}, {128811, 129586}, {129921, 130152}, 
		{131785, 132003}, {163393, 163619}, {164088, 164472}, {164835, 165068}, {167551, 167834}, {168204, 168427}, {182184, 182448},
	};

	public static double[][] preCGOE = new double[][]{
		{0.5674, 0.6267}, {0.642, 1.1644}, {0.5402, 0.6356}, {0.548, 1.0013}, {0.5019, 0.816}, {0.6218, 1.2267}, {0.6929, 0.7488}, {0.645, 0.6}, 
		{0.633, 0.6117}, {0.5752, 0.7604}, {0.5, 0.6667}, {0.5665, 0.6052}, {0.5866, 0.6993}, {0.5022, 0.7913}, {0.5, 0.6073},
	};

	public static int[][] trueCpGI = new int[][]{
		{10209, 10543}, {25982, 28449}, {36763, 37028}, {37489, 37886}, {65499, 65772}, {72129, 73615}, {128756, 128991}, {129215, 129824}, {129949, 130173},
		{131785, 132000}, {163354, 163701}, {164042, 164390}, {167474, 167870}, {168172, 168416}, {182169, 182405},
	};
	
	// ---------------------------------------------------------------------
//	public static void main(String[] args) {
//		ReadData read = new ReadData();
//		String sequence = read.readSquence("NT_113952.1.fasta");
//		showvis(sequence, preCpGI, preCGOE, trueCpGI, trueCpGI.length);
//		System.out.println("end");
//	}
	
	// ---------------------------------------------------------------
	@SuppressWarnings({ "deprecation", "serial" })
	// 配合 f.show();
	public static void showvis(final String sequence, final int[][] cpgnumber, final double[][] cpgnumber_CGOE, final int[][] trueCpGIlocation, final int trueCpGInum) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(4);
		int width = 1020;// 調大小 長
		int height = 940;// 寬
		int x = 0;
		int y = 0;
		// ------------視窗定義-------
		final JFrame frame = new JFrame("CpG Island Prediction"); // 主視窗定義
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(x, y, width, height); // 顯示視窗大小
		frame.setLayout(null); // <---父視窗不使用版面管理員
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});

		// -------------改成WINDOWS 的變大縮小(右上的小圖)
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowssLookAndFeel");
			SwingUtilities.updateComponentTreeUI(frame);
		} catch (Exception e) {

		}
		
		// -----------------------------------------------------
		JPanel Panel = new JPanel();
		Panel.setLayout(null);
		Panel.setBounds(40, 80, 958, 600);
		Panel.setOpaque(false);// 透明化
		Panel.setBorder(BorderFactory.createTitledBorder(
				new SoftBevelBorder(SoftBevelBorder.LOWERED, Color.GRAY, Color.GRAY), 
				"Sequence", 
				TitledBorder.DEFAULT_JUSTIFICATION, 
				TitledBorder.DEFAULT_POSITION, 
				new Font(Font.SERIF, Font.ITALIC, 20), Color.orange));
		frame.add(Panel);

		JPanel Panel3 = new JPanel();
		Panel3.setBounds(330, 700, 650, 180);
		Panel3.setLayout(null);
		Panel3.setOpaque(false);// 透明化
		Panel3.setBorder(BorderFactory.createTitledBorder(
				new SoftBevelBorder(SoftBevelBorder.LOWERED, Color.GRAY, Color.GRAY), 
				"CpGI information", 
				TitledBorder.DEFAULT_JUSTIFICATION, 
				TitledBorder.DEFAULT_POSITION, 
				new Font(Font.SERIF, Font.ITALIC, 20), new Color(255, 150, 180)));
		frame.add(Panel3);
		
		/*方法一 資料輸入 *///--------------------------------------------
		table = new JTable(data,columnNames);//參數:前是是資料,後面是標題
//		tableModel.setColumnIdentifiers(columnNames);
//		table = new JTable(tableModel);
		table.setFillsViewportHeight(false);//是否要填滿整個panel,只對方法二有效
		table.setRowHeight(20);
		table.getTableHeader().setReorderingAllowed(false);
		for(int i=0; i<data.length; i++){
			
		}
//		table.getColumn("see sequence").setCellRenderer(new ButtonRenderer());
//		table.getColumn("see sequence").setCellEditor(new ButtonEditor(new JCheckBox()));
//		
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
////		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //若設置它，列寬永遠固定
//		table.getColumnModel().getColumn(0).setPreferredWidth(30);
//		table.getColumnModel().getColumn(1).setPreferredWidth(100);
//		table.getColumnModel().getColumn(2).setPreferredWidth(40);
//		table.getColumnModel().getColumn(3).setPreferredWidth(40);
//		table.getColumnModel().getColumn(4).setPreferredWidth(100);
//		table.getColumnModel().getColumn(5).setPreferredWidth(55);
//		table.getColumnModel().getColumn(6).setPreferredWidth(70);
		/*
		TableColumnModel cModel = table.getColumnModel();
		TableColumn columnName = cModel.getColumn(0);
		columnName.setPreferredWidth(20);
		*/
//		table.setPreferredSize(new Dimension(770, data.length*20)); // 設大小
		JScrollPane tscroll = new JScrollPane(table);//加捲軸
		tscroll.getVerticalScrollBar().setUnitIncrement(50); // 直
		tscroll.getHorizontalScrollBar().setUnitIncrement(10); // 橫
		tscroll.setBounds(10, 25, 630, 150);
		tscroll.setViewportView(table);
		Panel3.add(tscroll);
		
		/*方法二 資料輸入 *///--------------------------------------------
//		JTable table = new JTable();
//		DefaultTableModel model = null;
//		
//		table.setRowHeight(20);
//		table.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
//		table.setBackground(Color.white);
//		
//		int  row_MinWidth[]={80, 150, 150, 150, 150, 150, 150};
//		Vector<String> Vtitle = new Vector<String>();
//		
//		for (int i = 0; i < row_MinWidth.length; i++) { //set head value 
//			Vtitle.add(columnNames[i]);
//		}
//		model = new DefaultTableModel(new Vector<String>(), Vtitle);
//		table = new JTable(model);
//
//		for (int i = 0; i < row_MinWidth.length; i++) { //set Min Width 
//			table.getColumnModel().getColumn(i).setMinWidth(row_MinWidth[i]);
//		}
//		
//		frame.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				frame.dispose();
//			}
//		});
//		
//		for (int i = 0; i < data.length; i++) {
//			Vector<Object> Vec = new Vector<Object>();
//			for (int j = 0; j < columnNames.length; j++) {
//				Vec.add(data[i][j]);
//			}
//			model.addRow(Vec);
//		}
//		for (int i = 0; i < data.length; i++) {
//			Vector<Object> Vec = new Vector<Object>();
//			for (int j = 0; j < columnNames.length; j++) {
//				Vec.add(data[i][j]);
//			}
//			model.addRow(Vec);
//		}
//		table.setPreferredSize(new Dimension(980, data.length*20)); // 設大小
//		JScrollPane tscroll = new JScrollPane(table);//加捲軸
//		tscroll.getVerticalScrollBar().setUnitIncrement(50); // 直
//		tscroll.getHorizontalScrollBar().setUnitIncrement(50); // 橫
//		tscroll.setBounds(10, 25, 230, 150);
//		tscroll.setViewportView(table);
//		Panel3.add(tscroll);
		
		
		
//		Pane.setPreferredSize(new Dimension(938, (50 * sequence.length() / 800) + 50)); // 設大小
//		JScrollPane scr = new JScrollPane(Pane); // 加入捲軸
//		scr.getVerticalScrollBar().setUnitIncrement(50); // 直
//		scr.getHorizontalScrollBar().setUnitIncrement(50); // 橫
//		Pane.setOpaque(false);// 透明化
//		scr.setBounds(10, 25, 938, 565);
//		Panel.add(scr);
//		
		
		// ---------------------------------------
		TextField1.setBounds(50, 815, 55, 30);
		frame.add(TextField1);
		TextField1.setText(String.valueOf(cpgnumber.length));

		TextField2.setBounds(50, 765, 55, 30);
		frame.add(TextField2);
		TextField2.setText(String.valueOf(sequence.length()));

		JLabel Label4 = new JLabel("CpG island number");
		Label4.setBounds(50, 800, 150, 10);
		Label4.setForeground(Color.BLUE);
		frame.add(Label4);

		JLabel Label5 = new JLabel("Sequence Length");
		Label5.setBounds(50, 750, 150, 10);
		Label5.setForeground(Color.BLUE);
		frame.add(Label5);

		// -----在TextArea2印出CpGI位置--------------------------------------------
		for (int i = 0; i < cpgnumber.length; i++) {
			TextArea2.append((i + 1) + "\t" + cpgnumber[i][0] + "\t" + cpgnumber[i][1] + "\t" + (cpgnumber[i][1] - cpgnumber[i][0]) + "\t" + nf.format(cpgnumber_CGOE[i][0]) + "\t" + nf.format(cpgnumber_CGOE[i][1]));
			TextArea2.append("\n");
		}

		// -----------------------畫圖----------------------------------------------
		JPanel Pane = new JPanel() {
			public void paint(Graphics g) { // 畫線
				int i = 0;
				int stringstart = 25;
				int linestart_X = 75;
				int linestart_Y = 25;
				int PreCpGIstart_Y = linestart_Y + 2;
				int TrueCpGIstart_Y = linestart_Y - 2;
				
				grapicsy = 0; // Y軸往下
				while (i < (sequence.length() / 800) + 1) {
					g.setColor(Color.BLACK); // -----------畫橫--------
					if (sequence.length() < 800) {
						g.drawString("" + i, stringstart, linestart_Y + 5);
						g.drawLine(linestart_X, linestart_Y, linestart_X + (sequence.length() % 800), linestart_Y);
					} else if (sequence.length() > 801 && i < (sequence.length() / 800)) {
						g.drawString("" + ((i * 800) + 1), stringstart, linestart_Y + 5 + grapicsy);
						g.drawLine(linestart_X, linestart_Y + grapicsy, linestart_X + 800, linestart_Y + grapicsy);
					}
					if (i == (sequence.length() / 800) && sequence.length() > 801) {// 畫幾條
						g.drawString("" + ((i * 800) + 1), stringstart, linestart_Y + 5 + grapicsy);
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
				g.setColor(Color.red);
				for (int j = 0; j < cpgnumber.length; j++) {
					for (int n = (cpgnumber[j][0] / 800); n <= ((cpgnumber[j][0] + (cpgnumber[j][1] - cpgnumber[j][0])) / 800); n++) {
						
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
				// --------畫出真實cpgisland位置-----------------------------------
				g.setColor(Color.BLUE);
				for (int j = 0; j < trueCpGInum; j++) {
					for (int n = (trueCpGIlocation[j][0] / 800); n <= ((trueCpGIlocation[j][0] + (trueCpGIlocation[j][1] - trueCpGIlocation[j][0])) / 800); n++) {
						
						if (n == 0) {
							if (trueCpGIlocation[j][1] >= 800 && trueCpGIlocation[j][0] == 0) {
								g.drawLine(linestart_X + n, TrueCpGIstart_Y, linestart_X + 800, TrueCpGIstart_Y);
							} else if (trueCpGIlocation[j][1] >= 800) {
								// g.setColor(Color.BLUE);
								g.drawLine((linestart_X + trueCpGIlocation[j][0]), TrueCpGIstart_Y, linestart_X + 800, TrueCpGIstart_Y);
							} else {
								g.drawLine((50 + trueCpGIlocation[j][0]), TrueCpGIstart_Y, 50 + (trueCpGIlocation[j][1] % 800), TrueCpGIstart_Y);
							}
							g.drawString("CpG island" + (j + 1), 50 + (trueCpGIlocation[j][0] - 800 * n), (TrueCpGIstart_Y - 10 + (50 * n)));
						} else if (n == (((trueCpGIlocation[j][0] + (trueCpGIlocation[j][1] - trueCpGIlocation[j][0])) / 800))) {
							if (trueCpGIlocation[j][0] / 800 == n && n == (trueCpGIlocation[j][0] + (trueCpGIlocation[j][1] - trueCpGIlocation[j][0])) / 800) {
								g.drawLine(linestart_X + (trueCpGIlocation[j][0] % 800), (TrueCpGIstart_Y + (50 * (n))), linestart_X + (trueCpGIlocation[j][1] % 800), (TrueCpGIstart_Y + (50 * (n))));
							} else {
								g.drawLine(linestart_X, (TrueCpGIstart_Y + (50 * (n))), linestart_X + (trueCpGIlocation[j][1] % 800), (TrueCpGIstart_Y + (50 * (n))));
							}
							g.drawString("CpG island" + (j + 1), 50 + (trueCpGIlocation[j][0] - 800 * n), (TrueCpGIstart_Y - 10 + (50 * n)));
						} else {
							if (n == (trueCpGIlocation[j][0] / 800)) {
								g.drawLine((linestart_X + (trueCpGIlocation[j][0] % 800)), (TrueCpGIstart_Y + (50 * (n))), linestart_X + 800, (TrueCpGIstart_Y + (50 * (n))));
								g.drawString("CpG island" + (j + 1), 50 + (trueCpGIlocation[j][0] - 800 * n), (TrueCpGIstart_Y - 10 + (50 * n)));
							} else {
								g.drawLine(linestart_X, (TrueCpGIstart_Y + (50 * (n))), linestart_X + 800, (TrueCpGIstart_Y + (50 * (n))));
							}
						}
					}
				}
			}
		};
		
		Pane.setPreferredSize(new Dimension(0, (50 * sequence.length() / 800) + 50)); // 設大小
		JScrollPane scr = new JScrollPane(Pane); // 加入捲軸
		scr.getVerticalScrollBar().setUnitIncrement(50); // 直
		scr.getHorizontalScrollBar().setUnitIncrement(50); // 橫
		Pane.setOpaque(false);// 透明化
		scr.setBounds(10, 25, 938, 565);
		Panel.add(scr);
		
		
		JLabel LabeGC = new JLabel(new ImageIcon("CPGpre.gif"));
		LabeGC.setBounds(270, 20, 450, 50);
		frame.add(LabeGC);

		JLabel Label = new JLabel(new ImageIcon("2.png")); // 連接背景圖檔
		Label.setBounds(0, 0, 1024, 950);
		frame.add(Label);
		// -------視窗顯示---------------------------------------------
		frame.show();

	}
	

}
