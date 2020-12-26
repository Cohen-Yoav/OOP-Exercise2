package homework2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.function.Executable;

import jdk.jfr.Timestamp;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * This class contains a set of test cases that can be used to test the graph
 * and shortest path finding algorithm implementations of homework assignment
 * #2.
 */
public class GraphTests extends ScriptFileTests {

	// black-box test are inherited from super
	public GraphTests(java.nio.file.Path testFile) {
		super(testFile);
	}

	@Test
	public void CheckGraphCtor(){
		Graph<WeightedNode> g1 = new Graph<>("g1");
		assertNotNull(g1);
	}

	@Test(timeout = 100)
	public void CheckAddOneNode () {
		Graph<WeightedNode> g1 = new Graph<>("g1");
		WeightedNode n1 = new WeightedNode("n1",2);
		try {
			g1.addNode(n1);
		}
		catch (Exception e) {
			assertFalse(e instanceof NodeExistsInGraphExeption);
		}
	}
	
	@Test(timeout = 100)
	public void checkAddNodeTwice() {
		Graph<WeightedNode> g1 = new Graph<>("g1");
		WeightedNode n1 = new WeightedNode("n1",2);
		try {
			g1.addNode(n1);
			g1.addNode(n1);
		}
		catch (Exception e) {
			assertTrue(e instanceof NodeExistsInGraphExeption);
		}
	}
	
	@Test()
	public void CheckAddEdge() {
		Graph<WeightedNode> g1 = new Graph<>("g1");
		WeightedNode n1 = new WeightedNode("n1",2);
		WeightedNode n2 = new WeightedNode("n2",4);
		try {
			g1.addNode(n1);
			g1.addNode(n2);
			g1.addEdge(n1,n2);
		}
		catch(Exception e){
			assertFalse(e instanceof NodeNotInGraphException);
			assertFalse(e instanceof NodeExistsInGraphExeption);
			assertFalse(e instanceof EdgeExistsInGraphExeption);
		}
	}


	@Test
	public void CheckAddEdgeParentNotInGraph(){
		Graph<WeightedNode> g1 = new Graph<>("g1");
		WeightedNode n1 = new WeightedNode("n1",2);
		WeightedNode n2 = new WeightedNode("n2",4);
		try {
			g1.addNode(n1);
			g1.addEdge(n2,n1);
		}
		catch(Exception e){
			assertTrue(e instanceof NodeNotInGraphException);
			assertFalse(e instanceof NodeExistsInGraphExeption);
			assertFalse(e instanceof EdgeExistsInGraphExeption);
		} 
	}

	@Test 
	public void CheckAddEdgeTwice(){
		Graph<WeightedNode> g1 = new Graph<>("g1");
		WeightedNode n1 = new WeightedNode("n1",2);
		WeightedNode n2 = new WeightedNode("n2",4);
		try {
			g1.addNode(n1);
			g1.addNode(n2);
			g1.addEdge(n1,n2);
			g1.addEdge(n1,n2);
		}
		catch(Exception e){
			assertFalse(e instanceof NodeNotInGraphException);
			assertFalse(e instanceof NodeExistsInGraphExeption);
			assertTrue(e instanceof EdgeExistsInGraphExeption);
		} 
	}

	@Test 
	public void CheckGetNodes(){
		Graph<WeightedNode> g1 = new Graph<>("g1");
		WeightedNode n1 = new WeightedNode("n1",2);
		WeightedNode n2 = new WeightedNode("n2",4);
		try {
			g1.addNode(n1);
			g1.addNode(n2);
			g1.getNodes();
		}
		catch(Exception e){
			assertFalse(e instanceof NodeNotInGraphException);
			assertFalse(e instanceof NodeExistsInGraphExeption);
			assertFalse(e instanceof EmptyGraphException);
		} 
	}

	@Test 
	public void CheckGetNodesEmptyGraph(){
		Graph<WeightedNode> g1 = new Graph<>("g1");
		try {
			g1.getNodes();
		}
		catch(Exception e){
			assertTrue(e instanceof EmptyGraphException);
		} 
	}

	@Test()
	public void CheckGetChildren() {
		Graph<WeightedNode> g1 = new Graph<>("g1");
		WeightedNode n1 = new WeightedNode("n1",2);
		WeightedNode n2 = new WeightedNode("n2",4);
		try {
			g1.addNode(n1);
			g1.addNode(n2);
			g1.addEdge(n1,n2);
			g1.getChildrenNodes(n1);
		}
		catch(Exception e){
			assertFalse(e instanceof NodeNotInGraphException);
			assertFalse(e instanceof NodeExistsInGraphExeption);
			assertFalse(e instanceof EdgeExistsInGraphExeption);
			assertFalse(e instanceof NoChildrenException);
		}
	}
	
