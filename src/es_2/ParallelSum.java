package es_2;

import java.util.Random;

public class ParallelSum {
	public static void main(String[] args) throws InterruptedException {
		int[] array = generateRandomArray(3000);
		int[] partialSums = new int[3];
		Thread[] threads = new Thread[3];

		for (int i = 0; i < 3; i++) {
			int startIndex = i * 1000;
			int endIndex = (i + 1) * 1000;

			threads[i] = new SumThread(array, startIndex, endIndex, partialSums, i);
			threads[i].start();
		}

		for (Thread thread : threads) {
			thread.join(); // Attendi la terminazione di ogni thread figlio
		}

		int totalSum = 0;
		for (int sum : partialSums) {
			totalSum += sum;
		}

		System.out.println("Somma totale: " + totalSum);
	}

	private static int[] generateRandomArray(int size) {
		int[] array = new int[size];
		Random random = new Random();

		for (int i = 0; i < size; i++) {
			array[i] = random.nextInt(100); // Genera un numero casuale compreso tra 0 e 99
		}

		return array;
	}
}

class SumThread extends Thread {
	private int[] array;
	private int startIndex;
	private int endIndex;
	private int[] partialSums;
	private int threadIndex;

	public SumThread(int[] array, int startIndex, int endIndex, int[] partialSums, int threadIndex) {
		this.array = array;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.partialSums = partialSums;
		this.threadIndex = threadIndex;
	}

	@Override
	public void run() {
		int sum = 0;
		for (int i = startIndex; i < endIndex; i++) {
			sum += array[i];
		}
		partialSums[threadIndex] = sum;
	}
}
