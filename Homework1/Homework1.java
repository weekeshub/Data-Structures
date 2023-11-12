import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Homework1 {

	public static void bubbleSort(int[] array) {

		int comparisons = 0;
		int exchanges = 0;
		boolean condition = true;

		for (int outer = 1; outer != array.length; outer++) {
			if (condition) {
				condition = false;
				for (int inner = 0; inner != array.length - outer; inner++) {
					comparisons++;
					if (array[inner] > array[inner + 1]) {
						int temp = array[inner];
						array[inner] = array[inner + 1];
						array[inner + 1] = temp;
						condition = true;
						exchanges++;
					}
				}
			} else {
				break;
			}
			System.out.println("\nBubble Sort:");
			System.out.println("Comparisons: " + comparisons);
			System.out.println("Exchanges: " + exchanges + "\n\n");
		}

	}

	public static void selectionSort(int[] array) {

		int comparisons = 0;
		int exchanges = 0;

		for (int outer = 0; outer < array.length - 1; outer++) {
			int smallestIndex = outer;
			for (int inner = outer + 1; inner != array.length; inner++) {
				comparisons++;
				if (array[inner] < array[smallestIndex]) {
					smallestIndex = inner;
				}
			}
			int temp = array[smallestIndex];
			array[smallestIndex] = array[outer];
			array[outer] = temp;
			exchanges++;
		}
		System.out.println("\nSelection Sort:");
		System.out.println("Comparisons: " + comparisons);
		System.out.println("Exchanges: " + exchanges + "\n\n");

	}

	public static void insertionSort(int[] array) {

		int comparisons = 0;
		int exchanges = 0;

		for (int outer = 1; outer < array.length; outer++) {
			int key = array[outer];
			int inner = outer - 1;
			while (inner >= 0 && array[inner] > key) {
				array[inner + 1] = array[inner];
				inner--;
				comparisons++;
				exchanges++;
			}
			array[inner + 1] = key;
		}
		System.out.println("\nInsertion Sort:");
		System.out.println("Comparisons: " + comparisons);
		System.out.println("Exchanges: " + exchanges + "\n\n");

	}

	public static void main(String[] args) throws IOException {

		// change array size depending on input data
		int[] array = new int[16];
		// int[] array = new int[2000];
		int index = 0;

		// scan in data set of choice by entering pathname
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the pathname of the input file: ");
		String inputPath = scan.nextLine();
		System.out.print("Enter the pathname of the output file: ");
		String outputPath = scan.nextLine();

		Scanner inputFile = new Scanner(new File(inputPath));
		PrintWriter outputFile = new PrintWriter(new FileWriter(outputPath));

		// parse file data into an array
		while (inputFile.hasNextInt()) {
			array[index++] = inputFile.nextInt();
			inputFile.nextLine();
		}

		// print unsorted array
		outputFile.println("\nUnsorted array: ");
		for (int i = 0; i < array.length; i++) {
			outputFile.println(array[i]);
		}

		// change sorting algorithm at will
		bubbleSort(array);
		// selectionSort(array);
		// insertionSort(array);

		// print newly sorted array
		outputFile.println("\n\nSorted array: ");
		for (int i = 0; i < array.length; i++) {
			outputFile.println(array[i]);
		}

		// close input and output files to prevent data leaks
		inputFile.close();
		outputFile.close();

	}

}
