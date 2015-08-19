/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohonen;

class AgenteViajero{

	private final int MAX_NODOS=30;
	public Grafo grafo;
	int nodoInicial;
	double costo;

	public AgenteViajero(){}
	public AgenteViajero(Grafo grafo,int nodoInicial){
		this.grafo=grafo;
		this.nodoInicial=nodoInicial;
		costo=0;
		}

	public void establecerGrafo(Grafo grafo){
		this.grafo=grafo;
		}
	public Grafo obtenerGrafo(){
		return grafo;
		}


		//retorna la secuencia de nodos que indican el camino
	public int[] metodoGreedy(){
		int i;
		int ganador=0;
		int nodoPivot;
		double menor=10000;
		int [] secuenciaNodos=new int[MAX_NODOS+1];
		int contador=0;
		double[][] matrizAdyacenciaPesos=grafo.obtenermatrizAdyacenciaPesos();

		int nNodos=grafo.numeroNodos();
		Nodo [] nodos=grafo.obtenerNodos();
		Arco [] arcos=grafo.obtenerArcos();


		secuenciaNodos[contador]=nodoInicial;
		nodos[nodoInicial].visitado=true;
		contador++;
		nodoPivot=nodoInicial;
		System.out.println("");
		System.out.println(" SOLUCION CON ALGORITMO GREEDY ");
		System.out.println(" NUMERO DE NODOS "+nNodos);
		for(i=0;i<nNodos;i++){

			if((matrizAdyacenciaPesos[nodoPivot][i]<menor)&&(nodos[i].visitado==false)&&(nodoPivot!=i)){

				menor=matrizAdyacenciaPesos[nodoPivot][i];
				ganador=i;


			}

			if(i==(nNodos-1)){

					secuenciaNodos[contador]=ganador;
					nodos[ganador].visitado=true;
					nodos[ganador].esSeleccionado();
					grafo.buscarArco(nodos[nodoPivot],nodos[ganador]);
					contador++;
					if (contador==nNodos){
						costo=costo+menor;
						menor=matrizAdyacenciaPesos[ganador][nodoInicial];
						costo=costo+menor;
						secuenciaNodos[contador]=nodoInicial;
						grafo.buscarArco(nodos[ganador],nodos[nodoInicial]);
						return secuenciaNodos;
						}
					nodoPivot=ganador;
					i=-1;
					costo=costo+menor;

					menor=10000;
					}

			}
			return secuenciaNodos;

		}

	public void mostrarSolucionGreedy(){

		int nNodos=grafo.numeroNodos();
		int [] secuenciaNodos=new int[MAX_NODOS];

		secuenciaNodos=metodoGreedy();

		System.out.println(" SECUENCIA DE NODOS ");
		for(int i=0; i<=nNodos;i++){

			System.out.print(" "+secuenciaNodos[i]);
			}
		System.out.println("");
		System.out.println(" COSTO = "+costo);
		}


	}