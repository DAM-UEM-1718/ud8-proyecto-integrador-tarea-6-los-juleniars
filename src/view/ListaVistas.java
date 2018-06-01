package view;

/**
 * Clase para contener vistas junto a su título para su uso en JTabbedPane
 */
public class ListaVistas {

    private String titulo;
    private Vista vista;

    public ListaVistas(String titulo, Vista vista) {
        this.titulo = titulo;
        this.vista = vista;
    }

    /**
     * @return El título de la vista
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return La vista
     */
    public Vista getVista() {
        return vista;
    }
}
