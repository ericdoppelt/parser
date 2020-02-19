## Introduction
#### This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). Discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).

Our team is trying to create a program that acts as a simple Logo program where a user can input specific commands into the program. These commands will then direct a turtle to move on a coordinate grid and draw a trace. The primary design goal of this project is to make it as flexible as possible to handle extensions later on in the project. For example, one of the most important areas where the project needs to be flexible is where the commands are stored and used. This is because we want to be able to add complex commands to the game in the future without having to change the rest of the project. Thus, areas like the turtle need to be compatible with recieving new forms of commands so that we can add new types later on.

As for the primary architecture of this project, we plan on having four APIs that will constitute the main portions of this project: UserInterface (Frontend Internal), CommandBox (Frontend External), Controller (Backend External), and ModelDatabase(Backend Internal). 

In the UserInterface, the turtle and its trace are represented which the user can see but not directly modify. Additionally, information such as history and possible commands are displayed here.

In the CommandBox, the user can add commands to affect this displayed grid. This indirectly changes the frontend internal by going through the Controller of the project, which represents the backend external of the program. Commands pass through this Controller to arrive at the ModelDatabase (Internal Backend), containing information needed to update the Turtle's position and history. 

After executing and storing this data, 
the Controller will then be called to update the view to reflect this change from the given command. 

In our project, the command input and the controller that gives information to the model will be open to new features and commands. The model's data storage and the drawing functions of the frontend will be closed as they should only update when the external APIs signal them to.



## Overview
#### This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. Describe the four APIs you intend to create (their purpose with regards to the program's functionality, and how they collaborate with each other) focusing specifically on the behavior, not the internal state. Include a picture of how the components are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). To keep these classes as flexible as possible, your team must describe two different implementations (i.e., data structures, file formats, etc.) and then design your method signatures so they do not reveal the specifics of either implementation option. Discuss specific classes, methods, and data structures, but not individual lines of code.!!!![](https://i.imgur.com/S1npx9B.png)

 

The main components of the program are the model, view, and controller package. The general idea is to have the view and model completely separate from each other and only connected via the controller package. The view package contains all the visual rendering such as the turtle view, grid view, command input box, and error view (which handles PopUps). The model contains all the hard logic of the program, including parsing and calculating multi-command commands and calculating data needed by the view to display the updated state of the game. The transfer of data between view and model is performed via controller. Controller contains an Interpreter and Infoontroller class. The interpreter receives the command from view and does preliminary checking for errors such as mispelling to ensure simple errors are caught before sending information to the model package. The controller will then send the instruction to the model, where the model will perform the logic necessary to parse through the commands. After parsing, the model will create Command objects that it will then execute through in order to update the data found in the turtles. Once the data has been updated, the model will create an InfoPackage that will be received by the Controller who will then give it to the View. The View will then unpack this data and use it to update its graphic interface.

The primary data structure used in the Model will be a stack, since this is a First-In, First-Out structure. This is needed to convert from Strings to Commands, especially when commands grow in complexity. This is done by creating a stack for arguments and commands that are used for such translation. By popping on and off the stack, commands can be created by the Model using the String passed in. These commands can later be converted to InfoBundles which are then visualized in the View using JavaFx.

To keep our classes as flexible as possible, we thought of different implementations of methods and made it so that our method of implementation is given by the method signature. For example, the public void sendInstruction() method in the Controller API is very vague as to what structures it passes from the Controller to the Model. As mentioned in the Design Considerations below, we discussed whether or not the Controller should pass a simple String input to the Model, or if it should pass the actual Command objects to the Model. Despite being relatively certain that we are going to pass the input string to the model, we opted to keep this method of sendInstruction() flexible so that it is unknown as to whether or not the instruction being sent is a String or a Command object.  

Additionally, to support flexibility, we have created an all-purpose class in the InfoBundle which transports information from the model to the view. Since we are in the early stages of planning, it is difficult to anticipate exactly what information the View will need from the Model and Controller to update its Grid. In our discussions, we contemplated passing a Command object to the View, and we likewise considered passing a method that would execute to update the view.

However, after more thought, it seems to be more reasonable to simply pass the parameters that are needed for the JavaFX commands that correspond to commands. Fd 50, for example, only needs to know the magnitude and direction of its movement, and as such, instead of passing a full command object, we could instead pass those two variables.

By creating an InfoBundle, we are essentially agreeing to pass *some* information from the Model to the View, but we are purposely leaving the contents of the class to be concretely defined as we implement the project. Therefore, if our class becomes heavily focused on a command heirarchy, we can pass commands through InfoBundle. If not, we can stick to our current goal of passing values needed for JavaFX only, letting the View class itself handle its updating with the input of certain parameters. 


