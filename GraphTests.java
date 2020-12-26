package homework2;

import static org.junit.Assert.*;

import org.junit.Test;

import jdk.jfr.Timestamp;

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

	@Timestamp
	void addNode (Graph<WeightedNode> g, WeightedNode n) {
		try {
			g.addNode(n);
		}
		catch (NodeExistsInGraphExeption e) {
			throw new NodeExistsInGraphExeption(e);
		}
	}
	@Test(timeout = 100, expected=NodeExistsInGraphExeption.class)
	public void CheckAddedNodeTwice() {
		WeightedNode n1 = new WeightedNode("n1",2);
		Graph<WeightedNode> g1 = new Graph<>("g1");
		addNode(g1,n1);
		addNode(g1,n1);		
	}
	

}
