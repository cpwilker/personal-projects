package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class representing a To-do with text, completed, due, priority and category fields
 */
public class Todo implements Comparable<Todo>{
  // Fields need to be immutable after construction *except* completed
  private Integer id; // auto-generated
  private String text; // required
  private Boolean completed = Boolean.FALSE; //optional
  private LocalDate due; // optional
  private Integer priority; // optional
  private String category; // optional

  public Todo(Builder builder) {
    this.id = builder.id;
    this.text = builder.text;
    this.completed = builder.completed;
    this.due = builder.due;
    this.priority = builder.priority;
    this.category = builder.category;
  }

  /**
   * Sets To Do to completed. Boolean
   */
  public void setCompleted() {
    this.completed = true;
  }

  /**
   * Returns the id of the object. Integer
   * @return the id of the object. Integer
   */
  public Integer getId() {
    return id;
  }

  /**
   * Returns the text of the object. String
   * @return the text of the object. String
   */
  public String getText() {
    return text;
  }

  /**
   * Returns the completed status of the object. Boolean
   * @return the completed status of the object. Boolean
   */
  public Boolean getCompleted() {
    return completed;
  }

  /**
   * Returns the due date of the object. LocalDate
   * @return the due date of the object. LocalDate
   */
  public LocalDate getDue() {
    return due;
  }

  /**
   * Returns the priority number of the object. Integer
   * @return the priority number of the object. Integer
   */
  public Integer getPriority() {
    return priority;
  }

  /**
   * Returns the category of the object. String
   * @return the category of the object. String
   */
  public String getCategory() {
    return category;
  }

  /**
   * Returns True if two To Do objects are equal. Boolean
   * @param o another To Do object
   * @return True if two To Do objects are equal. Boolean
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Todo todo = (Todo) o;
    return Objects.equals(id, todo.id) && Objects.equals(text, todo.text)
        && Objects.equals(completed, todo.completed) && Objects
        .equals(due, todo.due) && Objects.equals(priority, todo.priority)
        && Objects.equals(category, todo.category);
  }

  /**
   * Returns the hashcode of the To Do object. Integer
   * @return the hashcode of the To Do object. Integer
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, text, completed, due, priority, category);
  }

  /**
   * Returns String-format of the To Do object. String
   * @return String-format of the To Do object. String
   */
  @Override
  public String toString() {
    return "Todo{" +
        "id=" + id +
        ", text='" + text + '\'' +
        ", completed=" + completed +
        ", due=" + due +
        ", priority=" + priority +
        ", category='" + category + '\'' +
        '}';
  }

  /**
   * Builder class for the custom To Do objects.
   */
  public static class Builder {
    private Integer id;
    private String text;
    private boolean completed;
    private LocalDate due;
    private Integer priority;
    private String category;

    public Builder(String text) {
      this.text = text;
      this.completed = false;
      this.priority = 3;
    }

    /**
     * Sets the Builder id
     * @param id id of the To Do object. Integer
     * @return Builder object
     */
    public Builder id(Integer id) {
      if (id != null) {
        this.id = id;
      }
      return this;
    }

    /**
     * Sets the Builder completion status
     * @param completed Completion status of the To Do object. Boolean
     * @return Builder object
     */
    public Builder completed(Boolean completed) {
      this.completed = completed;
      return this;
    }

    /**
     * Sets the Builder due date
     * @param due due date of the To Do object. LocalDate
     * @return Builder Object
     */
    public Builder due(LocalDate due) {
      this.due = due;
      return this;
    }

    /**
     * Set the Builder priority
     * @param priority priority of the To Do object. Integer
     * @return Builder object
     */
    public Builder priority(Integer priority) {
      int DEFAULT_PRIORITY = 3;
      if (priority != null) {
        this.priority = priority;
      } else {
        this.priority = DEFAULT_PRIORITY;
      }
      return this;
    }

    /**
     * Set the Builder category
     * @param category category of the To Do object. String
     * @return Builder object
     */
    public Builder category(String category) {
      this.category = category;
      return this;
    }

    /**
     * Builds and returns a To Do object
     * @return a new To Do object
     */
    public Todo build() {
      return new Todo(this);
    }

    /**
     * Returns True if two Builder objects are equal. Boolean
     * @param o another To Do object
     * @return True if two Builder objects are equal. Boolean
     */
    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Builder builder = (Builder) o;
      return completed == builder.completed && Objects.equals(id, builder.id)
          && Objects.equals(text, builder.text) && Objects.equals(due, builder.due)
          && Objects.equals(priority, builder.priority) && Objects
          .equals(category, builder.category);
    }

    /**
     * Returns the hashcode of the Builder object. Integer
     * @return the hashcode of the Builder object. Integer
     */
    @Override
    public int hashCode() {
      return Objects.hash(id, text, completed, due, priority, category);
    }

    /**
     * Returns String-format of the Builder object. String
     * @return String-format of the Builder object. String
     */
    @Override
    public String toString() {
      return "Builder{" +
          "id=" + id +
          ", text='" + text + '\'' +
          ", completed=" + completed +
          ", due=" + due +
          ", priority=" + priority +
          ", category='" + category + '\'' +
          '}';
    }
  }

  /**
   * Returns -1 if this due is less than otherTodo (this.due is null or this.due is after other)
   * Returns 1 if this due is greater than otherTodo (otherTodo.due is null or this.due is
   * before other)
   * Returns 0 if both null or equal dates
   * @param otherTodo - todo object
   * @return integer
   */
  @Override
  public int compareTo(Todo otherTodo) {
    if (this.getDue() == null && otherTodo.getDue() != null) {
      return 1;
    }
    if (this.getDue() != null && otherTodo.getDue() == null) {
      return -1;
    }
    if (this.getDue() == null && otherTodo.getDue() == null) {
      return 0;
    }
    return this.getDue().compareTo(otherTodo.getDue());
  }
}
