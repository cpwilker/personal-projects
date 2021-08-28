package controller;

import java.util.Objects;

/**
 * Class to process command line arguments
 */
public class CmdLineProcessor {
  public AddToDo addToDo;
  public CompleteTodo completeTodo;
  public DisplayTodo displayTodo;
  private static final Integer MIN_ARGUMENTS = 3;

  /**
   * Constructor for a command line processor
   */
  public CmdLineProcessor() {
    addToDo = new AddToDo();
    completeTodo = new CompleteTodo();
    displayTodo = new DisplayTodo();
  }

  /**
   * Processes command line arguments, sets flags and paths, and checks for exceptions
   * @param args - command line arguments
   * @return True when finished processing command line arguments
   */
  public Boolean processArgs(String[] args) throws CmdLineExceptions {
    if (args.length < MIN_ARGUMENTS) {
      throw new IncorrectNumArgs("\nOptions --csv-file <path to file> are required."
          + " Please also include one (or some or all) of add, complete or display "
          + "commands.\n");
    }
    return (addToDo.validateFlag(args) &&
        completeTodo.validateFlag(args) &&
        displayTodo.validateFlag(args));
  }

  /**
   * Gets AddToDo
   * @return AddTodo
   */
  public AddToDo getAddToDo() {
    return addToDo;
  }

  /**
   * Gets CompleteTodo
   * @return CompleteTodo
   */
  public CompleteTodo getCompleteTodo() {
    return completeTodo;
  }

  /**
   * Gets DisplayTodo
   * @return DisplayTodo
   */
  public DisplayTodo getDisplayTodo() {
    return displayTodo;
  }

  /**
   * Compares two objects and returns true if equal
   * @param o - object to compare
   * @return - true if equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CmdLineProcessor that = (CmdLineProcessor) o;
    return Objects.equals(addToDo, that.addToDo) && Objects
        .equals(completeTodo, that.completeTodo) && Objects
        .equals(displayTodo, that.displayTodo);
  }

  /**
   * Returns hashcode of the processor
   * @return hashcode of the processor
   */
  @Override
  public int hashCode() {
    return Objects.hash(addToDo, completeTodo, displayTodo);
  }

  /**
   * Returns string of the command line processor
   * @return - string of the processor
   */
  @Override
  public String toString() {
    return "CmdLineProcessor{" +
        "addToDo=" + addToDo +
        ", completeTodo=" + completeTodo +
        ", displayTodo=" + displayTodo +
        '}';
  }
}
