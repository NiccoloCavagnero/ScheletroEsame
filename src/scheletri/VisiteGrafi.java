package scheletri;

public class VisiteGrafi {
	
	//DIJKSTRA
		public List<E> trovaCamminoMinimo(E partenza, E arrivo){
			
			DijkstraShortestPath<E, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(this.grafo);
			GraphPath<E, DefaultWeightedEdge> path = dijkstra.getPath(partenza, arrivo);
			
			return path.getVertexList();	
			
		}
		
		//COMPONENTI CONNESSE (Connectivity Inspector)
		public int getNumComponentiConnesse() {
			
			ConnectivityInspector<E, DefaultEdge> inspector = new ConnectivityInspector<>(grafo);
			return inspector.connectedSets().size();
		}
		
		//Definisco classe dentro altra classe
		//Classe per intercettare gli eventi del grafo quando è scandito da un iteratore (LISTENER)
		public class EdgeTraversedListener implements TraversalListener<E, DefaultWeightedEdge> {

			Graph<E, DefaultWeightedEdge> grafo;
			Map<E,E> back;
			
			public EdgeTraversedListener(Map<Fermata, Fermata> back,Graph<Fermata, DefaultWeightedEdge> grafo) {
				super();
				this.back = back;
				this.grafo=grafo;
			}

			@Override
			public void connectedComponentFinished(ConnectedComponentTraversalEvent arg0) {		
			}

			@Override
			public void connectedComponentStarted(ConnectedComponentTraversalEvent arg0) {		
			}

			public void edgeTraversed(EdgeTraversalEvent<DefaultWeightedEdge> ev) {	
		 		 
				/*Back codifica le relazioni si tipo CHILD -> PARENT
				 * 
				 * Per un nuovo vertice 'CHILD' scoperto devo avere che:
				 * 
				 * -CHILD è ancora sconosciuto (non ancora scoperto)
				 * -PARENT è gia stato visitato
				 */
				
				//Estraggo gli estremi dell'arco
				E sourceVertex = grafo.getEdgeSource(ev.getEdge());
				E targetVertex = grafo.getEdgeTarget(ev.getEdge());
				
				/*
				 * Se il grafo e' orientato, allora SOURCE==PARENT , TARGET==CHILD
				 * Se il grafo NON è orientato potrebbe essere il contrario..
				 */
				
				//Codice da riutilizzare
				if( !back.containsKey(targetVertex) && back.containsKey(sourceVertex)) {
					back.put(targetVertex, sourceVertex);
				} else if(!back.containsKey(sourceVertex) && back.containsKey(targetVertex)) {
					back.put(sourceVertex, targetVertex);
				}
			}

			@Override
			public void vertexFinished(VertexTraversalEvent<E> arg0) {		
			}

			@Override
			public void vertexTraversed(VertexTraversalEvent<E> arg0) {		
			}

		}
		
		//(RAGGIUNGIBILI)
		//Iterazione con la quale attraversiamo il grafo e ci aggiungiamo via via i vertici che troviamo (N.B. NON E' UN CAMMINO!)
			public List<Fermata> fermateRaggiungibili(E source){
				
				List<E> risultato = new ArrayList<E>();
				backVisit = new HashMap<>();
				
				//Creo iteratore e lo associo al grafo       
				//GraphIterator<Fermata, DefaultEdge> it = new BreadthFirstIterator<>(this.grafo,source); //in ampiezza
				GraphIterator<E, DefaultWeightedEdge> it = new DepthFirstIterator<>(this.grafo,source); //in profondita'
				
				it.addTraversalListener(new EdgeTraversedListener(backVisit, grafo)); //Questa classe potrebbe essere definita anche dentro la classe Model
				//A fine iterazione mi ritroverò la mappa back riempita
				
				//Devo popolare la mappa almeno col nodo sorgente
				backVisit.put(source, null);
				
				while(it.hasNext()) {
					risultato.add(it.next());
				}
				
				return risultato;
			}
			
			//OPPURE
			public List<E> trovaRaggiungibili(Country partenza) {
				
				List<E> raggiungibili = new ArrayList<E>();
				
				//CREO ITERATORE E LO ASSOCIO AL GRAFO      
				//GraphIterator<Fermata, DefaultEdge> it = new BreadthFirstIterator<>(this.grafo,source); //in ampiezza
				GraphIterator<Country, DefaultEdge> it = new DepthFirstIterator<>(this.grafo,partenza); //in profondita'
				
				while(it.hasNext()) {
					raggiungibili.add(it.next());
				}
				
				//Pulisco il primo valore della lista che è l'elemento stesso
				return raggiungibili.subList(1, raggiungibili.size());
			}
			
			//ADIACENTI
			public List<Integer> trovaAdiacenti(int distretto) {
				
				List<Integer> distretti = Graphs.neighborListOf(grafo, distretto);	
				Collections.sort(distretti, new OrdinaDistretti(grafo, distretto));
				
				return distretti;
			}

}