## User Interface
#### This section describes how the user will interact with your program (keep it simple to start). Describe the overall appearance of program's user interface components and how users interact with these components (especially those specific to your program, i.e., means of input other than menus or toolbars). Include one or more pictures of the user interface (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a dummy program that serves as a exemplar). Also describe any erroneous situations that are reported to the user (i.e., bad input data, empty data, etc.). This section should go into as much detail as necessary to cover all your team wants to say.

The user will interact with our program via a JavaFX Application. Upon running the program, the scene will be displayed showing the Slogo UI. The game will look very similar to a basic Logo game, which can be seen [here](https://www.calormen.com/jslogo/). There will be small variations to this display, however.

For one thing, stylizing buttons will be added to the UI. As specified by the [Basic Implementation](https://www2.cs.duke.edu/courses/compsci308/spring20/assign/03_parser/part2.php), Input will be needed to set the background of the screen; set an image for the turtle; set a color for the pen; and choose the language of the program.

To set the background color of the screen or pen, the user will need a variety of options to choose from. A JavaFX node such as a Color Picker that presents a RGB View is best for this, to allow flexibility in any color type.

The image of the turtle can be loaded in from preset image files on the computer that the game is run on. Images can be included in a seperate folder in the Parser project to give options.

Lastly, a language for the UI display will need to be set. This will be in the form of a drop down menu, since languages need to be accomodated by the program via ResourceBundles.

These input fields will be displayed above the text input, which will be a Text Field in JavaFX. The layout can be seen in the following [image](https://i.imgur.com/HRbvL3A.png).

The classes that will likely be needed for this are TurtleView (visualizes the turtle image and knows its location), GridView (visualizes the path of the turtle), InputBox (takes all the aforementioned input such as setting the background color), ErrorView (displays pop up errors), and InfoView (shows commands, history, and variables in text). For the InfoView, input is needed to select which information to display. A button (such as a ToggleButton) on top of the component can switch between the three categories. Additionally, a Help Button can be included, which can either link to a new page with instructions on how to use the program.

Next to the space to enter commands will further be three buttons. Run executes the command or throws an error; clear deletes all characters in the command box; and expand enlarges the text field to visualize a larger String.  

Lastly, the ErrorView will display errors that include empty commands, improperly formatted commands, and unrecognizable commands. In each case, a custom Pop Up will describe the problem.


## Design Details
#### This section describes each API introduced in the Overview in detail (as well as any other sub-components that may be needed but are not significant to include in a high-level description of the program). Describe how each API supports specific features given in the assignment specification, what resources it might use, how it is intended to be used, and how it could be extended to include additional requirements (from the assignment specification or discussed by your team). Finally, justify the decision to create each class introduced with respect to the design's key goals, principles, and abstractions. This section should go into as much detail as necessary to cover all your team wants to say.

CommandBox: The API for the CommandBox (Frontend External) consists of the parts of the project that will be sending information to the controller. In particular, the CommandBox will be responsible for taking the input string that the user types in and sending that information to the Controller (Backend External). The class that can be found in this API is the InputBox class, which is essentially where the graphical interface and the command line of the program will appear. This transfer of information will occur using methods like getCommand() to set the input string in the class, and sendCommand() to send the input string to the Controller. Lastly, the CommandBox will throw exceptions when an error can already be detected, such as inputting an invalid character in the CommandBox. This API will allow us to exchange information with the controller so that we have a clear separation between model and view when inputting data in. 

Controller: This API for the Controller (Backend External) is to act as the transfer of information between the view and the model. In particular, the Controller will receive an input command string from the CommandBox in order to begin its analysis of what the string is saying. For sure, we have an interpretor class that will determine if the string has valid commands and characters in it. Otherwise, this will throw an error. For example, if the user inputs a command "fs 50", the interpretor will catch this error and throw an exception/error object to stop the command from going through. As for the rest of this part of the project, the controller will either continue to parse through the command string or pass the command string to the ModelDatabase (Backend Internal). This design decision is described in more detail below. In some manner, it will send instructions to the ModelDatabase via the sendInstruction() method that will pass the instructions necessary to the ModelDatabase. 

This previous section describes the purpose of the Controller when going from the CommandBox to the Model; however, we plan on having the Controller also act as intemediary when exchanging information between the Model and the View. This is because we were finding it difficult to find a way to pass information from the model to the view without having to pass the view object or the model object to the other. As a result, we plan on making an InfoPackage in the Model where this Controller will use the methods of getInfoPackage() and sendInfoPackage() to take the InfoPackage from the Model and give it to the View so that it can update the graphical interface. This will allow us to keep the Model and the View separate from each other during the operation of this project. The InfoPackage will essentially hold the parameters needed for JavaFX to display the new command.

ModelDatabase: The purpose of this ModelDatabase API (Backend Internal) is for the game logic and storing of data for the Turtles in the Slogo project. This API consitutes the bulk of the Model and will have classes like the following:

1. Command Object Superclass - This superclass represents the command objects that will be made based on the input string from the command. 
    The Model will likely parse through the instruction given by the Controller and make Command objects based on the instruction. This Command superclass will have an abstract void method called execute that will be different for each Command subclass. The Command superclass constructor will contain a turtle's ID so that we can specify which turtle the command is for. In this case, we can just call Command.execute(), and the specified Turtle's data will be updated.
    The specific subclasses of the command object that need to be instantiated will be selected by using a Java Reflection or map structure so that we can choose the correct command subclass object. For example, the command "fd" will be mapped down to a command subclass called something similar to forwardCommand(int distance, int turtleID). Note: this implementation may change when actually creating the code. From here, when I call forwardCommand.execute(), the specified Turtle's position will be updated.
    We wanted this command object to have an inheritance structure because it would make it much easier to add new commands to the project when new features are to be added. To do this, we would just need to create a new subclass with the specific Command that we want to add. In addition, by having subclasses, we can use simple building block commands like forward and turn right to create more complex Commands like a ZigZag command or a triangle command by coupling several of these more basic commands together.
2. TurtleData Class - This class is used to represent the data found in a turtle. We have this as a separate class so that we can have multiple turtles on screen, each with their own coordinates, history of commands, and their direction. This class will have several methods that will allow us to get the turtle's new position, direction, etc. 
3. HistoryPackage Class - This class represents a HistoryPackage that will be added to a List in the TurtleData object. This HistoryPackage object has multiple data points like the old coordinate, the new coordinate, the turtles direction, and the type of line or command was used. This will allow us to store the history of a Turtle's actions so that we can rerun commands or just have a more detailed transcript of where the turtle has gone.
4. InfoPackage Class - This class represents an InfoPackage class that is to be passed from the Model to the Controller to the View. This InfoPackage will contain information like the new point that the turtle moves to, whether or not the turtle needs to draw a line, whether the turtle should show up, etc. However, we realize that this type of class with all the information is slightly inefficient because we are packaging information that may not be needed for specific commands. For example, the turtle visibility property does not need to be bundled with the forward command because the forward command does not toggle whether the turtle is visualized or not. As a result, we may explore using an inheritance structure or some other way so that we do not have to make a class that sends extraneous information for the view every time.
5. Model Class - This overarching class will survey over the TurtleData class and execute the Commands necessary for them. This class will thus represent most of the Model by parsing through the command input with an command and argument stack structure to determine if the input is valid, throw errors when the input is not valid, and creating and executing the Commands when the input is valid. The model class is also where we will be instantiating objects like the InfoPackage that the Controller will then take.

UserInterface: The purpose of this UserInterface API (Frontend Internal) is for visualization. This will consistute the bulk of the View of the project, as we will be creating the graphical interface of the project from this API. The following classes will be part of this API:

1. TurtleView Class - This class will act as the class that visualizes the turtle on the screen. We have this as its own separate class to accomodate for multiple turtles on the screen and will use methods like updateTurtleImage() to affect the visual aspects of the turtle
2. GridView Class - This class will represent the drawing area of the program and where the turtles will live and draw on. In this class, we plan on using methods like updateGrid() in order to update the drawings of the turtles when we receive information from the model.
3. ErrorView Class - This class is to allow us to create an object that we can use to throw an exception when we catch an error. This object will display a message and tell the user what the error was and potentially how to fix it if the error is fixable. These messages will be made using the .properties files to make them flexible to changes.
4. InfoView Class - This class represents the additional graphical interface that will show up with the commands, variables, and access help function of the view. This class will either be a new Scene that will replace the screen of the current drawing stage to allow the user to see what the commands are in the program, or we will create a new stage window that will show all this information. In addition, this InfoView class will be responsible for displaying the history of the program (i.e. past commands or turtle history)



## API as Code
#### Your APIs should be written as Java interfaces, types that cannot contain instance variables or private methods, in appropriate packages. These should be Java code files that compile and contain extensive comments to explain the purpose of each interface and each method within the interface (note this code can be generated directly from a UML diagram). Include any Exceptions you plan to throw because of errors that might occur within your methods. Note, this does not require that all of these types will remain as interfaces in the final implementation, just that the goal is for you to focus on each type's behavior and purpose. Also include the steps needed to complete the Use Cases below to help make your ideas more concrete.

See APIs found in our APIs package in the Parser IntelliJ project.

See Use Cases below.

## Design Considerations
#### This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. Include any design decisions that the group discussed at length and describe at least one alternative in detail (including pros and cons from all sides of the discussion). Describe any assumptions or dependencies regarding the program that impact the overall design. This section should go into as much detail as necessary to cover all your team wants to say.

One design consideration that we discussed as a team was how we were going to update the model with the input new commands. For this problem, we thought of two different designs: 
    1. A Command superclass that we can extend subclasses from to make new commands as we want to. When a user inputs a command, a Command object will be passed to the model where the model can interpret the command object and update its database of what the turtle should do. This will allow for easy packaging of the data that the model needs to update itself but relies on adding code in the Model to process the Command object to get the necessary information.
    2. The Command class/Controller will send the information directly to the model as a packet of information, such as a String that the model will use to update itself. This takes away the need for the Model to do the logic required to update the database, but the model will need to somehow take in the values from the Controller, even if some values like a distance is missing and we are just updating a color instead. 

Our team still needs to discuss this at length in order to figure out the best way to pass information to the Model and how to make it work well with out APIs.


Another design consideration that we discussed as a team was the role of the controller in this project when dealing with logic and errors. In all forms of the controller, we agreed upon the idea that the Controller will check for errors, but the extent to which these errors are checked differ. The following are some ideas that we discussed:
    1. One role of the controller was to parse through the entire string command input given by the user and it will first check to see if the string includes any invalid syntax. This will automatically throw an exception and stops the command from going through. In this design, the controller would perform the logic of parsing through the command via a command and an argument stack to determine whether or not the input string has enough arguments for the commands. The Controller will then either instantiate the Command objects or send the finalized string output to the model.
    2. Conversely, the other design that we discussed was having the controller act only as an error checker for spelling and proper syntax. This would mean that it only checks to see if the commands given are actual commands and would pass the command string to the model. The model would then perform the logic of parsing through to see if the command and arguments match up. If it does not see an error with the commands and arguments given, the Model will create the commands and execute them to update itself.
    
This is another design issue that we need to discuss in length, but we think that we are going end up keeping all the logic that needs to be completed when parsing the command in the model.
    
    
## Team Responsibilities
#### This section describes the program components each team member plans to take primary and secondary responsibility for and a high-level plan of how the team will complete the program.

Note that these are general guidelines and some details may change if natural modifications to these responsibilities arise.

Frank Tang - In charge of the Backend Internal API, consisting of the ModelDatabase that performs all the logic and creation of the commands of the project. 

Benjamin Lu - Backend External API, mainly focused on how interpretor command and controller classes all interact, and how to send data to model for updating. 

Eric Doppelt - In charge of the UI, working on the View package  and focusing mainly on the internal aspect of the code. This includes visualizing the Turtle and its Path dynamically, while also displaying text, a command line, and buttons.

Erik Gregorio - In charge of UI and working with View package. Focusing on design of the external view which includes passing user input ot controllers and handling error.

Our team will work on each of our parts separately and integrate them together when we work together/on GIT. Several of us, most likely the frontend and backend groups, will work together extensively in pair meetings because these subgroups will interact with each other greatly. We plan on having meetings at least every 3 days to make sure that everyone is up to date with the project and that the project is constantly updated. In addition, we plan on being in close contact with each other using GroupMe to notify team members of the progress that is being made on the project.   

## Use Cases

### The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history.

1) After hitting Run, the command is sent to the Controller using sendCommand()
2) There, the message is checked for basic errors, and after none are found, calls sendInstruction() to the Model 
3) In the backend, executeCommand() is called and the command and argument stacks are created. This creates the command objects
4) addHistory() is then called to add the command history to the turtle
5) getInfoPackage()  and sendInfoPakcage is called in the controller to tell the View the information needed to update
6) With the Info now stored in View, updateTurtle() and updateGrid() are called. Note that the InfoBox does not need changing since it is an observable list connected to the Turtle's history.

### The user sets the pen's color using the UI so subsequent lines drawn when the turtle moves use that color.

1) The user clicks the Pen Color button and selects a new color
2) updatePenColor() is called within the UI to store the new color in the GridView object
    3) Note that this change is handled internally in the UI, since the model has no access to color.

### The user loads a new language
1) The user clicks the language dropdown box
2) The user selects a pre-made option
3) Doing so triggers a resourceBundle switch, changing the UI to the new language
    4) Note that this change also is handled internally in the UI (where resourceBundle can be an instance variable). As such, again, Model is seperated from view.

### User enters a commmand:
1) The string is retrieved from the dialog box using getCommand(). 
2) The string is then made available to getCommand() method for retrieval.
3) The controller then retrieves the command for error checking using the getCommand() method.
### Command Error:
1) The parser detects an error while parsing the command string. 
2) The parser then uses throwError(Error E) in order to notify the view that there has been an error. It also passes an Error along with a message detailing what has gone wrong.
3) View uses throwError to display an error message to the user notifying them that there has been an error with the command they tried to excute and displays the error message for further details.

