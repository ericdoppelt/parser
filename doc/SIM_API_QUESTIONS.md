Authors: Benjamin Lu (bll32), Eric Doppelt (ead45), Erik Gregorio (eg158), Frank Tang (ft39)

## API Discovery
### Working on Simulation Team 5

##### The overall idea is to have separation between model, controller, and view. No javafx code would be found in either model and view, no game logic would be found in model and view, and no grid creation and neighbor finding will be found in the controller or view. Such design allows for flexibility and ease of adding new simulation and features. 

#### Internal Simulation API 

Model - getCell, getNeighbors (square, hex, torus)

Model is an abstract class which essentially provides a certain cell and neighbors. Extensions of the abstract model class include the square and hex models which each have their unique getNeighbors logic. 

Controller - updateSim, resetSim, setState

Controller is an abstract class which handles the simulation logic. All controllers need to be able to setStates based of the rules of the sim, update the simulation, and reset the simulation. Extensions of the abstract controller class are the five basic simulations and extension simulations. Each child simulation class contains the three fundamental methods and their own private methods to handle respective simulation logic. 

#### External Simulation API

View - updateAllCells, initCells

View is a public class that displays the simulation. Because
all simulations use a form of a grid, there is no need to make simulation specific views. The only method needed for the view is to initialize the simulation view and update the simulation view. 


