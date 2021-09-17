package com.spring.In28minutes;

import org.springframework.stereotype.Component;

@Component
public class QuickSort implements SortAlgorithm{
    private int partition(int a[], int l, int r)
    {
        int pivot = a[r];
        int pi = l;

        for(int i=l; i<r; i++)
            if(a[i] <= pivot)
            {
                int temp = a[i];
                a[i] = a[pi];
                a[pi] = temp;

                pi++;
            }
            int temp = a[pi];
            a[pi] = a[r];
            a[r] = temp;

            return pi;
    }

    private int randPartition(int a[], int l,int r)
    {
        int p = ( (int)Math.random()*(r-l+1) )+ l;

        int temp = a[p];
        a[p] = a[r];
        a[r] = temp;

        return partition(a, l, r);
    }
    private int [] quickSort(int a[], int l, int r)
    {
        if(l<r)
        {
            int pi = randPartition(a, l, r);
            quickSort(a, l, pi-1);
            quickSort(a, pi+1, r);
        }
        return a;
    }

    public int[] sort(int a[])
    {   System.out.println("Quick Sort implemented: ");
        return quickSort(a, 0, a.length - 1);
    }
}
