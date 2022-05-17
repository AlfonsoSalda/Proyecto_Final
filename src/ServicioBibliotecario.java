import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import javax.swing.JTextArea;

public class ServicioBibliotecario{
    JTextArea salida;
    public LinkedList<Libro> libros;
    public LinkedList<Usuario> usuarios;
    public LinkedList<Prestamo> prestamos;
    
    public ServicioBibliotecario(){
        libros = new LinkedList<>();
        usuarios = new LinkedList<>();
        prestamos = new LinkedList<>();
    }
    
    public void cargarLibros(){
        //LIBROS
        BufferedReader br = null;
        String id = "";
        String estatus = "";
        String autor ="";
        String edicion="";
        String editorial="";
        
        try {
            br=new BufferedReader(new FileReader("libros.txt"));
            while ((id= br.readLine())!=null) {
                estatus = br.readLine();
                autor = br.readLine();
                edicion = br.readLine();
                editorial = br.readLine();
                Libro libro = new Libro();
                libro.setIdLibro(Integer.parseInt(id));
                libro.setEstatus(true);
                libro.setAutor(autor);
                libro.setEdicion(edicion);
                libro.setEditorial(editorial);
                libros.add(libro);
            }
        } catch (IOException e) {
            System.out.println("Error"+e.getMessage());
        }
        finally{
            try{
                if(br !=null)
                    br.close();
                System.out.println(libros);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    public void cargarUsuarios(){
        //USUARIOS
        BufferedReader br = null;
        String id = "";
        String nombre = "";
        String apellidoP ="";
        String apellidoM ="";
        String direccion= "";
        String tipo="";
        
        try {
            br=new BufferedReader(new FileReader("usuarios.txt"));
            while ((id= br.readLine())!=null) {
                nombre = br.readLine();
                apellidoP = br.readLine();
                apellidoM = br.readLine();
                direccion = br.readLine();
                tipo = br.readLine();
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(Integer.parseInt(id));
                usuario.setNombre(nombre);
                usuario.setApellidoP(apellidoP);
                usuario.setApellidoM(apellidoM);
                usuario.setDireccion(direccion);
                usuario.setTipoUsuario(Integer.parseInt(tipo));
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            System.out.println("Error"+e.getMessage());
        }
        finally{
            try{
                if(br !=null)
                    br.close();
                System.out.println(usuarios);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    public void cargarPrestamos() throws ParseException{
        BufferedReader br=null;
        String id="";
        String fechaEntrada="";
        String fechaSalida="";
        String idLibro="";
        String idUsuario="";
        
        try {
            br=new BufferedReader(new FileReader("prestamos.txt"));
            while((id=br.readLine())!=null){
                fechaEntrada=br.readLine();
                fechaSalida=br.readLine();
                idLibro=br.readLine();
                idUsuario=br.readLine();
                Prestamo prestamo = new Prestamo(Integer.parseInt(id),fechaEntrada,fechaSalida,Integer.parseInt(idLibro),Integer.parseInt(idUsuario),libros);
                prestamo.setIdPrestamo(Integer.parseInt(id));
                prestamo.setFechaEntrada(LocalDate.parse(fechaEntrada,DateTimeFormatter.ISO_LOCAL_DATE));
                prestamo.setFechaSalida(LocalDate.parse(fechaSalida,DateTimeFormatter.ISO_LOCAL_DATE));
                prestamo.setIdLibro(Integer.parseInt(idLibro));
                prestamo.setIdUsuario(Integer.parseInt(idUsuario));
            }
        } catch (IOException e) {
            System.out.println("Error"+e.getMessage());
        } finally{
            try {
                if(br!=null){
                    br.close();
                    System.out.println(prestamos);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
     
    public void registrarPrestamo(){}
    
    public void actualizarEstatus(){}
    
    public void visualizarPrestamos(){}
    
}
