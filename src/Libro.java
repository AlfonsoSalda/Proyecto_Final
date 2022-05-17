public class Libro {
    private int idLibro;
    private boolean estatus;
    private String autor;
    private String edicion;
    private String editorial;
    
    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    
    @Override
    public String toString(){
        if(estatus)
            return "Id libro: "+idLibro+"\nAutor: "+autor+"\nEdicion: "+edicion+
                    "\nEditorial: "+editorial+"\n\t\tEstatus: EN BIBLIOTECA.\n";
        return "Id libro: "+idLibro+"\nAutor: "+autor+"\nEdicion: "+edicion+
                    "\nEditorial: "+editorial+"\n\t\tEstatus: PRESTADO.\n";
    }
    
}
