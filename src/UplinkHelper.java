import java.io.*;

class UplinkHelper
{
	 public static void main(String[] args) throws IOException, FileNotFoundException
		{
                         
		 FileReader in = new FileReader("C:/Users/sachit/workspace/practice/src/10mb.bin");
		 BufferedReader buf = new BufferedReader(in);
		 String line;
		 while ((line=buf.readLine()) !=null)
			{
			 	System.out.println (line);
			}

			buf.close();
		} 
}
