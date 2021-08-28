package model;

import controller.AddToDo;
import controller.CompleteTodo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class turns CSV into ArrayList when instantiated, preforms necessary updates on arraylist,
 * then has a method to write the ArrayList back over the csv when done updating.
 */
public class ToDoFunctions {

  private final AddToDo addObject;
  private final CompleteTodo completeObject;
  private String headers;
  private ArrayList<Todo> todoList;
  private final ToDoConverter converter;
  private static final Integer EVEN = 2;

  /**
   * Constructor for ToDoFunctions object
   * @param addObject - AddToDo object from controller holding data
   * @param completeObject - CompleteToDo object from controller holding data
   */
  public ToDoFunctions(AddToDo addObject, CompleteTodo completeObject) {
    this.addObject = addObject;
    this.completeObject = completeObject;
    this.todoList = new ArrayList<>();
    this.headers = "";
    this.converter = new ToDoConverter();
  }

  /**
   * @return todolist
   */
  public ArrayList<Todo> getTodoList() {
    return this.todoList;
  }

  /**
   * Method to read given csv file, create list of todos, preform necessary updates on said list,
   * and write back to the csv
   */
  public void manage() {
    csvToArrayList();
    if (this.addObject.getAddToDoFlag()) {
      addToDo();
    }
    if (this.completeObject.getCompleteTodoFlag()) {
      for (Integer id : this.completeObject.getIDs()) {
         completeToDo(id);
      }
    }
    overwriteCSV();
  }

  /**
   * Helper method to read data from csv to list of todos
   */
  private void csvToArrayList() {
    ArrayList<ArrayList<String>> todoListOfLists = new ArrayList<>();
    String firstLine;
    try {
      BufferedReader br = new BufferedReader(new FileReader(this.addObject.getPathToFile()));
      // Put headers in as keys
      if ((firstLine = br.readLine()) != null) {
        this.headers = firstLine;
        /// We don't need the headers because we can assume the same each time
        String nextLine;
        while ((nextLine = br.readLine()) != null) {
          String[] splitResult = nextLine.split("\"");
          // Each row is it's own list of strings
          ArrayList<String> row = new ArrayList<>();
          // Even i is a comma
          for (int i = 1; i < splitResult.length; i++) {
            if (i % EVEN == 1) {
              // Put row into it's own list. List within list
              // id, text, completed, due, priority, category
              row.add(splitResult[i]);
            }
          }
          // Append arraylist to arraylist
          todoListOfLists.add(row);
        }
      }
      // Turn list of lists into list of todos and store in constructor
      this.todoList = this.converter.changeToListOfToDos(todoListOfLists);
      try {
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates a new To Do object and adds it to the list
   * @return Todo object
   */
  public Todo addToDo() {
    // assign new to do object a new id
    int totalToDo = 0;
    for (Todo todoObjects: todoList) {
      totalToDo++;
    }
    int newId = totalToDo + 1;
    // build
    LocalDate dueDate;
    if (completeObject.getDueDate() != null) {
      dueDate = LocalDate.from(completeObject.getDueDate());
    } else {
      dueDate = null;
    }
    Todo tempTodo = new Todo.Builder(addObject.getDescription()).id(newId)
        .completed(completeObject.getCompletedFlag()).priority(completeObject.getPriorityNum())
        .due(dueDate).category(completeObject.getSetCategory()).build();
    this.todoList.add(tempTodo);
    return tempTodo;
  }

  /**
   * Sets a To Do object's completion given its id number.
   * @param id id of the completed To Do object. Integer
   */
  public void completeToDo(Integer id) {
    for (Todo todoObjects: todoList) {
      // get to do object's id
      if (todoObjects.getId().equals(id)) {
        // set complete to true
        todoObjects.setCompleted();
      }
    }
  }

  /**
   * Helper method to overwrite a csv file with list of todos
   */
  private void overwriteCSV() {
    // Clear file and overwrite
    try {
      FileWriter fw = new FileWriter(this.addObject.getPathToFile(), false);
      BufferedWriter bw = new BufferedWriter(fw);
      // write headers back in
      bw.write(this.headers);
      // Build each list in todoList to be " " and ,
      // for each list in arrayList todoList, add " " back around each string
      ArrayList<ArrayList<String>> todoListOfLists =
          this.converter.changeToListOfLists(this.getTodoList());
      for (ArrayList<String> currentToDo : todoListOfLists) {
        bw.newLine();
        // Looking at one todoObj
        StringBuilder oneLine = new StringBuilder();
        for (String s : currentToDo) {
          oneLine.append("\"");
          oneLine.append(s);
          oneLine.append("\"");
          oneLine.append(",");
        }
        // extra comma on end of string
        oneLine.deleteCharAt(oneLine.length() - 1);
        // write this line to csv file
        bw.write(oneLine.toString());
      }
      try {
        bw.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ToDoFunctions that = (ToDoFunctions) o;
    return Objects.equals(addObject, that.addObject) && Objects
        .equals(completeObject, that.completeObject) && Objects
        .equals(headers, that.headers) && Objects.equals(todoList, that.todoList)
        && Objects.equals(converter, that.converter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(addObject, completeObject, headers, todoList, converter);
  }

  @Override
  public String toString() {
    return "ToDoFunctions{" +
        "addObject=" + addObject +
        ", completeObject=" + completeObject +
        ", headers='" + headers + '\'' +
        ", todoList=" + todoList +
        '}';
  }
}
