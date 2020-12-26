package homework2;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A Graph models a Collection of Nodes and Edges. Graphs are mutable.
 * Edges are directed, the edge direction defines the parent node and child node.
 * we assume that there is only one edge from parent node to child node.
 * we assume that the type Node is immutable.
 */
public class Graph<Node extends Comparable<Node>> {

    // RepInvariant:
  	//   (g_name != null) && (node_map != null)
  	//   
  	//

  	// Abstraction Function:
  	//
    //  Directed Graph is an object the represents a collection of :
    // nodes - a group of object, that can be complex.
    // edges - represents the connection between the different nodes.
          
    private final Map<Node,Set<Node>> node_map;
    private final String g_name;

    /**
      * c`tor of Graph
      * @requires name != null
      * @modifies 
      * @effects Creates a new graph with g_name name
      */
    public Graph(String name) { //TODO WB check? add assert?
      node_map = new HashMap<>();
      this.g_name = name;
      checkRep();
    } 
      
    /**
      * Extend this Graph by adding a new node to it.
      * @requires new_node != null &&
      *           new_node is a valid node type for this particular Graph implementation 
      * @modifies this
      * @effects add the node to the container of Nodes in this Graph.
      * @throws NodeExistsInGraphExeption if the node is already in this
      */
    public void addNode(Node node) throws 
    NodeExistsInGraphExeption{
      checkRep();
      if (node_map.putIfAbsent(node , new HashSet<>()) != null) {
    	  checkRep();
    	  throw new NodeExistsInGraphExeption("Node is already in the graph");		
      }
      checkRep();
    }

    /**
      * Extend this Graph by adding a new Edge to it.\n
      * Set a directed edge from parent node to child node 
      * @requires parent_node != null && child_node != null
      * @modifies this
      * @effects add the edge to the container of edges in this Graph
      * @throws EdgeExistsInGraphExeption if the Edge is already in this
      * @throws NodeNotInGraphException if parent_node || child_nod not in graph
    */  
    public void addEdge(Node parent_node, Node child_node) throws 
    EdgeExistsInGraphExeption, NodeNotInGraphException{
      checkRep();
      if (!node_map.containsKey(parent_node) ||
    	  !node_map.containsKey(child_node)) {
    	  throw new NodeNotInGraphException("parent node or child node is not in the graph");
      }
      if (node_map.get(parent_node).contains(child_node)) {
    	  throw new EdgeExistsInGraphExeption("Edge is in the graph");
      }
      node_map.get(parent_node).add(child_node);
      checkRep();
    }

    /**
      * Return a unmodifiable Set of all the nodes in this
      * @requires 
      * @modifies 
      * @return a unmodifiable Set of all the nodes in this
      * @throws EmptyGraphException if there is no nodes in the graph
    */
    public Set<Node> getNodes () throws 
    EmptyGraphException{
      checkRep();
      if (node_map.isEmpty()) {
        checkRep();
        throw new EmptyGraphException("The graph has no nodes to return") ;
      }
      checkRep();
      return Collections.unmodifiableSet(node_map.keySet());
     
    }
    
    /**
      * Return a unmodifiable Set of all the Children nodes of parent node
      * @requires parent_node != null
      * @modifies 
      * @return a unmodifiable Set of all the Children nodes of parent node
      * @throws NoChildrenException if there is no Children nodes 
      * @throws NodeNotInGraphException if parent node not in graph
    */
    public Set<Node> getChildrenNodes(Node parent_node) throws 
    NodeNotInGraphException , NoChildrenException{
      checkRep();
      if (!node_map.containsKey(parent_node)){
        checkRep();
        throw new NodeNotInGraphException("node not in the graph") ; 
      }
      if(node_map.get(parent_node).isEmpty()){
        checkRep();
        throw new NoChildrenException("this node has no children") ;
      }
      checkRep();
      return Collections.unmodifiableSet(node_map.get(parent_node));
    }

    /**
     * return the name of the Graph
     * @requires
     * @modifies 
     * @return the name of the Graph
     */
    public String getGraphName() {
      checkRep();
      return g_name;
    }
    
    private void checkRep(){
      assert ( g_name != null ) :
      "graph name cannot be a null pointer ";
      assert (node_map != null ) :
      "the map of the graph cannot be null pointer";
    }
}
///yoav and binyamin learn git

