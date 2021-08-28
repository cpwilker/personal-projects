#ToDo List Application
Completed on April 22th 2021 by Courtney Wilkerson in collaboration with Madeleine van Zuylen and Kevin Van

##Application Description
This project is a command-line ToDo list application. The system will allow a user to add and
track the status of their ToDos by due date, category, priority, and status
(complete/incomplete).

The application stores all ToDos in a CSV file. The CSV file is a plain text file, containing data
organized into columns separated by a comma. The data in each column is enclosed in double
quotes. The first line of the file contains the headers for each column.
The CSV has 6 columns named id, text, completed, due, priority, and category.
This application assumes that the CSV column names will not change.

The information in each column is enclosed in double quotes. It is possible that column entries
may contain a comma. Some columns are considered optional and may not contain data. A cell that contains only “?” means that there is no value for this cell.

###Example of sample data the user would find in the CSV:

“id”,”text”,”completed”,”due”,”priority”,”category”  
"1","Finish HW9","false","8/2/2021","1","school"  
"2","Mail passport application, photo","true","8/28/2021","?","?"

##Application Functionality
The system supports the following functionality:  
● Add a new todo: The user must supply the information required by the Todo data
structure. They can also choose to specify the optional information. When a new Todo is
added, the CSV file will be updated.  
● Complete an existing todo: The user can set the completed status of an existing Todo to
true. When the status is changed, the CSV file will be updated.  
● Display todos: The user can request that the program display a list of Todos. By default, all of the
Todos will be printed, but the user can supply additional arguments to customize the list:  
&nbsp;&nbsp; ○ Filter the list to only include incomplete Todos;  
&nbsp;&nbsp; ○ Filter the list to only include Todos with a particular category;  
&nbsp;&nbsp; ○ Sort the Todos by date (ascending)  
&nbsp;&nbsp; ○ Sort the Todos by priority (ascending)  
The two filter arguments can be combined but only one sort can be applied at a time.

##Instructions for User

This program accepts the following command line arguments in any order:  
**--csv-file <path/to/file>** The CSV file containing the
todos. This option is required. User can provide just the csv file name if the csv file lies in the
same directory as the src folder.  
**--add-todo** Add a new todo. If this option is
provided, then --todo-text must
also be provided.  
**--todo-text </description of todo>** A description of the todo. Must be in quotes if longer than a single word.  
**--completed** (Optional) Sets the completed
status of a new todo to true.  
**--due </due date>** (Optional) Sets the due date of a
new todo. Date format needs to be mm/dd/yyyy: 03/22/2021
**--priority <1, 2, or 3>** (Optional) Sets the priority of a
new todo. The value can be 1, 2,
or 3.  
**--category </a category name>** (Optional) Sets the category of a
new todo. The value can be any
String. Categories do not need
to be pre-defined.  
**--complete-todo <id#>** Mark the Todo with the provided
ID as complete.  
**--display** Display todos. If none of the
following optional arguments are
provided, displays all todos.  
**--show-incomplete** (Optional) If --display is
provided, only incomplete todos
should be displayed.  
**--show-category </category />** (Optional) If --display is
provided, only todos with the
given category should be
displayed.    
**--sort-by-date** (Optional) If --display is
provided, sort the list of todos
by date order (ascending). Cannot
be combined with --sort-by-priority.  
**--sort-by-priority** (Optional) If --display is
provided, sort the list of todos
by priority (ascending). Cannot
be combined with --sort-by-date.

### Example User Inputs and Program Outputs
1. INPUT: --csv-file todos.csv --add-todo --todo-text "Wash Dog" --category home --complete-todo 4 --display --show-incomplete  
   OUTPUT:  
   |  id    |  text                                     |  completed  |  due          |  priority   |  category       |  
  |  1     |  Finish HW9                               |  false      |  3/22/2020    |  1          |  school         |  
|  3     |  Study for finals                         |  false      |  ?            |  2          |  school         |  
|  6     |  Wash Dog                                 |  false      |  ?            |  3          |  home           |  
   

2. INPUT: --csv-file todos.csv --add-todo --todo-text "Unload the dishwasher" --category home --completed --priority 2
   --display --show-category home --sort-by-priority  
   
   OUTPUT:  
   |  id    |  text                                     |  completed  |  due          |  priority   |  category       |  
|  7     |  Unload the dishwasher                    |  true       |  ?            |  2          |  home           |  
|  4     |  Clean the house                          |  true       |  3/22/2020    |  3          |  home           |  
|  5     |  Buy yarn for blanket, stuffed toy        |  true       |  ?            |  3          |  home           |  
|  6     |  Wash Dog                                 |  false      |  ?            |  3          |  home           |  
   
