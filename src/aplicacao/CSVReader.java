package aplicacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.Prato;

public class CSVReader {
	
	public static final String delimiter = ";";

	public static List<Prato> read(String csvFile) {
		try {
			File file = new File(csvFile);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line = "";

			String[] tempArr;
			List<Prato> pratos = new ArrayList<Prato>();
			Prato prato;
			int i=1;
			boolean glutem, lactose;
			
			line = br.readLine(); //primeira linha cabeçalho
			
			while ((line = br.readLine()) != null) {
				tempArr = line.split(delimiter);
				if (tempArr.length==4) {
					if (tempArr[3].equalsIgnoreCase("SIM")) {
						lactose = true; 
					}else {
						lactose = false;
					}
					
					if (tempArr[2].equalsIgnoreCase("SIM")) {
						glutem = true; 
					}else {
						glutem = false;
					}
		
					prato = new Prato (i,tempArr[0],null,1,0,lactose,glutem,null);
					pratos.add(prato);
					i++;
				}
				
//				for (String tempStr : tempArr) {
//					System.out.print(tempStr + " ");
//				}
//				System.out.println();
			}
			br.close();
			return pratos;

		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}

	}
	
	public static void main(String[] args) {
		// csv file to read
		String csvFile = "bdpratos.csv";
		CSVReader.read(csvFile);
	}

}
