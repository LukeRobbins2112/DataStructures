/*
*
*Quicksort
*/


public class Quicksort{

	private Quicksort(){}

	private final int CUTOFF = 7;

	private static int partition(Comparable[] a, int low, int high){

		int i = low;
		int j = high - 1;

		while(true){

			while(less(a[++i], a[low])){
				if (i == high){
					break;
				}
			}

			while (less(a[low], a[--j])){
				if (j == low){
					break;
				}
			}

			if (i >= j){
				break;
			}			
		}

		exchange(a, low, j);
		return j;
	}

	private static void sort(){

		if (high <= low + CUTOFF - 1){
			Insertion.sort(a, low, high);
			return;
		}

		int j = partition(a, low, high);
		sort(a, low, j-1);
		sort(a, j+1, high);

	}

	public static void sort(Comparable[] a){
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	private static void less(Comparable a, Comparable b){
		return (a.compareTo(b) < 0);
	}

	private static void exchange(Comparable[] a, int i, int j){
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}