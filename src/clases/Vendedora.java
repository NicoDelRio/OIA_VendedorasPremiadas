package clases;

public class Vendedora {
	private int numVendedora;
	private int v_ventas[];
	private boolean enCompetencia;
	
	public Vendedora(int numVendedora, int v_ventas[]) {
		this.numVendedora = numVendedora;
		this.v_ventas = v_ventas;
		this.enCompetencia = true;
	}
	
	public Vendedora (Vendedora vendedora) {
		this.numVendedora = vendedora.numVendedora;
		this.v_ventas = vendedora.v_ventas;
		this.enCompetencia = vendedora.enCompetencia;
	}

	public int getNumVendedora() {
		return numVendedora;
	}

	public boolean getEnCompetencia() {
		return enCompetencia;
	}

	public void setEnCompetencia(boolean enCompetencia) {
		this.enCompetencia = enCompetencia;
	}
	
	public void mostrar() {
		System.out.println("Vend Nro: " + numVendedora + ". Cant de ventas: " + v_ventas.length + ". En competencia: " + enCompetencia);
		System.out.print("Ventas:");
		for(int venta : v_ventas) {
			System.out.print(" " + venta);
		}
		System.out.println();
	}
	
	public int cantVentas() {
		return v_ventas.length;
	}
	

	public int mejorVentasConsecutivas(int cantVentConsec) {
		if(v_ventas.length < cantVentConsec)
			return -1;
		int auxVenta, mejorVentasConsecutivas = 0;
		for(int i = 0; i < v_ventas.length - cantVentConsec + 1; i++) {
			auxVenta = 0;
			for(int j = i; j < cantVentConsec + i; j++) {
				auxVenta += v_ventas[j];
			}
			if(mejorVentasConsecutivas < auxVenta)
				mejorVentasConsecutivas = auxVenta;
		}
		return mejorVentasConsecutivas;		
	}
	
	public int mejorVentasConsecutivas2(int cantVentConsec) {
		int auxVenta, mejorVentasConsecutivas = 0;
		for(int i = 0; i < v_ventas.length - cantVentConsec; i++) {
			auxVenta = 0;
			for(int j = i; j < cantVentConsec + i - 1; j++) {
				auxVenta += v_ventas[j];
			}
			if(mejorVentasConsecutivas < auxVenta)
				mejorVentasConsecutivas = auxVenta;
		}
		System.out.println(mejorVentasConsecutivas);
		return mejorVentasConsecutivas;		
	}	

}
