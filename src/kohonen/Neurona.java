/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohonen;

class Neurona{

	public double wx; //peso en x
	public double wy; //peso en y

	int  identificadorNodo; //se pasa los identificadores del nodo ganador
	int Nodo;  // identificador del nodo cuya Neurona ganadora es esta

	public Neurona(){

		//inicio los pesos de las Neuronas en forma aleatoria
		wx=Math.random();
		wy=Math.random();
		identificadorNodo=-1; //solo debe haber al final una Neurona ganadora por nodo presentado

		}
		//agrega el identificador de la ciudad a su respectiva Neurona ganadora
	public void agregarIdentificadorNodo(int identificadorNodo){
		this.identificadorNodo=identificadorNodo;

		}

		//esta Neurona no tiene ciudades relacionadas
	public void reiniciar(){
		identificadorNodo=-1;
		}

	public void establecerPesos(double wx,double wy){
		this.wx=wx;
		this.wy=wy;
		}
	public Boolean verificar(){
		if (identificadorNodo>=0)
		{
			return true;

			}
		else{

			return false;
			}


		}

	}