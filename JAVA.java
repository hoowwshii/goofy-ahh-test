import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class JAVA {
    public static int partition(ArrayList<Integer> list, int low, int high) {
        // Choose the pivot
        int pivot = list.get(high);

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = low - 1;

        // Traverse list[low..high] and move all smaller
        // elements on left side. Elements from low to
        // i are smaller after every iteration
        for (int j = low; j <= high - 1; j++) {
            if (list.get(j) < pivot) {
                i++;
                swap(list, i, j);
            }
        }

        // Move pivot after smaller elements and
        // return its position
        swap(list, i + 1, high);

        return i + 1;
    }

    public static void quickSort(ArrayList<Integer> list, int low, int high) {
        if (low < high) {
            // pi is the partition return index of pivot
            int pi = partition(list, low, high);

            // Recursion calls for smaller elements
            // and greater or equals elements
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }
     private static void swap(ArrayList<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
    public static void mergeSort(ArrayList<Integer> arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(ArrayList<Integer> arr, int left, int mid, int right) {
        int n1 = mid - left+1;
        int n2 = right - mid;

        // Create temp lists
        ArrayList<Integer> L = new ArrayList<>(n1);
        ArrayList<Integer> R = new ArrayList<>(n2);

        // Copy data to temp lists L[] and R[]
        for (int i = 0; i < n1; i++) {
            L.add(arr.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            R.add(arr.get(mid + 1 + j));
        }

        int i = 0, j = 0;
        int k = left;

        // Merge the temp lists back into arr[left..right]
        while (i < n1 && j < n2) {
            if (L.get(i) <= R.get(j)) {
                arr.set(k, L.get(i));
                i++;
            } else {
                arr.set(k, R.get(j));
                j++;
            }
            k++;
        }

        // Copy the remaining elements of L[], if there are any
        while (i < n1) {
            arr.set(k, L.get(i));
            i++;
            k++;
        }

        // Copy the remaining elements of R[], if there are any
        while (j < n2) {
            arr.set(k, R.get(j));
            j++;
            k++;
        }
    }



    public static void bubbleSort(ArrayList<Integer> arr, int n){
    boolean swapped;
    for(int i = 0; i<n-1;i++){
    swapped = false;
    for(int j = 0; j<n-i-1;j++){
        if(arr.get(j)> arr.get(j+1)) {
            int temp=arr.get(j);
            arr.set(j,arr.get(j+1));
            arr.set(j+1, temp);
            swapped=true;       
        }
    }
    if (swapped == false)
        break;
    }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        try(Scanner scanner = new Scanner(new File("file1.txt"))){
            while (scanner.hasNextInt()){
                Integer temp = scanner.nextInt();
                arr.add(temp);
            }
        }
        ArrayList<Integer> arr2= new ArrayList<Integer>();
        try(Scanner scanner = new Scanner(new File("file2.txt"))){
            while (scanner.hasNextInt()){
                Integer temp = scanner.nextInt();
                arr2.add(temp);
            }
        }
        ArrayList<Integer> arr3= new ArrayList<Integer>();
        try(Scanner scanner = new Scanner(new File("file3.txt"))){
            while (scanner.hasNextInt()){
                Integer temp = scanner.nextInt();
                arr3.add(temp);
            }
        }
        ArrayList<ArrayList<Integer> > ArrayofArrays = new ArrayList<ArrayList<Integer>>();
        ArrayofArrays.add(arr);
        ArrayofArrays.add(arr2);
        ArrayofArrays.add(arr3);
        for(int i = 1 ; i<=3; i++){
        ArrayList<Integer> copy1 = new ArrayList<>(ArrayofArrays.get(i-1)); 
        ArrayList<Integer> copy2 = new ArrayList<>(ArrayofArrays.get(i-1));
        ArrayList<Integer> copy3 = new ArrayList<>(ArrayofArrays.get(i-1));

        long start = (long) System.nanoTime();
        bubbleSort(copy1, copy1.size());
        long end = (long) System.nanoTime();
        float elapsed = (float)(end-start) / 1_000_000f;
        System.out.println("Bubble sort run ke " + i + " mencetak waktu: " +elapsed + "ms");
        
        start=System.nanoTime();
        mergeSort(copy2, 0, copy2.size()-1);
        end=System.nanoTime(); 
        elapsed = (float)(end-start) / 1_000_000f;
        System.out.println("Mergesort run ke " + i + " mencetak waktu: " +elapsed + "ms");
        
        start=System.nanoTime();
        quickSort(copy3, 0, copy3.size()-1);
        end=System.nanoTime(); 
        elapsed = (float)(end-start) / 1_000_000f;
        System.out.println("Quicksort run ke " + i + " mencetak waktu: " +elapsed+"ms");
    }
          
    }
}
