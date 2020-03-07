parser
====

This project implements a development environment that helps users write SLogo programs.

Names: Benjamin Lu, Eric Doppelt, Frank Tang, Erik Gregario


### Timeline

Start Date: 2/11/2020

Finish Date: 3/7/2020

Hours Spent: 120

### Primary Roles

Frank/Ben - General backend, commands, parsing, command construction

Eric/Erik - General frontend, UI, turtle rendering, command and history rendering

### Resources Used

Past Duvall labs, general stack overflow and other forums

### Running the Program

Main class: Main.java

Data files needed: All files under the resource folder

Features implemented: View state of turtle real time, click to execute
commands in command history, click to edit user defined variables, user
defined commands, provide different type of turtle images, ability to
save and load game configurations, turtle animations, ability to recognize
various languages, recognize display commands, update command history when
language changes, allow user to undo/redo last command. 



### Notes/Assumptions

Assumptions or Simplifications: preferences can be saved and loaded via XML, properties
file helps define what the UI looks like to reduce data cluttering 

Interesting data files: DuvallTurtle.png

Known Bugs: Turtle is initially offset by a certain amount, when turning
the turtle the existing pen color will change, when using a to command
from command history the command does not execute


### Impressions

Project was very challenging, however introduced many new concepts such 
as reflection and lambda expressions, additionally due to the complexity of the
project, we were pushed extensively to try to implement the best design as possible,
while many hours were spent on the project and we are proud of what we have, there
are additional elements we wish we had more time to implement such as multiple turtles. 
