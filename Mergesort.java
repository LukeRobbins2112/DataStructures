/*
*
*Mergesort
*/

public class MergeSort(){

	private Comparable[] aux;
	private final int CUTOFF = 7;

	private MergeSort(){}

	private void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high){
		assert isSorted(a, low, mid);
		assert isSorted(a, mid+1, high);

		for (int k = low; k <=high; k++){		//copy array into aux
			aux[k] = a[k];
		}

		int i = low;
		int j = mid+1;

		for (int k = low; k <= high; k++){
			if (i > mid){
				a[k] = aux[j++];
			}
			else if (k > high){
				a[k] = aux[i++];
			}
			else if (less(aux[j], aux[i])){
				a[k] = aux[j++];
			}
			else{
				a[k] = aux[i++];
			}
		}

		assert isSorted(a, low, high);
	}

	private void sort(Comparable[] a, Comparable[] aux, int low, int high){

		if (high <= low + CUTOFF - 1){
			Insertion.sort(a, low, high);		//User for smaller subarrays where higher overhead of mergesort creates inefficiency
			return;
		}

		int mid = low + ((high - low) / 2);
		sort (a, aux, low, mid);
		sort (a, aux, mid+1, high);

		if (!less(a[mid+1], a[mid])){
			return;
		}

		merge(a, aux, low, mid, high);

	}

	public void sort(Comparable[] a){
		aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}

	private static void less(Comparable a, Comparable b){
		return (a.compareTo(b) < 0);
	}

	private static boolean isSorted(Comparable[] a, int low, int high) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])){
            	return false;
            }
            
        return true;
    }


}







