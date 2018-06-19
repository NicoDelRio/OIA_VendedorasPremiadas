package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class PremioConLista {
	
	private List <Vendedora> listaVendedoras = new ArrayList<Vendedora>();
	private int cantVentConsec; // la cantidad de ventas consecutivas a tener en cuenta para iniciar la búsqueda 
								// de la ganadora (1 <= N <= 1000)
	
	public PremioConLista (String fileIn) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(fileIn));
		sc.useLocale(Locale.ENGLISH);
		
		int cantVendedoras = sc.nextInt();
		for (int i = 0; i < cantVendedoras; i++) {								//CC = O(n)
			int cantVentas = sc.nextInt();
			int[] v_aux_importes = new int[cantVentas];
			for (int j = 0; j < cantVentas; j++) {
				v_aux_importes[j]  = sc.nextInt();
			}
			listaVendedoras.add(new Vendedora(i + 1, v_aux_importes));
		}
		cantVentConsec = sc.nextInt();
		sc.close();	
	}
	
	public void mostrarVendedoras() {
		for(Vendedora vendedora : listaVendedoras) {
			vendedora.mostrar();;
		}
	}
	
	public void resolver(String fileOut) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter(fileOut));  
		int auxVenta, mejorVentasConsecutivas;
		boolean hayVendedorasConVentasConsecutivas = false;
		do {
			mejorVentasConsecutivas = 0;
//			for(Vendedora vendedora : listaVendedoras) {								//CC = O(n)
			for(Vendedora vendedora : listaVendedoras) {								//CC = O(n)
				System.out.println(vendedora.getNumVendedora());
				if(vendedora.cantVentas() < cantVentConsec) {
					listaVendedoras.remove(vendedora);
				}
				else {
					hayVendedorasConVentasConsecutivas = true;
					auxVenta = vendedora.mejorVentasConsecutivas(cantVentConsec);
					if(mejorVentasConsecutivas < auxVenta) {
						mejorVentasConsecutivas = auxVenta;
					}
				}
			}
			if(listaVendedoras.size() > 1) {
				for (Vendedora vendedora : listaVendedoras) {								
					if(vendedora.mejorVentasConsecutivas(cantVentConsec) < mejorVentasConsecutivas) {
						listaVendedoras.remove(vendedora);
					}
				}
				cantVentConsec++;
			}
		}while(listaVendedoras.size() > 1);
		if(!hayVendedorasConVentasConsecutivas) {
			salida.println("No hay ganadoras");
			salida.close();
			return;
		}
		if(listaVendedoras.isEmpty()) {
			salida.println("No se puede desempatar");
			salida.close();
			return;
		}
		salida.println(listaVendedoras.get(0).getNumVendedora());
		salida.println(cantVentConsec + " " + listaVendedoras.get(0).mejorVentasConsecutivas(cantVentConsec));
		salida.close();		
	}
	
	public void resolverConLista(String fileOut) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter(fileOut));  
		int auxVenta, mejorVentasConsecutivas;
		boolean hayVendedorasConVentasConsecutivas = false;
		do {
			mejorVentasConsecutivas = 0;
			for(Vendedora vendedora : listaVendedoras) {								//CC = O(n)
//				vendedora.mostrar();
				if(vendedora.cantVentas() < cantVentConsec) {
					System.out.println("Eliminada por no tener las " + cantVentConsec + " cant de ventas, Vend Nro: " + vendedora.getNumVendedora());
					if(listaVendedoras.remove(vendedora))
						System.out.println("elimino :" + vendedora.getNumVendedora());;
				}
				else {
					hayVendedorasConVentasConsecutivas = true;
					auxVenta = vendedora.mejorVentasConsecutivas(cantVentConsec);
					if(mejorVentasConsecutivas < auxVenta) {
						mejorVentasConsecutivas = auxVenta;
					}
				}
			}
			if(listaVendedoras.size() > 1) {
				for (Vendedora vendedora : listaVendedoras) {								
					if(vendedora.mejorVentasConsecutivas(cantVentConsec) < mejorVentasConsecutivas) {
						System.out.println("Eliminada por ventas menor a la mejor: " + vendedora.getNumVendedora());
						listaVendedoras.remove(vendedora);
					}
				}
				cantVentConsec++;
			}
		}while(listaVendedoras.size() > 1);
		if(!hayVendedorasConVentasConsecutivas) {
			System.out.println("No hay ganadoras");
			salida.println("No hay ganadoras");
			salida.close();
			return;
		}
		if(listaVendedoras.isEmpty()) {
			System.out.println("No se puede desempatar");
			salida.println("No se puede desempatar");
			salida.close();
			return;
		}
		System.out.println(listaVendedoras.get(0).getNumVendedora());
		System.out.println(cantVentConsec + " " + listaVendedoras.get(0).mejorVentasConsecutivas(cantVentConsec));
		salida.println(listaVendedoras.get(0).getNumVendedora());
		salida.println(cantVentConsec + " " + listaVendedoras.get(0).mejorVentasConsecutivas(cantVentConsec));
		salida.close();		
	}
	
	
}
