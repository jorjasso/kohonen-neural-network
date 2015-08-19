/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohonen;


//Jorge Guevara Diaz

class Kohonen{

	int iteraciones; //numero de iteracciones del algortimo
	double error;   //error maximo permitido
	double tasaAprendizaje;
	double tasaAprendizajeFinal;
	double radioVecindad;
	double radioVecindadFinal;
	CapaCompeticion capa;
	int opcion;


	Grafo grafo;
	Nodo [] nodos;

		//entran el vector de nodos del grafo
		//y la opcion para la ventana traingular o gaussiana
		public Kohonen(Grafo grafo, int opcion){

			this.grafo=grafo;
			this.opcion=opcion;
			iteraciones =50000;
			tasaAprendizaje=0.9999999;
			tasaAprendizajeFinal=0.01;
			radioVecindadFinal=0.9;
			radioVecindad=50;
	//		this.nodos=grafo.numeroNodos();
			nodos = new Nodo[grafo.numeroNodos()];
			copiarNodos(grafo);
			System.out.println("");
			System.out.println(" SOLUCION CON ALGORITMO DE Kohonen");
			System.out.println(" NUMERO DE NODOS "+nodos.length);
			capa=new CapaCompeticion(nodos.length); //se crea la capa de competicion con un determinado numero de neuronas
			aprendizaje();
			mostrarCamino(0);
			System.out.println(" FIN ");
			mostrarCamino(1);

			}


		public void aprendizaje(){
			int ban=0;
			int i,j;
			int ganador;
			int contador=0;
			for(i=0;i<iteraciones;i++){

				reinicializar();
				//para todas las neuronas de la capa
				//de competicion propagar


			//	 ban=1;
					for(j=0;j<nodos.length;j++){

					ganador=obtenerGanadora(nodos[j]);
					if(ganador!=-1){

					//ajustar pesos de la neurona ganadora
					//y de su vecindad
						ajustarPesos(ganador,nodos[j]);
						}


					}//del for

					if(i==0){
					System.out.println(" INICIO");
					mostrarCamino(0);
					}

				//todo nodo tiene una unica neurona ganadora
				//o distancia menor que un error prefijado entonces acabar
/*				if(verificar()==nodos.length){
					System.out.print(" verificar() "+verificar()+" nodos.length "+nodos.length);
					eliminarNeuronas();
					break;

					}

*/				//actualizar tasa de aprendizaje y radio de vecindad
				//a(t)=a0-(af-a0)t/ta
				tasaAprendizaje=tasaAprendizaje+(tasaAprendizajeFinal-tasaAprendizaje)*i/iteraciones;
				radioVecindad=radioVecindad+(radioVecindadFinal-radioVecindad)*i/iteraciones;

				if(contador==5){
	//				eliminarNeuronas();
					contador=0;
					}
				contador++;


				//reinicializar vector de neuronas
			//	if(i<iteraciones-1){

			//	}

				} //de las iteraciones

				System.out.println(" TASA DE APRENDIZAJE = "+tasaAprendizaje+" RADIO DE VECINDAD = "+radioVecindad);

			}

		//entra las coordenadas normalizadas x,y de un nodo
		//y se diseminan por toda la capa de competicion
		public int obtenerGanadora(Nodo nodo){
			//buscar por todas las neuronas de la capa de competicion
		double menor=10000;
	//		double mayor=0;
			double x1,y1; //valores del nodo
		//	nodo.normalizar();

		/*	x1=nodo.obtnerX1();
			y1=nodo.obtnery1();
		*/
			x1=nodo.obtenerX();
			y1=nodo.obtenerY();

			double dist;
			int nNeuronaGanadora=-1;  //me indica la neurona que gano
			//se puede mejorar con una busqueda binaria por ejemplo
			for(int i=0;i<capa.neuronas.length;i++){
				dist=distancia(x1,y1,capa.neuronas[i].wx,capa.neuronas[i].wy);
	//			System.out.println("distancia en for ="+dist);
					if(dist<menor){
			//		if(dist>mayor){
			//			mayor=dist;
						menor=dist;
						nNeuronaGanadora=i;
	//				System.out.println("ganadora en for ="+nNeuronaGanadora);

						}

				}

			//le paso el identificador del nodo ganador
			if(capa.neuronas[nNeuronaGanadora].identificadorNodo==-1)//si el nodo no ha ganado todavia
			{
			  capa.neuronas[nNeuronaGanadora].identificadorNodo=nodo.obtenerIdentificador();
//			  System.out.println("ganadora ="+nNeuronaGanadora);
//			  	System.out.println("capa.neuronas[nNeuronaGanadora].identificadorNodo ="+capa.neuronas[nNeuronaGanadora].identificadorNodo);
			  return nNeuronaGanadora;

				}

			else{
				//la neurona tiene ya un nodo asociado , entoces crear una neurona alrededor de ella
				double lado=0;
				lado=Math.random();
				if(lado<0.5){//crear neurona en el lado izquierdo de la ganadora
					capa.agregarNeurona(nNeuronaGanadora);
//   	            capa.neuronas[nNeuronaGanadora].identificadorNodo=nodo.obtenerIdentificador();
//					return nNeuronaGanadora;//nadie gano;
					return -1;//nadie gano;
					}
				if(lado>=0.5){//crear neurona en el lado derecho de la ganadora

					if((nNeuronaGanadora+1)!=(capa.neuronas.length-1)){
						capa.agregarNeurona(nNeuronaGanadora+1);
//						capa.neuronas[nNeuronaGanadora+1].identificadorNodo=nodo.obtenerIdentificador();
//				  	    return nNeuronaGanadora+1;//nadie gano;
						return -1;//nadie gano;
						}


					}

				}
				return -1;
			}


