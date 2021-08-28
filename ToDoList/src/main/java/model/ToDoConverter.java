package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class to convert a list of strings to a todo object
 */
public class ToDoConverter {

  /**
   * Constructor for converter
   */
  public ToDoConverter() {
  }

  /**
   * Changes string rep of date to LocalDateTime obj
   * @param dateString - string representation of date
   * @return local date time obj
   */
  public LocalDate stringToDate(String dateString) {
    // dates look like 3/22/21
    String[] splitDate = dateString.split("/");
    int year = Integer.parseInt(splitDate[2]);
    int month = Integer.parseInt(splitDate[0]);
    int day = Integer.parseInt(splitDate[1]);
    LocalDateTime dateTime = LocalDateTime.of(year, month, day, 0, 0);
    return dateTime.toLocalDate();
  }

  /**
   * Changes list of lists to list of todo objects
   * @param listOfLists - list of lists of strings representing a todo
   * @return list of todos
   */
  public ArrayList<Todo> changeToListOfToDos(ArrayList<ArrayList<String>> listOfLists) {
    ArrayList<Todo> todoList = new ArrayList<>();
    // Loop through outer array list
    for (ArrayList<String> todos : listOfLists) {
      // [id, text, completed, due, priority, category]
      LocalDate tempDate;
      Boolean tempCompleted;
      Integer tempPriority;
      // Change ? marks to null
      for (int i = 0; i < 6; i++) {
        if (todos.get(i).equals("?")) {
          todos.set(i, null);
        }
      }
      Integer tempID = Integer.valueOf(todos.get(0));
      if (todos.get(2) != null) {
        tempCompleted = Boolean.valueOf(todos.get(2));
      } else {
        tempCompleted = null;
      }
      if (todos.get(4) != null) {
        tempPriority = Integer.valueOf(todos.get(4));
      } else {
        tempPriority = null;
      }
      if (todos.get(3) != null) {
        tempDate = stringToDate(todos.get(3));
      } else {
        tempDate = null;
      }
      Todo tempTodo = new Todo.Builder(todos.get(1)).id(tempID).completed(tempCompleted).due(tempDate).priority(tempPriority).category(todos.get(5)).build();
      todoList.add(tempTodo);
    }
    return todoList;
  }

  /**
   * Convert LocalDate to correct string format
   * @param date - localDate
   * @return string
   */
  public String dateToString(LocalDate date) {
    // month/date/year
    String month = String.valueOf(date.getMonthValue());
    String day = String.valueOf(date.getDayOfMonth());
    String year = String.valueOf(date.getYear());
    return month + "/" + day + "/" + year;
  }

  /**
   * Converts a list of todos to a list of lists of strings
   * @param todoList - list of todos
   * @return list of list of strings
   */
  public ArrayList<ArrayList<String>> changeToListOfLists(ArrayList<Todo> todoList) {
    String QUESTION = "?";
    ArrayList<ArrayList<String>> listOfLists = new ArrayList<>();
    for (Todo todo : todoList) {
      ArrayList<String> todoStrings = new ArrayList<>();
      // Change to list of strings
      todoStrings.add(todo.getId().toString());
      todoStrings.add(todo.getText());
      todoStrings.add(todo.getCompleted().toString());
      if (todo.getDue() == null) {
        todoStrings.add(QUESTION);
      } else {
        String dueString = dateToString(todo.getDue());
        todoStrings.add(dueString);
      }
      todoStrings.add(todo.getPriority().toString());
      if (todo.getCategory() == null) {
        todoStrings.add(QUESTION);
      } else {
        todoStrings.add(todo.getCategory());
      }
      listOfLists.add(todoStrings);
    }
    return listOfLists;
  }

  /**
   * Hash code method. All converters are the same
   * @return hashcode
   */
  @Override
  public int hashCode() {
    return 1299389;
  }

  /**
   * Equals method. All converters are the same
   * @param obj - ToDoConverter
   * @return true always
   */
  @Override
  public boolean equals(Object obj) {
    return true;
  }
}
