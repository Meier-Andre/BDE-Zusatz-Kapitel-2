package de.fhms.am.ueb2;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class AutomarkenGen {

	/**
	 * Erstellung einer Datei mit Automarken
	 * @param args
	 * @throws Exception
	 */

	public static void main (String[] args) throws Exception{

		String [] marken = {"BMW", "Audi", "Porsche", "Lada", "Mercedes", 
				"VW", "Fiat", "Ferrari", "Bugatti", "Lamborghini"};

		FileWriter outFile = new FileWriter(args[0]);
		PrintWriter out = new PrintWriter(outFile);

		Random randomGenerator = new Random ();
		for (int i=0; i<=150000; i++){
			int randomNo = randomGenerator.nextInt(marken.length);
			String carname = marken [randomNo];
			out.println(carname);
		}
		out.close();
		outFile.close();
	}

}
