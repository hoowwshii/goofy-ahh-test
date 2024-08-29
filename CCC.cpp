#include <iostream>
#include <fstream>
#include <chrono>
#include <vector>
#include <functional>
using namespace std;

int partition(vector<int>& arr, int low, int high) {
  
    // Choose the pivot
    int pivot = arr[high];
  
    // Index of smaller element and indicates 
    // the right position of pivot found so far
    int i = low - 1;

    // Traverse arr[;ow..high] and move all smaller
    // elements on left side. Elements from low to 
    // i are smaller after every iteration
    for (int j = low; j <= high - 1; j++) {
        if (arr[j] < pivot) {
            i++;
            swap(arr[i], arr[j]);
        }
    }
    
    // Move pivot after smaller elements and
    // return its position
    swap(arr[i + 1], arr[high]);  
    return i + 1;
}

// The QuickSort function implementation
void quickSort(vector<int>& arr, int low, int high) {
  
    if (low < high) {
      
        // pi is the partition return index of pivot
        int pi = partition(arr, low, high);

        // Recursion calls for smaller elements
        // and greater or equals elements
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}


void merge(vector<int>& arr, int left, int mid, int right)
{
    int n1 = mid - left + 1;
    int n2 = right - mid;

    // Create temp vectors
    vector<int> L(n1), R(n2);

    // Copy data to temp vectors L[] and R[]
    for (int i = 0; i < n1; i++)
        L[i] = arr[left + i];
    for (int j = 0; j < n2; j++)
        R[j] = arr[mid + 1 + j];

    int i = 0, j = 0;
    int k = left;

    // Merge the temp vectors back 
    // into arr[left..right]
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        }
        else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    // Copy the remaining elements of L[], 
    // if there are any
    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }

    // Copy the remaining elements of R[], 
    // if there are any
    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}

// begin is for left index and end is right index
// of the sub-array of arr to be sorted
void mergeSort(vector<int>& arr, int left, int right)
{
    if (left >= right)
        return;

    int mid = left + (right - left) / 2;
    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);
    merge(arr, left, mid, right);
}



void bubbleSort(vector<int> arr, int n)
{
    int i, j;
    bool swapped;
    for (i = 0; i < n - 1; i++) {
        swapped = false;
        for (j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                swap(arr[j], arr[j + 1]);
                swapped = true;
            }
        }

        // If no two elements were swapped
        // by inner loop, then break
        if (swapped == false)
            break;
    }
}


int main(){
    ifstream file("file.txt");
    vector<int> arr;
    int value;
    while(file >> value){
        arr.push_back(value);
    }

    for (int i = 1; i <= 3; i++)
    {
    vector<int> copy1 = arr;
    vector<int> copy2 = arr;
    vector<int> copy3 = arr;
    
    auto start = chrono::high_resolution_clock::now();
    bubbleSort(copy1, copy1.size());
    auto end = chrono::high_resolution_clock::now();
    chrono::duration<double, milli> elapsed = end-start;
    cout << "Bubble sort run ke " << i << " mencetak waktu: " << elapsed.count() << "ms" << endl;
    
    start = chrono::high_resolution_clock::now();
    mergeSort(copy2,0, copy2.size()-1);
    end = chrono::high_resolution_clock::now();
    elapsed=end-start;
    cout << "Mergesort run ke " << i << " mencetak waktu: " << elapsed.count() << "ms" << endl;
    
    start = chrono::high_resolution_clock::now();
    quickSort(copy3,0, copy3.size()-1);
    end = chrono::high_resolution_clock::now();
    elapsed = end-start;
    cout << "Quicksort run ke " << i << " mencetak waktu: " << elapsed.count() << "ms" << endl;
    
    }
    
    
}