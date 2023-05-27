package Modelo.POJO;

public class EquipoComputo {
    private int idEquipoComputo;
    private String descripcionEquipo;
    
    public EquipoComputo(){
    }

    public EquipoComputo(int idEquipoComputo, String descripcionEquipo) {
        this.idEquipoComputo = idEquipoComputo;
        this.descripcionEquipo = descripcionEquipo;
    }

    public int getIdEquipoComputo() {
        return idEquipoComputo;
    }

    public void setIdEquipoComputo(int idEquipoComputo) {
        this.idEquipoComputo = idEquipoComputo;
    }

    public String getDescripcionEquipo() {
        return descripcionEquipo;
    }

    public void setDescripcionEquipo(String descripcionEquipo) {
        this.descripcionEquipo = descripcionEquipo;
    }
}