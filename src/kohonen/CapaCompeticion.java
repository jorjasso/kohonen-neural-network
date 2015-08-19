/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohonen;

class CapaCompeticion{

		public int nNeuronas;
		public Neurona [] neuronas;

		public CapaCompeticion(int nNeuronas)//numero de nueronas de la capa de competcion
		{
			this.nNeuronas=nNeuronas;
			neuronas = new Neurona[4*nNeuronas+1]; //creo neuronas de la capa de competicion
			inicializarNeuronas();	              //inicializa las nueronas
//			ordenarNeuronas(neuronas);                       //ordena las neuronas de acuerdo a su peso wx
			}

		public void inicializarNeuronas(){

			int tamano=3*nNeuronas;
			//solo inicializo una parte de mi vector de neuronas
			//de la capa de competicion
			//con el fin de poder crear mas neuronas
			//las cuales seran añadidas del final de mi vector neuronas
			for(int i=0;i<neuronas.length;i++){
				if(i<tamano){
					neuronas[i]=new Neurona();
				}
				else{
					neuronas[i]=new Neurona();
					neuronas[i].wx=10000;//valor muy grande nunca ganaran
					neuronas[i].wy=10000;//valor muy grande nunca ganaran
					}
				}

			}

		//ingresa la posicion en el vector donde resulto
		//la Neurona doblemente ganadora
		public void agregarNeurona(int pos){
			Neurona [] temporal=new Neurona[neuronas.length];
			double peso=0;

			for(int i=0;i<neuronas.length;i++){

				if(i<pos){

				temporal[i]=neuronas[i];
				}
				//creamos la Neurona en pos
				//y actualizamos los pesos
				if(i==pos){

					//en este caso añadimos la Neurona despues de pos
					if(pos==0){
					temporal[i]=neuronas[pos];
					peso=0.04*neuronas[pos+1].wx+0.95*neuronas[pos].wx+0.01*Math.random();
					temporal[i+1]=new Neurona(); //creo la Neurona en pos+1
					temporal[i+1].wx=peso;
					peso=0.04*neuronas[pos+1].wy+0.95*neuronas[pos].wy+0.01*Math.random();
					temporal[i+1].wy=peso;
					i=i+2;
						}
					else{
					peso=0.04*neuronas[pos-1].wx+0.95*neuronas[pos].wx+0.01*Math.random();
	//				System.out.print(" PESO X "+peso);
					temporal[i]=new Neurona(); //creo la Neurona en pos
					temporal[i].wx=peso;
					peso=0.04*neuronas[pos-1].wy+0.95*neuronas[pos].wy+0.01*Math.random();
	//				System.out.print(" PESO Y "+peso);
					temporal[i].wy=peso;
						}


					}
				if(i>pos){

					temporal[i]=neuronas[i-1];
					}

				}
			neuronas=temporal;
			mostrar();

			}
		public void EliminarNeurona(int pos){
			neuronas[pos].wx=Math.random();
			neuronas[pos].wy=Math.random();
//
//			for(int i=pos;i<neuronas.length-1;i++){
//				neuronas[i]=neuronas[i+1];
//				}


			/*experimento*/
			/*mutacion*/
	//		neuronas[neuronas.length-1].wx=Math.random();
	//		neuronas[neuronas.length-1].wy=Math.random();
	//		 mostrar();
			}

		//muestra todas las neuronas con sus pesos de esta capa
		public void mostrar(){

	//		System.out.println("NEURONAS");
//			for(int i=0;i<neuronas.length;i++){

	//			System.out.println(" "+neuronas[i].wx+" "+neuronas[i].wy+"  | ");

	//			}
	//			System.out.println("");
			}

			//ordena las neuronas de acuerdo a su componente wx de pesos aleatorios
		public void ordenarNeuronas(Neurona [] A){

			int i;
	//		System.out.println("A antes");
			for( i=0;i<A.length;i++){
	//			System.out.print(" "+A[i].wx);
				}


			quicksort(A,0,A.length-1);

	//		System.out.println("A despues");
			for( i=0;i<A.length;i++){
	//			System.out.print(" "+A[i].wx);
				}


			}

		public void quicksort(Neurona [] A, int p , int r)
		{int q;
			if(p<r){

				q=particionar(A,p,r);
				quicksort(A,p,q-1);
				quicksort(A,q+1,r);
				}
			}

		public int particionar(Neurona []A, int p,int r){
			double  x;
			double temp;
			int i,j;

			x=A[r].wx;
			i=p-1;
			//j=r+1;

			for(j=p ; j<=r-1;j++){

				if(A[j].wx<=x){
					i=i+1;
					temp=A[i].wx;
					A[i].wx=A[j].wx;
					A[j].wx=temp;
					}

				}

			temp=A[i+1].wx;
			A[i+1].wx=A[r].wx;
			A[r].wx=temp;
			return i+1;
			}




			}


