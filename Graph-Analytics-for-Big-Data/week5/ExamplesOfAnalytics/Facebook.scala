//
// Set log level to error, suppress info and warn messages
//
import org.apache.log4j.Logger
import org.apache.log4j.Level

Logger.getLogger("org").setLevel(Level.ERROR)
Logger.getLogger("akka").setLevel(Level.ERROR)


//
// Hands On: Building A Graph
//
import org.apache.spark.graphx._


val facebookGraph = GraphLoader.edgeListFile(sc, "./EODATA/facebook_combined.txt")


//
// Hands On: Network Connectedness and Clustering Components
//

import org.graphstream.graph.implementations._

val graph: SingleGraph = new SingleGraph("facebookGraph")

// Set up the visual attributes for graph visualization.
graph.addAttribute("ui.stylesheet","url(file:.//style/stylesheet-simple)")
graph.addAttribute("ui.quality")
graph.addAttribute("ui.antialias")

// Given the facebookGraph, load the graphX vertices into GraphStream
for ((id, _) <- facebookGraph.vertices.collect()) {
  graph.addNode(id.toString).asInstanceOf[SingleNode]
}

// Load the graphX edges into GraphStream edges
for ((Edge(x, y, _), count) <- facebookGraph.edges.collect().zipWithIndex) {
  graph.addEdge(count.toString, x.toString, y.toString).asInstanceOf[AbstractEdge]
}

// Display the graph.
graph.display()
