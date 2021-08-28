package controller;

import java.util.Locale;
import java.util.Objects;

/**
 * Class that represents an AddToDo
 */
public class AddToDo extends Options {
  private static final String csvFile = "--csv-file";
  private static final String addTodo = "--add-todo";
  private static final String todoText = "--todo-text";
  private static final Integer maxFlags = 3;
  private Integer flagCount = 0;
  Boolean csvFileFlag = Boolean.FALSE;
  Boolean addToDoFlag = Boolean.FALSE;
  Boolean toDoTxtFlag = Boolean.FALSE;
  String pathToFile;
  String description;

  /**
   * Constructor for the AddToDo class
   */
  public AddToDo() {
  }

  /**
   * Validates that the given flags are correct
   * @param args - command line arguments
   * @return True if all the given flags and paths are correct for adding a ToDo
   * @throws CmdLineExceptions if options given are incorrect
   */
  @Override
  public Boolean validateFlag(String[] args) throws CmdLineExceptions{
    this.setsOptions(args);
    // Must have --csv-file flag
    if (!csvFileFlag) {
      throw new IncorrectFlag("\n" + csvFile + " <path to file> is required.\n");
    }
    // Can only add one todo at a time
    if (flagCount > maxFlags){
      throw new IncorrectNumArgs("\nYou may only add one ToDo at a time (no duplicate flags).\n");
    }
    // Cannot have --add-todo without --todo-text and vice versa
    if ((addToDoFlag && !toDoTxtFlag) || (!addToDoFlag && toDoTxtFlag)){
      throw new IncorrectFlag("\nMust have both: " + addTodo + " and " + todoText + "\n");
    }
    return Boolean.TRUE;
  }

  /**
   * Sets the flags and paths given on the command line
   * @param args - given command line arguments
   */
  @Override
  public void setsOptions(String[] args) {
    Integer i = 0;
    for (i = 0; i < args.length; i++){
      String cmdLower = args[i].toLowerCase();
      if (cmdLower.equals(csvFile)){
        this.setCsvFileFlag(Boolean.TRUE);
        this.flagCount++;
        checkPath(args, i);
        this.setPathToFile(args[i+1]);
      }
      if (cmdLower.equals(addTodo)){
        this.setAddToDoFlag(Boolean.TRUE);
        this.flagCount++;
      }
      if (cmdLower.equals(todoText)){
        this.setToDoTxtFlag(Boolean.TRUE);
        this.flagCount++;
        checkPath(args, i);
        this.setDescription(args[i+1]);
      }
    }
  }

  /**
   * Sets the CsvFileFlag
   * @param csvFileFlag - Boolean to set the CsvFileFlag
   */
  public void setCsvFileFlag(Boolean csvFileFlag) {
    this.csvFileFlag = csvFileFlag;
  }

  /**
   * Set the setAddToDoFlag
   * @param addToDoFlag - Boolean to set the setAddToDoFlag
   */
  public void setAddToDoFlag(Boolean addToDoFlag) {
    this.addToDoFlag = addToDoFlag;
  }

  /**
   * Sets the setToDoTxtFlag
   * @param toDoTxtFlag - Boolean to set the setToDoTxtFlag
   */
  public void setToDoTxtFlag(Boolean toDoTxtFlag) {
    this.toDoTxtFlag = toDoTxtFlag;
  }

  /**
   * Sets the setPathToFile
   * @param pathToFile - Boolean to set the setPathToFile
   */
  public void setPathToFile(String pathToFile) {
    this.pathToFile = pathToFile;
  }

  /**
   * Sets the setDescription
   * @param description - Boolean to set the setDescription
   */
  public void setDescription(String description) {
    this.description = description;
  }


  /**
   * Gets CsvFileFlag
   * @return - True if the CsvFileFlag is given, false otherwise
   */
  public Boolean getCsvFileFlag() {
    return csvFileFlag;
  }

  /**
   * Gets AddToDoFlag
   * @return - True if the AddToDoFlag is given, false otherwise
   */
  public Boolean getAddToDoFlag() {
    return addToDoFlag;
  }

  /**
   * Gets ToDoTxtFlag
   * @return - True if the ToDoTxtFlag is given, false otherwise
   */
  public Boolean getToDoTxtFlag() {
    return toDoTxtFlag;
  }

  /**
   * Gets pathToFile
   * @return - String path to file
   */
  public String getPathToFile() {
    return pathToFile;
  }

  /**
   * Gets description
   * @return - String description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Returns true if two AddToDos are equal
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
    AddToDo addToDo = (AddToDo) o;
    return Objects.equals(flagCount, addToDo.flagCount) && Objects
        .equals(csvFileFlag, addToDo.csvFileFlag) && Objects
        .equals(addToDoFlag, addToDo.addToDoFlag) && Objects
        .equals(toDoTxtFlag, addToDo.toDoTxtFlag) && Objects
        .equals(pathToFile, addToDo.pathToFile) && Objects
        .equals(description, addToDo.description);
  }

  /**
   * Returns hashcode of AddToDO
   * @return hashcode of AddToDO
   */
  @Override
  public int hashCode() {
    return Objects.hash(flagCount, csvFileFlag, addToDoFlag, toDoTxtFlag, pathToFile, description);
  }

  /**
   * Returns String of the AddToDo
   * @return String of the AddToDo
   */
  @Override
  public String toString() {
    return "AddToDo{" +
        "flagCount=" + flagCount +
        ", csvFileFlag=" + csvFileFlag +
        ", addToDoFlag=" + addToDoFlag +
        ", toDoTxtFlag=" + toDoTxtFlag +
        ", pathToFile='" + pathToFile + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
