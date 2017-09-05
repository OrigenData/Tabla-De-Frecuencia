package tabla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.*;
import org.jfree.chart.plot.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.GridLayout;

@SuppressWarnings("serial")
public class tablepress extends JFrame{

	/*
	 * En seguida se declaran variables globales
	 *
	 */
	//private Object[] Grafica1_Frec;
	private JPanel Graphics1 = new JPanel(); ;
	private JPanel Graphics2 = new JPanel();
	private JPanel Graphics3 = new JPanel();
	//private ChartPanel cp ;
	XYSeries series;
	private Double[] FAR;
	private int[] Frecuencia_A;
	private Double[]Frecuencia_R;
	private Double[] Marca;
	private int[]Frecuencia_I;
	private int[][]VectorValores;
	private data dt; //clase
	private JPanel contentPane;
	private JTextField textNum;
	private int j=1; //Esta variable indica el segundo valor a capturar
	private int o=0; //Conteo del vector dataValorNumint[]
	private Object [][]data;//= {{1,2,3},{4,5,6}}; //Valores a v
	private String[] columnNames = {"Clase", "Intervalo", "Marca de clase \"m\"", "Frecuencia \"f\"", "Frecuencia \"fi/n\"","FA","FAR \"Fi/n\""};
	private DefaultTableModel myModel = new DefaultTableModel(data,columnNames);
	private JTable table_data;
	public static int numCaptura=0;
	private DefaultPieDataset pieDataset = new DefaultPieDataset();

	///Este Vector sera utilizado para guardar los datos
	public int dataValorNumint[];
	//private int dataValorNumParseInt;


	//Aqui es la tabla de valores campturados
	private Object[][] dataValor=new Object[getNumCaptura()][1]; //Valores a v
	private String[] columnName = {"Valores"};
	private DefaultTableModel myModelValor = new DefaultTableModel(dataValor,columnName);
	private JTable table_valor;

