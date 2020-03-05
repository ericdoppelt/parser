# SLOGO Refactoring Discussion

## Model

The majority of design issues lie in the backend. CommandDatabase currently
is a massive map of commands, we plan to extremely reduce the size
of this class with reflection. In terms of commands, refactoring will need to
be done to reduce code redundancy. May commands share similar code, such as
dotimes and for commands. Super classes will be made that includes the shared
logic, and specific commands will inherit the super class. ModelParser
will be refactored to have enum lists to reduce the need for magic 
numbers and to remove dirty long if-else trees. The repeat command
will incorporate lambda expressions given that only one method within the modelparser
class is needed by the repeat command. Communication between front end
and our file package will need to be explored to see the best way to 
save game configurations. 

## View

Inheritance will be incorporated for front elements. Lots of front end elements
require an input method, likewise an abstract class will be created that
has this input method that classes can pull from. General communication 
between front and back end needs to be improved to make sure that there is 
as little overlap between model and view as possible. A new inheritance 
hierarchy is being considered for replacing toggle buttons related to command
history. Other than that just general cleaning up and refactoring to reduce
the amount of redundant code. 


