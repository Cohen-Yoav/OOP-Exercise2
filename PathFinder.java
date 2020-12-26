package homework2;

import java.util.HashMap;

/**
 * 
 * A PathFinder is a generic class that finds a shortest path in a given graph
 * <p>
 * <b>The following generic arguments are used in this class:</b>
 * <pre>
 *   N : class Object that stands for Node, should extends Comparable and be immutable
 *   P : class Object that stands for Path, should extends the inteface Path
 * </pre> 
 * </p>
 * </p>
 */

import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class PathFinder<N extends Comparable<N>,P extends Path<N, P>>  {
    
	/**
     * find a shortest path in the given graph
     * The path found start in one of the given starts and end in one of the given goals
     * The algorithm check each start node if it exist in the given graph
     * in case there is a node that is not part of the graph it will be ignored
     * @requires graph != null && starts =! null && goals != null
     * @modifies 
     * @return a shortest path from one of the starts to one of the goals
     * @return null in case no path was found
     * @throws NotValidArgumentException in case of empty graph || empty starts || empty goals
     * 		   with a suitable error massage
     * @throws EmptyGraphException in case the given graph is empty
     */
    public P findShortestPath(Graph<N> graph , Set<P> starts, Set<N> goals) //TODO handle Exeptions
    throws NotValidArgumentException, EmptyGraphException{
    	
    	graph.getNodes();
    	if (starts.isEmpty()) {
    		throw new NotValidArgumentException("starts is empty");
    	}
    	
    	if (goals.isEmpty()) {
    		throw new NotValidArgumentException("goals is empty");
    	}
    	
        Map<N,P> paths = new HashMap<>();
        PriorityQueue<P> active = new PriorityQueue<>();
        Set<N> finished = new HashSet<>();

        for (P start : starts ){
        	try {
        		graph.getChildrenNodes(start.getEnd());
        	}
        	catch (NoChildrenException e) {}
        	catch (NodeNotInGraphException e) { continue; }
            paths.putIfAbsent(start.getEnd(), start);
            active.add(start);
        }   

        // the action of the algorithm

        while (!active.isEmpty()){
            P queue_min = active.poll();
            P queue_min_path = queue_min;
            Set<N> children = null;
            
            if (goals.contains(queue_min_path.getEnd())){
                return queue_min_path;
            }

            boolean in_active = false;
            try {
            	children = graph.getChildrenNodes(queue_min_path.getEnd());
            }
            catch (NoChildrenException e) {
            	finished.add(queue_min.getEnd());
            	continue;
            }
            
            catch (Exception e) {}
            
            for (N c : children) {
            	in_active = false;
                P cpath = queue_min_path.extend(c);
                if (!finished.contains(c)){
                    for (P path : active) {
                        if (path.getEnd().equals(c)){
                            in_active = true;
                            break;
                        }
                    }
                    if(!in_active) {
                        paths.putIfAbsent(c, cpath);
                        active.add(cpath);
                    }
                }
            }
            finished.add(queue_min.getEnd());
        }
        return null;
    }
}
