package model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import model.Todo.Builder;
import org.junit.Before;
import org.junit.Test;

public class TodoTest {
  private Todo testToDo;
  private Todo testToDo2;
  private Builder testBuilder;
  private Builder testBuilder2;
  private String text = "mop kitchen";
  private String text2 = "minimal inputs";
  private Integer id = 2;
  private Integer priority = 1;
  private LocalDate dueDate = LocalDate.of(2021,4,24);
  private LocalDate dueDate2020 = LocalDate.of(2020, 4, 24);
  private String category = "home";
  private Boolean completed = false;

  @Before
  public void setUp() throws Exception {
    testToDo = new Todo.Builder(text).id(id).completed(completed).due(dueDate).priority(priority)
        .category(category).build();
    testToDo2 = new Todo.Builder(text2).build();
    testBuilder = new Builder(text).id(id).completed(completed).due(dueDate).priority(priority)
        .category(category);
    testBuilder2 = new Builder(text2);
  }

  @Test
  public void setCompleted() {
    testToDo.setCompleted();
    assertTrue(testToDo.getCompleted());
  }

  @Test
  public void getId() {
    assertEquals(Integer.valueOf(2),testToDo.getId());
    assertNull(testToDo2.getId());
  }

  @Test
  public void getText() {
    assertEquals(text,testToDo.getText());
  }

  @Test
  public void getCompleted() {
    assertFalse(testToDo.getCompleted());
    assertFalse(testToDo2.getCompleted());
  }

  @Test
  public void getDue() {
    assertEquals(dueDate,testToDo.getDue());
    assertNull(testToDo2.getDue());
  }

  @Test
  public void getPriority() {
    assertEquals(priority,testToDo.getPriority());
    assertEquals(Integer.valueOf(3),testToDo2.getPriority());
  }

  @Test
  public void getCategory() {
    assertEquals(category,testToDo.getCategory());
    assertNull(testToDo2.getCategory());
  }

  @Test
  public void testEquals() {
    assertTrue(testToDo.equals(testToDo));
    assertTrue(testBuilder.equals(testBuilder));
    assertFalse(testToDo.equals(testToDo2) && testToDo2.equals(testToDo));
    assertFalse(testBuilder.equals(testBuilder2) && testBuilder2.equals(testBuilder));
    Todo testTodo3 = testToDo;
    Builder testBuilder3 = testBuilder;
    assertFalse(testToDo.equals(testToDo2) && testToDo2.equals(testTodo3) &&
        testToDo.equals(testTodo3));
    assertFalse(testBuilder.equals(testBuilder2) && testBuilder2.equals(testBuilder3) &&
        testBuilder.equals(testBuilder3));
    assertFalse(testToDo.equals(null));
    assertFalse(testBuilder.equals(null));
  }

  @Test
  public void testHashCode() {
    assertEquals(testToDo.hashCode(), testToDo.hashCode());
    assertEquals(testBuilder.hashCode(),testBuilder.hashCode());
    assertNotEquals(testToDo.hashCode(), testToDo2.hashCode());
    assertNotEquals(testBuilder.hashCode(), testBuilder2.hashCode());

  }

  @Test
  public void testToString() {
    assertEquals("Todo{id=null, text='minimal inputs', completed=false, due=null, "
        + "priority=3, category='null'}",testToDo2.toString());
    assertEquals("Builder{id=null, text='minimal inputs', completed=false, due=null, "
        + "priority=3, category='null'}",testBuilder2.toString());
    assertEquals("Todo{id=2, text='mop kitchen', completed=false, due=2021-04-24, "
        + "priority=1, category='home'}", testToDo.toString());
  }

  @Test
  public void compareTo() {
    Todo todoNull = new Todo.Builder("test").build();
    Todo todo2020 = new Todo.Builder("test1").due(dueDate2020).build();
    Todo todo2021 = new Todo.Builder("test2").due(dueDate).build();
    assertEquals(0, todo2021.compareTo(todo2021));
    assertEquals(0, todoNull.compareTo(todoNull));
    assertEquals(1, todoNull.compareTo(todo2020));
    assertEquals(-1, todo2020.compareTo(todoNull));
    assertEquals(-1, todo2020.compareTo(todo2021));
    assertEquals(1, todo2021.compareTo(todo2020));
  }
}