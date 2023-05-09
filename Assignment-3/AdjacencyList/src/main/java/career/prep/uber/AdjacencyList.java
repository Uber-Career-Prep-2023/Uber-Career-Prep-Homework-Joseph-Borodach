package career.prep.uber;

/**
 * Question 1: Build an Adjacency List/Set Representation of a Graph
 *
 * Instructions:
 *      - Given an array of pairs of values representing edges in an unweighted graph,
 *          create the equivalent adjacency list/set representation (a map from element to a list or set of elements).
 *      - Pairs represent directed edges: (A, B) means there is an edge from A to B.
 *      - If the pair (B, A) is also provided then there is an undirected edge between A and B.
 *      - For simplicity, you may assume that each node of the graph stores an integer rather than a generic data type and that the elements are distinct.
 *      - Implement a basic DFS and BFS searching for a target value and a topological sort (using either DFS or Kahn’s algorithm).
 *
 * Using ints vs generic nodes:
 *      Pros of ints:
 *          - Simplicity
 *      Pros of generic nodes:
 *          - More challenging and fun
 *          - Generics! Can be applied to different data types
 *      I chose to go with generics since the instructions used the word "may" which implied that I could choose:
 *          - "you may assume that each node of the graph stores an integer
 *
 * Data structure for storing edges:
 *      - Set
 *          - Pros:
 *              Don't need to worry about duplicates
 *      - Arrays
 *          - Cons:
 *              - Static Size: Either need to set size to the number of nodes - 1, or expand the array as nodes are added
 *                  - Since the problem didn't include a method to add edges to the graph this is not an issue
 *              - Must check explcitiy for duplciates
 *      - Lists
 *          - Pros:
 *              Dynamic size - Can just add a node to the list
 *          - Cons
 *
 * Since there were many trade offs for different data structures and whether to use ints or genetic nodes, I will discuss the trade offs but implement the API as is.
 */
public class AdjacencyList {

}





















