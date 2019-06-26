package scheletri;

import java.awt.Graphics;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
	
	// EDITOR
	String string = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ._";
	
	private EventsDao dao;
	private Map<Integer,District> dMap;
	private Graph<District,DefaultWeightedEdge> graph;
	
	// Recursion
	private List<E> best;
	
	
	public Model() {
		dao  = new EventsDao();
		dMap = new HashMap<>();
	}
	
	public List<Year> getYearsList(){
		return dao.yearsList();
	}
	
	public void buildGraph() {
		graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(graph, );
		
		
		
		
		System.out.println("#vertici: "graph.vertexSet().size());
		System.out.println("#archi: "graph.edgeSet().size());
	}
	
	public void init() {
		best = new ArrayList<>();
		List<E> partial = new ArrayList<>();
		
		recursion(partial);
	}
	
	private void recursion(partial) {
		
		if () {
			best = new ArrayList<>(partial);
			System.out.println(best.size());
			return;
		}
		
		if ( )
			return;
		
		for ( ) {
			
			partial.add();
			recursion(partial);
			partial.remove();
		}
	}
	
	public Graph<District, DefaultWeightedEdge> getGraph() {
		return graph;
	}

	public Map<Integer, District> getdMap() {
		return dMap;
	}

}
