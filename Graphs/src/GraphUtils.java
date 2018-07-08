

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class GraphUtils {

	public static int minDistance(Graph graph, String src, String dest) {

		/* IMPLEMENT THIS METHOD! */
		if(graph==null || src==null|| dest==null ) return -1;
		if(src.equals(dest)) return 0;
		if(graph.containsElement(src) && graph.containsElement(dest))
		{	
			Set<Node> marked=new HashSet<Node>();
			Queue<Node> toExplore=new LinkedList<Node>();
			HashMap<Node,Integer> noOfEdges=new HashMap<Node,Integer>();
			noOfEdges.put(graph.getNode(src), 0);
			toExplore.add(graph.getNode(src));
			Node required=graph.getNode(dest);
			int level=0;
			boolean flag=false;
			while(!toExplore.isEmpty()&& toExplore!=null )
			{
				Node toExplor=toExplore.remove();
				level=noOfEdges.get(toExplor);
				level++;
				for(Node node:graph.getNodeNeighbors(toExplor))
				{
					if(noOfEdges.containsKey(node) && level<noOfEdges.get(node))
					{
						noOfEdges.put(node, level);
						
					}
					if(node.equals(required)) flag=true;
					//noOfEdges.put(node, level);
					if(!marked.contains(node) && !noOfEdges.containsKey(node))
                    { toExplore.add(node);
                     noOfEdges.put(node, level);
                    }
				}
				marked.add(toExplor);
			}
			if(flag)
			return noOfEdges.get(required);
		}
		return -1;
	}
	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {

		
		/* IMPLEMENT THIS METHOD! */
		if(graph==null || src==null|| !graph.containsElement(src)|| distance<1) return null;
		Set<String> setOfNodes=new HashSet<String>();
        if(distance>=1 && graph.containsElement(src))
		{
			
			
			HashMap<Node,Integer> noOfEdges=new HashMap<Node,Integer>();
			noOfEdges.put(graph.getNode(src), 0);
			Stack<Node> nodesWithinLevel=new Stack<Node>();
			nodesWithinLevel.add(graph.getNode(src));
			int level=0;
			while(!nodesWithinLevel.isEmpty() && nodesWithinLevel!=null)
			{
				Node toExplor=nodesWithinLevel.pop();
				level=noOfEdges.get(toExplor);
				level++;
				if(level<=distance)
				{
				for(Node node:graph.getNodeNeighbors(toExplor))
				{				
						if(!setOfNodes.contains(node.element) && !noOfEdges.containsKey(node))
						{
							setOfNodes.add(node.getElement());
                            noOfEdges.put(node, level);
							nodesWithinLevel.add(node);  
						}
					    if(noOfEdges.containsKey(node) && level< noOfEdges.get(node) )
						{							
                            noOfEdges.put(node, level);
						}	
				}
				}
			
			}
		}
		return setOfNodes; // this line is here only so this code will compile if you don't modify it
	}


	public static boolean isHamiltonianPath(Graph g, List<String> values) {

		/* IMPLEMENT THIS METHOD! */
				String startingElement=values.get(0);
		String lastElement=values.get(values.size()-1);
		boolean flag=false;
		if(startingElement.equalsIgnoreCase(lastElement) && g.containsElement(startingElement))
		{
			flag=true;
			System.out.println("Starting and last element are same -- kind of cycle");
			
			for(int i=0;i<values.size();i++)
			{
				String temp=values.get(i);
				
				
			}
		}
			
        
		return flag; // this line is here only so this code will compile if you don't modify it
	}

}
