package com.weza_lab.benenfance.optimumcoops.group;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.weza_lab.benenfance.optimumcoops.MainActivity;
import com.weza_lab.benenfance.optimumcoops.R;
import com.weza_lab.benenfance.optimumcoops.database.DBHelper;
import com.weza_lab.benenfance.optimumcoops.database.DBQueries;
import com.weza_lab.benenfance.optimumcoops.pojo.Groups;

public class Add_group extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private ScrollView login_form;
    private Spinner type_spinner;
    private AutoCompleteTextView nom;

    private AutoCompleteTextView adresse;

    private DBQueries dbQueries;
    private DBHelper dbHelper;
    private int default_type_;
    private LinearLayout bottom_layout;

    // UI references.
    private GroupRegisterTask mAuthTask = null;
    private View mProgressView;
    private Button inscription_button;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgroup);


        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setBackgroundColor(R.color.blue);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ajouter groupe");

        getSupportActionBar().setElevation(0.0f);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        //innitialise les variables
        //initialisee la  base des donmnes
        dbQueries = new DBQueries(getApplicationContext());
        dbHelper = new DBHelper(getApplicationContext());

        type_spinner = findViewById(R.id.spinner_type);
        nom = findViewById(R.id.nom_insc);
        adresse = findViewById(R.id.adresse_insc);

        bottom_layout = findViewById(R.id.bottom_layout);

        mProgressView = findViewById(R.id.login_progress_insc);
        login_form = findViewById(R.id.login_form);

        inscription_button = findViewById(R.id.inscription_button_insc);
        inscription_button.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter_spiner = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.type_users, R.layout.optimum_simple_spinner_item);

        adapter_spiner.setDropDownViewResource(R.layout.optimum_spiner_dropdown);
        type_spinner.setAdapter(adapter_spiner);

        //ajouter ecouteru sur le type d utilistateur
        type_spinner.setOnItemSelectedListener(this);

        default_type_ = 100;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.inscription_button_insc) {
            attemptRegister();
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            login_form.setVisibility(show ? View.GONE : View.VISIBLE);
            login_form.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    login_form.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            login_form.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int a = type_spinner.getSelectedItemPosition();

        if (a == 0) { //agriculteur 100
            default_type_ = 100;
            nom.setVisibility(View.VISIBLE);
            adresse.setVisibility(View.VISIBLE);
            bottom_layout.setVisibility(View.VISIBLE);

        } else if (a == 1) {
            default_type_ = 101;
            //petit commercant 101
            nom.setVisibility(View.VISIBLE);
            adresse.setVisibility(View.VISIBLE);
            bottom_layout.setVisibility(View.VISIBLE);
        } else if (a == 2) {
            default_type_ = 102;
            //employer 102
            nom.setVisibility(View.VISIBLE);
            adresse.setVisibility(View.VISIBLE);
            bottom_layout.setVisibility(View.VISIBLE);
        } else if (a == 3) {
            default_type_ = 103;
            //Entrepreneur 103
            nom.setVisibility(View.VISIBLE);
            adresse.setVisibility(View.VISIBLE);
            bottom_layout.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Operation non permise!",
                    Toast.LENGTH_SHORT).show();
            nom.setVisibility(View.GONE);
            adresse.setVisibility(View.GONE);
            bottom_layout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private boolean isAddressValid(String adress) {
        return adress.length() > 4;
    }

    private boolean isPlantationValide(String dim) {
        return dim.length() > 0;
    }


    private boolean isNameValid(String password) {
        return password.length() > 3;
    }

    private void attemptRegister() {
        if (mAuthTask != null) {
            return;
        }
        //Enlever les message d'Erreur s'il y en a
        nom.setError(null);
        adresse.setError(null);
        //recuperer les differentes donnees
        String nom_ = nom.getText().toString().trim();
        String adresse_ = adresse.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        //Check si erreur il y a
        if (!TextUtils.isEmpty(nom_) && !isNameValid(nom_)) {
            nom.setError(getString(R.string.error_invalid_length));
            focusView = nom;
            cancel = true;
        }
        if (!TextUtils.isEmpty(adresse_) && !isAddressValid(adresse_)) {
            adresse.setError(getString(R.string.error_invalid_adress));
            focusView = adresse;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new GroupRegisterTask(nom_,
                    adresse_,
                    default_type_);
            mAuthTask.execute((Void) null);
        }

    }

    public class GroupRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private String nom_;
        private String adresse_;
        private int default_type_;

        public GroupRegisterTask(String nom_, String adresse_, int default_type_) {
            this.nom_ = nom_;
            this.adresse_ = adresse_;
            this.default_type_ = default_type_;
        }


        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            dbQueries.open();

            //check if phone number exists
            if (!dbQueries.checkGroup(nom_)) {
                Groups groups = new Groups(0, nom_, adresse_, null, default_type_);

                if (!dbQueries.checkGroup(nom_)) {
                    dbQueries.insertGroup(groups);
                } else {
                    dbQueries.close();
                    return false;
                }
            } else return false;

            dbQueries.close();

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                Toast.makeText(getApplicationContext(), "GROUPE ENREGISTRER AVEC SUCCESS", Toast.LENGTH_SHORT).show();
                finish();

                //shared preference

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                //finish();

            } else {
                Toast.makeText(getApplicationContext(), "Erreur, ce identifiant existe deja", Toast.LENGTH_SHORT).show();

                //mPasswordView.setError(getString(R.string.error_incorrect_password));
                //mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
