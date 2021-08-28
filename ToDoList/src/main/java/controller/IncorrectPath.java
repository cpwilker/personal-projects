package controller;

/**
 * Class to handle errors when path is missing after the appropriate flag
 */
public class IncorrectPath extends CmdLineExceptions{
  public IncorrectPath(String message) {
    super(message);
  }
}
