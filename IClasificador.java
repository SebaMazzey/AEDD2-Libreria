package UT06_07;



public interface IClasificador {

	public static final int METODO_CLASIFICACION_INSERCION = 1;
	public static final int METODO_CLASIFICACION_SHELL = 2;
	public static final int METODO_CLASIFICACION_BURBUJA = 3;
	public static final int METODO_CLASIFICACION_QUICKSORT = 4;
	public static final int METODO_CLASIFICACION_HEAPSORT = 5;
	public static final int METODO_CLASIFICACION_SELECCION = 6;

	/**
	 * Punto de entrada al clasificador.
	 * Se le debe pasar en el primer argumento el conjunto de datos a ordenar (array de int)
	 * y en el segundo, el algoritmo de ordenaci�n a utilizar.
	 * Por ejemplo. clasificar(new int{3,2,1},IClasificador.METODO_CLASIFICACION_INSERCION).
	 * 
	 * @param metodoClasificacion
	 * @param orden
	 * @param tamanioVector
	 * @return Un vector del tam. solicitado, ordenado por el algoritmo solicitado
	 */
	public abstract int[] clasificar(int[] datosParaClasificar,
			int metodoClasificacion);
        
	public abstract int[] ordenarPorSeleccion(int[] datosParaClasificar);

	/**
	 * @param datosParaClasificar
	 * @return
	 */
	public abstract int[] ordenarPorHeapSort(int[] datosParaClasificar);

	/**
	 * @param datosParaClasificar
	 * @return
	 */
	public abstract int[] ordenarPorQuickSort(int[] datosParaClasificar);

	/**
	 * @param datosParaClasificar
	 * @return
	 */
	public abstract int[] ordenarPorShell(int[] datosParaClasificar);

	/**
	 * @param datosParaClasificar
	 * @return
	 */
	public abstract int[] ordenarPorInsercion(int[] datosParaClasificar);

	/**
	 * @param datosParaClasificar
	 * @return
	 */
	public abstract int[] ordenarPorCuenta(int[] datosParaClasificar);

}