package TestEnvironment;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CompilerTester {

	public static void main(String args[]) {
	    
		 try {
			 InputStream inputStream = CompilerTester.class.getClassLoader().getResourceAsStream("testcases.txt");
			 BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
			 String l ;
			 while((l = r.readLine()) != null) {
				 System.out.println(l);
			 }
			 
			 
	      } catch(Exception e) {
	         System.out.println(e);
	      }
	}
}
