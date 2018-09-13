package com.weza_lab.benenfance.optimumcoops.credit;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.weza_lab.benenfance.optimumcoops.R;
import com.weza_lab.benenfance.optimumcoops.parcel.CreditParcel;
import com.weza_lab.benenfance.optimumcoops.parcel.PersonnesParcel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Details_credit extends AppCompatActivity {

    private TextView detail_users, user, name_, phone_, motif_, montant_, taux_,
            datte_, echeance_, etat_;
    private Button schedule_, payment_, save_;
    private long mDatte;
    private CreditParcel c_;
    private PersonnesParcel p_;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_credit);
        c_ = new CreditParcel();
        p_ = new PersonnesParcel();

        Intent intent = this.getIntent();
        if (intent != null) {
            Bundle credit_data = intent.getBundleExtra("credit_data");
            c_ = credit_data.getParcelable("credit");
            p_ = credit_data.getParcelable("personne");
        }
      /*  if (intent.hasExtra("mDate")) {
            //mDatte = Long.parseLong(intent.getLongExtra("mDate",0));
            mDatte = intent.getLongExtra("mDate",0);
        } else mDatte = 0;*/
        //innitialise les variables
        //initialisee la  base des donmnes

        detail_users = findViewById(R.id.detail_users);
        user = findViewById(R.id.user);
        name_ = findViewById(R.id.name_);
        phone_ = findViewById(R.id.phone_);
        motif_ = findViewById(R.id.motif_);
        montant_ = findViewById(R.id.montant_);
        taux_ = findViewById(R.id.taux_);
        datte_ = findViewById(R.id.datte_);
        echeance_ = findViewById(R.id.echeance_);
        etat_ = findViewById(R.id.etat_);

        schedule_ = findViewById(R.id.schedule_);
        payment_ = findViewById(R.id.payment_);
        save_ = findViewById(R.id.save_);

        Toolbar toolbar = findViewById(R.id.toolbar);

        //Personnes p = dbQueries2.readOnePersonnes(c_.getPhone_user_credit());
        detail_users.setText("Details du credit");

        if (p_.getDefault_type() == 100)
            user.setText("Agriculteur");
        else if (p_.getDefault_type() == 101)
            user.setText("Commercant");
        else if (p_.getDefault_type() == 102)
            user.setText("Employer");
        else if (p_.getDefault_type() == 103)
            user.setText("Entrepreneur");

        name_.setText(p_.getNom_a() + " " + p_.getPostnom_a());
        phone_.setText(p_.getPhone_a());

        motif_.setText(c_.getMotif_credit());
        montant_.setText(c_.getSomme_credit() + "");
        taux_.setText(c_.getTaux_credit() + "");

        Date date_credit = new Date(c_.getTimestamp());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        String date_string = df.format(date_credit);
        datte_.setText(date_string);
        echeance_.setText(c_.getDuree() + "");
        if (c_.getEtat_credit() == 0)
            etat_.setText("En attente");
        else if (c_.getEtat_credit() == 1)
            etat_.setText("Invalide");
        else if (c_.getEtat_credit() == 2)
            etat_.setText("Valide");


        toolbar.setBackgroundColor(R.color.blue);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Details");

        getSupportActionBar().setElevation(0.0f);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);


    }

}
