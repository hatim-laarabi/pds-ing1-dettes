public class Pieces {

    private int id_piece;
    private String nb_piece;
    private int prix;

    public Pieces(int id_piece, String nb_piece, int prix) {
        this.id_piece = id_piece;
        this.nb_piece = nb_piece;
        this.prix = prix;
    }

    public int getId_piece() {
        return id_piece;
    }

    public void setId_piece(int id_piece) {
        this.id_piece = id_piece;
    }

    public String getNb_piece() {
        return nb_piece;
    }

    public void setNb_piece(String nb_piece) {
        this.nb_piece = nb_piece;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
