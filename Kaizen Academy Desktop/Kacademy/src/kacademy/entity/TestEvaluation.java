package kacademy.entity;

public class TestEvaluation {
private int id_evaluation;
private String nom_evaluation;
private String lien_evaluation;

    public TestEvaluation() {
    }

    public TestEvaluation(int id_evaluation) {
        this.id_evaluation = id_evaluation;
    }

    public TestEvaluation(String nom_evaluation, String lien_evaluation) {
        this.nom_evaluation = nom_evaluation;
        this.lien_evaluation = lien_evaluation;
    }


    public TestEvaluation(int id_evaluation, String nom_evaluation, String lien_evaluation) {
        this.id_evaluation = id_evaluation;
        this.nom_evaluation = nom_evaluation;
        this.lien_evaluation = lien_evaluation;
    }

    public int getId_evaluation() {
        return id_evaluation;
    }

    public String getNom_evaluation() {
        return nom_evaluation;
    }

    public String getLien_evaluation() {
        return lien_evaluation;
    }

    public void setId_evaluation(int id_evaluation) {
        this.id_evaluation = id_evaluation;
    }

    public void setNom_evaluation(String nom_evaluation) {
        this.nom_evaluation = nom_evaluation;
    }

    public void setLien_evaluation(String lien_evaluation) {
        this.lien_evaluation = lien_evaluation;
    }

    @Override
    public String toString() {
        return "TestEvaluation{" + "id_evaluation=" + id_evaluation + ", nom_evaluation=" + nom_evaluation + ", lien_evaluation=" + lien_evaluation + '}';
    }

     @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TestEvaluation other = (TestEvaluation) obj;
        if (this.id_evaluation != other.id_evaluation) {
            return false;
        }
        return true;
    }


}
