package data;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class PaymentEp {
    @Id
    private Long id;
    private int id_payment;
    private float somme_payment;
    private int id_credit_payment;
    private long date_payment;

    public PaymentEp() {
    }

    public PaymentEp(int id_payment, float somme_payment, int id_credit_payment, long date_payment) {
        this.id_payment = id_payment;
        this.somme_payment = somme_payment;
        this.id_credit_payment = id_credit_payment;
        this.date_payment = date_payment;
    }

    public int getId_payment() {
        return id_payment;
    }

    public void setId_payment(int id_payment) {
        this.id_payment = id_payment;
    }

    public float getSomme_payment() {
        return somme_payment;
    }

    public void setSomme_payment(float somme_payment) {
        this.somme_payment = somme_payment;
    }

    public int getId_credit_payment() {
        return id_credit_payment;
    }

    public void setId_credit_payment(int id_credit_payment) {
        this.id_credit_payment = id_credit_payment;
    }

    public long getDate_payment() {
        return date_payment;
    }

    public void setDate_payment(long date_payment) {
        this.date_payment = date_payment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
