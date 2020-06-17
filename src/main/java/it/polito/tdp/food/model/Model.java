package it.polito.tdp.food.model;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;


public class Model {
	Graph<String, DefaultWeightedEdge> grafo;
	FoodDao dao;
	List<AdiacentPortion> path;
	double sommaPesiPath;
	
	public Model() {
		 dao = new FoodDao();
	}
	
	public void creaGrafo(double calorie) {
		this.grafo = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, dao.listVertici(calorie));
		
		for (Arco a : dao.getAllArco())
			if (this.grafo.containsVertex(a.getPortUno()) && this.grafo.containsVertex(a.getPortDue())) {
				Graphs.addEdge(grafo, a.getPortUno(), a.getPortDue(), a.getWeight());
			}
	}
	
	public Graph<String, DefaultWeightedEdge> getGraph() {
		if (grafo == null)
			throw new NullPointerException("Non è stato ancora generato alcun grafo.");
		else
			return this.grafo;
	}
	
	public String getCreation() {
		if (grafo == null)
			throw new NullPointerException("Non è stato ancora generato alcun grafo.");
		
		return String.format("Grafo creato (%d vertici, %d archi)\n", 
				this.grafo.vertexSet().size(), this.grafo.edgeSet().size());
	}
	
	public List<AdiacentPortion> getCorrelati(String vertice) {
		if (grafo == null)
			throw new NullPointerException("Non è stato ancora generato alcun grafo.");
		
		
		List<AdiacentPortion> porzioniAdiacenti = new ArrayList<AdiacentPortion>();
		
		for (String v : Graphs.neighborListOf(grafo, vertice))
			porzioniAdiacenti.add(new AdiacentPortion(v, grafo.getEdgeWeight(grafo.getEdge(vertice, v))));
		
		return porzioniAdiacenti;
	}
	
	public List<AdiacentPortion> percorsoDiPesoMassimo(int N, String v) {
		int numeroPassi = N;
		this.path = new ArrayList<AdiacentPortion>();
		this.sommaPesiPath = 0.0;
		
		AdiacentPortion vertice = new AdiacentPortion(v, 0.0);
		
		
		ricerca(0, numeroPassi, new ArrayList<AdiacentPortion>(), vertice);
		
		return path;
	}
	
	private void ricerca(int livello, int numeroPassi, List<AdiacentPortion> parziale, AdiacentPortion precedente) {
		if (livello==numeroPassi)
		{
			double somma = 0.0;
			for (AdiacentPortion a : parziale)
				somma += a.getWeight();
			
			if (sommaPesiPath<somma) {
				sommaPesiPath = somma;
				path = new ArrayList<AdiacentPortion>(parziale);
			}
			return;
		}
		
		if (Graphs.neighborListOf(grafo, precedente.getPortionName()).size()==0)
			throw new NullPointerException("Non è possibile proseguire l'esplorazione. Non esistono grafi seguenti.");
		
		for (String v : Graphs.neighborListOf(grafo, precedente.getPortionName())) {
			AdiacentPortion ad = new AdiacentPortion(v, grafo.getEdgeWeight(grafo.getEdge(precedente.getPortionName(), v)));

			if (!parziale.contains(ad)) {
				
				parziale.add(ad);
				ricerca(livello+1, numeroPassi, parziale, precedente);
				parziale.remove(parziale.size()-1);
				
			}
		}
			
	}
}
