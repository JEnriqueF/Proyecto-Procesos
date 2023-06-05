package Modelo.POJO;

public class Servicio {
    private int idServicio, idTipoServicio, idCliente, idEquipoComputo, idPersonal;
    private String descripcionDiagnostico, descripcionMantenimiento, estadoServicio;
    private double cotizacion, montoTotal;
    private String descripcionEquipo;
    
    public Servicio(){
    }

    public Servicio(int idServicio, int idTipoServicio, int idCliente, int idEquipoComputo, int idPersonal, String descripcionDiagnostico, String descripcionMantenimiento, String estadoServicio, double cotizacion, double montoTotal) {
        this.idServicio = idServicio;
        this.idTipoServicio = idTipoServicio;
        this.idCliente = idCliente;
        this.idEquipoComputo = idEquipoComputo;
        this.idPersonal = idPersonal;
        this.descripcionDiagnostico = descripcionDiagnostico;
        this.descripcionMantenimiento = descripcionMantenimiento;
        this.estadoServicio = estadoServicio;
        this.cotizacion = cotizacion;
        this.montoTotal = montoTotal;
    }

   public Servicio(int idServicio, int idCliente, int idEquipoComputo) {
    this.idServicio = idServicio;
    this.idCliente = idCliente;
    this.idEquipoComputo = idEquipoComputo;
}


    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(int idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEquipoComputo() {
        return idEquipoComputo;
    }

    public void setIdEquipoComputo(int idEquipoComputo) {
        this.idEquipoComputo = idEquipoComputo;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getDescripcionDiagnostico() {
        return descripcionDiagnostico;
    }

    public void setDescripcionDiagnostico(String descripcionDiagnostico) {
        this.descripcionDiagnostico = descripcionDiagnostico;
    }

    public String getDescripcionMantenimiento() {
        return descripcionMantenimiento;
    }

    public void setDescripcionMantenimiento(String descripcionMantenimiento) {
        this.descripcionMantenimiento = descripcionMantenimiento;
    }

    public String getEstadoServicio() {
        return estadoServicio;
    }

    public void setEstadoServicio(String estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

    public double getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(double cotizacion) {
        this.cotizacion = cotizacion;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

 public String getTipoServicio() {
    String tipoServicio = "";

    // Lógica para obtener el tipo de servicio
    // Puede ser una consulta a la base de datos, una búsqueda en una lista, etc.

    return tipoServicio;
}


 public void setTipoServicio(String tipoServicio) {
    // Lógica para establecer el tipo de servicio
    // Puede ser asignar el valor a una variable de instancia, actualizar una columna en la base de datos, etc.
}


  public void setDescripcionEquipo(String descripcionEquipo) {
    this.descripcionEquipo = descripcionEquipo;
}

    public String getDescripcionEquipo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



}