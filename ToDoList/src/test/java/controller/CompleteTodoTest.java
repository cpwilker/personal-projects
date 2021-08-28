package controller;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class CompleteTodoTest {
  CompleteTodo comOne;
  CompleteTodo comTwo;
  CompleteTodo comThree;
  String[] argsOne = {"--completed", "--due"};
  String[] argsTwo = {"--completed", "--due", "1111-11-11 11:11"};
  String[] argsThree = {"--completed", "--due", "date"};
  String[] argsFour = {"--priority", "4"};
  String[] argsFive = {"--priority", "2"};
  String[] argsSix = {"--category"};
  String[] argsSeven = {"--category", "cat1"};
  String[] argsEight = {"--complete-todo", "23"};


  @Before
  public void setUp() throws Exception {
    comOne = new CompleteTodo();
    comTwo = new CompleteTodo();
    comThree = comOne;
  }

  @Test
  public void validateFlag() {
    assertTrue(comOne.validateFlag(argsTwo));
    assertTrue(comOne.validateFlag(argsEight));

  }

  @Test (expected = IncorrectPath.class)
  public void validateFlagTwo() throws CmdLineExceptions{
    comOne.validateFlag(argsOne);
    comOne.validateFlag(argsThree);
    comOne.validateFlag(argsFour);
    comOne.validateFlag(argsSix);
  }

  @Test
  public void setsOptions() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime dateTime = LocalDateTime.parse("1111-11-11 11:11", formatter);
    comOne.setsOptions(argsTwo);
    assertTrue(comOne.getCompletedFlag());
    assertEquals(dateTime, comOne.getDueDate());
  }


  @Test
  public void setCompletedFlag() {
    comOne.setsOptions(argsTwo);
    assertTrue(comOne.getCompletedFlag());
  }

  @Test
  public void setDueFlag() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime dateTime = LocalDateTime.parse("1111-11-11 11:11", formatter);
    comOne.setsOptions(argsTwo);
    assertEquals( dateTime, comOne.getDueDate());
  }

  @Test
  public void setPriorityFlag() {
    comOne.setsOptions(argsFive);
    assertTrue(comOne.priorityFlag);
  }

  @Test
  public void setCategoryFlag() {
    comOne.setsOptions(argsSeven);
    assertTrue(comOne.categoryFlag);
  }

  @Test
  public void setCompleteTodoFlag() {
    comOne.setsOptions(argsEight);
    assertTrue(comOne.getCompleteTodoFlag());
  }

  @Test
  public void setDueDate() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime dateTime = LocalDateTime.parse("1111-11-11 11:11", formatter);
    comOne.setsOptions(argsTwo);
    assertEquals(dateTime, comOne.getDueDate());
  }

  @Test
  public void setPriorityNum() {
    comOne.setsOptions(argsFive);
    Integer answer = 2;
    assertEquals(answer, comOne.getPriorityNum());
  }

  @Test
  public void setCategory() {
    comOne.setsOptions(argsSeven);
    String answer = "cat1";
    assertEquals(answer, comOne.getSetCategory());
  }

  @Test
  public void getCompletedFlag() {
    comOne.setsOptions(argsTwo);
    assertTrue(comOne.getCompletedFlag());
  }

  @Test
  public void getCompleteTodoFlag() {
    comOne.setsOptions(argsEight);
    assertTrue(comOne.getCompleteTodoFlag());
  }

  @Test
  public void getDueDate() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime dateTime = LocalDateTime.parse("1111-11-11 11:11", formatter);
    comOne.setsOptions(argsTwo);
    assertEquals(dateTime, comOne.getDueDate());
  }

  @Test
  public void getPriorityNum() {
    comOne.setsOptions(argsFive);
    Integer answer = 2;
    assertEquals(answer, comOne.getPriorityNum());
  }

  @Test
  public void getSetCategory() {
    comOne.setsOptions(argsSeven);
    String answer = "cat1";
    assertEquals(answer, comOne.getSetCategory());
  }

  @Test
  public void getIDs() {
    ArrayList<Integer> answer = new ArrayList<>();
    answer.add(23);
    comOne.setsOptions(argsEight);
    assertEquals(answer, comOne.getIDs());
  }

  @org.junit.Test
  //Tests equals reflexivity
  public void testEqualsReflexivity() {
    assertTrue(comOne.equals(comOne));
  }

  @org.junit.Test
  //Tests equals symmetry
  public void testEqualsSymmetry() {
    assertTrue(comOne.equals(comTwo) == comTwo.equals(comOne));
  }

  @org.junit.Test
  //Tests equals null
  public void testEqualsNull() {
    assertFalse(comOne.equals(null));
  }

  @org.junit.Test
  //Tests equals consistency
  public void testEqualsConsistency() {
    Boolean testResult = comOne.equals(comTwo);
    assertEquals(testResult, comOne.equals(comTwo));
  }

  @org.junit.Test
  //Tests equals transitivity
  public void testEqualsTransitivity() {
    comTwo = comOne;
    assertTrue(comOne.equals(comTwo) &&
        comOne.equals(comThree)
        && comOne.equals(comThree));
  }

  @org.junit.Test
  //Test hashcode
  public void testHashCode() {
    Integer zero = 0;
    assertFalse(zero == comOne.hashCode());
  }

  @org.junit.Test
  //Test hashcode consistency
  public void testHashCodeConsistency(){
    int testHashCode = comOne.hashCode();
    assertEquals(testHashCode, comOne.hashCode());
  }

  @Test
  public void testToString() {
    String answerString = "CompleteTodo{completedFlag=false, dueFlag=false, "
        + "priorityFlag=false, categoryFlag=false, completeTodoFlag=false, "
        + "dueDate=null, priorityNum=null, setCategory='null', iDs=[]}";
    assertEquals(answerString, comOne.toString());
  }
}