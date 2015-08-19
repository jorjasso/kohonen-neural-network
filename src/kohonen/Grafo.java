/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohonen;


class Grafo //
  { //

   private final int MAX_NODOS=30;
   private final int MAX_ARCOS=MAX_NODOS*(MAX_NODOS-1)/2;
   private Nodo[] nodos;
   private Arco[] arcos;
   private int [][] matrizAdyacencia;
   private double [][] matrizAdyacenciaPesos;
   private int nNodos;
   private int nArcos;

   		public Grafo(){

   			nodos = new Nodo[MAX_NODOS];
   			arcos = new Arco[MAX_ARCOS];
   			matrizAdyacencia = new int[MAX_NODOS][MAX_NODOS];
   			matrizAdyacenciaPesos= new double[MAX_NODOS][MAX_NODOS];
   			inicializarMatrizAdyacencia();
   			nNodos=0;
   			nArcos=0;


   			}

  	private void inicializarMatrizAdyacencia(){

  		for(int i=0;i<MAX_NODOS;i++){
  			for(int j=0;j<MAX_NODOS;j++){
  				matrizAdyacencia[i][j]=0;
  				matrizAdyacenciaPesos[i][j]=0;

  			}
  		}
  		}

    public void agregarNodo(Nodo nodo){

    	nodos[nNodos++]= nodo;
    	}

  	//se relaciona con el identificador del nodo
  	public void agregarArcos(Arco arco){

  		matrizAdyacencia[arco.u.identificador][arco.v.identificador]=1;
  		matrizAdyacencia[arco.v.identificador][arco.u.identificador]=1;

  		arcos[nArcos++]=arco;

  		}

  	public void mostrar(){
  		System.out.println(" NUMERO DE NODOS : "+this.nNodos);
  		System.out.println(" NUMERO DE ARCOS : "+this.nArcos);
  		System.out.println(" NODOS : ");
  		int i,j;
  		for(i=0;i<nNodos;i++){
  		System.out.print(" nodo = "+nodos[i].identificador);
  		System.out.print(" nombre = "+nodos[i].nombre);

  		}
  		System.out.println("");
  		System.out.println(" ARCOS : ");
  		for(i=0;i<nArcos;i++){
  		System.out.print(" arco = "+arcos[i].peso);
  		}
  		System.out.println("");
  		System.out.println(" MATRIZ DE ADYACENCIA");
  		for(i=0;i<nNodos;i++){
  			for(j=0;j<nNodos;j++){
  				System.out.print(" "+matrizAdyacencia[i][j]);
  				}
  				System.out.println("");
  			}

  		System.out.println("");
  		System.out.println(" MATRIZ DE PESOS");
  		for(i=0;i<nNodos;i++){
  			for(j=0;j<nNodos;j++){
  				System.out.print(" "+matrizAdyacenciaPesos[i][j]);
  				}
  				System.out.println("");
  			}

  		}

  	public Nodo encontrarNodo(int x, int y){
  		double menor=1000;
  		int nNodoRetorno=0;
        for(int i=0;i<nNodos;i++){
  //      	System.out.println("Nodo = "+nodos[i].obtenerIdentificador());
   //     	System.out.println("NodoXY = ");
   //     	nodos[i].XY();
            if(nodos[i].distancia(x,y)<menor)
            {
            menor=nodos[i].distancia(x,y);
            nNodoRetorno=i;
    //      System.out.println("distacia = "+nodos[i].distancia(x,y));
            }
        }
  //      System.out.println("distacia Ganadora= "+nodos[nNodoRetorno].distancia(x,y));
  		//retorna el nodo ganador
        return nodos[nNodoRetorno];
    }


  	public Arco encontrarArco(int x, int y){
  		double menor=1000;
  		int nArcoRetorno=0;
        for(int i=0;i<nArcos;i++){
  //      	System.out.println("Nodo = "+nodos[i].obtenerIdentificador());
   //     	System.out.println("NodoXY = ");
   //     	nodos[i].XY();
            if(arcos[i].distancia(x,y)<menor)
            {
            menor=arcos[i].distancia(x,y);
            nArcoRetorno=i;
    //      System.out.println("distacia = "+nodos[i].distancia(x,y));
            }
        }
  //      System.out.println("distacia Ganadora= "+nodos[nNodoRetorno].distancia(x,y));
  		//retorna el arco ganador
        return arcos[nArcoRetorno];
    }

  	public int numeroNodos(){
  		return nNodos;
  		}

  	public int numeroArcos(){
  		return nArcos;
  		}

  	public void dibujarGrafo(java.awt.Graphics g){

  		for(int i=0;i<nNodos;i++){
  			nodos[i].dibujar(g);
  			}

  		for(int i=0;i<nArcos;i++){
  			arcos[i].dibujar(g);
  			}

  		}


  	public void grafoMultiplementeConexo(){

  		Arco arco;
 		nArcos=0;
  		for(int i=0;i<nNodos;i++){
  			for(int j=i+1;j<nNodos;j++){
  			arco = new Arco(nodos[i],nodos[j]);
  			arco.establecerIdentificador(nArcos);
  			agregarArcos(arco);
  			}

  		}

  		}

  	public void  establecerPesosAutomaticos(){
  		int i;
  		for(i=0;i<nArcos;i++){
  			arcos[i].establecerPeso();
  			matrizAdyacenciaPesos[arcos[i].u.identificador][arcos[i].v.identificador]=arcos[i].obtenerPeso();
  			matrizAdyacenciaPesos[arcos[i].v.identificador][arcos[i].u.identificador]=arcos[i].obtenerPeso();
  			}



  		}

  	public void establecerPesos(Arco arco){

  		matrizAdyacenciaPesos[arco.u.identificador][arco.v.identificador]=arco.obtenerPeso();
  		matrizAdyacenciaPesos[arco.v.identificador][arco.u.identificador]=arco.obtenerPeso();
  		}

  	public double[][] obtenermatrizAdyacenciaPesos(){
  		return matrizAdyacenciaPesos;
  		}

  	public Arco[] obtenerArcos(){
  		return arcos;
  		}

  	public Nodo[] obtenerNodos(){
  		return nodos;
  		}

  	public Nodo obtenerNodo(int pos){

  		return nodos[pos];

  		}
  	//busca el arco que contiene los dos nodos
  	//para fines de pintado
  	public void buscarArco(Nodo u, Nodo v){

  		for(int i=0;i<nArcos;i++){
  			if((arcos[i].u.obtenerIdentificador()==u.obtenerIdentificador())&&(arcos[i].v.obtenerIdentificador()==v.obtenerIdentificador()))
  			{arcos[i].esSeleccionado();

  				}
  				if((arcos[i].v.obtenerIdentificador()==u.obtenerIdentificador())&&(arcos[i].u.obtenerIdentificador()==v.obtenerIdentificador()))
  			{arcos[i].esSeleccionado();

  				}
  			}
  		}

  		//devuelve el arco que tiene los nodos u y v
  	public Arco encontrarArco(Nodo u, Nodo v){

  		for(int i=0;i<nArcos;i++){
  			if((arcos[i].u.obtenerIdentificador()==u.obtenerIdentificador())&&(arcos[i].v.obtenerIdentificador()==v.obtenerIdentificador()))
  			{return arcos[i];

  				}
  				if((arcos[i].v.obtenerIdentificador()==u.obtenerIdentificador())&&(arcos[i].u.obtenerIdentificador()==v.obtenerIdentificador()))
  			{return arcos[i];

  				}
  			}
  			return null;
  		}

  	public void resetear(){
  		int i;
  		for(i=0;i<nNodos;i++){

  			nodos[i]=null;
  			}

  		for(i=0;i<nArcos;i++){

  			arcos[i]=null;
  			}

  		inicializarMatrizAdyacencia();
  		nNodos=0;
  		nArcos=0;


  		}

  	  }
  /*
    grafo(int, boolean)
    int V()
    int E()
    boolean dirigido()
    int insertar(Arcos)
    void remover(Arcos)
    boolean Arco(int, int)
    ListaAdyacencia obtenerListaAdyacencia(int)
    */


