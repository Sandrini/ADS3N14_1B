
public class BubbleSort {
	
	public void iniciarBubbleSort() {
		int array[] = { 7, 4, 1, 9, 3, 5, 2, 8, 6 };
		
		System.out.print("Numeros desordenados:\n");
		
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "  ");
		}
		System.out.println();
		int compara = 0;
		int ordena = 0;

		int n = array.length;

		for (int i = 0; i < n; i++) {
			
			for (int j = 1; j < (n - i); j++) {

				compara++;

				int t;

				if (array[j - 1] > array[j]) {

					t = array[j - 1];

					array[j - 1] = array[j];

					array[j] = t;

					ordena++;

				}//fim do if
			}//fim do primeiro for
			
		}//fim do segundo for
		
		System.out.print("Numeros Ordenados: ");
		for (int c = 0; c < array.length; c++) {
			System.out.print(array[c] + "  ");
		}
		
	}//Fim do metodo
	
}//Fim da Classe