	//Variables para las Graficas
	private DefaultCategoryDataset dataset = new DefaultCategoryDataset(); //Grafica 1
	JFreeChart chart;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {




		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tablepress frame = new tablepress();


					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public tablepress() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[97px,grow][5px][124px,grow][5px][144px]", "[25px][180.00px,grow][192.00,grow]"));

		JLabel lblNewLabel = new JLabel("Ingrese el valor #1");
		contentPane.add(lblNewLabel, "flowx,cell 0 0,alignx left,aligny center");



				textNum = new JTextField();
				textNum.setToolTipText("");
				contentPane.add(textNum, "cell 0 0,alignx left,aligny center");
				textNum.setColumns(10);

				////////////////////////   Accion del boton Captura   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

				JButton btnNewButton = new JButton("Captura");
				btnNewButton.addActionListener(new ActionListener() {
					@SuppressWarnings("unused")
					public void actionPerformed(ActionEvent arg0) {



						int dataValorN =0;


						/*
						 * Testing on console
						 */
						System.out.println("El valor de captura: "+getNumCaptura()
						+"\nLa longitud es: "+dataValorNumint.length);
						System.out.println("El valor d j:  "+j);
						System.out.println("El valor d o:  "+o);
						System.out.println("El valor d Array:  "+dataValorNumint.length);
						/*
						 * End testing on console
						 */

						try {
							//
							if(j<=getNumCaptura()) {
								//

								//Captura
								String dataValorNum []= {textNum.getText()};


								String dataValorNumintVar = textNum.getText().toString();


								/*
								 * Testing on console
								 */
								System.out.println("En String: "+dataValorNumintVar);

								if(dataValorNumintVar.matches("\\d+")) { //check if only digits. Could also be text.matches("[0-9]+")
									//
									dataValorN = Integer.parseInt(dataValorNumintVar);
									dataValorNumint[o]=dataValorN;
									}

								else{
										System.out.println("not a valid number");
										}


								/*
								 * Testing on console
								 */
								System.out.println("En int: "+dataValorNumint[o]);



								myModelValor.addRow(dataValorNum); //Se guardan los valoles en la primera tabla

								o++;
								j++;

								if(j<=getNumCaptura()) {

									lblNewLabel.setText("Ingrese el valor #"+j);
								}else {
									//

									/*
									 * Aqui termina la captura de datos, puede que ejecute otro metodo donde se realicen las operaciones
									 *
									 */
									lblNewLabel.setText("Todos los datos fueron capturados");


									///// Aqui se emprezara a llenar los datos de la siguiente tabla \\\

									//Esta clase hara todas las operaciones
									classOperation();

									//for(@SuppressWarnings("rawtypes")
									int q=0;
									int d=0;
									int l=0;
									System.out.println("Este es el numero de intervlor: "+dt.NumIntervalos());



									//Poligono de frecuencia

									series = new XYSeries("Crecimiento XYGrafico");
									/*series.add(1, 10);
									series.add(2, 20);
									series.add(3, 10);
									series.add(4, 30);
									series.add(5, 40);*/


									//Aqui se intertan los valores que seran asignados a la segunda tabla y las graficas
									for(int z= 0; z<dt.NumIntervalos(); z++) {

											//Aqui es donde se despliega el contenido de la segunda tabla
											dataset.setValue(Frecuencia_I[z], "Frecuencia", "< "+VectorValores[z][q]+" , "+VectorValores[z][q+1]+" >");// Grafica 1


											//Grafica 2
											pieDataset.setValue("< "+VectorValores[z][q]+" , "+VectorValores[z][q+1]+" >",Frecuencia_I[z]);

											//Grafica 3
											series.add(Frecuencia_I[z],VectorValores[z][q]/*Marca[z]*/);


											Object []w= {(l+1),"["+VectorValores[z][q]+" , "+VectorValores[z][q+1]+" )",Marca[z],Frecuencia_I[z], Frecuencia_R[z], Frecuencia_A[z], FAR[z]};
											myModel.addRow(w);  //Aqui se agrega el numero de clases
											System.out.println("El valor de z: "+q);
											l++;


									}




									//Se ejecutaran estos metodos para mostrar las graficas en las pestanias
									Grafica1();
									Grafica2();
									Grafica3();




									/*
									 * Testing on console
									 */
									for(int x=0; x<getNumCaptura(); x++) {
										System.out.println("\nLa matriz con valor: "+dataValorNumint[x]);
									}


							}



						}//Aqui termina if(j<=getNumCaptura())

						}catch(Exception errorr) {
							System.err.println("Muestra mensaje: "+errorr.getMessage());
						}




						//Limpia la caja de texto
						textNum.setText("");


					}
				});


				///////////////////////// Posicionamiento de Tabs \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

				contentPane.add(btnNewButton, "cell 0 0,alignx left,aligny top");

				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				contentPane.add(tabbedPane, "cell 2 0 3 3,grow");

				//JPanel Graphics1 = new JPanel();
				tabbedPane.addTab("Grafica 1", null, Graphics1, null);
				Graphics1.setLayout(new GridLayout(1, 0, 0, 0));

				//JPanel Graphics2 = new JPanel();
				tabbedPane.addTab("Grafica 2", null, Graphics2, null);
				Graphics2.setLayout(new GridLayout(1, 0, 0, 0));

				//JPanel Graphics3 = new JPanel();
				tabbedPane.addTab("Grafica 3", null, Graphics3, null);
				Graphics3.setLayout(new GridLayout(1, 0, 0, 0));



				//////////////////// Aqui se representan las tablas \\\\\\\\\\\\\\\\\\\

				JScrollPane scrollPane2 = new JScrollPane();
				contentPane.add(scrollPane2, "cell 0 1,growx");

				table_valor = new JTable(myModelValor);
				table_valor.setCellSelectionEnabled(true);
				scrollPane2.setViewportView(table_valor);
				//contentPane.add(table_valor, "cell 0 1,grow");


				/////////////////////////////////////////////////////////////////////
				JScrollPane scrollPane1 = new JScrollPane();
				contentPane.add(scrollPane1, "cell 0 2,growx");



						//JTable table_data = new JTable(dataa,columnNames);
						table_data = new JTable(myModel);
						table_data.setCellSelectionEnabled(true);
						scrollPane1.setViewportView(table_data);
						table_data.setPreferredScrollableViewportSize(new Dimension(500,500));

	}

	//Aqui se captura el numero de datos
	public int getNumCaptura() {
		//numero=numCaptura;
		//dataValorNumint=new int [new Integer(numCaptura)];
		return numCaptura;
	}

	public void setNumCaptura(int numCaptura) {
		tablepress.numCaptura = numCaptura;
		//Aqui se asigna la longitud del Array donde se capturara los valores
		dataValorNumint=new int [new Integer(numCaptura)];
	}

