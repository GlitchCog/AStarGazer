AStarGazer
==========

AStarGazer is a program for visualizing the A* search algorithm on a Tile Map.

The A* search algorithm (en.wikipedia.org/wiki/A_Star_Search_Algorithm) is 
used to traverse a connection of nodes, called a graph. This implementation 
uses it to find a path between a starting point and a goal point on a two-
dimensional grid, a problem often encountered in tile-based video game AI. It 
also displays the inner workings of the algorithm by coloring the open and 
closed sets of nodes on the map and drawing a line representing the current 
path being investigated.

Usage Instructions:

Step - Moves the algorithm forward one step. A step is defined as popping the 
       minimum cost node from the open set, analyzing its cost with a 
       heuristic, and adding its valid neighbors to the open set.

Solve - Continues to step until the algorithm is complete.

Reset - Resets the algorithm to its initial state to be re-run.

Generate - Generates a new random map and start and goal points.

Heuristics - Select which heuristic to use, meaning how should the distance 
             between two points be evaluated in the algorithm.

    Manhattan - Manhattan (or Taxicab) distance only permits travelling along 
                the x and y axes, so the distance between (0, 0) and (1, 1) is 
                2 from travelling along the X axis 1 unit and then up the Y 
                axis 1 unit, and not the hypotenuse, which would be sqrt(2)/2. 
                It's so named because in a city you're bound to the streets. 
                You can't cut diagonally through a building to go north/south 
                one block and east/west one block.
    Euclidean - This is normal distance calculated by the Pythagorean formula: 
                d = sqrt((a_x-b_x)^2+(a_y-b_y)^2)
    Euclidean Squared - This is the normal distance formula, but without 
                taking the square root. Because the distance is only used to 
                compare, comparing the squares of two values still works to 
                determine which path is shorter. This heuristic is an 
                optimized version of the Euclidean hueuristic.

Neighbors - Select the method by which neighboring tiles are selected.

    4-directional - Neighbors are selected from the traversable tiles to the 
                    north, east, south, and west only. This works best with 
                    the Manhattan heuristic.
    8-directional - Neighbors are selected from diagonally adjacent tiles in 
                    addition to the north, east, south, and west directions. 
                    This works best with the Euclidean heuristic. Combining 
                    this neighbor selection scheme with the Manhattan 
                    heuristic can yield strange results because a diagonal 
                    path over a single tile is counted as a distance of 2.
    Jump Point -    Neighbors are selected by extending rays out from the 
                    current tile until an obstacle or the goal is hit. These 
                    tiles are "neighbors" in the sense that they are to be 
                    used in the next step of the algorithm, but are not 
                    necessarily adjacent.

Colors - Select a color scheme for the map panel.

Solve Delay - How long the solve process wait before running the next step.

Zoom - Zoom in and out of the map panel.

Generate Obstacles - This option will cause maps to generate with random 
                     obstacle blocks to force the algorithm to find its way 
                     around things.

Full Dijkstra Search (h=0) - This option sets the h value to zero, meaning 
                     there is no incentive for the algorithm to select a good 
                     path to test first. It treats all points equally in terms 
                     of how far is left to travel. This converts the A* search 
                     algorithm into the more generalized Dijkstra's algorithm.

Randomize Equicost Nodes - Selecting this option will shuffle the neighboring 
                     points before they are sorted prior to being pushed onto 
                     the open set. One feature of the Manhattan heuristic is 
                     that on a full grid it doesn't matter when you choose to 
                     go along the X axis and when you choose to go along the Y 
                     axis. You could travel only in the Y direction until you 
                     were at the correct row, and then travel only in the X 
                     direction until you reach your destination. Alternatively 
                     you could zigzag with every step. The distance travelled 
                     is the same in both cases. This means that there are lots 
                     of equidistant paths to decide between when using a 
                     Manhattan heuristic. The decision to try one direction 
                     instead of another frequently becomes a factor of which 
                     is added to the set first, and the sort will just leave 
                     equal cost nodes in their original ordering. Randomizing 
                     them will prevent the heuristic from favoring one 
                     direction simply because tiles in that direction are 
                     added to the set before tiles in another direction.

