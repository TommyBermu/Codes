package VideoJuegos;

public class VideoJuego {
    private int codigo;
    private String titulo;
    private String plataforma;
    private int cantJugadores;
    private String categoria;
    
    public VideoJuego() {
    }

    public VideoJuego(int codigo, String titulo, String plataforma, int cantJugadores, String categoria) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.cantJugadores = cantJugadores;
        this.categoria = categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public int getCantJugadores() {
        return cantJugadores;
    }

    public void setCantJugadores(int cantJugadores) {
        this.cantJugadores = cantJugadores;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "VideoJuego [codigo=" + codigo + ", titulo=" + titulo + ", plataforma=" + plataforma + ", cantJugadores="
                + cantJugadores + ", categoria=" + categoria + "]";
    }
}   
