# Note The Following Changes: 
- Each question should have a hyperlink for the implementation class and test class to save time locating the relevant classes.

# Part 1: Data Structure Implementations
## Instructions
- If you are unfamiliar with graph or heaps, please read the relevant articles before attempting the corresponding problems:
  - Graphs:
    - Graph representations
    - Topological sorting
    - Kahn’s algorithm
    - Directed vs. undirected graphs
    - Cyclic vs. acyclic graphs
    - Weighted vs. unweighted graphs
  - Heaps:
    - Definition & types of heap
    - Array representation
- You’re welcome to consult other internet resources on the definition and use cases of these data structures, but do not read any implementations before attempting these problems. 
- (If you’ve seen or created implementations in the past, of course that’s okay, but do these problems without consulting them.)
- The core implementations do not have a maximum time; you will need them to understand in order to do the rest of the assignment. 
- If you are stuck, please get help from your mentor.
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
## Instructions
- For each problem, identify the appropriate data structure for solving the problem. 
- For graph problems, also identify the appropriate graph algorithm. 
- State the data structure (and algorithm, if applicable) in a comment at the top of your file. 
- Then, write a function to solve the problem and write test cases to check your function. 
- When run, the file you submit should execute your function on your test cases (e.g., through a main method, if applicable in your language). 
- State the time and space complexity of your solution in a comment at the top of your file.
- Time how long you spend on each problem. 
- You should actively work on each problem for a MAXIMUM of 40 minutes. Once 40 minutes has elapsed, submit whatever you have, regardless of whether you are finished. 
- Please indicate in a comment at the bottom of your file how long you spent on the problem. 
- It is important that you are honest about how long each problem took you as it will help your mentor help you!

## Question 4: NumberOfIslands
- [Implementation Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/NumberOfIslands/src/main/java/career/prep/uber/NumberOfIslands.java)
- [Test Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/NumberOfIslands/src/test/java/career/prep/uber/NumberOfIslandsTests.java)
- Given a binary matrix in which 1s represent land and 0s represent water. 
- Return the number of islands (contiguous 1s surrounded by 0s or the edge of the matrix).
<img width="753" alt="Screenshot 2023-05-23 at 09 53 05" src="https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/assets/95253429/8017e96b-7a8c-432c-b1be-486768d233d6">

## Question 5: FirstKBinaryNumbers
- [Implementation Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/FirstKBinaryNumbers/src/main/java/career/prep/uber/FirstKBinaryNumbers.java)
- [Test Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/FirstKBinaryNumbers/src/test/java/career/prep/uber/FirstKBinaryNumbersTests.java)
- Given a number, k, return an array of the first k binary numbers, represented as strings.
<img width="1075" alt="Screenshot 2023-05-23 at 11 17 38" src="https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/assets/95253429/f4c028f8-344e-4c37-984b-a3c6cb813837">

## Question 6: RoadNetworks
- [Implementation Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/RoadNetworks/src/main/java/career/prep/uber/RoadNetworks.java)
- [Test Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/RoadNetworks/src/test/java/career/prep/uber/RoadNetworksTests.java)
- In some states, it is not possible to drive between any two towns because they are not connected to the same road network. 
- Given a list of towns and a list of pairs representing roads between towns, return the number of road networks. 
- (For example, a state in which all towns are connected by roads has 1 road network, and a state in which none of the towns are connected by roads has 0 road networks.)
<img width="497" alt="Screenshot 2023-05-24 at 08 37 48" src="https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/assets/95253429/b0093da3-fbab-4ce8-8fb8-e111351f5cc2">
<img width="508" alt="Screenshot 2023-05-24 at 08 38 02" src="https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/assets/95253429/6c37428c-8bdf-4e6a-8605-f42a38b13db3">

## Question 7: ReverseWords
- [Implementation Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/ReverseWords/src/main/java/career/prep/uber/ReverseWords.java)
- [Test Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/ReverseWords/src/test/java/career/prep/uber/ReverseWordsTests.java)
- Given a string, return the string with the order of the space-separated words reversed.
<img width="418" alt="Screenshot 2023-05-25 at 12 57 41" src="https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/assets/95253429/3cbc2d55-a399-4359-80d9-9bed8f6ce1fd">

## Question 8: AlternatingPath
- [Implementation Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/AlternatingPath/src/main/java/career/prep/uber/AlternatingPath.java)
- [Test Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/AlternatingPath/src/test/java/career/prep/uber/AlternatingPathTests.java)
- Given an origin and a destination in a directed graph in which edges can be blue or red.
- Determine the length of the shortest path from the origin to the destination in which the edges traversed alternate in color. 
- Return -1 if no such path exists.
<img width="797" alt="Screenshot 2023-05-25 at 14 23 51" src="https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/assets/95253429/15089e80-bb7f-4059-97e6-591d7a70ee42">

## Question 9: MergeKSortedArrays
- [Implementation Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/MergeKSortedArrays/src/main/java/career/prep/uber/MergeKSortedArrays.java)
- [Test Class](https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/blob/main/Assignment-3/MergeKSortedArrays/src/test/java/career/prep/uber/MergeKSortedArraysTests.java)
- Given an array of k sorted arrays, merge the k arrays into a single sorted array.
<img width="520" alt="Screenshot 2023-05-29 at 07 45 09" src="https://github.com/Uber-Career-Prep-2023/Uber-Career-Prep-Homework-Joseph-Borodach/assets/95253429/2099052f-3962-4e27-b3dd-4bbf100f26a1">

## Question 10: PrerequisiteCourses
- [Implementation Class]()
- [Test Class]()
- Given: 
  - A list of courses that a student needs to take to complete their major.
  - A map of courses to their prerequisites.
- Return a valid order for them to take their courses assuming they only take 1 course for their major at once.

## Question 11: 
- [Implementation Class]()
- [Test Class]()
- 
