package controller;

/**
 * Class to handle errors when a flag is missing as a command line argument.
 */
public class IncorrectFlag extends CmdLineExceptions {

  public IncorrectFlag(String message) {
    super(message);
  }
}
