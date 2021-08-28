package controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class CmdLineProcessorTest {
  CmdLineProcessor cmlOne;
  CmdLineProcessor cmlTwo;
  CmdLineProcessor cmlThree;
  String[] argsOne = {"--csv-file", "path"};
  String[] argsTwo = {"--csv-file", "path", "--add-todo", "--todo-text", "finish hw",
      "--completed", "--priority", "1"};
  String[] argsThree = {"--csv-file", "path", "--add-todo", "--todo-text", "finish hw",
      "--completed", "--priority", "1", "--display", "--sort-by-date "};
  String[] argsFour = {"--csv-file", "path", "--add-todo", "--todo-text", "finish hw",
      "--completed", "--priority", "1", "--display", "--sort-by-date ", "--sort-by-priority "};

  @org.junit.Before
  public void setUp() throws Exception {
    cmlOne = new CmdLineProcessor();
    cmlTwo = new CmdLineProcessor();
    cmlThree = cmlOne;
  }
  @Test
  public void processArgsTest(){
    assertTrue(cmlOne.processArgs(argsTwo));
    assertTrue(cmlTwo.processArgs(argsThree));

  }

  @Test (expected = IncorrectNumArgs.class)
  public void processArgsOne() throws CmdLineExceptions{
    cmlOne.processArgs(argsOne);
    cmlTwo.processArgs(argsFour);
  }

  @Test
  public void getAddToDo() {
    AddToDo add = new AddToDo();
    assertEquals(add, cmlOne.getAddToDo());
  }

  @Test
  public void getCompleteTodo() {
    CompleteTodo complete = new CompleteTodo();
    assertEquals(complete, cmlOne.getCompleteTodo());
  }

  @Test
  public void getDisplayTodo() {
    DisplayTodo display = new DisplayTodo();
    assertEquals(display, cmlOne.getDisplayTodo());
  }

  @org.junit.Test
  //Tests equals reflexivity
  public void testEqualsReflexivity() {
    assertTrue(cmlOne.equals(cmlOne));
  }

  @org.junit.Test
  //Tests equals symmetry
  public void testEqualsSymmetry() {
    cmlOne.processArgs(argsTwo);
    assertTrue(cmlOne.equals(cmlTwo) == cmlTwo.equals(cmlOne));
  }

  @org.junit.Test
  //Tests equals null
  public void testEqualsNull() {
    assertFalse(cmlOne.equals(null));
  }

  @org.junit.Test
  //Tests equals consistency
  public void testEqualsConsistency() {
    Boolean testResult = cmlOne.equals(cmlTwo);
    assertEquals(testResult, cmlOne.equals(cmlTwo));
  }

  @org.junit.Test
  //Tests equals transitivity
  public void testEqualsTransitivity() {
    cmlTwo = cmlOne;
    assertTrue(cmlOne.equals(cmlTwo) &&
        cmlOne.equals(cmlThree)
        && cmlOne.equals(cmlThree));
  }

  @org.junit.Test
  //Test hashcode
  public void testHashCode() {
    Integer zero = 0;
    assertFalse(zero == cmlOne.hashCode());
  }

  @org.junit.Test
  //Test hashcode consistency
  public void testHashCodeConsistency(){
    int testHashCode = cmlOne.hashCode();
    assertEquals(testHashCode, cmlOne.hashCode());
  }

  @Test
  public void testToString() {
    String answerString = "CmdLineProcessor{addToDo=AddToDo{flagCount=0, "
        + "csvFileFlag=false, addToDoFlag=false, toDoTxtFlag=false, "
        + "pathToFile='null', description='null'}, "
        + "completeTodo=CompleteTodo{completedFlag=false, dueFlag=false, "
        + "priorityFlag=false, categoryFlag=false, completeTodoFlag=false, "
        + "dueDate=null, priorityNum=null, setCategory='null', iDs=[]}, "
        + "displayTodo=DisplayTodo{displayFlag=false, showIncompleteFlag=false, "
        + "showCategoryFlag=false, sortByDateFlag=false, sortByPriorityFlag=false, "
        + "category='null'}}";
    assertEquals(answerString, cmlOne.toString());
  }
}