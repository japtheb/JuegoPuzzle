import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Puzzle extends JFrame {

	int tablero[][] = new int[4][4];
	final static int NUMERO_DE_LINEAS = 4;
	final static int NUMERO_DE_COLUMNAS = 4;
	int filaNumero = 0;
	int columnaNumero = 0;
	private JPanel panel;
	private JButton botonCargar;
	private JButton[][] matrizBotones;
	private Solucion solucion;

	public Puzzle() {
		setTitle("Juego puzzle");
		getContentPane().setLayout(null);
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 400, 400);
		getContentPane().add(panel);
		botonCargar = new JButton("Cargar puzzle");
		botonCargar.setBounds(100, 50, 200, 30);
		botonCargar.addActionListener(botonCargarListener);
		panel.add(botonCargar);

		setSize(400, 400);
		setLocationRelativeTo(this.getParent());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		validate();
		setVisible(true);

		for (int i = 0; i < NUMERO_DE_LINEAS * 4; i++) {
			filaNumero = i / NUMERO_DE_LINEAS;
			columnaNumero = i % NUMERO_DE_LINEAS;
			tablero[filaNumero][columnaNumero] = i;
		}
	}

	public <tablero> void swap(int n1, int n2) {

	}

	private final ActionListener botonCargarListener = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			try {
				botonCargar.setVisible(false);
				String ruta = mostrarSeleccionArchivo();
				String entrada = cargarArchivo(ruta);
				solucion = new Solucion(entrada);
				String[][] matrizEntrada = solucion.getMatrizEntrada();
				matrizBotones = new JButton[matrizEntrada.length][matrizEntrada[0].length];
				for (int i = 0; i < matrizEntrada.length; i++) {
					final int auxI = i;
					for (int j = 0; j < matrizEntrada[0].length; j++) {
						final int auxJ = j;
						matrizBotones[i][j] = new JButton(matrizEntrada[i][j]);
						matrizBotones[i][j].setBounds(4 * j, 4 * i, 40, 40);
						matrizBotones[i][j]
								.addActionListener(new ActionListener() {

									// @Override
									public void actionPerformed(ActionEvent e) {

									}

								});
						panel.add(matrizBotones[i][j]);
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	};

	private String mostrarSeleccionArchivo() {
		JFileChooser archivo = new JFileChooser();
		archivo.setDialogTitle("Abrir Archivo: ");
		archivo.setMultiSelectionEnabled(false);
		int leer = archivo.showOpenDialog(null);
		if (leer == JFileChooser.APPROVE_OPTION) {
			return archivo.getSelectedFile().getAbsolutePath();
		} else {
			throw new IllegalStateException();
		}

	}

	public String cargarArchivo(String ruta) throws IOException {
		String resultado = "";
		FileInputStream fileInputStream = new FileInputStream(ruta);
		int bytes = fileInputStream.read();
		while (bytes != -1) {
			resultado += (char) bytes;
			bytes = fileInputStream.read();
		}
		String matriz[][] = crearMatrizEntrada(resultado);
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				System.out.print(matriz[i][j]);
			}
			System.out.println();
		}
		fileInputStream.close();
		System.exit(0);
		return resultado;
	}

	private String[][] crearMatrizEntrada(String datoEntrada) {
		String[] lineas = datoEntrada.split("[\\r\\n]+");
		String[][] resultado = new String[lineas.length][lineas[0].split("-").length];

		for (int i = 0; i < lineas.length; i++) {
			String linea = lineas[i];
			String[] valores = linea.split("-");
			for (int j = 0; j < valores.length; j++) {
				resultado[i][j] = valores[j];
			}
		}
		return resultado;
	}

	public static void main(String[] args) {
		new Puzzle();

	}

}
