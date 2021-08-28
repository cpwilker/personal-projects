package controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DisplayTodoTest {
  DisplayTodo displayOne;
  DisplayTodo displayTwo;
  DisplayTodo displayThree;
  String[] argsOne = {"--display", "--show-incomplete"};
  String[] argsTwo = {"--show-incomplete"};
  String[] argsThree = {"--display", "--sort-by-date", "--sort-by-priority"};
  String[] argsFour = {"--display", "--show-category", "cat1", "--sort-by-priority"};
  String[] argsFive = {"--display", "--show-category", "--sort-by-priority"};

  @Before
  public void setUp() throws Exception {
    displayOne = new DisplayTodo();
    displayTwo = new DisplayTodo();
    displayThree = displayOne;
  }

  @Test
  public void validateFlagOne() throws CmdLineExceptions{
    assertTrue(displayOne.validateFlag(argsOne));
  }

  @Test (expected = IncorrectFlag.class)
  public void validateFlagTwo() throws CmdLineExceptions{
    displayOne.validateFlag(argsTwo);
    displayOne.validateFlag(argsThree);
  }

  @Test
  public void setsOptionsOne() {
    displayOne.setsOptions(argsOne);
    assertTrue(displayOne.getDisplayFlag());
    assertTrue(displayOne.getShowIncompleteFlag());
    assertFalse(displayOne.getShowCategoryFlag());
    assertFalse(displayOne.getSortByDateFlag());
    assertFalse(displayOne.getSortByPriorityFlag());
  }

  @Test (expected = IncorrectPath.class)
  public void setsOptionsTwo() {
    displayOne.setsOptions(argsFive);
  }

  @Test
  public void setDisplayFlag() {
    displayOne.setsOptions(argsOne);
    assertTrue(displayOne.getDisplayFlag());
  }

  @Test
  public void setShowIncompleteFlag() {
    displayOne.setsOptions(argsOne);
    assertTrue(displayOne.getShowIncompleteFlag());
  }

  @Test
  public void setShowCategoryFlag() {
    displayOne.setsOptions(argsFour);
    assertTrue(displayOne.getShowCategoryFlag());
  }

  @Test
  public void setSortByDateFlag() {
    displayOne.setsOptions(argsThree);
    assertTrue(displayOne.getSortByDateFlag());
  }

  @Test
  public void setSortByPriorityFlag() {
    displayOne.setsOptions(argsThree);
    assertTrue(displayOne.getSortByPriorityFlag());
  }

  @Test
  public void setCategory() {
    displayOne.setsOptions(argsFour);
    assertEquals("cat1", displayOne.getCategory());
  }

  @Test
  public void getDisplayFlag() {
    displayOne.setsOptions(argsOne);
    assertTrue(displayOne.getDisplayFlag());
  }

  @Test
  public void getShowIncompleteFlag() {
    displayOne.setsOptions(argsOne);
    assertTrue(displayOne.getShowIncompleteFlag());
  }

  @Test
  public void getShowCategoryFlag() {
    displayOne.setsOptions(argsFour);
    assertTrue(displayOne.getShowCategoryFlag());
  }

  @Test
  public void getSortByDateFlag() {
    displayOne.setsOptions(argsThree);
    assertTrue(displayOne.getSortByDateFlag());
  }

  @Test
  public void getSortByPriorityFlag() {
    displayOne.setsOptions(argsThree);
    assertTrue(displayOne.getSortByPriorityFlag());
  }

  @Test
  public void getCategory() {
    displayOne.setsOptions(argsFour);
    assertEquals("cat1", displayOne.getCategory());
  }

  @org.junit.Test
  //Tests equals reflexivity
  public void testEqualsReflexivity() {
    assertTrue(displayOne.equals(displayOne));
  }

  @org.junit.Test
  //Tests equals symmetry
  public void testEqualsSymmetry() {
    assertTrue(displayOne.equals(displayTwo) == displayTwo.equals(displayOne));
  }

  @org.junit.Test
  //Tests equals null
  public void testEqualsNull() {
    assertFalse(displayOne.equals(null));
  }

  @org.junit.Test
  //Tests equals consistency
  public void testEqualsConsistency() {
    Boolean testResult = displayOne.equals(displayTwo);
    assertEquals(testResult, displayOne.equals(displayTwo));
  }

  @org.junit.Test
  //Tests equals transitivity
  public void testEqualsTransitivity() {
    displayTwo = displayOne;
    assertTrue(displayOne.equals(displayTwo) &&
        displayOne.equals(displayThree)
        && displayOne.equals(displayThree));
  }

  @org.junit.Test
  //Test hashcode
  public void testHashCode() {
    Integer zero = 0;
    assertFalse(zero == displayOne.hashCode());
  }

  @org.junit.Test
  //Test hashcode consistency
  public void testHashCodeConsistency(){
    int testHashCode = displayOne.hashCode();
    assertEquals(testHashCode, displayOne.hashCode());
  }

  @Test
  public void testToString() {
    String answerString = "DisplayTodo{displayFlag=false, showIncompleteFlag=false, "
        + "showCategoryFlag=false, sortByDateFlag=false, sortByPriorityFlag=false, "
        + "category='null'}";
    assertEquals(answerString, displayOne.toString());
  }
}