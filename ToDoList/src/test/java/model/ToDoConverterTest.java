package model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class ToDoConverterTest {

  ToDoConverter testTodoConverter;
  LocalDate testDate = LocalDate.of(2020, 4, 24);
  String testString = "4/24/2020";
  Todo testTodo = new Todo.Builder("test").due(testDate).id(1).build();
  ArrayList<String> list = new ArrayList();
  ArrayList<ArrayList<String>> testList = new ArrayList<>();
  ArrayList<Todo> testTodoList = new ArrayList<>();


  @Before
  public void setUp() throws Exception {
    testTodoConverter = new ToDoConverter();
    list.add("1");
    list.add("test");
    list.add("false");
    list.add(testString);
    list.add("3");
    list.add("?");
    testList.add(list);
    testTodoList.add(testTodo);
  }

  @Test
  public void stringToDate() {
    assertEquals(testDate, testTodoConverter.stringToDate(testString));
  }

  @Test
  public void changeToListOfToDos() {
    assertEquals(testTodoList, testTodoConverter.changeToListOfToDos(testList));
  }

  @Test
  public void dateToString() {
    assertEquals(testString, testTodoConverter.dateToString(testDate));
  }

  @Test
  public void changeToListOfLists() {
    assertEquals(testList, testTodoConverter.changeToListOfLists(testTodoList));
  }
}