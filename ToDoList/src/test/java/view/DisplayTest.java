package view;

import static org.junit.Assert.*;

import controller.DisplayTodo;
import java.time.LocalDate;
import java.util.ArrayList;
import model.ToDoConverter;
import model.Todo;
import org.junit.Before;
import org.junit.Test;

public class DisplayTest {

  Display testDisplay;
  LocalDate testDate2020 = LocalDate.of(2020, 2, 2);
  LocalDate testDate2021 = LocalDate.of(2021, 2, 2);
  Todo todo1 = new Todo.Builder("test1").id(1).due(testDate2021).category("school")
      .priority(3).completed(true).build();
  Todo todo2 = new Todo.Builder("test2").id(2).due(testDate2020).category("home").
      priority(2).build();
  ArrayList<Todo> testTodoList = new ArrayList<>();
  DisplayTodo testDisplayPreferences = new DisplayTodo();

  @Before
  public void setUp() throws Exception {
    testTodoList.add(todo1);
    testTodoList.add(todo2);
    testDisplay = new Display(testTodoList, testDisplayPreferences);
  }

  @Test
  public void filterByIncomplete() {
    ArrayList<Todo> expectedList = new ArrayList<>();
    expectedList.add(todo2);
    testDisplay.filterByIncomplete();
    assertEquals(expectedList, testDisplay.getTodoList());
  }

  @Test
  public void filterByCategory() {
    ArrayList<Todo> expectedList = new ArrayList<>();
    expectedList.add(todo2);
    testDisplay.filterByCategory("home");
    assertEquals(expectedList, testDisplay.getTodoList());
  }

  @Test
  public void sortByPriority() {
    ArrayList<Todo> expectedList = new ArrayList<>();
    expectedList.add(todo2);
    expectedList.add(todo1);
    testDisplay.sortByPriority();
    assertEquals(expectedList, testDisplay.getTodoList());
  }

  @Test
  public void sortByDueDate() {
    ArrayList<Todo> expectedList = new ArrayList<>();
    expectedList.add(todo2);
    expectedList.add(todo1);
    testDisplay.sortByDueDate();
    assertEquals(expectedList, testDisplay.getTodoList());
  }

  @Test
  public void equals() {
    Display testDisplay2 = new Display(testTodoList, testDisplayPreferences);
    Display testDisplay3 = new Display(testTodoList, testDisplayPreferences);
    assertTrue(testDisplay.equals(testDisplay));
    assertTrue(testDisplay.equals(testDisplay2) && testDisplay2.equals(testDisplay));
    assertTrue(testDisplay.equals(testDisplay2) && testDisplay2.equals(testDisplay3) &&
        testDisplay.equals(testDisplay3));
    assertFalse(testDisplay.equals(null));
    assertTrue(testDisplay.equals(testDisplay2));
    assertTrue(testDisplay.equals(testDisplay2));
  }

  @Test
  public void hashcode() {
    Display testDisplay2 = new Display(testTodoList, testDisplayPreferences);
    assertFalse(testDisplay.hashCode() == 0);
    int testHash = testDisplay.hashCode();
    assertEquals(testHash, testDisplay.hashCode());
    assertTrue(testDisplay.equals(testDisplay2) && (testDisplay.hashCode()
        == testDisplay2.hashCode()));
  }

  @Test
  public void testToString() {
    ToDoConverter testConverter = new ToDoConverter();
    String expectedString = "Display{" +
        "todoList=" + testTodoList +
        ", converter=" + testConverter +
        ", displayData=" + testDisplayPreferences +
        '}';
    assertEquals(expectedString, testDisplay.toString());
  }
}