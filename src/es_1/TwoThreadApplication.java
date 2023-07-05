package es_1;

public class TwoThreadApplication {
	public static void main(String[] args) {
		Thread thread1 = new SymbolThread("*");
		Thread thread2 = new SymbolThread("#");

		thread1.start();
		thread2.start();
	}
}

class SymbolThread extends Thread {
	private String symbol;

	public SymbolThread(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.print(symbol);
			try {
				Thread.sleep(1000); // Attendi un secondo (1000 millisecondi)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
