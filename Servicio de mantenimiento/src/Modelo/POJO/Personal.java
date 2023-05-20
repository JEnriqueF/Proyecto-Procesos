package Modelo.POJO;

public class Personal {
    private int idPersonal, idTipoUsuario;
    private String usuario, contrasenia;

    public Personal(){
    }
    
    public Personal(int idPersonal, int idTipoUsuario, String usuario, String contrasenia) {
        this.idPersonal = idPersonal;
        this.idTipoUsuario = idTipoUsuario;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
}