package tabla;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/*
*
*Esta clase hara todas la operaciones necesarias para mandarlas a imprimir a la clase tablepress
*
*/
public class data {

	@SuppressWarnings("unused")
	private int r=0;
	private int numCap=0;
	private int[] VectorValores;
	Object[] filtro;
	//private Integer[] VectorOrdenado;
	@SuppressWarnings("unused")
	private int MAYOR;
	private int MENOR;
	private int intervalos[][];
	private int[] l;
	private Double []Fre_r;
	private int[] Fre_a;
	private Double []Fre_AR;

	public data() {
		// TODO Auto-generated constructor stub
	}

	public data(int NC) {
		this.numCap=NC;
	}

	public int getNumCaptura() {
		return numCap;
	}

	public void setNumCaptura(int numCaptura) {
		this.numCap = numCaptura;

	}


	@SuppressWarnings("unused")
	public void setArray(int []Array) { //Este metodo copiara el arreglo original para trabajar en esta clase

		int num, aux;
		 int comparaciones = 0;
		 int intercambios = 0;


		VectorValores=new int[getNumCaptura()];
	    System.arraycopy(Array, 0, VectorValores, 0,getNumCaptura());
	    System.out.print("array2 = ");


	    //Ordenamiento de burbuja
	    for(int z = 1; z < getNumCaptura(); ++z) {
	        for(int v = 0; v < (getNumCaptura() - z); ++v) {
	           comparaciones++;
	           if(VectorValores[v]  > VectorValores[v + 1]) {
	              aux = VectorValores[v];
	              VectorValores[v] = VectorValores[v + 1];
	              VectorValores[v + 1] = aux;
	              intercambios++;
	           }
	        }
	      }



	    for(int r=0; r<getNumCaptura(); r++) {
	    	 System.out.print(VectorValores[r] + " ");
	    }

	}


	public int Rango() {

        int mayor;
        int menor;
        @SuppressWarnings("unused")
		int pos;
        mayor=VectorValores[0];
        menor=VectorValores[0];
        pos=0;
        for(int f=1;f<getNumCaptura();f++) {
            if (VectorValores[f]>mayor) {
                mayor=VectorValores[f];
                pos=f;
            }
        }

        pos=0;
        for(int f=1;f<getNumCaptura();f++) {
            if (VectorValores[f]<menor) {
                menor=VectorValores[f];
                pos=f;
            }
        }

        MAYOR=mayor;
        MENOR=menor;
        return mayor-menor;

	}



	public int NumIntervalos() {
		//regla de 'Sturges'
		int K;
		K=((int)(3.3*Math.log10(getNumCaptura())))+1;
		return K;

	}

	public int AmpIntervalor() {
		int C;
		C=(Rango())/(NumIntervalos());
		return C;

	}

	public int[][] Intervalos() { //Este arreglo devolvera todos los intervalos
		intervalos=new int[NumIntervalos()][2];
		int suma=MENOR;

		for(int x=0; x<NumIntervalos(); x++) {
			for(int z=0; z<2; z++) {
				if(z==0) {
					intervalos[x][z]=suma;
				}
				else {
					suma+=AmpIntervalor();
					intervalos[x][z]=suma;
				}
			}
		}


		for(int x=0; x<NumIntervalos(); x++) {
			for(int z=0; z<2; z++) {
				if(z==0) {
					System.out.print("[ "+intervalos[x][z]);
					//intevalos[x][z]=suma;
				}
				else {
					//suma+=AmpIntervalor();
					System.out.print(" "+intervalos[x][z]+" )");
					//intevalos[x][z]=suma;
				}
			}
		}


		return intervalos;
	}



	@SuppressWarnings("unused")
	public Double[] Marca() {

		Double w;
		int q=0;
		int d=0;
		int l=0;
		Double []Marc=new Double[NumIntervalos()];

		for(int z= 0; z<NumIntervalos(); z++) {

			//Aqui es donde se despliega el contenido de la segunda tabla



			w= (double) (intervalos[z][q]+intervalos[z][q+1])/2;
			Marc[z]=w;
			//myModel.addRow(w);  //Aqui se agrega el numero de clases
			//System.out.println("El valor de z: "+q);
			l++;

		}
		return Marc;

	}


	@SuppressWarnings("unused")
	public int[] Frecuencia_i() {
		int g=0;
		int w;
		int q=0;
		int d=0;
		l= new int[NumIntervalos()];


		for(int z= 0; z<NumIntervalos(); z++) {

			//Aqui es donde se despliega el contenido de la segunda tabla
			//VectorValores[];
			//System.out.println(VectorValores[z]);

			for(int h=0; h<getNumCaptura(); h++) {

				if(VectorValores[h] < intervalos[z][q+1] && intervalos[z][q]<=VectorValores[h]) {
					l[z]+=1;
				}


			}


		}

		return l;


	}

	public Double[] Frecuencia_r() {

		Fre_r = new Double[NumIntervalos()];

		for(int x=0; x<NumIntervalos(); x++) {
			Fre_r[x]=((double) (l[x]))/((double)(getNumCaptura()));
		}

		return Fre_r;

	}


	public int[] Frecuancia_a() {
		Fre_a =  new int [NumIntervalos()];
		Fre_a[0]=l[0];
		//Fre_a[1]=l[1]+Fre_a[0];
		//Fre_a[2]=l[2]+Fre_a[1];
		//Fre_a[3]=l[3]+Fre_a[2];
		int v=0;
		for(int x=0; x<NumIntervalos(); x++) {
			v+=1;
			if(v<NumIntervalos()) {
				Fre_a[v]=l[v]+Fre_a[x];
			}



		}

		return Fre_a;
	}

	public Double[] FaR() {

	Fre_AR = new Double[NumIntervalos()];

		for(int x=0; x<NumIntervalos(); x++) {
			Fre_AR[x]=((double) (Fre_a[x]))/((double)(getNumCaptura()));
		}

		return Fre_AR;

	}




}
