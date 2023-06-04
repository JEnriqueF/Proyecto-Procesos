package Modelo.POJO;

public class TipoServicio {
    private int idTipoServicio;
    private String tipoServicio;
    private double cobroManoObra;
    
    public TipoServicio(){
    }

    public TipoServicio(int idTipoServicio, String tipoServicio, double cobroManoObra) {
        this.idTipoServicio = idTipoServicio;
        this.tipoServicio = tipoServicio;
        this.cobroManoObra = cobroManoObra;
    }

    public int getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(int idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public double getCobroManoObra() {
        return cobroManoObra;
    }

    public void setCobroManoObra(double cobroManoObra) {
        this.cobroManoObra = cobroManoObra;
    }
  
}