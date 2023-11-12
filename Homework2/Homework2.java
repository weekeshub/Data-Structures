import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Homework2 {

	public static int comparisons = 0;
	public static int exchanges = 0;
	
	// BUBBLE SORT
	
	public static void bubbleSort(int[] array) {

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
		}

	}
	
	//SELECTION SORT

	public static void selectionSort(int[] array) {

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

	}

	// INSERTION SORT
	
	public static void insertionSort(int[] array) {

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

	}

	// SHELL SORT

	// segmented insertion sort method
	static void segmentedInsertionSort(int[] array, int size, int interval) {

		for (int outer = interval; outer < size; outer++) {
			int current = array[outer];
			int inner = outer;
			while (inner > interval - 1 && (array)[inner - interval] > current) {
				comparisons++;
				array[inner] = array[inner - interval];
				inner = inner - interval;
				exchanges++;
			}
			array[inner] = current;
		}

	}

	// shell sort method
	static void shellSort(int[] array, int size) {

		size = array.length;

		// bad sequence: H/2
		int interval = size / 2;

		// good sequence: H = 3*H + 1

		// prototype data
		// int interval = 13;

		// large data
		// int interval = 1093;

		while (interval > 0) {
			segmentedInsertionSort(array, size, interval);
			interval = interval / 2;
		}

	}

	// MERGE SORT

	// merge method
	static void merge(int[] resultArray, int[] leftArray, int[] rightArray, int left, int right) {

		int leftIndex = 0;
		int rightIndex = 0;
		int resultIndex = 0;

		// keep taking smallest in front of either array
		// until one array is empty
		while (leftIndex < left && rightIndex < right) {
			comparisons++;
			if (leftArray[leftIndex] <= rightArray[rightIndex]) {
				// take from left array and increment
				resultArray[resultIndex] = leftArray[leftIndex];
				leftIndex++;
				resultIndex++;
			} else {
				// take from right array and increment
				exchanges++;
				resultArray[resultIndex] = rightArray[rightIndex];
				rightIndex++;
				resultIndex++;
			}
		}

		// now one sub-array or the other is empty

		// take all remaining elements from left
		while (leftIndex < left) {
			resultArray[resultIndex] = leftArray[leftIndex];
			leftIndex++;
			resultIndex++;

		}

		// take all remaining elements from right
		while (rightIndex < right) {
			resultArray[resultIndex] = rightArray[rightIndex];
			rightIndex++;
			resultIndex++;
		}

	}

	// mergeSort method
	static void mergeSort(int[] array, int length) {

		// base case: array consists of single element
		if (length < 2) {
			return;
		}

		// split array into left and right halves
		int mid = length / 2;
		int[] leftArray = new int[mid];
		int[] rightArray = new int[length - mid];

		// move elements from source array into corresponding sub arrays
		for (int index = 0; index < mid; index++) {
			leftArray[index] = array[index];
		}
		for (int index = mid; index < length; index++) {
			rightArray[index - mid] = array[index];
		}

		// call merge sort on each half
		mergeSort(leftArray, mid);
		mergeSort(rightArray, length - mid);

		// merge the result
		merge(array, leftArray, rightArray, mid, length - mid);

	}

	public static void main(String[] args) throws IOException {

		// change array size at will depending on input data
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
		outputFile.println("Unsorted array: ");
		for (int i = 0; i < array.length; i++) {
			outputFile.println(array[i]);
		}

		// change sorting algorithm at will by commenting in desired algorithm
		// bubbleSort(array);
		// selectionSort(array);
		// insertionSort(array);
		// shellSort(array, array.length);
		mergeSort(array, array.length);

		// print newly sorted array
		outputFile.println("\nSorted array: ");
		for (int i = 0; i < array.length; i++) {
			outputFile.println(array[i]);
		}
		outputFile.println("Comparisons: " + comparisons);
		outputFile.println("Exchanges: " + exchanges);

		// close input and output files to prevent data leaks
		inputFile.close();
		outputFile.close();

	}

}
