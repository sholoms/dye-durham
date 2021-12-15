package dye.durham;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NameSorterTest {

  // test a regular case with no double names and same capitalization
  @Test
  void testMergeSort() {
    String[] unsorted = { "Janet Parsons", "Vaughn Lewis", "Adonis Julius Archer", "Shelby Nathan Yoder" };
    String[] sorted = { "Adonis Julius Archer", "Vaughn Lewis", "Janet Parsons", "Shelby Nathan Yoder" };
    NameSorter merger = new NameSorter();
    Assertions.assertArrayEquals(sorted, merger.mergeSort(unsorted));
  }

  // test where there are multiple of the same last name
  @Test
  void testMergeSortSameLastName() {
    String[] unsortedSameLast = { "John Gardner", "Ashley Ann Gardner", "Ashley Gardner" };
    String[] sortedSameLast = { "Ashley Gardner", "Ashley Ann Gardner", "John Gardner" };
    NameSorter merger = new NameSorter();
    Assertions.assertArrayEquals(sortedSameLast, merger.mergeSort(unsortedSameLast));
  }

  // check that it sorts correctly when last names are the same and first names
  // are the same with different numbers of names
  @Test
  void testMergeSortfirstNames() {
    String[] unsortedDiffCapitalisation = { "Janet Parsons", "Vaughn Lewis", "adonis Julius archer",
        "shelby Nathan aoder", "leo Gardner", "Ashley Gardner" };
    String[] sortedDiffCapitalisation = { "shelby Nathan aoder", "adonis Julius archer", "Ashley Gardner",
        "leo Gardner", "Vaughn Lewis", "Janet Parsons" };

    NameSorter merger = new NameSorter();
    Assertions.assertArrayEquals(sortedDiffCapitalisation, merger.mergeSort(unsortedDiffCapitalisation));
  }

  // test where names have different capitalizations
  @Test
  void testMergeSortCaseInsensitive() {
    String[] unsortedDiffCapitalisation = { "Janet Parsons", "Vaughn Lewis", "adonis Julius archer",
        "shelby Nathan aoder", "leo Gardner", "Ashley Gardner" };
    String[] sortedDiffCapitalisation = { "shelby Nathan aoder", "adonis Julius archer", "Ashley Gardner",
        "leo Gardner", "Vaughn Lewis", "Janet Parsons" };

    NameSorter merger = new NameSorter();
    Assertions.assertArrayEquals(sortedDiffCapitalisation, merger.mergeSort(unsortedDiffCapitalisation));
  }

}
