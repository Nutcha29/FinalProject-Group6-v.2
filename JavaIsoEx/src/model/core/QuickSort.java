/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core;

import java.util.ArrayList;
import model.core.buildings.Building;

/**
 *
 * @author Wouter
 */
public class QuickSort {

  private ArrayList<DrawableObject> list;

  public ArrayList<DrawableObject> getList() {
    return list;
  }

  public void setList(ArrayList<DrawableObject> list) {
    this.list = list;
  }

  public QuickSort(ArrayList<DrawableObject> list) {
    this.setList(list);
  }

  public ArrayList<DrawableObject> sort() {
    // Check for empty or null array
    if (this.getList().size() < 2) {
      return this.getList();
    }

    try {
    quicksort(0, this.getList().size() - 1);
    } catch (NullPointerException e) {
      System.err.println("Something went wrong sorting..");
    }

    return this.getList();
  }

  private void quicksort(int low, int high) {
    int i = low, j = high;
    // Get the pivot element from the middle of the list
    double pivot = this.getList().get(low + (high - low) / 2).getDrawIndex();

    // Divide into two lists
    while (i <= j) {
      // If the current value from the left list is smaller then the pivot
      // element then get the next element from the left list
      while (this.getList().get(i).getDrawIndex() < pivot) {
        i++;
      }
      // If the current value from the right list is larger then the pivot
      // element then get the next element from the right list
      while (this.getList().get(j).getDrawIndex() > pivot) {
        j--;
      }

      // If we have found a values in the left list which is larger then
      // the pivot element and if we have found a value in the right list
      // which is smaller then the pivot element then we exchange the
      // values.
      // As we are done we can increase i and j
      if (i <= j) {
        exchange(i, j);
        i++;
        j--;
      }
    }
    // Recursion
    if (low < j)
      quicksort(low, j);
    if (i < high)
      quicksort(i, high);
  }

  private void exchange(int i, int j) {
    DrawableObject temp = this.getList().get(i);
    this.getList().set(i, this.getList().get(j));
    this.getList().set(j, temp);
  }
}