		public void ajustarPesos(int ganador,Nodo nodo){
			//actualizar pesos de la neurona ganadora
			double wx;
			double wy;

			//actualizar pesos de la vecindad
			for(int i=0;i<capa.neuronas.length;i++){


					wx=capa.neuronas[i].wx;
					wy=capa.neuronas[i].wy;

					if(opcion==0){//ventana triangular
				//		capa.neuronas[i].wx=wx+tasaAprendizaje*ventanaTriangular(i,ganador)*(nodo.obtnerX1()-wx);
				//		capa.neuronas[i].wy=wy+tasaAprendizaje*ventanaTriangular(i,ganador)*(nodo.obtnery1()-wy);

						capa.neuronas[i].wx=wx+tasaAprendizaje*ventanaTriangular(i,ganador)*(nodo.obtenerX()-wx);
						capa.neuronas[i].wy=wy+tasaAprendizaje*ventanaTriangular(i,ganador)*(nodo.obtenerY()-wy);

					}


	//				System.out.println(" Pesos antes  x"+capa.neuronas[i].wx);
	//				System.out.println(" Pesos antes  y"+capa.neuronas[i].wy);

					if(opcion==1){ //ventana gaussiana
				//		capa.neuronas[i].wx=wx+tasaAprendizaje*ventanaGaussiana(i,ganador)*(nodo.obtnerX1()-wx);
				//		capa.neuronas[i].wy=wy+tasaAprendizaje*ventanaGaussiana(i,ganador)*(nodo.obtnery1()-wy);

						capa.neuronas[i].wx=wx+tasaAprendizaje*ventanaGaussiana(i,ganador)*(nodo.obtenerX()-wx);
						capa.neuronas[i].wy=wy+tasaAprendizaje*ventanaGaussiana(i,ganador)*(nodo.obtenerY()-wy);
						}

	//				System.out.println(" Pesos despues  x"+capa.neuronas[i].wx);
	//				System.out.println(" Pesos despues  y"+capa.neuronas[i].wy);
//					}



				}

	//		capa.ordenarNeuronas(capa.neuronas);
			}

		//ventana triangular para actualizar la vecindad
		public double ventanaTriangular(int i, int g){

			double valor=0;
			if(Math.abs(i-g)>radioVecindad){
				valor=0;
		//		System.out.println(" radioVecindad) "+radioVecindad);
		//		System.out.println(" Math.abs(i-g) "+Math.abs(i-g));
		//		System.out.println(" valor "+valor);
				return valor;
				}

			if(Math.abs(i-g)<=radioVecindad){

				valor=(radioVecindad-(double)Math.abs(i-g))/radioVecindad;
		//		System.out.println(" radioVecindad) "+radioVecindad);
		//		System.out.println(" Math.abs(i-g) "+Math.abs(i-g));
		//		System.out.println(" valor "+valor);
				return valor;
				}

			return valor;
			}

		public double ventanaGaussiana(int i , int g){

			double dist;
//			System.out.print("  i  "+i+" g "+g+" ");
//			dist=(-distancia(capa.neuronas[i],capa.neuronas[g])*distancia(capa.neuronas[i],capa.neuronas[g]))/(2*tasaAprendizaje*tasaAprendizaje);
			dist=-((double)(i-g)*(double)(i-g))/(double)(2*radioVecindad*radioVecindad);
			dist=Math.exp(dist);
//			System.out.print(" gausianna "+dist);
			return dist;
			}

		//elimina las neuronas que nunca ganaron en la capa de competicion
		public void eliminarNeuronas(){

			for(int i=0;i<capa.neuronas.length;i++){

				//nunca ganaron
				if(capa.neuronas[i].identificadorNodo==-1){
					capa.EliminarNeurona(i);
					}
				}

			}

		public void reinicializar(){

			for(int i=0;i<capa.neuronas.length;i++){

				capa.neuronas[i].reiniciar();

				}

			}

