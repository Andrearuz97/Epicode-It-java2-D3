package es_3;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class RegistroPresenze {
	private static final String CHARSET = "UTF-8";
	private Presenza[] presenze;
	private int count;

	public RegistroPresenze() {
		presenze = new Presenza[100];
		count = 0;
	}

	public void aggiungiPresenza(String nome, int giorni) {
		Presenza presenza = new Presenza(nome, giorni);
		presenze[count] = presenza;
		count++;
	}

	public void salvaSuDisco(String filePath) throws IOException {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < count; i++) {
			Presenza presenza = presenze[i];
			sb.append(presenza.getNome()).append("@").append(presenza.getGiorni()).append("#");
		}

		FileUtils.writeStringToFile(new File(filePath), sb.toString(), CHARSET);
	}

	public Presenza[] caricaDaDisco(String filePath) throws IOException {
		String content = FileUtils.readFileToString(new File(filePath), CHARSET);
		String[] presenzeString = content.split("#");
		presenze = new Presenza[presenzeString.length];
		count = 0;

		for (int i = 0; i < presenzeString.length; i++) {
			String[] presenzaData = presenzeString[i].split("@");
			String nome = presenzaData[0];
			int giorni = Integer.parseInt(presenzaData[1]);
			Presenza presenza = new Presenza(nome, giorni);
			presenze[count] = presenza;
			count++;
		}

		return presenze;
	}

	public void stampaPresenze() {
		for (int i = 0; i < count; i++) {
			System.out.println(presenze[i].getNome() + ": " + presenze[i].getGiorni() + " giorni");
		}
	}
}
