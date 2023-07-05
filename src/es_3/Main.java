package es_3;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		RegistroPresenze registro = new RegistroPresenze();

		registro.aggiungiPresenza("Mario Rossi", 5);
		registro.aggiungiPresenza("Giorgio Bianchi", 7);
		registro.aggiungiPresenza("Gianni Verdi", 7);

		try {
			registro.salvaSuDisco("presenze.txt");
			System.out.println("Presenze salvate su disco.");

			registro.caricaDaDisco("presenze.txt");
			logger.info("Presenze caricate da disco:");
			registro.stampaPresenze();
		} catch (IOException e) {
			logger.error("Si è verificato un errore durante il salvataggio o il caricamento delle presenze.");
			e.printStackTrace();
		}
	}
}
