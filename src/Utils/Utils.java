package Utils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Utils {

	public static List<String> readFromConsole() {
		List<String> inputLines = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter input");
		while(true) {
			System.out.print("Python_Input>>");
			String text = sc.nextLine();
			if(text.isEmpty()) break;
			else {
				inputLines.add(text);
			}
		}
		System.out.println("Read " +inputLines.size() +" from console.");
		return inputLines;
	}

	public static List<String> readFromFile(String inputPath) {
		List<String> lines = new ArrayList<>();
		try {
			FileInputStream fstream = new FileInputStream(inputPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				lines.add(strLine);
			}
		} catch(Exception e) {
			System.out.println("Error in reading the file from console." + e);
		}
		return lines;
	}
}
