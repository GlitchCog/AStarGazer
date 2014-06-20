package astargazer;

/**
 * The state of the algorithm. There is no initial state, just running, and there are two versions of the completed
 * state: one where a path is found and one where there is no valid path between the start and the goal points
 * 
 * @author Matt Yanos
 */
public enum StatusEnum
{
    RUNNING, 
    COMPLETED_FOUND, 
    COMPLETED_NOT_FOUND;
}