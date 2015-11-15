import java.io.*;
import java.util.regex.Pattern;

public class ReadData {
	/* 讀取資料用的抽象類別, 內容皆為全域變數 */
	public static String filepath = ""; // 路徑
	public static String filename = ""; // 檔案名稱
	public static String[] subfilename; // 宣告檔案名稱陣列
	private String lineStr;
	private int file_num;
	private String[] files_name_array;
	private String files_name;
	private int file_index;

	/* ReadDatabase的建構子 */
	public ReadData(String path) {
		File file = new File(path);
		 //filename=path.split("/")[path.split("/").length-1];
		filename=file.getName();
		 System.out.println(filename);
		//filepath = path.replaceAll(filename,"");
		// filepath = path.replaceAll(filename,"");
		 filepath = file.getParent()+"\\";
		//System.out.println(filepath);
		//System.out.println(file.getPath().split(Pattern.quote(File.separator))[0]);
		clustvis.path=filepath;
		
	}

	public String read_filename() throws IOException {
		FileReader fr = new FileReader(filepath + filename);
		BufferedReader br = new BufferedReader(fr);
		while ((lineStr = br.readLine()) != null) {
//			System.out.println(lineStr);
			files_name = lineStr;
		}
		return files_name;
	}
	public String[] read_file_name() throws IOException {
		read_file_num();

		file_index = 0;
		FileReader fr = new FileReader(filepath + filename);
		BufferedReader br = new BufferedReader(fr);
		while ((lineStr = br.readLine()) != null) {
			files_name_array[file_index] = lineStr;
			file_index++;
		}
		return files_name_array;
	}

	public void read_file_num() throws IOException {
		FileReader fr = new FileReader(filepath + filename);
		System.out.println(filepath + filename);
		BufferedReader br = new BufferedReader(fr);
		while ((lineStr = br.readLine()) != null) {
			file_num++;
		}
		files_name_array = new String[file_num];
	}

	/* 讀取序列 */
	public String readSquence(String subfilename) {
		String squences = "";
		try {
			FileReader fr = new FileReader(filepath + subfilename);
			BufferedReader br = new BufferedReader(fr);
			while ((lineStr = br.readLine()) != null) {
				if (lineStr.startsWith(">")) {

				} else {
					squences += lineStr;
				}
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			System.err.println(e);
		}
		return squences;
	}

}
