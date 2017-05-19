package EstructurasBasicas;
import EstructurasBasicas.GNodo;;



public class GenericList <T> {
	private GNodo<T> _cabeza;
	private GNodo<T> _cola;
	
	public GenericList(){
		_cabeza = null;
		_cola = null;
	}
	
	/**
	 * Esta lista genérica va a insertar datos al final de la lista. 
	 * @param pdato = parámetro que será de cualquier tipo que se desee 
	 */
	public void insertarAlFinal (T pdato){
		
		GNodo<T> nodo = new GNodo<T>(pdato);
		nodo.setValor(pdato);

		if (_cabeza == null) {
			_cabeza = nodo;
			_cola = nodo;
		} else {
			_cola.setSiguiente(nodo);
			_cola = nodo;			
		}
		
		
	}
	/**
	 * Obtiene de la lista cada dato
	 * @return _cabeza = la cabeza será el dato hasta que cabeza sea igual a nulo
	 */
	public GNodo<T> getIterator(){
		System.out.println(_cabeza == null);
		return _cabeza;
	}
		
	public boolean busqueda(T pValor) {
        
        GNodo<T> temp = (GNodo<T>) _cabeza;
        
        while(temp != null){
            System.out.println("Lista no es nula");
            if(temp.getValor() == pValor){
                return true;
            }
            
            temp = temp.getSiguiente();
        }
        
        return false;
    }

	public void print(){
        //Verifica si la lista contiene elementos
        if(_cabeza == null){
            // Crea una copia de la lista
            GNodo<T> aux = _cabeza;
            // Recorre la lista hasta el final
            while(aux != null){
                // Imprime en pantalla el valor del nodo
                System.out.println(aux.getValor());
                //Avanza al siguiente nodo
                aux = aux.getSiguiente();
                //Incrementa el contador de la posición
            }
        }
    }
}