		public void mostrarCamino(int opcion){

			System.out.println("");
			System.out.println(" CAMINO DE NEURONAS EN Kohonen");
			int [] temporal=new int[nodos.length+1];
			int j=0;
			double [][] matriz = new double[temporal.length][4];


			if(opcion==1){


			for(int i=0;i<capa.neuronas.length;i++){
						if(capa.neuronas[i].identificadorNodo!=-1){

						temporal[j]=capa.neuronas[i].identificadorNodo;

				/*		matriz[j][0]=nodos[temporal[j]].obtnerX1();
						matriz[j][1]=nodos[temporal[j]].obtnery1();
						matriz[j][2]=capa.neuronas[i].wx;
						matriz[j][3]=capa.neuronas[i].wy;
					*/
						matriz[j][0]=nodos[temporal[j]].obtenerX();
						matriz[j][1]=nodos[temporal[j]].obtenerY();
						matriz[j][2]=capa.neuronas[i].wx;
						matriz[j][3]=capa.neuronas[i].wy;
							j++;
						}
						}
				temporal[nodos.length]=temporal[0];
				for(int i=0;i<temporal.length;i++){
					System.out.print(" "+temporal[i]);
					}
				System.out.println("");
				System.out.println(" COSTO EN Kohonen  "+calcularCosto(temporal));
				System.out.println("*****************************************");

				System.out.println(" APRENDIZAJE");
				for(int i=0;i<matriz[0].length;i++){
					System.out.println(" "+matriz[i][0]+" "+matriz[i][1]+"  - "+matriz[i][2]+" "+matriz[i][3]);
					}

				}

			else{
				for(int i=0;i<capa.neuronas.length;i++){
				System.out.print(" "+capa.neuronas[i].identificadorNodo);
				}
			}

//
		  	    System.out.println("");


			}







		public double calcularCosto(int [] temporal){
			Arco arco=new Arco();
			double costo=0;
			int identificador=0;

			try{for(int i=0;i<temporal.length;i++){

			//	if(capa.neuronas[i+1].identificadorNodo!=-1&&capa.neuronas[i+1].identificadorNodo!=capa.neuronas[i].identificadorNodo){
					if(i==temporal.length-1){
						return costo;
						}
						arco=grafo.encontrarArco(grafo.obtenerNodo(temporal[i]),grafo.obtenerNodo(temporal[i+1]));
						arco.opciones=opcion+1;
					//	arco.dibujar(this.gr)
						System.out.println(" nodos  "+temporal[i]+" - "+temporal[i+1]);
						costo=costo+arco.obtenerPeso();
						System.out.println(" arco "+arco.obtenerPeso());
						System.out.println(" costo "+costo);



				//	identificador=i+1;
				}

				}
			catch(Exception e){

				System.out.println("LA RED NO APRENDIO");


				}
			/*	if(capa.neuronas[i].identificadorNodo==-1){
					arco=grafo.encontrarArco(grafo.obtenerNodo(capa.neuronas[i-1].identificadorNodo),grafo.obtenerNodo(capa.neuronas[0].identificadorNodo));
					costo=costo+arco.obtenerPeso();
					System.out.println(" nodos  "+capa.neuronas[i].identificadorNodo+" - "+capa.neuronas[0].identificadorNodo);
					System.out.println(" arco "+arco.obtenerPeso());
					System.out.println(" costo "+costo);
					break;
					}
				if(capa.neuronas[i+1].identificadorNodo==capa.neuronas[i].identificadorNodo){
					arco=grafo.encontrarArco(grafo.obtenerNodo(capa.neuronas[i].identificadorNodo),grafo.obtenerNodo(capa.neuronas[0].identificadorNodo));
					costo=costo+arco.obtenerPeso();
					System.out.println(" nodos  "+capa.neuronas[i].identificadorNodo+" - "+capa.neuronas[0].identificadorNodo);
					System.out.println(" arco "+arco.obtenerPeso());
					System.out.println(" costo "+costo);
					break;
					}
				}
				*/

			return costo;



			}

		public void copiarNodos(Grafo grafo){
//			System.out.println("grafo.numeroNodos()"+grafo.numeroNodos());
			for(int i=0;i<grafo.numeroNodos();i++){
				nodos[i]=grafo.obtenerNodo(i);
//				System.out.println("grafo.obtenerNodo(i)"+grafo.obtenerNodo(i).obtenerIdentificador());
				}

			}
			//distancia euclidiana
		public double distancia(double x,double y,double x1,double y1){
			double dx=x-x1;
			double dy=y-y1;

			return Math.sqrt(dx*dx + dy*dy);

			}

		public double distancia(Neurona u, Neurona v)	{
			double dx=u.wx-v.wx;
			double dy=u.wy-v.wy;
			double dist;

		//	System.out.print(" ux "+u.wx+" v.wx "+v.wx);
		//	System.out.print(" uy "+u.wy+" v.wy "+v.wy);

			dist=Math.sqrt(dx*dx + dy*dy);
//			System.out.print(" distancia "+dist);
			return dist;
			}


		public int   verificar(){

			int cont=0;
		for(int j=0;j<nodos.length;j++){
			for(int i=0;i<capa.neuronas.length;i++){

				if(nodos[j].obtenerIdentificador()==capa.neuronas[i].identificadorNodo){
					cont++;


					}

					}

				}
			return cont;
			}

	}