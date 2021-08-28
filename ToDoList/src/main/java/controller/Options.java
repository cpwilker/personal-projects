package controller;

/**
 * Abstract class with methods to validate command line processor inputs
 */
public abstract class Options implements IOptions {
  private static final String flag = "--";

  /**
   * Validates the options in the command line processor
   * @param args - command line processor
   * @return true if the command line processor is correct
   */
  @Override
  public Boolean validateFlag(String[] args) {
    return Boolean.TRUE;
  }

  /**
   * Sets the flag and paths
   * @param args - command line arguments
   */
  @Override
  public void setsOptions(String[] args) {
  }

  /**
   * Checks that a path follows a given flag
   * @param args - command line arguments
   * @param i - command line argument
   */
  @Override
  public void checkPath(String[] args, Integer i) {
    if(i+1 >= args.length || args[i+1].startsWith(flag))
      throw new IncorrectPath("\nMust have path after flag.\n");
  }
}
