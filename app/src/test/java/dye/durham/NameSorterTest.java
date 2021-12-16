package dye.durham;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NameSorterTest {
  // test a regular case with no double names and same capitalization
  private NameSorter merger;

  @BeforeEach
  void createMerger() {
    merger = new NameSorter();
  }


  @Test
  void testMergeSort() {
    String[] unsorted = { "Janet Parsons", "Vaughn Lewis", "Adonis Julius Archer", "Shelby Nathan Yoder" };
    String[] sorted = { "Adonis Julius Archer", "Vaughn Lewis", "Janet Parsons", "Shelby Nathan Yoder" };
    String message = "Should sort names by last name alphabetically";
    Assertions.assertArrayEquals(sorted, this.merger.mergeSort(unsorted), message);
  }

  // test where there are multiple of the same last name
  @Test
  void testMergeSortSameLastName() {
    String[] unsortedSameLast = { "John Gardner", "Ashley Ann Gardner", "Ashley Gardner" };
    String[] sortedSameLast = { "Ashley Gardner", "Ashley Ann Gardner", "John Gardner" };
    String message = "Should sort names by given names when last names are the same";
    Assertions.assertArrayEquals(sortedSameLast, this.merger.mergeSort(unsortedSameLast), message);
  }

  // check that it sorts correctly when last names are the same and first names
  // are the same with different numbers of given names
  @Test
  void testMergeSortfirstNames() {
    String[] unsortedDiffCapitalisation = { "Janet Parsons", "Vaughn Lewis", "adonis Julius archer",
        "shelby Nathan aoder", "leo Gardner", "Ashley Gardner" };
    String[] sortedDiffCapitalisation = { "shelby Nathan aoder", "adonis Julius archer", "Ashley Gardner",
        "leo Gardner", "Vaughn Lewis", "Janet Parsons" };
    String message = "Should sort names correctly when there are different numbers of given names";
    Assertions.assertArrayEquals(sortedDiffCapitalisation, this.merger.mergeSort(unsortedDiffCapitalisation), message);
  }

  // test where names have different capitalizations
  @Test
  void testMergeSortCaseInsensitive() {
    String[] unsortedDiffCapitalisation = { "Janet Parsons", "Vaughn Lewis", "adonis Julius archer",
        "shelby Nathan aoder", "leo Gardner", "Ashley Gardner" };
    String[] sortedDiffCapitalisation = { "shelby Nathan aoder", "adonis Julius archer", "Ashley Gardner",
        "leo Gardner", "Vaughn Lewis", "Janet Parsons" };
    String message = "Should sort names correcty regardless of capitilization";
    Assertions.assertArrayEquals(sortedDiffCapitalisation, this.merger.mergeSort(unsortedDiffCapitalisation), message);
  }

}