	@Test()
	public void CheckGetChildrenNoChildren() {
		Graph<WeightedNode> g1 = new Graph<>("g1");
		WeightedNode n1 = new WeightedNode("n1",2);
		WeightedNode n2 = new WeightedNode("n2",4);
		try {
			g1.addNode(n1);
			g1.addNode(n2);
			g1.getChildrenNodes(n1);
		}
		catch(Exception e){
			assertFalse(e instanceof NodeNotInGraphException);
			assertFalse(e instanceof NodeExistsInGraphExeption);
			assertTrue(e instanceof NoChildrenException);
		}
	}
	
	@Test()
	public void CheckGetChildrenParentNotInGraph() {
		Graph<WeightedNode> g1 = new Graph<>("g1");
		WeightedNode n1 = new WeightedNode("n1",2);
		WeightedNode n2 = new WeightedNode("n2",4);
		try {
			g1.addNode(n2);
			g1.getChildrenNodes(n1);
		}
		catch(Exception e){
			assertTrue(e instanceof NodeNotInGraphException);
			assertFalse(e instanceof NodeExistsInGraphExeption);
			assertFalse(e instanceof NoChildrenException);
		}
	}

	@Test
	public void CheckGetGraphName() {
		Graph<WeightedNode> g1 = new Graph<>("g1");
		assertEquals("success","g1",g1.getGraphName());
	}

	@Test
	public void CheckPathFinderEmptyStart(){
		Graph<WeightedNode> g1 = new Graph<>("g1");
		Set<WeightedNodePath> strtA = new HashSet<>();
		Set<WeightedNode> goalsA = new HashSet<>();
		WeightedNode n1 = new WeightedNode("n1",2);
		PathFinder<WeightedNode,WeightedNodePath> pf = new PathFinder<>();
		goalsA.add(n1);
		try{
			g1.addNode(n1);
			pf.findShortestPath(g1,strtA,goalsA);
		}
		catch(Exception e){
			assertFalse(e instanceof NodeNotInGraphException);
			assertFalse(e instanceof NodeExistsInGraphExeption);
			assertTrue(e instanceof NotValidArgumentException);
			assertFalse(e instanceof EmptyGraphException);
		}
	}

	@Test
	public void CheckPathFinderEmptyGoals(){
		Graph<WeightedNode> g1 = new Graph<>("g1");
		Set<WeightedNodePath> startA = new HashSet<>();
		Set<WeightedNode> goalsA = new HashSet<>();
		WeightedNode n1 = new WeightedNode("n1",2);
		PathFinder<WeightedNode,WeightedNodePath> pf = new PathFinder<>();
		startA.add(new WeightedNodePath(n1));
		try{
			g1.addNode(n1);
			pf.findShortestPath(g1,startA,goalsA);
		}
		catch(Exception e){
			assertFalse(e instanceof NodeNotInGraphException);
			assertFalse(e instanceof NodeExistsInGraphExeption);
			assertTrue(e instanceof NotValidArgumentException);
			assertFalse(e instanceof EmptyGraphException);
		}
	}

	@Test
	public void CheckPathFinderEmptyGraph(){
		Graph<WeightedNode> g1 = new Graph<>("g1");
		Set<WeightedNodePath> startA = new HashSet<>();
		Set<WeightedNode> goalsA = new HashSet<>();
		WeightedNode n1 = new WeightedNode("n1",2);
		PathFinder<WeightedNode,WeightedNodePath> pf = new PathFinder<>();
		startA.add(new WeightedNodePath(n1));
		goalsA.add(n1);
		try{
			pf.findShortestPath(g1,startA,goalsA);
		}
		catch(Exception e){
			assertFalse(e instanceof NotValidArgumentException);
			assertTrue(e instanceof EmptyGraphException);
		}
	}

	@Test
	public void CheckPathFinderForLoopOneIter(){
		Graph<WeightedNode> g1 = Build_Graph();
		WeightedNode[] nodes = null;
		try {
			nodes = new WeightedNode[g1.getNodes().size()];
			g1.getNodes().toArray(nodes);
		}
		catch(Exception e){}
		Set<WeightedNodePath> startA = new HashSet<>();
		Set<WeightedNode> goalsA = new HashSet<>();
		PathFinder<WeightedNode,WeightedNodePath> pf = new PathFinder<>();

		startA.add(new WeightedNodePath(nodes[0]));
		goalsA.add(nodes[4]);
		try{
			pf.findShortestPath(g1,startA,goalsA);
		}
		catch(Exception e){
			assertFalse(e instanceof NotValidArgumentException);
			assertFalse(e instanceof EmptyGraphException);
		}
	}