	//Aqui se calcula las operaciones de la clase data.java
	public void  classOperation() {

		//int[][]VectorValores;
		dt =new data(getNumCaptura());
		dt.setArray(dataValorNumint);
		System.out.println("\nEl rango es: "+dt.Rango());

		data= new Object[dt.NumIntervalos()][8];
		System.out.println("El numero de intervalor es : "+dt.NumIntervalos());
		dt.Intervalos();



		//Aqui se compia a otro vector los valores de los intervalos
		VectorValores=new int[dt.NumIntervalos()][2];
	    System.arraycopy(dt.Intervalos(), 0, VectorValores, 0,dt.NumIntervalos());


		/*
		 * Testing on console
		 */
	    System.out.print("Muestra = ");

	    for(int r=0; r<dt.NumIntervalos(); r++) {
	    	for(int z=0; z<2; z++){
	    		System.out.print(VectorValores[r][z] + " ");
	    	}

	    }

	    //Imprime
		for(int x=0; x<dt.NumIntervalos(); x++) {
			for(int z=0; z<2; z++) {
				if(z==0) {
					System.out.print("\n\n[ "+VectorValores[x][z]);
					//intevalos[x][z]=suma;
				}
				else {
					//suma+=AmpIntervalor();
					System.out.print(" "+VectorValores[x][z]+" )");
					//intevalos[x][z]=suma;
				}
			}
		}
		/*
		 * End testing on console
		 */



		//Aqui esta la Marca
		dt.Marca();
		Marca=new Double[dt.NumIntervalos()];
	    System.arraycopy(dt.Marca(), 0, Marca, 0,dt.NumIntervalos());

	    dt.Frecuencia_i();
	    Frecuencia_I=new int[dt.NumIntervalos()];
	    System.arraycopy(dt.Frecuencia_i(), 0, Frecuencia_I, 0,dt.NumIntervalos());

	    dt.Frecuencia_r();
	    Frecuencia_R = new Double[dt.NumIntervalos()];
	    System.arraycopy(dt.Frecuencia_r(), 0, Frecuencia_R, 0,dt.NumIntervalos());

	    dt.Frecuancia_a();
	    Frecuencia_A=new int[dt.NumIntervalos()];
	    System.arraycopy(dt.Frecuancia_a(), 0, Frecuencia_A, 0,dt.NumIntervalos());


	    dt.FaR();
	    FAR=new Double[dt.NumIntervalos()];
	    System.arraycopy(dt.FaR(), 0, FAR, 0,dt.NumIntervalos());

	    /*dt.Grafica1_Frec_VectorOrden();
	    Grafica1_Frec=new Object[dt.getLongitudGrafica1()];
	    System.arraycopy(dt.Grafica1_Frec_VectorOrden(), 0, Grafica1_Frec, 0, dt.getLongitudGrafica1());

	    System.out.println("////////////////////////////////////////////"+dt.getLongitudGrafica1());
	    for (int x=0; x<dt.getLongitudGrafica1(); x++) {
	        System.out.print("\n@@"+Grafica1_Frec[x]);
	        //r++;
	        }*/
	}


	public void Grafica1() {

		//Grafica1
		chart = ChartFactory.createBarChart("Histograma","Intervalos", "Frecuencia", dataset, PlotOrientation.VERTICAL, false,true, false);
		CategoryPlot  plot = chart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.GREEN);
		//Grafica1
		ChartPanel cp =new ChartPanel(chart);

		//Graphics1.setLayout(null);
		Graphics1.removeAll();
		Graphics1.add(cp);
		Graphics1.updateUI();

	}


	public void Grafica2() {

		//Grafica2
		/*XYSeriesCollection dataset2 = new XYSeriesCollection();
		dataset2.addSeries(series);
		JFreeChart chart1 = ChartFactory.createXYAreaChart("Poligono De frecuencia", "Intervalos", "Marca", dataset2, PlotOrientation.VERTICAL, true, true, false);
		*/

		JFreeChart chart1 = ChartFactory.createPieChart("Grafica de pastel",pieDataset,true,true,false);

		ChartPanel cp2= new ChartPanel(chart1);
		Graphics2.removeAll();
		Graphics2.add(cp2);
		Graphics2.updateUI();
	}

	public void Grafica3() {

		//Grafica 3
		XYSeriesCollection dataset21 = new XYSeriesCollection();
		dataset21.addSeries(series);
		JFreeChart jfreechart3 = ChartFactory.createXYLineChart("Poligono de frecuecias", "Intervalos", "Marca", dataset21, PlotOrientation.VERTICAL, false, false, false);
		ChartPanel cp3= new ChartPanel(jfreechart3);
		Graphics3.removeAll();
		Graphics3.add(cp3);
		Graphics3.updateUI();
	}
}
