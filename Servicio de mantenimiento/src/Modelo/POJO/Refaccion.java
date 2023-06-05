package Modelo.POJO;

/**
 *
 * @author froyl
 */
public class Refaccion {
    private String nombreRefaccion, proveedor;
    private double precioCosto;
    private int unidades, idRefaccion;

    public Refaccion() {
        
    }

    public Refaccion(String nombreRefaccion, String proveedor, double precioCosto, int unidades, int idRefaccion) {
        this.nombreRefaccion = nombreRefaccion;
        this.proveedor = proveedor;
        this.precioCosto = precioCosto;
        this.unidades = unidades;
        this.idRefaccion = idRefaccion;
    }

    public String getNombreRefaccion() {
        return nombreRefaccion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public double getPrecioCosto() {
        return precioCosto;
    }

    public int getUnidades() {
        return unidades;
    }

    public int getIdRefaccion() {
        return idRefaccion;
    }

    public void setNombreRefaccion(String nombreRefaccion) {
        this.nombreRefaccion = nombreRefaccion;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void setPrecioCosto(double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public void setIdRefaccion(int idRefaccion) {
        this.idRefaccion = idRefaccion;
    }
    
    public String toString(){
        return nombreRefaccion;
    }
}
