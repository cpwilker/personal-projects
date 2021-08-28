package controller;

import static org.junit.Assert.*;

import org.graalvm.compiler.core.common.type.ArithmeticOpTable.BinaryOp.Add;
import org.junit.Before;
import org.junit.Test;

public class AddToDoTest {
  AddToDo addOne;
  AddToDo addTwo;
  AddToDo addThree;
  String[] argsOne = {"--csv-file", "path"};
  String[] argsTwo = {"--csv-file"};
  String[] argsThree = {"--add-todo"};
  String[] argsFour = {"--csv-file", "path", "--add-todo", "--todo-text" , "finished hw"};


  @Before
  public void setUp() throws Exception {
    addOne = new AddToDo();
    addTwo = new AddToDo();
    addThree = addOne;
  }

  @Test
  public void validateFlag() {
    assertTrue(addOne.validateFlag(argsOne));
    assertTrue(addTwo.validateFlag(argsFour));
  }

  @Test (expected = IncorrectPath.class)
  public void validateFlagTwo() throws CmdLineExceptions{
    addOne.validateFlag(argsOne);
    addOne.validateFlag(argsTwo);
    addOne.validateFlag(argsThree);
  }

  @Test
  public void setsOptions() {
    addTwo.validateFlag(argsFour);
    assertTrue(addTwo.getCsvFileFlag());
    assertTrue(addTwo.getAddToDoFlag());
    assertTrue(addTwo.getToDoTxtFlag());
    assertEquals("path", addTwo.getPathToFile());
    assertEquals("finished hw", addTwo.getDescription());
  }

  @Test
  public void csvFileFlag() {
    addTwo.setCsvFileFlag(Boolean.TRUE);
    assertTrue(addTwo.getCsvFileFlag());
  }

  @Test
  public void setAddToDoFlag() {
    addTwo.setAddToDoFlag(Boolean.TRUE);
    assertTrue(addTwo.getAddToDoFlag());
  }

  @Test
  public void setToDoTxtFlag() {
    addTwo.setToDoTxtFlag(Boolean.TRUE);
    assertTrue(addTwo.getToDoTxtFlag());
  }

  @Test
  public void setPathToFile() {
    addTwo.setPathToFile("path");
    String ans = "path";
    assertEquals(ans, addTwo.getPathToFile());
  }

  @Test
  public void setDescription() {
    addTwo.setDescription("finish hw");
    String ans = "finish hw";
    assertEquals(ans, addTwo.getDescription());
  }

  @org.junit.Test
  //Tests equals reflexivity
  public void testEqualsReflexivity() {
    assertTrue(addOne.equals(addOne));
  }

  @org.junit.Test
  //Tests equals symmetry
  public void testEqualsSymmetry() {
    assertTrue(addOne.equals(addTwo) == addTwo.equals(addOne));
  }

  @org.junit.Test
  //Tests equals null
  public void testEqualsNull() {
    assertFalse(addOne.equals(null));
  }

  @org.junit.Test
  //Tests equals consistency
  public void testEqualsConsistency() {
    Boolean testResult = addOne.equals(addTwo);
    assertEquals(testResult, addOne.equals(addTwo));
  }

  @org.junit.Test
  //Tests equals transitivity
  public void testEqualsTransitivity() {
    addTwo = addOne;
    assertTrue(addOne.equals(addTwo) &&
        addOne.equals(addThree)
        && addOne.equals(addThree));
  }

  @org.junit.Test
  //Test hashcode
  public void testHashCode() {
    Integer zero = 0;
    assertFalse(zero == addOne.hashCode());
  }

  @org.junit.Test
  //Test hashcode consistency
  public void testHashCodeConsistency(){
    int testHashCode = addOne.hashCode();
    assertEquals(testHashCode, addOne.hashCode());
  }

  @Test
  public void testToString() {
    String answerString = "AddToDo{flagCount=0, csvFileFlag=false, addToDoFlag=false, "
        + "toDoTxtFlag=false, pathToFile='null', description='null'}";
    assertEquals(answerString, addOne.toString());
  }
}