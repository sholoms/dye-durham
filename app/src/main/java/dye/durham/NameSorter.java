package dye.durham;


// to save to file
import java.io.*;
// to load file
import java.util.Scanner;
import java.util.ArrayList;

public class NameSorter {
  // have constructor with no arguments to enable testing
  public NameSorter() {
  }

  public NameSorter(String unsortedFilePath) {
    String[] namesList = this.loadNames(unsortedFilePath);
    this.mergeSort(namesList);
    this.displayNames(namesList);
    this.saveToFile(namesList);
  }

  private String[] loadNames(String unsortedFilePath) {
    Scanner tempSc = null;
    try {
      tempSc = new Scanner(new FileReader(unsortedFilePath));
    } catch (FileNotFoundException e) {
      System.out.println("file not found");
    }

    ArrayList<String> unsortednames = new ArrayList<String>();
    if (tempSc != null) {
      while (tempSc.hasNext()) {
        unsortednames.add(tempSc.nextLine());
      }
    }

    return unsortednames.toArray(new String[unsortednames.size()]);
  }

  // protected to allow
  protected String[] mergeSort(String[] namesArray) {
    int listLength = namesArray.length;
    int listMiddle = listLength / 2;
    // return the given list if if length is 0 to stop recursion
    if (listLength < 2) {
      return namesArray;
    }
    // create 2 sub arrays to sort and then merge once sorted
    String[] leftHalf = new String[listMiddle];
    String[] rightHalf = new String[listLength - listMiddle];

    for (int i = 0; i < listMiddle; i++) {
      leftHalf[i] = namesArray[i];
    }
    for (int i = listMiddle; i < listLength; i++) {
      rightHalf[i - listMiddle] = namesArray[i];
    }

    // merge sort th
    mergeSort(leftHalf);
    mergeSort(rightHalf);

    // merge the two sorted arrays into the original array
    merge(namesArray, leftHalf, rightHalf, listMiddle);

    return namesArray;
  }

  private void merge(String[] namesArray, String[] leftHalf, String[] rightHalf, int middle) {
    int nameIndex = 0, leftIndex = 0, rightIndex = 0;
    while (nameIndex < namesArray.length) {
      // get the starting indexes of the last names
      int leftLastNameBeg = leftHalf[leftIndex].lastIndexOf(" ");
      int rightLastNameBeg = rightHalf[rightIndex].lastIndexOf(" ");

      String leftLastName = leftHalf[leftIndex].substring(leftLastNameBeg);
      String rightLastName = rightHalf[rightIndex].substring(rightLastNameBeg);
      // add whichever element of the sorted arrays is lower to the array
      int comparison = leftLastName.compareToIgnoreCase(rightLastName);
      if (comparison < 0) {
        namesArray[nameIndex] = leftHalf[leftIndex];
        leftIndex++;
      } else if (comparison > 0) {
        namesArray[nameIndex] = rightHalf[rightIndex];
        rightIndex++;
      } else {
        // if the last names are the same than compare the full names
        if (leftHalf[leftIndex].substring(0, leftLastNameBeg)
            .compareToIgnoreCase(rightHalf[rightIndex].substring(0, rightLastNameBeg)) < 0) {
          namesArray[nameIndex] = leftHalf[leftIndex];
          leftIndex++;
        } else {
          namesArray[nameIndex] = rightHalf[rightIndex];
          rightIndex++;
        }
      }
      nameIndex += 1;

      // if one sub array is completely added add the rest of the other subarray
      while (leftIndex == leftHalf.length && rightIndex < rightHalf.length) {
        namesArray[nameIndex] = rightHalf[rightIndex];
        rightIndex++;
        nameIndex += 1;
      }
      while (rightIndex == rightHalf.length && leftIndex < leftHalf.length) {
        namesArray[nameIndex] = leftHalf[leftIndex];
        leftIndex++;
        nameIndex += 1;
      }
    }
  }

  private void displayNames(String[] names) {
    for (int i = 0; i < names.length; i++) {
      System.out.println(names[i]);
    }
  }

  private void saveToFile(String[] sortedNames) {
    PrintWriter pw = null;
    try {
      pw = new PrintWriter("sorted-names-list.txt");
    } catch (FileNotFoundException e) {
      System.out.println("I/O error");
    }

    if (pw != null) {
      for (int i = 0; i < sortedNames.length; i++) {
        pw.println(sortedNames[i]);
      }
      pw.close();
    }
  }

  public static void main(String[] args) {
    new NameSorter(args[0]);
  }
}
