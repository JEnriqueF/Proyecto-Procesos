package Modelo.POJO;

public class Cliente {
    private int idEquipoComputo;
    private int idCliente;
    private String nombre, numTelefono, correo;
    private String descripcionEquipo;
    
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
    
    
    
    public int getIdEquipoComputo() {
        return idEquipoComputo;
    }

    public void setIdEquipoComputo(int idEquipoComputo) {
        this.idEquipoComputo = idEquipoComputo;
    }

  public String getDescripcionEquipo() {
    // Aquí debes obtener la descripción del equipo asociado al cliente desde una fuente de datos
    // Puede ser una consulta a una base de datos o la obtención de un atributo del cliente que almacene la descripción del equipo
    
    // Por ejemplo, si tienes un atributo en la clase Cliente llamado "descripcionEquipo":
    return this.descripcionEquipo;
}

}
