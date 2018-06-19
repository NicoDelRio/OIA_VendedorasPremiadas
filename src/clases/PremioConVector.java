package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;


public class PremioConVector {
	private Vendedora[] v_vendedoras;
	private boolean hayGanadora = false;
	private int cantVentConsec; // la cantidad de ventas consecutivas a tener en cuenta para iniciar la búsqueda 
								// de la ganadora (1 <= N <= 1000)
	
	public PremioConVector (String fileIn) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(fileIn));
		sc.useLocale(Locale.ENGLISH);
		
		int cantVendedoras = sc.nextInt();
		v_vendedoras = new  Vendedora[cantVendedoras];
		for (int i = 0; i < cantVendedoras; i++) {								//CC = O(n)
			int cantVentas = sc.nextInt();
			int[] v_aux_importes = new int[cantVentas];
			for (int j = 0; j < cantVentas ; j++) {
				v_aux_importes[j]  = sc.nextInt();
			}
			v_vendedoras[i] = (new Vendedora(i + 1, v_aux_importes));
		}
		cantVentConsec = sc.nextInt();
		sc.close();	
	}
	
	public void mostrarVendedoras() {
		for(Vendedora vendedora : v_vendedoras) {
			if(vendedora.getEnCompetencia())
				vendedora.mostrar();
		}
	}
	
	public void resolver(String fileOut) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter(fileOut));  
		int auxVenta, mejorVendedora = 0, mejorVentasConsecutivas, cVendConMejorVenta;
//		System.out.println("v_vendedoras.length: " + v_vendedoras.length);
		do {
			mejorVentasConsecutivas = 0;
			cVendConMejorVenta = 0;
			for(int i = 0; i < v_vendedoras.length; i++) {							//CC = O(n)
//				System.out.println("Ciclo for - bucle nro: " + (i + 1));
				if(v_vendedoras[i].getEnCompetencia()) {
					if(v_vendedoras[i].cantVentas() < cantVentConsec) {
						v_vendedoras[i].setEnCompetencia(false);
					}
					else {
						auxVenta = v_vendedoras[i].mejorVentasConsecutivas(cantVentConsec);
						if(mejorVentasConsecutivas == auxVenta) {
							cVendConMejorVenta++;
						}
						if(mejorVentasConsecutivas < auxVenta) {
							mejorVentasConsecutivas = auxVenta;
							cVendConMejorVenta = 1;
							mejorVendedora = i + 1;
							hayGanadora = true;
						}
					}					
				}
			}
//			System.out.println("Finalizo ciclo for");
//			System.out.println("cVendConMejorVenta:" + cVendConMejorVenta);
			if(cVendConMejorVenta > 1) {
				for (int i = 0; i < v_vendedoras.length; i++) {								
					if(v_vendedoras[i].getEnCompetencia() && v_vendedoras[i].mejorVentasConsecutivas(cantVentConsec) < mejorVentasConsecutivas) {
						v_vendedoras[i].setEnCompetencia(false);;
					}
				}
				cantVentConsec++;
			}
//			System.out.println("cVendConMejorVenta: " + cVendConMejorVenta);
		}while(cVendConMejorVenta > 1);
		if(!hayGanadora) {
			System.out.println("No hay ganadoras");
			salida.println("No hay ganadoras");
			salida.close();
			return;
		}
		if(cVendConMejorVenta == 0) {
			System.out.println("No se puede desempatar");
			salida.println("No se puede desempatar");
			salida.close();
			return;
		}
		System.out.println(mejorVendedora);
		System.out.println(cantVentConsec + " " + mejorVentasConsecutivas);
		salida.println(mejorVendedora);
		salida.println(cantVentConsec + " " + mejorVentasConsecutivas);
		salida.close();		
	}

}
