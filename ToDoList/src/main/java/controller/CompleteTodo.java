package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.io.*;


/**
 * Class that represents a CompleteTodo
 */
public class CompleteTodo extends Options {
  private static final String completed = "--completed";
  private static final String due = "--due";
  private static final String priority = "--priority";
  private static final String category = "--category";
  private static final String completeToDo = "--complete-todo";
  private static final Integer minPriority = 1;
  private static final Integer maxPriority = 3;
  Boolean completedFlag = Boolean.FALSE;
  Boolean dueFlag = Boolean.FALSE;
  Boolean priorityFlag = Boolean.FALSE;
  Boolean categoryFlag = Boolean.FALSE;
  Boolean completeTodoFlag = Boolean.FALSE;
  LocalDateTime dueDate;
  Integer priorityNum;
  String setCategory;
  ArrayList<Integer> iDs = new ArrayList<>();

  /**
   * Constructor for the CompleteTodo class
   */
  public CompleteTodo() {
  }

  /**
   * Validates that the given flags are correct
   * @param args - command line arguments
   * @return True if all the given flags and paths are correct for completing a ToDo
   * @throws CmdLineExceptions if options given are incorrect
   */
  @Override
  public Boolean validateFlag(String[] args){
    this.setsOptions(args);
    return Boolean.TRUE;
  }

  /**
   * Sets the flags and paths given on the command line
   * @param args - given command line arguments
   */
  @Override
  public void setsOptions(String[] args) throws CmdLineExceptions{
    Integer i;
    for (i = 0; i < args.length; i++){
      String cmdLower = args[i].toLowerCase();
      if (cmdLower.equals(completed)){
        this.setCompletedFlag(Boolean.TRUE);
      }
      if (cmdLower.equals(due)){
        this.setDueFlag(Boolean.TRUE);
        checkPath(args, i);
        try {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
          LocalDateTime dateTime = LocalDateTime.parse(args[i+1], formatter);
          this.setDueDate(dateTime);
        }
        catch (Exception e){
          throw new IncorrectPath("Please enter due date in form yyyy-MM-dd HH:mm.\n");
        }
      }
      if (cmdLower.equals(priority)){
        this.setPriorityFlag(Boolean.TRUE);
        checkPath(args, i);
        int intPriority = Integer.parseInt(args[i+1]);
        if (intPriority > maxPriority || intPriority < minPriority){
          throw new IncorrectPath("\nPriority must be 1, 2, or 3.\n");
        }
        this.setPriorityNum(intPriority);
      }
      if (cmdLower.equals(category)){
        this.setCategoryFlag(Boolean.TRUE);
        checkPath(args, i);
        this.setCategory(args[i+1]);
      }
      if (cmdLower.equals(completeToDo)){
        this.setCompleteTodoFlag(Boolean.TRUE);
        checkPath(args, i);
        // Convert ID to Integer
        int intID = Integer.parseInt(args[i+1]);
        iDs.add(intID);
      }
    }
  }

  /**
   * Sets the completedFlag
   * @param completedFlag - True if the completedFlag is present, false otherwise
   */
  public void setCompletedFlag(Boolean completedFlag) {
    this.completedFlag = completedFlag;
  }

  /**
   * Sets the dueFlag
   * @param dueFlag - True if the dueFlag is present, false otherwise
   */
  public void setDueFlag(Boolean dueFlag) {
    this.dueFlag = dueFlag;
  }

  /**
   * Sets the priorityFlag
   * @param priorityFlag - True if the priorityFlag is present, false otherwise
   */
  public void setPriorityFlag(Boolean priorityFlag) {
    this.priorityFlag = priorityFlag;
  }

  /**
   * Sets the categoryFlag
   * @param categoryFlag - True if the categoryFlag is present, false otherwise
   */
  public void setCategoryFlag(Boolean categoryFlag) {
    this.categoryFlag = categoryFlag;
  }

  /**
   * Sets the completeTodoFlag
   * @param completeTodoFlag - True if the completeTodoFlag is present, false otherwise
   */
  public void setCompleteTodoFlag(Boolean completeTodoFlag) {
    this.completeTodoFlag = completeTodoFlag;
  }

  /**
   * Sets the due date of the todo
   * @param dueDate - the due date of the todo expressed as a LocalDate
   */
  public void setDueDate(LocalDateTime dueDate) {
    this.dueDate = dueDate;
  }

  /**
   * Sets the priorityNum
   * @param priorityNum - sets the priorityNum as an Integer
   */
  public void setPriorityNum(Integer priorityNum) {
    this.priorityNum = priorityNum;
  }

  /**
   * Sets the category
   * @param setCategory - sets thecategory
   */
  public void setCategory(String setCategory) {
    this.setCategory = setCategory;
  }

  /**
   * Get completedFlag
   * @return true is the completedFlag is given, false otherwise
   */
  public Boolean getCompletedFlag() {
    return completedFlag;
  }

  /**
   * Get completeToDo Flag
   * @return true is the completeTodo flag is given, false otherwise
   */
  public Boolean getCompleteTodoFlag() {
    return completeTodoFlag;
  }

  /**
   * Gets Due Date
   * @return - LocalDate Due Date of the todo
   */
  public LocalDateTime getDueDate() {
    return dueDate;
  }

  /**
   * Gets PriorityNum
   * @return - Integer PriorityNum
   */
  public Integer getPriorityNum() {
    return priorityNum;
  }

  public String getSetCategory() {
    return setCategory;
  }

  /**
   * Gets IDs
   * @return - ArrayList of IDs
   */
  public ArrayList<Integer> getIDs() {
    return iDs;
  }

  /**
   * Returns true if two CompleteToDo are equal
   * @param o - object to compare
   * @return true if objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CompleteTodo that = (CompleteTodo) o;
    return Objects.equals(completedFlag, that.completedFlag) && Objects
        .equals(dueFlag, that.dueFlag) && Objects.equals(priorityFlag, that.priorityFlag)
        && Objects.equals(categoryFlag, that.categoryFlag) && Objects
        .equals(completeTodoFlag, that.completeTodoFlag) && Objects
        .equals(dueDate, that.dueDate) && Objects.equals(priorityNum, that.priorityNum)
        && Objects.equals(setCategory, that.setCategory) && Objects
        .equals(iDs, that.iDs);
  }

  /**
   * Returns hashcode of CompleteToDo
   * @return hashcode of CompleteToDo
   */
  @Override
  public int hashCode() {
    return Objects
        .hash(completedFlag, dueFlag, priorityFlag, categoryFlag, completeTodoFlag, dueDate,
            priorityNum, setCategory, iDs);
  }

  /**
   * Returns String of the CompleteToDo
   * @return String of the CompleteToDo
   */
  @Override
  public String toString() {
    return "CompleteTodo{" +
        "completedFlag=" + completedFlag +
        ", dueFlag=" + dueFlag +
        ", priorityFlag=" + priorityFlag +
        ", categoryFlag=" + categoryFlag +
        ", completeTodoFlag=" + completeTodoFlag +
        ", dueDate=" + dueDate +
        ", priorityNum=" + priorityNum +
        ", setCategory='" + setCategory + '\'' +
        ", iDs=" + iDs +
        '}';
  }
}
