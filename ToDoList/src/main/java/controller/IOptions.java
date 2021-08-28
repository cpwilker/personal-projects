package controller;

/**
 * Interface for flag validation methods
 */
public interface IOptions {

  /**
   * Validates the options in the command line processor
   * @param args - command line processor
   * @return true if the command line processor is correct
   */
  Boolean validateFlag(String[] args);

  /**
   * Sets the flag and paths
   * @param args - command line arguments
   */
  void setsOptions(String[] args);

  /**
   * Checks that a path follows a given flag
   * @param args - command line arguments
   * @param i - command line argument
   */
  void checkPath(String[] args, Integer i);
}

