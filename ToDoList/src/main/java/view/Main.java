
package view;

import controller.CmdLineProcessor;
import java.io.IOException;
import model.ToDoFunctions;

public class Main {

  public static void main(String[] args) throws IOException {
    CmdLineProcessor cmdLineProcessor = new CmdLineProcessor();
    cmdLineProcessor.processArgs(args);
    ToDoFunctions manager = new ToDoFunctions(cmdLineProcessor.addToDo,
        cmdLineProcessor.completeTodo);
    manager.manage();
    Display display = new Display(manager.getTodoList(), cmdLineProcessor.displayTodo);
    display.displayTodos();
  }
}