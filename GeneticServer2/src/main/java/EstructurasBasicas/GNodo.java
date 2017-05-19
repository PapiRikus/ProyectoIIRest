package EstructurasBasicas;
/**
 * 
 * @author 
 *
 * @param <T>
 */
public class GNodo <T> {
    //Variable que es el nodo actual
    private T valor;
    //Nodo siguiente
    private GNodo<T> siguiente;

    /**
     * Constructor del nodo genérico
     * @param valor
     */
    public GNodo(T valor) {
        this.valor = valor;
    }

    /**
     * 
     * @return valor
     */
    public T getValor() {
        return valor;
    }

    /**
     * 
     * @param valor = Valor del nodo actual
     */
    public void setValor(T valor) {
        this.valor = valor;
    }

    /**
     * Retorna el valor del siguiente Nodo
     * @return siguiente
     */
    public GNodo<T> getSiguiente() {
        return siguiente;
    }
    
/**
 * Método para tomar el valor del siguiente Nodo
 * @param siguiente
 */
    public void setSiguiente(GNodo<T> siguiente) {
        this.siguiente = siguiente;
    }
    
}