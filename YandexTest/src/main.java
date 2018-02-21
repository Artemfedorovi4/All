import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class main {

	public static void main(String[] args) {
		FileInputStream fin;

		String fileStr = "";
		String[] arr;

		try {
			fin = new FileInputStream("input.txt");
			int v = -1;
			while((v = fin.read()) != -1&&v!='\r'&&v!='\n') {
				fileStr+=(char)v;
			}
			
			int num = Integer.parseInt(fileStr);
			arr = new String[num];
			fileStr = "";

			//int[] arrI = new int[num];
			while ((v = fin.read()) != -1) {
				if ((char) v != '\r' && (char) v != '\n')
					fileStr += (char) v;
			}

			arr = fileStr.split(" ");
			int maxInt = 0;
			for (int f = 0; f < num; f++) {
					//arrI[f] = Integer.parseInt(arr[f]);
				if (maxInt < Integer.parseInt(arr[f]))
					maxInt = Integer.parseInt(arr[f]);
			}
			
			boolean bool = true;
			for (int i = 0; i < arr.length; i++) {
				int summ = 0;
				for (int f = 0; f < arr.length; f++) {
					if (i != f) {
						summ += Integer.parseInt(arr[f]);

					}
				}
				if (summ <= Integer.parseInt(arr[i])) {
					bool = false;
				}
			}

			PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
			if (bool)
				writer.println("Possible");
			else
				writer.println("Impossible");
			writer.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
