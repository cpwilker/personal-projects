package controller;

import java.util.Objects;

/**
 * Class that represents a DisplayToDo
 */
public class DisplayTodo extends Options {
  private static final String display = "--display";
  private static final String showIncomplete = "--show-incomplete";
  private static final String showCategory = "--show-category";
  private static final String sortByDate = "--sort-by-date";
  private static final String sortByPriority = "--sort-by-priority";
  Boolean displayFlag = Boolean.FALSE;
  Boolean showIncompleteFlag = Boolean.FALSE;
  Boolean showCategoryFlag = Boolean.FALSE;
  Boolean sortByDateFlag = Boolean.FALSE;
  Boolean sortByPriorityFlag = Boolean.FALSE;
  String category;

  /**
   * Constructor for the DisplayToDo class
   */
  public DisplayTodo() {
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
    if (!displayFlag && (showIncompleteFlag || showCategoryFlag ||
        sortByDateFlag || sortByPriorityFlag)){
      throw new IncorrectFlag("\n" + display + " must be used when other display "
          + "flags are given.\n");
    }
  if (sortByDateFlag && sortByPriorityFlag){
    throw new IncorrectFlag("\n" + sortByDate + " and " + sortByPriority +
        " cannot be used together.\n");
  }
    return Boolean.TRUE;
  }

  /**
   * Sets the flags and paths given on the command line
   * @param args - given command line arguments
   */
  @Override
  public void setsOptions(String[] args) {
    Integer i;
    for (i = 0; i < args.length; i++) {
      String cmdLower = args[i].toLowerCase();
      if (cmdLower.equals(display)){
        this.setDisplayFlag(Boolean.TRUE);
      }
      if (cmdLower.equals(showIncomplete)){
        this.setShowIncompleteFlag(Boolean.TRUE);
      }
      if (cmdLower.equals(showCategory)){
        this.setShowCategoryFlag(Boolean.TRUE);
        checkPath(args, i);
        this.setCategory(args[i+1]);
      }
      if (cmdLower.equals(sortByDate)){
        this.setSortByDateFlag(Boolean.TRUE);
      }
      if (cmdLower.equals(sortByPriority)){
        this.setSortByPriorityFlag(Boolean.TRUE);
      }
    }
  }

  /**
   * Sets display flag
   * @param displayFlag - true if display flag is given
   */
  public void setDisplayFlag(Boolean displayFlag) {
    this.displayFlag = displayFlag;
  }

  /**
   * Sets showIncompleteFlag
   * @param showIncompleteFlag - true if showIncompleteFlag flag is given
   */
  public void setShowIncompleteFlag(Boolean showIncompleteFlag) {
    this.showIncompleteFlag = showIncompleteFlag;
  }

  /**
   * Sets showCategory flag
   * @param showCategoryFlag - true if showCategoryFlag is given
   */
  public void setShowCategoryFlag(Boolean showCategoryFlag) {
    this.showCategoryFlag = showCategoryFlag;
  }

  /**
   * Sets sortByDateFlag
   * @param sortByDateFlag - true if sortByDateFlag is given
   */
  public void setSortByDateFlag(Boolean sortByDateFlag) {
    this.sortByDateFlag = sortByDateFlag;
  }

  /**
   * Sets sortByPriorityFlag
   * @param sortByPriorityFlag - true if sortByPriorityFlag is given
   */
  public void setSortByPriorityFlag(Boolean sortByPriorityFlag) {
    this.sortByPriorityFlag = sortByPriorityFlag;
  }

  /**
   * Sets category
   * @param category - true if category is given
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * Gets display flag
   * @return - true if display flag was given, false otherwise
   */
  public Boolean getDisplayFlag() {
    return displayFlag;
  }

  /**
   * Gets showIncompleteFlag
   * @return - true if showIncompleteFlag is given, false otherwise
   */
  public Boolean getShowIncompleteFlag() {
    return showIncompleteFlag;
  }

  /**
   * Gets showCategoryFlag
   * @return - true if showCategoryFlag is given, false otherwise
   */
  public Boolean getShowCategoryFlag() {
    return showCategoryFlag;
  }

  /**
   * Gets sortByDateFlag
   * @return - true if sortByDateFlag is given, false otherwise
   */
  public Boolean getSortByDateFlag() {
    return sortByDateFlag;
  }

  /**
   * Gets sortByPriorityFlag
   * @return - true if sortByPriorityFlag is given, false otherwise
   */
  public Boolean getSortByPriorityFlag() {
    return sortByPriorityFlag;
  }

  /**
   * Gets category
   * @return - true if category is given, false otherwise
   */
  public String getCategory() {
    return category;
  }

  /**
   * Returns true if two DisplayToDo are equal
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
    DisplayTodo that = (DisplayTodo) o;
    return Objects.equals(displayFlag, that.displayFlag) && Objects
        .equals(showIncompleteFlag, that.showIncompleteFlag) && Objects
        .equals(showCategoryFlag, that.showCategoryFlag) && Objects
        .equals(sortByDateFlag, that.sortByDateFlag) && Objects
        .equals(sortByPriorityFlag, that.sortByPriorityFlag) && Objects
        .equals(category, that.category);
  }

  /**
   * Returns hashcode of DisplayToDo
   * @return hashcode of DisplayToDo
   */
  @Override
  public int hashCode() {
    return Objects
        .hash(displayFlag, showIncompleteFlag, showCategoryFlag, sortByDateFlag, sortByPriorityFlag,
            category);
  }

  /**
   * Returns String of the DisplayToDo
   * @return String of the DisplayToDo
   */
  @Override
  public String toString() {
    return "DisplayTodo{" +
        "displayFlag=" + displayFlag +
        ", showIncompleteFlag=" + showIncompleteFlag +
        ", showCategoryFlag=" + showCategoryFlag +
        ", sortByDateFlag=" + sortByDateFlag +
        ", sortByPriorityFlag=" + sortByPriorityFlag +
        ", category='" + category + '\'' +
        '}';
  }
}
