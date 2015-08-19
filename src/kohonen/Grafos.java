/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohonen;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.net.URL;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Grafos implements ActionListener{

    final static boolean RIGHT_TO_LEFT = false;
    static final private String NUEVO = "Nuevo";
    static final private String ABRIR = "Abrir";
    static final private String GUARDAR = "Guardar";
    static final private String GRAFO_TOTALMENTE_CONEXO = "GTC";
    static final private String PESOS_AUTOMATICOS = "Pesos Automaticos";
    static final private String AGENTE_VIAJERO = "TSP Greedy";
    static final private String KOHONENI = "TSP Kohonen I";
    static final private String KOHONENII = "TSP Kohonen II";

    JPanel panelPrincipal;
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;
	Graficar canvas1;

	JFrame frame;// en que frame esta

	int opcion;  //0 dibujar grafos, 1 dibujar lineas

	public Grafos(JFrame frame){

		this.frame=frame;
		opcion=0;
		panelPrincipal = new JPanel(new GridBagLayout());
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
	/*	panel4=new JPanel();
	*/	agregarComponentes();

  	    panel1.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Area dibujo"),
            BorderFactory.createEmptyBorder(0,5,5,5)));

        panel2.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Dibujar"),
            BorderFactory.createEmptyBorder(0,5,5,5)));

        panel3.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Barra de Herramientas"),
            BorderFactory.createEmptyBorder(0,5,5,5)));

		GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        	if (RIGHT_TO_LEFT) {
            	panelPrincipal.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      	     }
        c.fill = GridBagConstraints.HORIZONTAL;
        	c.gridwidth = 1;
       		c.weightx = 1.0;
			c.weighty = 1.0;
        	c.ipady = 5;
        //	c.ipadx = 40;
        	c.gridx = 0;
			c.gridy = 0;
        panelPrincipal.add(panel3,c);
        	c.ipady = 0;      //
			c.weightx = 1.0;
			c.weighty = 1.0;
			c.gridwidth = 1;
			c.gridx = 0;
			c.gridy = 1;
        panelPrincipal.add(panel1,c);
         	c.gridwidth = 1;
       		c.weightx = 1.0;
			c.weighty = 1.0;
        	c.ipady = 5;
        //	c.ipadx = 40;
        	c.gridx = 0;
			c.gridy = 2;
        panelPrincipal.add(panel2,c);

		//panelPrincipal.add(panel1);
		//panelPrincipal.add(panel2);
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.PAGE_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	/*	panelPrincipal.add(panel3);
		panelPrincipal.add(panel4);
	*/	}

	public void agregarComponentes(){

		crearPanel1();
		crearPanel2();
    	crearPanel3();
	/*	crearPanel4();
	*/	}


	public void crearPanel1(){
		canvas1= new Graficar(this);
		canvas1.tamano(800,350);
	//	canvas1.graficarDatos(f1(),obtenerMayor(f1()));
		panel1.add(canvas1);

		}

	public void crearPanel2(){

		String nodoCadena ="Nodos";
		String arcoCadena ="Arcos";
		String mostrarCadena ="Mostrar";

		String nodoValorCadena ="Valor Nodo";
		String arcoValorCadena ="Valor Arco";

		JRadioButton nodoButton = new JRadioButton(nodoCadena);
    	nodoButton.setMnemonic(KeyEvent.VK_B);
    	nodoButton.setActionCommand(nodoCadena);
    	nodoButton.setSelected(true);

    	JRadioButton arcoButton = new JRadioButton(arcoCadena);
    	arcoButton.setMnemonic(KeyEvent.VK_C);
    	arcoButton.setActionCommand(arcoCadena);

    	JRadioButton mostrarButton = new JRadioButton(mostrarCadena);
    	mostrarButton.setMnemonic(KeyEvent.VK_D);
    	mostrarButton.setActionCommand(mostrarCadena);

    	JRadioButton nodoValorButton = new JRadioButton(nodoValorCadena);
    	nodoValorButton.setMnemonic(KeyEvent.VK_D);
    	nodoValorButton.setActionCommand(nodoValorCadena);

    	JRadioButton arcoValorButton = new JRadioButton(arcoValorCadena);
    	arcoValorButton.setMnemonic(KeyEvent.VK_D);
    	arcoValorButton.setActionCommand(arcoValorCadena);

    /*	JRadioButton rabbitButton = new JRadioButton(rabbitString);
    	rabbitButton.setMnemonic(KeyEvent.VK_R);
    	rabbitButton.setActionCommand(rabbitString);

    	JRadioButton pigButton = new JRadioButton(pigString);
    	pigButton.setMnemonic(KeyEvent.VK_P);
    	pigButton.setActionCommand(pigString);
   */
    //Group the radio buttons.
    	ButtonGroup group = new ButtonGroup();
    	group.add(nodoButton);
    	group.add(arcoButton);
    	group.add(mostrarButton);
    	group.add(nodoValorButton);
    	group.add(arcoValorButton);
    /*	group.add(pigButton);
    */

    	nodoButton.addActionListener(this);
    	arcoButton.addActionListener(this);
    	mostrarButton.addActionListener(this);
    	nodoValorButton.addActionListener(this);
    	arcoValorButton.addActionListener(this);


	    panel2 = new JPanel(new GridLayout(1, 0));
        panel2.add(nodoButton);
        panel2.add(nodoValorButton);
        panel2.add(arcoButton);
        panel2.add(arcoValorButton);
        panel2.add(mostrarButton);




    }

    public void crearPanel3(){



    	JToolBar toolBar = new JToolBar("Barra de Herramientas");

    	 JButton button = null;

        button = hacerBotonNavegable("Nuevo", NUEVO,
                                      "Nuevo Grafo",
                                      "Nuevo");
        toolBar.add(button);


        button = hacerBotonNavegable("Abrir", ABRIR,
                                      "Abrir Grafo",
                                      "Abrir");
        toolBar.add(button);


        button = hacerBotonNavegable("Guardar", GUARDAR,
                                     "Guardar Grafo",
                                     "Guardar");
        toolBar.add(button);

		button = hacerBotonNavegable("GTC", GRAFO_TOTALMENTE_CONEXO,
                                      "Grafo Totalmente Conexo",
                                      "GTC");
        toolBar.add(button);

        button = hacerBotonNavegable("distancias", PESOS_AUTOMATICOS,
                                      "Pesos Automaticos",
                                      "Pesos Automaticos");
        toolBar.add(button);


        button = hacerBotonNavegable("Solucion Greedy", AGENTE_VIAJERO,
                                     "Agente Viajero con solucion Greedy",
                                     "Solucion Greedy");
        toolBar.add(button);

        button = hacerBotonNavegable("Solucion KohonenI", KOHONENI,
                                     "Agente Viajero solucionado con Kohonen y ventana gaussiana",
                                     "Solucion KohonenI");
        toolBar.add(button);

        button = hacerBotonNavegable("Solucion KohonenII", KOHONENII,
                                     "Agente Viajero solucionado con Kohonen y ventana triangular",
                                     "Solucion KohonenII");
        toolBar.add(button);



	    panel3 = new JPanel(new GridLayout(1, 0));
        panel3.add(toolBar);

    }



    protected JButton hacerBotonNavegable(String imageName,
    									  String actionCommand,
    									  String toolTipText,
                                          String altText) {
        //Busca la Imagen
        String imgLocation = "/imagenes/"+ imageName+ ".gif";
        URL imageURL = Grafos.class.getResource(imgLocation);

        //Crea e inicializa el Boton
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);

        if (imageURL != null) {                      //imagen encontrada
            button.setIcon(new ImageIcon(imageURL, altText));
        } else {                                     //imagen no encontrada
            button.setText(altText);

        }

        return button;
    }


    public void actionPerformed(ActionEvent e) {



             if(e.getActionCommand()=="Nodos"){
             	opcion=0;
             	}
			 if(e.getActionCommand()=="Arcos"){
			 	opcion=1;
			 	}

			 if(e.getActionCommand()=="Mostrar"){
			 	opcion=3;
			 	Grafo G;
			 	  G=canvas1.obtenerGrafo();
			 	  G.mostrar();
			 	}

			 if(e.getActionCommand()=="Valor Nodo"){
			 	opcion=2;
			 	}

			 if(e.getActionCommand()=="Valor Arco"){
			 	opcion=4;
			 	}

			 if (NUEVO.equals(e.getActionCommand())) {
			 //opcion=5;
			 Grafo grafo;
             grafo=canvas1.obtenerGrafo();
             grafo.resetear();
             canvas1.resetear();
             canvas1.establecerGrafo(grafo);
             canvas1.repaint();
             }
             else if (ABRIR.equals(e.getActionCommand())) {
             opcion=6;
             System.out.println("opcion = "+opcion);
             }
             else if (GUARDAR.equals(e.getActionCommand())) {
             opcion=7;
             System.out.println("opcion = "+opcion);
             }
             else if (GRAFO_TOTALMENTE_CONEXO.equals(e.getActionCommand())) {
             //opcion=8;
             Grafo grafo;
             grafo=canvas1.obtenerGrafo();
             grafo.grafoMultiplementeConexo();
             canvas1.establecerGrafo(grafo);
             canvas1.repaint();
             }

             else if (PESOS_AUTOMATICOS.equals(e.getActionCommand())) {
             Grafo grafo;
             grafo=canvas1.obtenerGrafo();
             grafo.establecerPesosAutomaticos();
             canvas1.establecerGrafo(grafo);
             canvas1.repaint();
             }

             else if (AGENTE_VIAJERO.equals(e.getActionCommand())) {
             Grafo grafo;
             grafo=canvas1.obtenerGrafo();
             AgenteViajero greedy=new AgenteViajero(grafo,0);
             greedy.mostrarSolucionGreedy();
         //    canvas1.establecerGrafo(grafo);
             canvas1.repaint();             //ruta segun kohonen
 //            kohonen k=new kohonen(grafo);

             }

             else if (KOHONENI.equals(e.getActionCommand())) {
             Grafo grafo;
             grafo=canvas1.obtenerGrafo();
             Kohonen redNeuronal=new Kohonen(grafo,1);//gaussiana
          //  greedy.mostrarSolucionGreedy();
         //    canvas1.establecerGrafo(grafo);
             canvas1.repaint();
             //ruta segun kohonen
       //      kohonen k=new kohonen(grafo);

             }
             else if (KOHONENII.equals(e.getActionCommand())) {
             Grafo grafo;
             grafo=canvas1.obtenerGrafo();
             Kohonen redNeuronal=new Kohonen(grafo,0);//triangular
          //  greedy.mostrarSolucionGreedy();
         //    canvas1.establecerGrafo(grafo);
             canvas1.repaint();

             }

	}

	public int opcion(){
		return opcion;
		}

	public JFrame obtenerFrame(){
		return this.frame;
		}

    private static void crearMostrarGUI() {

    	JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("El Problema del Agente Viajero                Jorge Luis Guevara Diaz jorjasso@hotmail.com");
        Grafos ventana = new Grafos(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(ventana.panelPrincipal);
        frame.pack();
        frame.setVisible(true);

       
        }
    public static void main(String[] args) {

	    int identificador=0;

  /*      Nodo n1= new Nodo("A");
        	 n1.establecerIdentificador(identificador++);
        Nodo n2= new Nodo("B");
        	 n2.establecerIdentificador(identificador++);
        Nodo n3= new Nodo("C");
         	 n3.establecerIdentificador(identificador++);
        Nodo n4= new Nodo("D");
             n4.establecerIdentificador(identificador++);
        Nodo n5= new Nodo("E");
        	 n5.establecerIdentificador(identificador++);

        Arco a1=new Arco(n1,n2);
        	 a1.peso(5);
        Arco a2=new Arco(n2,n3);
        	 a2.peso(6);
        Arco a3=new Arco(n3,n5);
        	 a3.peso(7);
        Arco a4=new Arco(n1,n4);
        	 a4.peso(8);
        Arco a5=new Arco(n2,n4);
        	 a5.peso(8);

        Grafo grafo=new Grafo();
        	  grafo.agregarNodo(n1);
        	  grafo.agregarNodo(n2);
        	  grafo.agregarNodo(n3);
        	  grafo.agregarNodo(n4);
        	  grafo.agregarNodo(n5);
        	  grafo.agregarArcos(a1);
        	  grafo.agregarArcos(a2);
        	  grafo.agregarArcos(a3);
        	  grafo.agregarArcos(a4);
        	  grafo.agregarArcos(a5);

        	 grafo.mostrar();

       Nodo prueba=new Nodo(10,5);
       grafo.agregarNodo(prueba);
       grafo.encontrarNodo(150,70);
    */

    /*prueba del quick sort*/
  /*  double [] A={0.25,0.00333,0.1,-0.5,0.564,0.5641,-0.156,0.156,0};
      capaCompeticion capa=new capaCompeticion(5);
  //    capa.ordenarNeuronas(A);
   		capa.agregarNeurona(3);
   		capa.EliminarNeurona(2);
   */   javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                crearMostrarGUI();
            }
        });




    }
}
