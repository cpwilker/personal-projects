package model;

import static org.junit.Assert.*;

import controller.CmdLineProcessor;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import model.Todo.Builder;
import org.junit.Before;
import org.junit.Test;

public class ToDoFunctionsTest {

  ToDoFunctions testToDoFunctions;
  ToDoFunctions testToDoFunctions2;
  String[] args = {"--csv-file", "test_todos.csv", "--add-todo", "--todo-text", "test", "--category",
      "test", "--complete-todo", "1"};
  String[] args2 = {"--csv-file", "test_todos.csv", "--add-todo", "--todo-text", "test2", "--category",
      "home", "--complete-todo", "1"};


  @Before
  public void setUp() throws Exception {
    CmdLineProcessor cmdLineProcessor = new CmdLineProcessor();
    CmdLineProcessor cmdLineProcessor2 = new CmdLineProcessor();
    cmdLineProcessor.processArgs(args);
    cmdLineProcessor2.processArgs(args2);
    testToDoFunctions = new ToDoFunctions(cmdLineProcessor.addToDo, cmdLineProcessor.completeTodo);
    testToDoFunctions2 = new ToDoFunctions(cmdLineProcessor2.addToDo,cmdLineProcessor2.completeTodo);
  }

  @Test
  public void getTodoList() {
    ArrayList<Todo> expectedList = new ArrayList<Todo>();
    assertEquals(expectedList, testToDoFunctions.getTodoList());
  }


  @Test
  public void manage() throws IOException {
    ArrayList<Todo> expectedList = new ArrayList<>();
    Todo todo1 = new Todo.Builder("Finish HW9").id(1)
        .due(LocalDate.of(2020, 03, 22)).priority(1).category("school")
        .completed(true).build();
    Todo todo2 = new Todo.Builder("test").id(2).priority(3).category("test").build();
    expectedList.add(todo1);
    expectedList.add(todo2);
    testToDoFunctions.manage();
    assertEquals(expectedList, testToDoFunctions.getTodoList());
  }

  @Test
  public void addToDo() {
    Todo addedToDo = testToDoFunctions.addToDo();
    assertTrue(testToDoFunctions.getTodoList().contains(addedToDo));
  }

  @Test
  public void completeToDo() {
    Todo addedToDo = testToDoFunctions.addToDo();
    assertFalse(addedToDo.getCompleted());
    testToDoFunctions.completeToDo(1);
    assertTrue(addedToDo.getCompleted());
  }

  @Test
  public void testEquals() {
    assertTrue(testToDoFunctions.equals(testToDoFunctions));
    assertFalse(testToDoFunctions.equals(testToDoFunctions2) && testToDoFunctions2
        .equals(testToDoFunctions));
    ToDoFunctions testToDoFunctions3 = testToDoFunctions;
    assertFalse(testToDoFunctions.equals(testToDoFunctions2) && testToDoFunctions2
        .equals(testToDoFunctions3) && testToDoFunctions3.equals(testToDoFunctions));
    assertFalse(testToDoFunctions.equals(null));
  }

  @Test
  public void testHashCode() {
    assertTrue(testToDoFunctions.hashCode() == testToDoFunctions.hashCode());
    assertTrue(testToDoFunctions.hashCode() != testToDoFunctions2.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("ToDoFunctions{addObject=AddToDo{flagCount=3, csvFileFlag=true, "
        + "addToDoFlag=true, toDoTxtFlag=true, pathToFile='test_todos.csv', description='test'}, "
        + "completeObject=CompleteTodo{completedFlag=false, dueFlag=false, priorityFlag=false, "
        + "categoryFlag=true, completeTodoFlag=true, dueDate=null, priorityNum=null, "
        + "setCategory='test', iDs=[1]}, headers='', todoList=[]}", testToDoFunctions.toString());
  }
}