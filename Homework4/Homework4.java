import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Homework4 {

	public static int comparisons = 0;
	public static int exchanges = 0;

	// SHELL SORT

	// segmented insertion sort method
	public static void segmentedInsertionSort(int[] array, int size, int interval) {

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
	public static void shellSort(int[] array, int size) {

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
	public static void merge(int[] resultArray, int[] leftArray, int[] rightArray, int left, int right) {

		int leftIndex = 0;
		int rightIndex = 0;
		int resultIndex = 0;

		// keep taking smallest in front of either array
		// until one array is empty
		while (leftIndex < left && rightIndex < right) {
			comparisons++;
			if (leftArray[leftIndex] <= rightArray[rightIndex]) {
				// take from left array and increment
				exchanges++;
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
			exchanges++;
			resultArray[resultIndex] = leftArray[leftIndex];
			leftIndex++;
			resultIndex++;
		}

		// take all remaining elements from right
		while (rightIndex < right) {
			exchanges++;
			resultArray[resultIndex] = rightArray[rightIndex];
			rightIndex++;
			resultIndex++;
		}

	}

	// mergeSort method
	public static void mergeSort(int[] array, int length) {

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

	// HEAP SORT

	// heap sort method
	public static void heapSort(int[] array) {
		int n = array.length - 1;
		int y = n / 2;
		while (y > 0) {
			downheap(array, y, n);
			y = y - 1;
		}
		y = n;
		while (y > 1) {
			exchanges++;
			int temp = array[1];
			array[1] = array[y];
			array[y] = temp;
			y = y - 1;
			downheap(array, 1, y);
		}
	}

	// downheap support method for heap sort
	public static void downheap(int[] array, int j, int y) {
		int n = y;
		boolean foundSpot = false;
		int l = j;
		int key = array[l];
		int k = 2 * l; // get left child first
		while (k <= n && !foundSpot) {
			if (k < n && !precedes(array[k + 1], array[k])) {
				k = k + 1;
				comparisons++;
			}
			if (!precedes(array[k], key)) {
				comparisons++;
				exchanges++;
				array[l] = array[k];
				l = k;
				k = 2 * l;
			} else {
				foundSpot = true;
			}
		}
		array[l] = key;
	}

	// precedes support method for heap sort
	public static boolean precedes(int a, int b) {
		return (a < b);
	}
	
	// MAIN METHOD

	public static void main(String[] args) throws IOException {

		// change array size depending on input data
		int[] array = new int[17];
		//int[] array = new int[2001];
		int index = 1;

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
		for (int i = 1; i < array.length; i++) {
			outputFile.println(array[i]);
		}

		// change sorting algorithm at will
		// shellSort(array, array.length);
		// mergeSort(array, array.length);
		heapSort(array);

		// print newly sorted array
		outputFile.println("\nSorted array: ");
		for (int i = 1; i < array.length; i++) {
			outputFile.println(array[i]);
		}
		outputFile.println("\nComparisons: " + comparisons);
		outputFile.println("Exchanges: " + exchanges);

		// close input and output files to prevent data leaks
		inputFile.close();
		outputFile.close();

	}

}
