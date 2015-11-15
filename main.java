import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class main {
	private static int location;
	// parameter
	private double CpG_content;// GC
	private int CpG_length_min;
	private int CpG_length_max;
	private double CpG_OE;
	private String path = "";
	// ui
	JFrame helpjf =new help_jframe();
	JFrame mainjf = new JFrame("parameter");
	JButton BTN_load = new JButton("load file");
	JTextField JTF_Loadpath = new JTextField();
	JButton BTN_run = new JButton("run");
	JButton BTN_open_help = new JButton("help");
	JTextField JTF_pso_population=new JTextField("300");
	JTextField JTF_pso_generation=new JTextField("100");
	JTextField JTF_pvalue=new JTextField("0.001");
	JTextField JTF_contig_sequence=new JTextField("0");
	JTextField JTF_threshold_dt=new JTextField("65");
	
	public main() throws IOException, InterruptedException {
		Fitness.CpG_content=0.5;
		Fitness.CpG_oe=0.6;
		System.out.println(System.getProperty("user.dir"));
		helpjf.setVisible(false);
		//helpjf.setDefaultCloseOperation(2);
		mainjf.setBounds(50, 50, 800, 500);
		
		mainjf.setLayout(null);			
		mainjf.setDefaultCloseOperation(3);
		addloadbutton();
		addrun_and_help();
		adddpsoset();
		
		// File file = chooser.getSelectedFile();
		// System.out.print(file.getPath());

		// System.out.print(this.getClass().getResource(""));

		TitledBorder blackline = BorderFactory
				.createTitledBorder("pso parameter");
		blackline.setTitleJustification(TitledBorder.CENTER);
		JPanel psoset =new JPanel();
		psoset.setBorder(blackline);
		psoset.setBounds(50, 60, 300, 150);
		psoset.setLayout(null);
		JTF_pso_generation.setBounds(150, 30, 100, 30);
		JTF_pso_generation.setHorizontalAlignment(JTextField.CENTER);
		JTF_pso_population.setBounds(150, 85, 100, 30);
		JTF_pso_population.setHorizontalAlignment(JTextField.CENTER);
		psoset.add(JTF_pso_generation);
		psoset.add(JTF_pso_population);
		JLabel JLB_pso_generation=new JLabel("generation");
		JLB_pso_generation.setBounds(35, 30, 100, 30);
		JLB_pso_generation.setForeground(Color.BLUE);
		JLabel JLB_pso_population=new JLabel("population");
		JLB_pso_population.setBounds(35, 85, 100, 30);
		JLB_pso_population.setForeground(Color.BLUE);
		psoset.add(JLB_pso_generation);
		psoset.add(JLB_pso_population);
		mainjf.add(psoset);
		mainjf.setVisible(true);
	}

	private void adddpsoset() {
		TitledBorder blackline = BorderFactory
				.createTitledBorder("parameter");
		blackline.setTitleJustification(TitledBorder.CENTER);
		JPanel set =new JPanel();
		set.setBorder(blackline);
		set.setBounds(400, 60, 300, 250);
		set.setLayout(null);
		JTF_pvalue.setBounds(150, 30, 100, 30);
		JTF_pvalue.setHorizontalAlignment(JTextField.CENTER);
		JTF_contig_sequence.setBounds(150, 85, 100, 30);
		JTF_contig_sequence.setHorizontalAlignment(JTextField.CENTER);
		JTF_threshold_dt.setBounds(150, 140, 100, 30);
		JTF_threshold_dt.setHorizontalAlignment(JTextField.CENTER);
		set.add(JTF_pvalue);
		set.add(JTF_contig_sequence);
		set.add(JTF_threshold_dt);
		JLabel JLB_pvalue=new JLabel("p-value");
		JLB_pvalue.setBounds(30, 30, 110, 30);
		JLB_pvalue.setForeground(Color.BLUE);
		JLabel JLB_contig_sequence=new JLabel("contig sequence");
		JLB_contig_sequence.setBounds(30, 85, 110, 30);
		JLB_contig_sequence.setForeground(Color.BLUE);
		JLabel JLB_threshold_dt=new JLabel("distance threshold");
		JLB_threshold_dt.setBounds(30, 140, 110, 30);
		JLB_threshold_dt.setForeground(Color.BLUE);
		set.add(JLB_pvalue);
		set.add(JLB_contig_sequence);
		set.add(JLB_threshold_dt);
		mainjf.add(set);
	}

	private void addrun_and_help() {
		//run =================================================================================//
		BTN_run.setBounds(650, 350, 100, 40);
		BTN_run.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if(path!=""&&path.endsWith(".txt")){
					PSO.STOP_MOVE=Integer.valueOf(JTF_pso_generation.getText());
					PSO.parNum=Integer.valueOf(JTF_pso_population.getText());
					CpGcluster.p_value_limit=Double.valueOf(JTF_pvalue.getText());
					CpGcluster.percentile_limit=Integer.valueOf(JTF_threshold_dt.getText());
					rundata();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		mainjf.add(BTN_run);
		//help =================================================================================//
		BTN_open_help.setBounds(525, 350, 100, 40);
		BTN_open_help.addActionListener(new ActionListener(){

			
			public void actionPerformed(ActionEvent e) {
			helpjf.setVisible(true);
				
			}
			
		});
		
		mainjf.add(BTN_open_help);
	}

	private void addloadbutton() {

		BTN_load.setBounds(50, 10, 100, 30);
		BTN_load.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				// chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = chooser.showOpenDialog(mainjf);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file =	chooser.getSelectedFile();
					path = chooser.getSelectedFile().getAbsolutePath();
					JTF_Loadpath.setText(path);
					//filepath = path.replaceAll(filename,"");
					// filepath = path.replaceAll(filename,"");					
					EstimateAcc.filepath=file.getParent()+"\\";
					clustvis.path=file.getParent()+"\\";
					//EstimateAcc.CpGfilename=file.getName();
				} else if (result == JFileChooser.CANCEL_OPTION) {
				}
			}

		});
		JTF_Loadpath.setBounds(200, 10, 500, 30);
		mainjf.add(BTN_load);
		mainjf.add(JTF_Loadpath);
	}

	private void rundata() throws IOException, InterruptedException {
		/* 時間設定參數 */
		long startTime; // 啟始時間(毫秒)
		long stopTime; // 結束時間(毫秒)
		long sumTime; // 總共花費的時間(秒)
		long allsec_d;
		long hour;
		long min;
		long sec;
		/* 啟始時間(毫秒) */
		runner.start();
		for (int i = 0; i < 1; i++) {
			startTime = System.currentTimeMillis();
			/* 執行CpGClust */
			new CpGcluster(i,path);
			/* CpGClust結束時間(毫秒) */
			stopTime = System.currentTimeMillis();
			sumTime = stopTime - startTime;
			allsec_d = sumTime / 1000;
			hour = allsec_d / 3600;
			min = allsec_d / 60;
			if (min >= 60)
				min = min % 60;
			sec = allsec_d % 60;

			double acc = EstimateAcc.getACC();
			double sn = EstimateAcc.getSN();
			double sp = EstimateAcc.getSP();
			double pc = EstimateAcc.getPC();
			double cc = EstimateAcc.getCC();
			System.out.println(hour + "時" + min + "分" + sec + "秒");
		}
		runner.stop();
		System.out.println(Fitness.CpG_content);
		System.out.println(Fitness.CpG_oe);
	}

	static Thread runner = new Thread() {
		public void run() {
			int count = 0;
			while (1 == 1) {
				count++;
				try {
					sleep(1000);
					location = CalculatingPvalues.getlocation();
					// System.out.println("sec "+count+"=\t"+location);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};

	public static void main(String[] args) throws IOException,
			InterruptedException {
		new main();
	}
}
