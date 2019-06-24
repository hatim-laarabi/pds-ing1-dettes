public class Piece {

    private int id_piece;
    private String nom_piece;
    private int prix;

    public Piece(int id_piece, String nb_piece, int prix) {
        this.id_piece = id_piece;
        this.nom_piece = nb_piece;
        this.prix = prix;
    }

    public int getId_piece() {
        return id_piece;
    }

    public void setId_piece(int id_piece) {
        this.id_piece = id_piece;
    }

    public String getNom_piece() {
        return nom_piece;
    }

    public void setNom_piece(String nom_piece) {
        this.nom_piece = nom_piece;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
