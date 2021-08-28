package controller;

/**
 * Class to handle errors when too few or too many command line arguments are passed.
 */
public class IncorrectNumArgs extends CmdLineExceptions {

  public IncorrectNumArgs(String message) {
    super(message);
  }
}
