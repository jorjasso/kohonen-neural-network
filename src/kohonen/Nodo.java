/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohonen;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

class Nodo {

	public  int identificador;
	public	String nombre;  //nombre del nodo
	public  Boolean visitado;

	//para dibujo
	public  Boolean seleccionado;
	public  double distancia;
	int x;
	int y;
	int r;

	//para algoritmo de kohonen
	double x1;
	double y1;

	public Nodo(){}
	public Nodo(String nombre){
		this.nombre=nombre;
		visitado=false;
		seleccionado=false;
		}

	public Nodo(int x, int y){
		visitado=false;
		seleccionado=false;
		this.x=x;
		this.y=y;
		r=10;

		nombre="sin nombre";
		normalizar();
		}


	public void establecerIdentificador(int identificador){
		this.identificador=identificador;
		}

	public int obtenerIdentificador(){
		return identificador;
		}

	public void establecerNombre(String nombre){
		this.nombre=nombre;
		}

	public String obtenerNombre(){
		return nombre;
		}

	public void dibujar(java.awt.Graphics g) {
		Color colorNodo;
        if(seleccionado==true) {colorNodo=java.awt.Color.red;}
        else {colorNodo=java.awt.Color.blue;
        }
       // g.fillOval(x-r,y-r,2*r,2*r);
        GradientPaint  redtowhite = new GradientPaint(x,y,colorNodo,x+25, y,Color.white);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(redtowhite);
		g2.fill (new Ellipse2D.Double(x, y, r*2,r*2));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setPaint(java.awt.Color.gray);
		g2.drawString(""+this.obtenerIdentificador()+" : "+this.obtenerNombre(), x, y);



    }

    public void dibujarBlanco(java.awt.Graphics g) {
		Color colorNodo=java.awt.Color.white;

        GradientPaint  redtowhite = new GradientPaint(x,y,colorNodo,x+25, y,Color.white);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(colorNodo);
		g2.fill (new Ellipse2D.Double(x, y, r*2,r*2));
//		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

   	public double distancia(int x, int y) {
        double temp=Math.sqrt(0.0+(this.x+10-x)*(this.x+10-x)+(this.y+10-y)*(this.y+10-y));
        this.distancia=temp;
         return temp;
    }

    public double obtenerDistancia(){
    	return distancia;
    	}
    public void esSeleccionado(){
    	seleccionado=true;
    	}

    public void noEsSeleccionado(){
    	seleccionado=false;
    	}

    public Boolean seleccionado(){
    	return seleccionado;

    	}
    public void XY(){

    	System.out.print(" x = "+x+" y = "+y);

    	}

    public void estabelcerXY(int x, int y){
    	this.x=x;
    	this.y=y;
    	}

    public int obtenerX(){
    	return x;
    	}
	public int obtenerY(){
    	return y;
    	}
    	//normaliza las coordenadas  para el algoritmo de kohonen
    public void normalizar (){
    	double dist;
    	double sum;
    	sum=x*x+y*y;
    	dist=Math.sqrt(sum);
    	x1=x/dist;
    	y1=y/dist;
    	}

    //retorna los valores normalizdos para kohonen
    public double obtnerX1(){
    	return x1;
    	}
    public double obtnery1(){
    	return y1;
    	}

	}



