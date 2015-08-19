/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohonen;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.text.*;

class Arco {

	Nodo u;
	Nodo v;
	double peso;

	public  int identificador;
	public  double distancia;
	public  Boolean seleccionado;
	public int opciones;

//	int x1,y1,x,y;

	public Arco(){
		}

	public Arco(Nodo u, Nodo v){

		seleccionado=false;
		opciones=0;

		this.u=u;
		this.v=v;
		peso=0;
/*		x1=0;
		y1=0;
		x=0;
		y=0;
*/
		}
	public void peso(double peso){
		this.peso=peso;
		}

	public double obtenerPeso(){
		return peso;
		}

	public void establecerIdentificador(int identificador){
		this.identificador=identificador;
		}

	public int obtenerIdentificador(){
		return identificador;
		}

	public double obtenerDistancia(){
    	return distancia;
    	}

	public double distancia(int x, int y) {
        double a, b, c;
        a=(u.x-x)*(u.x-x)+(u.y-y)*(u.y-y)+0.0;
        b=(v.x-x)*(v.x-x)+(v.y-y)*(v.y-y)+0.0;
        c=(u.x-v.x)*(u.x-v.x)+(u.y-v.y)*(u.y-v.y)+0.0;
  //      System.out.println("a = "+a+" b = "+b+" c ="+c);
  //     System.out.println("formula"+Math.sqrt(b-(c+b-a)*(c+b-a)/4.0/c));
        if(a>=b+c) {
 		distancia=Math.sqrt((double) b);
        return Math.sqrt((double) b);
        }
        if(b>=a+c){
        distancia=Math.sqrt((double) a);
         return Math.sqrt((double) a);
         }
         distancia=Math.sqrt(b-(c+b-a)*(c+b-a)/4.0/c);
        return Math.sqrt(b-(c+b-a)*(c+b-a)/4.0/c);
    }

	public void establecerPeso(){

		peso=(u.x-v.x)*(u.x-v.x)+(u.y-v.y)*(u.y-v.y);
		peso=Math.sqrt((double) peso);
		}
	public void dibujar(java.awt.Graphics g){

		Color colorArco;
        if(seleccionado==true) {colorArco=java.awt.Color.red;}
        else {colorArco=java.awt.Color.DARK_GRAY;
        }

        if(opciones==1) colorArco=java.awt.Color.BLUE;//dibujar kohonen triangular
        if(opciones==2) colorArco=java.awt.Color.GREEN;//dibujar kohonen gaussiana

   //     g.drawLine(u.x,u.y,v.x,v.y);
   		if(this.u.obtenerIdentificador()!=this.v.obtenerIdentificador())
        {
        	//dibujar linea
        Graphics2D g2 = (Graphics2D) g;
//	   	Color colorArco = Color.black;

        g2.setPaint(colorArco);
        g2.draw(new Line2D.Double(u.x+10,u.y+10, v.x+10 ,v.y+10));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //para formatear la salida de los valores de los pesos
        DecimalFormat miFormato = new DecimalFormat("###.##");
        String output = miFormato.format(this.obtenerPeso());

        g2.setPaint(java.awt.Color.gray);
		g2.drawString(""+output, (u.x+10+v.x+10)/2,(u.y+10+v.y+10)/2);
        }

        else{
        	//dibujar ciclo
        	Graphics2D g2 = (Graphics2D) g;
//	   		Color fg3D = Color.lightGray;

	   		g2.setPaint(colorArco);
	   		g2.setStroke(new BasicStroke(2.0f));
			g2.draw(new Arc2D.Double(u.x-25, u.y+10,50,10,110, 135,Arc2D.PIE));

        }

    }


    public void dibujar(java.awt.Graphics g, Color colorArco){

	//	Color colorArco;
   //   if(seleccionado==true) {colorArco=java.awt.Color.red;}
   //    else {colorArco=java.awt.Color.DARK_GRAY;
   //    }

   //     g.drawLine(u.x,u.y,v.x,v.y);
   		if(this.u.obtenerIdentificador()!=this.v.obtenerIdentificador())
        {
        	//dibujar linea
        Graphics2D g2 = (Graphics2D) g;
//	   	Color colorArco = Color.black;

        g2.setPaint(colorArco);
        g2.draw(new Line2D.Double(u.x+10,u.y+10, v.x+10 ,v.y+10));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //para formatear la salida de los valores de los pesos
        DecimalFormat miFormato = new DecimalFormat("###.##");
        String output = miFormato.format(this.obtenerPeso());

        g2.setPaint(java.awt.Color.gray);
		g2.drawString(""+output, (u.x+10+v.x+10)/2,(u.y+10+v.y+10)/2);
        }

        else{
        	//dibujar ciclo
        	Graphics2D g2 = (Graphics2D) g;
//	   		Color fg3D = Color.lightGray;

	   		g2.setPaint(colorArco);
	   		g2.setStroke(new BasicStroke(2.0f));
			g2.draw(new Arc2D.Double(u.x-25, u.y+10,50,10,110, 135,Arc2D.PIE));

        }

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

/*	public coordenadas(int x1,int y1,int x, int y){
		this.x1=x1;
		this.y1=y1;
		this.x=x;
		this.y=y;
		}
*/
	}