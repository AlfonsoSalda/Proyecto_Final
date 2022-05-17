import static java.lang.Thread.sleep;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class Prestamo implements Runnable{
    JTextArea salida;
    LinkedList<Libro> libros;
    private Thread hilo;
    private int idPrestamo;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private long dias;
    private int idLibro;
    private int idUsuario;
    
    public Prestamo(int idPrestamo, String fechaE, String fechaS, int idLibro, int idUsuario,LinkedList<Libro> libros, JTextArea salida) throws ParseException {
       this.idPrestamo = idPrestamo;
       this.idLibro = idLibro;
       this.idUsuario = idUsuario;
       fechaEntrada = LocalDate.parse(fechaE, DateTimeFormatter.ISO_LOCAL_DATE);
       fechaSalida = LocalDate.parse(fechaS, DateTimeFormatter.ISO_LOCAL_DATE);
       this.libros = libros;
       this.salida = salida;
       
       Duration diff = Duration.between(fechaEntrada.atStartOfDay(), fechaSalida.atStartOfDay());
       dias = diff.toDays();
       System.out.println("Dias: "+dias);
       hilo = new Thread(this);
       bloquearLibro();
       salida.setText(libros.toString());
       hilo.start();  //el hilo ejecuta el método run de contador atras
    }

    public Prestamo(int idPrestamo, String fechaE, String fechaS, int idLibro, int idUsuario,LinkedList<Libro> libros) {
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.idUsuario = idUsuario;
        fechaEntrada = LocalDate.parse(fechaE, DateTimeFormatter.ISO_LOCAL_DATE);
        fechaSalida = LocalDate.parse(fechaS, DateTimeFormatter.ISO_LOCAL_DATE);
        this.libros = libros;
    }

    @Override
    public void run(){
        for(int i=(int)dias; i>0; i--){
            System.out.println("\t\tiempo="+i);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Prestamo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        desbloquearLibro();
        salida.setText(libros.toString());
    }
    
    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public long getDias() {
        return dias;
    }

    public void setDias(long dias) {
        this.dias = dias;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public void bloquearLibro(){
        for(Libro lib: libros){
            if(lib.getIdLibro() == idLibro){
                lib.setEstatus(false);
                System.out.println(lib.toString());
            }
        }
    }
    
    public void desbloquearLibro(){
        for(Libro lib: libros){
            if(lib.getIdLibro() == idLibro){
                lib.setEstatus(true);
                System.out.println(lib.toString());
            }
        }
    }
    
    @Override
    public String toString(){
        return "ID prestamo: "+idPrestamo+"\nFecha de entrada: "+fechaEntrada+
                "\nFecha de salida: "+fechaSalida+"\nDias: "+dias+"\nId libro: "
                +idLibro+"\nId usuario: "+idUsuario+"\n";
    }
    
}
