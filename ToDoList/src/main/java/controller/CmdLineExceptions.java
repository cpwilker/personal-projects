package controller;

/**
 * Parent class to store all subclasses that represent command line exceptions to be thrown
 */
public class CmdLineExceptions extends RuntimeException {

  private static String usage = "Usage:\n"
      + "--csv-file The CSV file containing the\n"
      + "todos. This option is required.\n"
      + "--add-todo  Add a new todo. If this option is\n"
      + "provided, then --todo-text must also be provided.\n"
      + "--todo-text <description of todo> A description of the todo.\n"
      + "--completed (Optional) Sets the completed status of a new todo to true. \n"
      + "--due <due date> (Optional) Sets the due date of a new todo "
      + "in format \"yyyy-MM-dd HH:mm\"\n"
      + "--priority <1, 2, or 3> Optional) Sets the priority of a\n"
      + "new todo. The value can be 1, 2, or 3.\n"
      + "--category <a category name> (Optional) Sets the category of a\n"
      + "new todo. The value can be any String. \n"
      + "--complete-todo <id> Mark the Todo with the provided\n"
      + "ID as complete.\n"
      + "--display  Display todos. If none of the\n"
      + "following optional arguments are\n"
      + "provided, will displays all todos.\n"
      + "--show-incomplete  (Optional) If --display is\n"
      + "provided, only incomplete todos will be displayed.\n"
      + "--show-category <category> (Optional) If --display is\n"
      + "provided, todos with the given category will be displayed.\n"
      + "--sort-by-date (Optional) If --display is provided, sort\n"
      + "the list of todos by date order (ascending). Cannot\n"
      + "be combined with --sort-by-priority.\n"
      + "--sort-by-priority (Optional) If --display is provided,\n"
      + "sort the list of todos by priority (ascending). Cannot\n"
      + "be combined with --sort-by-date\n\n"
      + "Example:\n"
      + " --csv-file todos.csv --add-todo --todo-text \"hw to finish\" "
      + "--due \"1111-11-11 11:11\"\n";

  /**
   * Constructor for CmdLineExceptions
   * @param message - error message to user expressed as a String
   */
  public CmdLineExceptions(String message) {
    super(message + "\n" + usage);
  }
}