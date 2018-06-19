package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException  {
		String rutaIn = "Estructura de Carpetas\\Preparacion de la Prueba\\Lote de Prueba\\Entrada\\";
		String rutaOut = "Estructura de Carpetas\\Ejecucion de Prueba\\Salida Obtenida\\";
		String extIn =  ".in";
		String extOut =  ".out";
		PremioConVector premio;
		
		List<String> listFileName = new ArrayList<String>();
		cargarListaNombreArchivo(rutaIn, listFileName);
	    for(String fileName : listFileName) {
	    	premio = new PremioConVector(rutaIn + fileName);
    		premio.resolver(rutaOut + fileName.replace(extIn, extOut));
	    }
//		PremioConVector premio = new PremioConVector(rutaIn + "03_enunciado_ej_3.in");
//		premio.mostrarVendedoras();
//		premio.resolver(rutaOut + "03_enunciado_ej_3.in".replace(extIn, extOut));
//		premio.resolver(rutaOut + "02_enunciado_ej_2.in".replace(extIn, extOut));
	}
	
	private static void cargarListaNombreArchivo(String dir, List<String> listFileName) {
		File[] files = new File(dir).listFiles();
		for (File file : files) {
		    if (file.isFile()) {
		    	listFileName.add(file.getName());
		    }
		}
	}
	
}
