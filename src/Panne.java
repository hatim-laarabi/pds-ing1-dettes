public class Panne {

    private int id_panne;
    private int nb_pieces;
    private int cout_pieces;
    private int nb_heures;
    private int cout_panne;

    public Panne (int id_panne, int nb_pieces, int cout_pieces, int nb_heure, int cout_panne) {

        this.id_panne = id_panne;
        this.nb_pieces = nb_pieces;
        this.cout_pieces = cout_pieces;
        this.nb_heures = nb_heure;
        this.cout_panne = cout_panne;

    }

    public int getId_panne() {
        return id_panne;
    }

    public int getNb_pieces() {
        return nb_pieces;
    }

    public int getCout_pieces() {
        return cout_pieces;
    }

    public int getNb_heures() {
        return nb_heures;
    }

    public void setId_panne(int id_panne) {
        this.id_panne = id_panne;
    }

    public void setNb_pieces(int nb_pieces) {
        this.nb_pieces = nb_pieces;
    }

    public void setCout_pieces(int cout_pieces) {
        this.cout_pieces = cout_pieces;
    }

    public void setNb_heures(int nb_heures) {
        this.nb_heures = nb_heures;
    }

    public int getCout_panne() {
        return cout_panne;
    }

    public void Cout_panne(int cout_panne) {
        this.cout_panne = cout_panne;
    }
}
