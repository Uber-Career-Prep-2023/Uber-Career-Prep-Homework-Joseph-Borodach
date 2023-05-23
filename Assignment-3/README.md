# Note The Following Changes: 
- Each question should have a hyperlink for the implementation class and test class to save time locating the relevant classes.

# Part 1: Data Structure Implementations
## Question 1: Build an Adjacency List/Set Representation of a Graph
- [Implementation Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/AdjacencyList/src/main/java/career/prep/uber/AdjacencyList.java)
- Tests Classes:
  - [Adjacency List/Set](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/AdjacencyList/src/test/java/career/prep/uber/AdjacencyListTests.java)
  - [DFS **&** BFS](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/AdjacencyList/src/test/java/career/prep/uber/GraphTraversalTests.java)
  - [Topological Sort](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/AdjacencyList/src/test/java/career/prep/uber/TopologicalSortTests.java)
- Given an array of pairs of values representing edges in an unweighted graph, create the equivalent adjacency list/set representation (a map from element to a list or set of elements). Pairs represent directed edges: (A, B) means there is an edge from A to B. 
- If the pair (B, A) is also provided then there is an undirected edge between A and B. 
- For simplicity, you may assume that each node of the graph stores an integer rather than a generic data type and that the elements are distinct. 
- Implement a basic DFS and BFS searching for a target value and a topological sort (using either DFS or Kahn’s algorithm).
<img width="668" alt="Screenshot 2023-05-09 at 14 05 44" src="https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/assets/95253429/c33189d8-7bbb-44b2-b98d-be79f712dfef">
<img width="468" alt="Screenshot 2023-05-09 at 14 05 59" src="https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/assets/95253429/55383fe5-efa8-40a7-a813-8d3c811aadf7">

## Question 2: Build a Heap
- [Implementation Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/Heap/src/main/java/career/prep/uber/Heap.java)
- [Test Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/Heap/src/test/java/career.prep.uber/HeapTests.java)
- Write a min heap class according to the following API using an array as the underlying data structure. 
- (A max heap has the same implementation; you simply need to flip the direction of the comparators.) 
- For simplicity, you can assume that the heap holds integers rather than generic comparables.
<img width="738" alt="Screenshot 2023-05-21 at 16 35 36" src="https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/assets/95253429/4c4fcd6c-9895-4bb9-acbc-10e5e1a3f69d">

## Question 3: Build a Priority Queue
- [Implementation Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/PriorityQueue/src/main/java/career/prep/uber/PriorityQueue.java)
- [Test Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/PriorityQueue/src/test/java/career/prep/uber/PriorityQueueTests.java)
- A priority queue functions like a queue except that elements are removed in order of priority rather than insertion. 
- This is typically implemented as a max heap that stores pairs of elements and numeric weights, with the weights used as the values in the heap. 
- Implement a priority queue according to the following API using a heap as the underlying data structure. 
- For simplicity, you can assume the priority queue stores string elements with integer priorities. 
- Start by copy-pasting your heap implementation from question 2 and making modifications.
<img width="1252" alt="Screenshot 2023-05-22 at 09 50 34" src="https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/assets/95253429/be417b88-67a5-4f9b-a583-d6cdb989168e">

# Part 2: Graph & Data Structure Selection Problems
## Question 4: NumberOfIslands
- [Implementation Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/NumberOfIslands/src/main/java/career/prep/uber/NumberOfIslands.java)
- [Test Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/NumberOfIslands/src/test/java/career/prep/uber/NumberOfIslandsTests.java)
- Given a binary matrix in which 1s represent land and 0s represent water. 
- Return the number of islands (contiguous 1s surrounded by 0s or the edge of the matrix).
<img width="753" alt="Screenshot 2023-05-23 at 09 53 05" src="https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/assets/95253429/8017e96b-7a8c-432c-b1be-486768d233d6">