	@Test
	public void CheckPathFinderForLoopfiveIter(){
		Graph<WeightedNode> g1 = Build_Graph();
		WeightedNode[] nodes = null;
		try {
			nodes = new WeightedNode[g1.getNodes().size()];
			g1.getNodes().toArray(nodes);
		}
		catch(Exception e){}

		Set<WeightedNodePath> startA = new HashSet<>();
		Set<WeightedNode> goalsA = new HashSet<>();
		PathFinder<WeightedNode,WeightedNodePath> pf = new PathFinder<>();
		
		for (WeightedNode wn : nodes) {
			startA.add(new WeightedNodePath(wn));
		}
		goalsA.add(nodes[0]);
		try{
			pf.findShortestPath(g1,startA,goalsA);
		}
		catch(Exception e){
			assertFalse(e instanceof NotValidArgumentException);
			assertFalse(e instanceof EmptyGraphException);
		}
	}

	@Test
	public void CheckPathFinderNoWhile(){
		Graph<WeightedNode> g1 = Build_Graph();
		WeightedNode[] nodes = null;
		try {
			nodes = new WeightedNode[g1.getNodes().size()];
			g1.getNodes().toArray(nodes);
		}
		catch(Exception e){}

		Set<WeightedNodePath> startA = new HashSet<>();
		Set<WeightedNode> goalsA = new HashSet<>();
		PathFinder<WeightedNode,WeightedNodePath> pf = new PathFinder<>();
		
		startA.add(new WeightedNodePath(new WeightedNode("Binyamin",27)));
		goalsA.add(nodes[0]);
		WeightedNodePath p = null;
		try{
			p = pf.findShortestPath(g1,startA,goalsA);
		}
		catch(Exception e){
			assertFalse(e instanceof NotValidArgumentException);
			assertFalse(e instanceof EmptyGraphException);
		}
		assertNull(p);
	}

	@Test
	public void CheckPathFinderWhileOneIter(){
		Graph<WeightedNode> g1 = Build_Graph();
		WeightedNode[] nodes = null;
		try {
			nodes = new WeightedNode[g1.getNodes().size()];
			g1.getNodes().toArray(nodes);
		}
		catch(Exception e){}

		Set<WeightedNodePath> startA = new HashSet<>();
		Set<WeightedNode> goalsA = new HashSet<>();
		PathFinder<WeightedNode,WeightedNodePath> pf = new PathFinder<>();
		
		startA.add(new WeightedNodePath(nodes[0]));
		goalsA.add(nodes[0]);
		WeightedNodePath p = null;
		try{
			p = pf.findShortestPath(g1,startA,goalsA);
		}
		catch(Exception e){
			assertFalse(e instanceof NotValidArgumentException);
			assertFalse(e instanceof EmptyGraphException);
		}
		assertEquals(2.0, p.getCost(), 0.01);
	}

	public void CheckPathFinderWhileFiveIter(){
		Graph<WeightedNode> g1 = Build_Graph();
		WeightedNode[] nodes = null;
		try {
			nodes = new WeightedNode[g1.getNodes().size()];
			g1.getNodes().toArray(nodes);
		}
		catch(Exception e){}

		Set<WeightedNodePath> startA = new HashSet<>();
		Set<WeightedNode> goalsA = new HashSet<>();
		PathFinder<WeightedNode,WeightedNodePath> pf = new PathFinder<>();
		
		startA.add(new WeightedNodePath(nodes[0]));
		goalsA.add(nodes[4]);
		WeightedNodePath p = null;
		try{
			p = pf.findShortestPath(g1,startA,goalsA);
		}
		catch(Exception e){
			assertFalse(e instanceof NotValidArgumentException);
			assertFalse(e instanceof EmptyGraphException);
		}
		assertEquals(8.0, p.getCost(), 0.01);
	}
	

	private Graph<WeightedNode> Build_Graph () {

		Graph<WeightedNode> g = new Graph<>("g1");
		WeightedNode A = new WeightedNode("A",2);
		WeightedNode B = new WeightedNode("B",1);
		WeightedNode C = new WeightedNode("C",3);
		WeightedNode D = new WeightedNode("D",1);
		WeightedNode E = new WeightedNode("E",4);
		
		try {
			g.addNode(A);
			g.addNode(B);
			g.addNode(C);
			g.addNode(D);
			g.addNode(E);

			g.addEdge(A,B);
			g.addEdge(A,C);
			g.addEdge(B,C);
			g.addEdge(B,D);
			g.addEdge(C,D);
			g.addEdge(C,E);
			g.addEdge(D,E);
		}
		catch (Exception e){}

		return g;
	}
}
