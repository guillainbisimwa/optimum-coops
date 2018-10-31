package data;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class TrancheEp {
    @Id
    private Long id;
    private int id_tranche;
    private float somme_tranche;
    private int id_credit_tranche;
    private long date_tranche;

    public TrancheEp() {
    }

    public TrancheEp(int id_tranche, float somme_tranche, int id_credit_tranche, long date_tranche) {
        this.id_tranche = id_tranche;
        this.somme_tranche = somme_tranche;
        this.id_credit_tranche = id_credit_tranche;
        this.date_tranche = date_tranche;
    }

    public int getId_tranche() {
        return id_tranche;
    }

    public void setId_tranche(int id_tranche) {
        this.id_tranche = id_tranche;
    }

    public float getSomme_tranche() {
        return somme_tranche;
    }

    public void setSomme_tranche(float somme_tranche) {
        this.somme_tranche = somme_tranche;
    }

    public int getId_credit_tranche() {
        return id_credit_tranche;
    }

    public void setId_credit_tranche(int id_credit_tranche) {
        this.id_credit_tranche = id_credit_tranche;
    }

    public long getDate_tranche() {
        return date_tranche;
    }

    public void setDate_tranche(long date_tranche) {
        this.date_tranche = date_tranche;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
