package view;

import controller.DisplayTodo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import model.ToDoConverter;
import model.Todo;

/**
 * Class to display todos in filtered and sorted fashions.
 */
public class Display {

  private final ArrayList<Todo> todoList;
  private final ToDoConverter converter;
  private final DisplayTodo displayData;
  private static final String printFormat = "|  %-5s |  %-40s |  %-10s |  %-12s |  %-10s |  %-15s|\n";

  /**
   * Constructor for Display object
   * @param todoList - list of todo objects to display
   * @param displayData - DisplayToDo object from controller holding data
   */
  public Display(ArrayList<Todo> todoList, DisplayTodo displayData) {
    this.displayData = displayData;
    this.todoList = todoList;
    this.converter = new ToDoConverter();
  }

  /**
   * @return todolist
   */
  public ArrayList<Todo> getTodoList() {
    return this.todoList;
  }

  /**
   * Display todos in array list (filtered and sorted already)
   */
  public void printDisplay() {
    ArrayList<ArrayList<String>> todoStrings = this.converter.changeToListOfLists(this.todoList);
    displayHeaders();
    System.out.println("\n");
    for (ArrayList<String> currentToDo : todoStrings) {
      System.out.format(printFormat, currentToDo.get(0), currentToDo.get(1), currentToDo.get(2),
          currentToDo.get(3), currentToDo.get(4), currentToDo.get(5));
    }
  }

  /**
   * Uses user data to call necessary sort and filter functions. Calls print function
   * to display the data
   */
  public void displayTodos() {
    if (this.displayData.getShowIncompleteFlag()) {
      filterByIncomplete();
    }
    if (this.displayData.getShowCategoryFlag()) {
      filterByCategory(this.displayData.getCategory());
    }
    if (this.displayData.getSortByPriorityFlag()) {
      sortByPriority();
    }
    else if (this.displayData.getSortByDateFlag()) {
      sortByDueDate();
    }
    printDisplay();
  }

  /**
   * Filter out all todos that are already complete
   */
  public void filterByIncomplete() {
    this.todoList.removeIf(Todo::getCompleted);
    if (this.todoList.size() == 0) {
      System.out.println("No Incomplete ToDos: Nothing to display");
    }
  }

  /**
   * Filter out all todos that are not matching the given category
   * @param category - category to filter on
   */
  public void filterByCategory(String category) {
    this.todoList.removeIf(todoObject -> todoObject.getCategory() == null);
    this.todoList.removeIf(todoObject -> !todoObject.getCategory().equals(category));
    if (this.todoList.size() == 0) {
      System.out.println("No ToDos of that category: Nothing to display");
    }
  }

  /**
   * Sort ArrayList of Todos by priority
   */
  public void sortByPriority() {
    boolean sorted = false;
    while (!sorted) {
      sorted = true;
      for (int i = 0; i < this.todoList.size() - 1; i++) {
        if (this.todoList.get(i).getPriority() > this.todoList.get(i+1).getPriority()) {
          Collections.swap(this.todoList, i, i+1);
          sorted = false;
        }
      }
    }
  }

  /**
   * Sort ArrayList of Todos by due date
   * Todos with no due date specified come last
   */
  public void sortByDueDate() {
    boolean sorted = false;
    while (!sorted) {
      sorted = true;
      for (int i = 0; i < this.todoList.size() - 1; i++) {
        if (this.todoList.get(i).compareTo(this.todoList.get(i+1)) > 0) {
          Collections.swap(this.todoList, i, i+1);
          sorted = false;
        }
      }
    }
  }

  /**
   * Displays the headers of the csv file
   */
  private void displayHeaders() {
    System.out.format(printFormat, "id", "text", "completed", "due", "priority", "category");
  }

  /**
   * Equals method
   * @param o - display object
   * @return true if equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Display display = (Display) o;
    return Objects.equals(todoList, display.todoList) && Objects
        .equals(converter, display.converter) && Objects
        .equals(displayData, display.displayData);
  }

  /**
   * hashcode method
   * @return hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(todoList, converter, displayData);
  }

  /**
   * ToString method
   * @return string
   */
  @Override
  public String toString() {
    return "Display{" +
        "todoList=" + todoList +
        ", converter=" + converter +
        ", displayData=" + displayData +
        '}';
  }
}
