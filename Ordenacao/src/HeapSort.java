
public class HeapSort {

	public static int compara = 0;
	public static int troca = 0;

	public void iniciarHeapSort() {

		int array[] = { 9, 4, 6, 5, 8, 1, 7, 3, 2 };

		System.out.println("HEAPSORT\n\n\n");
		System.out.print("Numeros fora de ordem:\n\n\n");

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println("\n\n\n");
		System.out.println();

		array = heapSort(array, array.length);

		System.out.print("Numeros na ordem com base na ordenacao:\n\n\n");

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println("\n\n\n");
		System.out.println();
		System.out.println("Comparacoes: " + compara + " Troca: " + troca+"\n\n\n");

	}

	public static int[] heapSort(int[] v, int n) {
		buildMaxHeap(v);

		for (int i = v.length - 1; i > 0; i--) {
			swap(v, i, 0);
			maxHeapify(v, 0, --n);
		}
		return v;
	}

	private static void buildMaxHeap(int[] v) {
		for (int i = v.length / 2; i >= 0; i--)
			maxHeapify(v, i, v.length);
	}

	private static void maxHeapify(int[] v, int pos, int n) {
		int max;

		int left = 2 * pos + 1;
		int right = 2 * pos + 2;

		compara++;
		if ((left < n) && (v[left] > v[pos])) {
			max = left;
			troca++;
		} else {
			max = pos;
		}

		compara++;
		if ((right < n) && (v[right] > v[max])) {
			max = right;
			troca++;
		}

		if (max != pos) {
			swap(v, pos, max);
			maxHeapify(v, max, n);
		}
	}

	public static void swap(int[] v, int j, int apos) {
		int aux = v[j];
		v[j] = v[apos];
		v[apos] = aux;
	}

}
