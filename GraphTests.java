package homework2;

import static org.junit.Assert.*;

import org.junit.Test;

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

	@Test(timeout = 100)
	public void addNodeCheck() {
		WeightedNode n1 = new WeightedNode("n1",2);
		WeightedNode n2 = new WeightedNode("n2",4);
		Graph<WeightedNode> g1 = new Graph<>("g1");
		try {
			g1.addNode(n1);
		}
		catch (NodeExistsInGraphExeption e) {
			
		}
		
	}
	

}
