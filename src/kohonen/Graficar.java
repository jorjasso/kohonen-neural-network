/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohonen;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.awt.image.*;
import java.awt.geom.*;
import javax.swing.*;


class Graficar extends Canvas implements MouseListener, MouseMotionListener{

	  	private final int MAX_NODOS=30;
        private final int MAX_ARCOS=MAX_NODOS*(MAX_NODOS-1)/2;
        JOptionPane OptionPane;
	  	ImageIcon icon;


        int opcion;
        int x, y,x1,y1;
        boolean pressOut = false;
        boolean firstTime = true;
        Nodo [] nodos = new Nodo[MAX_NODOS];
        Arco [] arcos =new Arco[MAX_ARCOS];
        int nNodos=0;
        int nArcos=0;
        Nodo v;
        Arco a;
        Grafo grafo=new Grafo();
        Grafos G;
        int identificador;    // identificador para el nodo
        int identificadorArco; // identificador para el arco
        int bandera;  //para pintado de arcos
        BufferedImage bi;//buffer para pintado
        Graphics2D big;
		Rectangle area;

        public Graficar(Grafos G){
                setBackground(Color.white);
                addMouseMotionListener(this);
                addMouseListener(this);

         //       icon= crearIcnos("images/middle.gif");


                this.opcion=opcion; //0 para Graficar nodos , 1 para grafricar arcos
				this.G=G;//paso el objeto ventana para Graficarlo;
				identificador=0;
				bandera=0;
				opcion=G.opcion();//paso la opcion de dibujo del checkbox

				bi = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);//buffer de imagenes
                big = bi.createGraphics();//objeto g2D

        }

       	public void tamano(int ancho, int altura){
		this.setSize(ancho,altura);
		}

		public void mousePressed(MouseEvent e){
    			x =  e.getX();
                y =  e.getY();
                //seleccionar nodos
                opcion=G.opcion();
		     	 if(grafo.numeroNodos()>0){
                	//actualizar todos los nodos como no selecionados
                	for(int i=0;i<nNodos;i++){
        	    			nodos[i].noEsSeleccionado();
        	    			}
                	//obtiene nodo ganador con menor distancia para seleccionarlo
        	         v=grafo.encontrarNodo(x,y);
        	         if(v.obtenerDistancia()<12){   //si presiono dentro de un nodo
/*chequear si va o no va*/bandera=0;
        	    	    identificador=v.obtenerIdentificador();//obtiene identificador de nodo seleccionado
        	    	    nodos[identificador].esSeleccionado();//hace seleccionado al nodo ganador
        	    	    nodos[identificador].dibujar(getGraphics());
        	    		for(int i=0;i<nNodos;i++){
        	    			if(i!=identificador){
        	    			//dibuja todos los nodos no seleccionados
        	    			   nodos[i].noEsSeleccionado();
        	    			   nodos[i].dibujar(getGraphics());
        	    			   }
        	    			}
						if(opcion==1){  //si presiono y suelto para que no se dibuje una linea al principio
							bandera=1;
        	    	     	repaint(0);
							}
						if(opcion==2){  //ingresar valor a nodos
		 	//			OptionPane.showMessageDialog(G.obtenerFrame(), "Mensaje.");
			//				System.out.println("ingresar valor");
					 		String s = (String)JOptionPane.showInputDialog(G.obtenerFrame(),"Nombre : ","Ingresar Valores",JOptionPane.PLAIN_MESSAGE,icon,null,"Nombre Nodo");
								repaint();
			                    if ((s != null) && (s.length() > 0)) {
			     //                 System.out.println("cadena introducida es... " + s + "!");
			                        nodos[identificador].establecerNombre(s);
			                        return;
			                    }
               //     	   System.out.println("nose ha introducido nada!");
							}

        	    	    }
        	         else{   //si presiono en el area blanca
        	    	//se inicializan y se repintan todos los nodos
        	    	    for(int i=0;i<nNodos;i++){
        	    			//dibuja todos los nodos no seleccionados
        	    		    nodos[i].noEsSeleccionado();
        	    			nodos[i].dibujar(getGraphics());
        	    			repaint(0);
        	    			}
        	    	    }
        	    }//del if(grafo.numeroNodos()>0){

               //seleccionar arcos
             if(opcion==4){  //seleccionar arcos e ingresar valores para arcos seleccionados
               if(grafo.numeroArcos()>0){
               	//actualizar todos los arcos como no selecionados
       //       	System.out.println("nArcos en Graficar"+nArcos);
                	for(int i=0;i<nArcos;i++){
        	    			arcos[i].noEsSeleccionado();
        	    			}
        	    	 //obtiene arco ganador con menor distancia para seleccionarlo
        	         a=grafo.encontrarArco(x,y);
        	         if(a.obtenerDistancia()<20){   //si presiono dentro de un arco
        	         	bandera=1;
        	    	    identificadorArco=a.obtenerIdentificador();//obtiene identificador de arco seleccionado
        	    	    arcos[identificadorArco].esSeleccionado();//hace seleccionado al arco ganador
        	    	    arcos[identificadorArco].dibujar(getGraphics());
        	    	    for(int i=0;i<nArcos;i++){
        	    			if(i!=identificadorArco){
        	    			//dibuja todos los arcos no seleccionados
        	    			   arcos[i].noEsSeleccionado();
        	    			   arcos[i].dibujar(getGraphics());
        	    			   }
        	    			}

        	   		if(opcion==4){  //ingresar valor a nodos
		 	//			OptionPane.showMessageDialog(G.obtenerFrame(), "Mensaje.");
			//				System.out.println("ingresar valor");
					 		String s = (String)JOptionPane.showInputDialog(G.obtenerFrame(),"Peso : ","Ingresar Valores",JOptionPane.PLAIN_MESSAGE,icon,null,"Peso Arco");
								repaint();
			                    if ((s != null) && (s.length() > 0)) {
			                    	double prueba;
			//                      System.out.println("El peso  introducido es... " + s + "!");
			                        arcos[identificadorArco].peso(Double.valueOf(s));
			                        grafo.establecerPesos(arcos[identificadorArco]);//establece los pesos correspondientes en la matriz de pesos
			                        prueba=Double.valueOf(s);
			              //        System.out.println(prueba);
			                        //dibujar todos como no seleccionados
			                        for(int i=0;i<nArcos;i++){
        	    					arcos[i].noEsSeleccionado();
        	    					}
			                        repaint();
			                        return;
			                    }
         //           	   System.out.println("no se ha introducido nada!");
							}

        	    		} // del if(v.obtenerDistancia()<12)

        	    	else{   //si presiono en el area blanca
        	    	//se inicializan y se repintan todos los arcos
        	    	    for(int i=0;i<nArcos;i++){
        	    			//dibuja todos los arcos no seleccionados
        	    		    arcos[i].noEsSeleccionado();
        	    			arcos[i].dibujar(getGraphics());
        	    			repaint(0);
        	    			}
        	    	    }
               	 } //del  if(grafo.numeroArcos()>0)
        	    }//del if(opcion==4)


   //     	    	System.out.println("se ha pulsado :("+x+","+y+")");


		}


		public void mouseDragged(MouseEvent e){
			        x =  e.getX();
                    y =  e.getY();
                try{
                	//  	int x1,y1;
                	opcion=G.opcion();
		    		if(opcion==0)	//dibujar nodos
		    		  {


			          if(nodos[identificador].seleccionado()==true){
			         	nodos[identificador].estabelcerXY(x,y);
			         	 e.consume();
           				 repaint(0);
           				 e.consume();
			         //	repaint();
			         	}
		    		  }
		    		if(opcion==1)	//dibujar arcos
		    		  {  bandera=0;  //dibujo las lineas temporales
		    		  	if(nodos[identificador].seleccionado()==true){
						x1=nodos[identificador].obtenerX();
						y1=nodos[identificador].obtenerY();
					//	System.out.println(" x1 = "+x1+" y1 = "+y1);
			        //    dibujarLinea(nodos[identificador].x,nodos[identificador].y,x,y,getGraphics());
			             e.consume();
           				 repaint(0);
           				 e.consume();
			           //repaint();
		    		  	}
		              }

                }
                catch(Exception exception){

				System.out.println("");


				}


                }
		public void mouseReleased(MouseEvent e){
						x =  e.getX();
		                y =  e.getY();

		                int nodoAlcanzadoID;
		                if(opcion==1){
		                	v=grafo.encontrarNodo(x,y);
        	         		if(v.obtenerDistancia()<12)
        	         		{ //creo el arco
        	         				// si el nodo seleccionado, no es el mismo
        	         				// se dinuja un arco, en caso contrario
        	         				//es un nodo con un arco hacia si mismo
        	         		//	    if(v.obtenerIdentificador()!=identificador){

        	         				nodoAlcanzadoID=v.obtenerIdentificador();
        	         				arcos[nArcos]=new Arco(nodos[identificador],nodos[nodoAlcanzadoID]);
        	         				arcos[nArcos].establecerIdentificador(nArcos);//asigno identificador
        	         				grafo.agregarArcos(arcos[nArcos]);
        	         				nArcos++;
		                			repaint(0);

		                	}
		                	else{
		                		bandera=1;  //no dibujar la linea
		                		repaint(0);
		                		}

			             }
			          repaint(0);
        	}
        public void mouseMoved(MouseEvent e){}
        public void mouseClicked(MouseEvent e) {

				opcion=G.opcion();
		    	if(opcion==0)	//dibujar nodos
		    	{
		    		if(e.getClickCount()==2){
		       			x =  e.getX();
		                y =  e.getY();
						nodos[nNodos]=new Nodo(x,y);
						nodos[nNodos].establecerIdentificador(nNodos);
						nodos[nNodos].dibujar(getGraphics());

	//					System.out.println("Nodos"+nodos[nNodos].identificador);
		     	   	    grafo.agregarNodo(nodos[nNodos]);
		     	   	    nNodos++;

	//	     	   	    System.out.println("opcion en Graficar"+G.opcion());
       			 	}
		    		}


    }
        public void mouseExited(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}

        public void paintComponent(Graphics g){

		update(g);
	  }




		public void update(Graphics g){
			Graphics2D g2 = (Graphics2D) g;
			Dimension dim = getSize();
			int w = dim.width;
            int h = dim.height;

          	if(firstTime){
		 		 bi = (BufferedImage)createImage(w, h);
		 		 big = bi.createGraphics();
		 		 big.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		 		 area = new Rectangle(dim);
		 		 firstTime = false;

		  }

		    big.setColor(Color.white);
        	big.clearRect(0, 0, area.width, area.height);

	        grafo.dibujarGrafo(big);
	        if(opcion==1)
	    	{
	    		if(bandera==0)
	    		dibujarLinea(x1,y1,x,y,big); //dibuja la linea temporal
	    		if(bandera==1){
	    		dibujarLinea(x1,y1,x1+10,y1+10,big);  //no dibuja la linea
	    		}

	    		}
		    g2.drawImage(bi, 0, 0, this);

	}
	   public void dibujarLinea(int x, int y,int x1,int y1,Graphics g){

	   	Graphics2D g2 = (Graphics2D) g;
	   	Color fg3D = Color.lightGray;

        g2.setPaint(fg3D);
        g2.draw(new Line2D.Double(x+10, y+10, x1 , y1));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawString("Line2D", x, y);

	   	}
	   	/*para nuevo*/

	   	public void resetear(){
	   		int i;
  		for(i=0;i<nNodos;i++){
  			nodos[i]=null;
  			}

  		for(i=0;i<nArcos;i++){
  			arcos[i]=null;
  			}
	   		nNodos=0;
	   		nArcos=0;
	   		}


	   	/************/

	   	/*para grafos multiplemente conexos*/
	   	public Grafo obtenerGrafo(){

	   		return grafo;
	   		}

	   	public void establecerGrafo(Grafo grafo){
	   		this.grafo=grafo;
	   		this.nNodos=grafo.numeroNodos();
	   		this.nArcos=grafo.numeroArcos();
            this.arcos=grafo.obtenerArcos();
            this.nodos=grafo.obtenerNodos();



	   		}

	   	public int obtenerIdentificadorNodo(){
	   		return identificador;
	   		}
	   	public void nArcos(int nArcos){
	   		this.nArcos=nArcos;
	   		}

	   	public void establecerArcos(Arco [] arcos)	{
	   		this.arcos=arcos;
	   		}
	   	/**********************************/
	   	 protected static ImageIcon crearIcnos(String path) {
        java.net.URL imgURL = Graficar.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("No se puede encontrar archivo: " + path);
            return null;
        }
    }


		}