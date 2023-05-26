package Modelo.POJO;

public class Cliente {
    private int idCliente;
    private String nombre, numTelefono, correo;
    
    public Cliente(){
    }

    public Cliente(int idCliente, String nombre, String numTelefono, String correo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.numTelefono = numTelefono;
        this.correo = correo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}