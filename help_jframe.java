import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class help_jframe extends JFrame{ 
	String[] STR_help_title={"load file","contig sequence","threshold dt","p-value","population","generation","run","save"};
	String[] STR_help_content={"button is help to open the dataset for ClusterPSO program implementation. For example, when using supplementary test data set placed in “D:/ClusterPSO_GUI/data/NT_113952.1.fasta” please input the data path “D:/ClusterPSO_GUI/data/NT_113952_1.txt”",
			"contig sequence is the contig sequence which is defined as 0 or 1. If user want to predict CpG islands from contig sequence that user will set 0, otherwise set 1.",
								"distance threshold is the distance threshold between adjacent CpG dinucleotides within array",
								"p-value is the significance level (e.g. 0.01) for the cluster, which indicates a potential for the presence of island within the detected cluster.",
								"population is the population size of particle swarm optimization.",
								"generation is the generation number of particle swarm optimization.",
								"click to implement the ClusterPSO program.",
								"click to output the figure to the “D:/ClusterPSO_GUI/data/picture name”. The user can save another picture name for the CpG island prediction figure."};
	JPanel JPL_help=new JPanel();
	public help_jframe() {
		this.setBounds(500, 300, 500, 600);
		
		JPL_help.setLayout(null);
		JScrollPane js1 =new JScrollPane(JPL_help);
		for (int i = 0; i < STR_help_content.length; i++) {
			one_help abc=new one_help(new JLabel(STR_help_title[i]+":"),STR_help_content[i]);
			abc.setBounds(0, i*abc.total_sizey, abc.total_sizex, abc.total_sizey);
			JPL_help.add(abc);
			JPL_help.setSize(abc.total_sizex, i*abc.total_sizey);
			JPL_help.setPreferredSize(new Dimension(abc.total_sizex,i*abc.total_sizey));
		}
		
		js1.setBounds(0, 0, 500, 600);
		js1.setPreferredSize(new Dimension(500,600));
		this.add(js1);
		this.setVisible(true);
		
	}
}
class one_help extends JPanel{
	public int label_leftwidth=110;
	public int label_leftheight=110;
	public int label_rightwidth=300;
	public int label_rightheight=label_leftheight;
	public int total_sizex=label_leftwidth+label_rightwidth;
	public int total_sizey=label_leftheight;	
	public one_help(JLabel title,String STR_content) {
		this.setLayout(null);
		title.setBounds(0, 0,label_leftwidth , label_leftheight);
		title.setForeground(Color.BLUE);
		title.setHorizontalAlignment(JLabel.CENTER);
		JLabel content= new JLabel();
		
		content.setBounds(110, 0, label_rightwidth,label_rightheight);
		content.setPreferredSize(new Dimension(label_rightwidth,label_rightheight));
		content.setText("<html>"+STR_content+"</html>");    
		this.add(title);
		
		this.add(content);
		//this.add(label);
		
		
	}
	
	public static void main(String[] args) {
		new help_jframe();
	}
}
