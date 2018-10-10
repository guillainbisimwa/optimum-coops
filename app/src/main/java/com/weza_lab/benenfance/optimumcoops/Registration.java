package com.weza_lab.benenfance.optimumcoops;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.weza_lab.benenfance.optimumcoops.database.DBHelper;
import com.weza_lab.benenfance.optimumcoops.database.DBQueries;
import com.weza_lab.benenfance.optimumcoops.pojo.Agriculteurs;
import com.weza_lab.benenfance.optimumcoops.pojo.Employer;
import com.weza_lab.benenfance.optimumcoops.pojo.Entrepreneurs;
import com.weza_lab.benenfance.optimumcoops.pojo.Petit_commercant;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class Registration extends AppCompatActivity implements LoaderCallbacks<Cursor>, AdapterView.OnItemSelectedListener, OnClickListener {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    private final Pattern phoneRegex = Pattern.compile("^\\+243[0-9]{9}$",
            Pattern.CASE_INSENSITIVE);

    // UI references.
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserRegisterTask mAuthTask = null;
    private View mProgressView;
    private View mLoginFormView;
    private Spinner type_spinner;
    private AutoCompleteTextView nom;
    private AutoCompleteTextView postnom;
    private AutoCompleteTextView phone;
    private RadioGroup gender_group;
    private RadioButton radio_male;
    private RadioButton radio_female;
    private AutoCompleteTextView mots_de_passe;
    private AutoCompleteTextView mots_de_passe_conf;
    private AutoCompleteTextView adresse;
    private AutoCompleteTextView plantation, domaine, employeur, entreprise;
    private TextInputLayout domaine_layout;
    private TextInputLayout employeur_layout;
    private TextInputLayout plantation_layout;
    private TextInputLayout entreprise_layout;
    private DBQueries dbQueries;
    private DBHelper dbHelper;
    private int default_type_;
    private LinearLayout radio_layout, bottom_layout;
    private Button inscription_button;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        //initialisee la  base des donmnes
        dbQueries = new DBQueries(getApplicationContext());
        dbHelper = new DBHelper(getApplicationContext());

        type_spinner = findViewById(R.id.spinner_type);
        nom = findViewById(R.id.nom);
        postnom = findViewById(R.id.postnom);
        phone = findViewById(R.id.phone);
        gender_group = findViewById(R.id.gender_group);
        radio_male = findViewById(R.id.radio_male);
        radio_female = findViewById(R.id.radio_female);
        mots_de_passe = findViewById(R.id.mots_de_passe);
        mots_de_passe_conf = findViewById(R.id.mots_de_passe_conf);
        adresse = findViewById(R.id.adresse);

        //les layouts de difference etntre les utilisateurs
        plantation_layout = findViewById(R.id.plantation_layout);
        domaine_layout = findViewById(R.id.domaine_layout);
        employeur_layout = findViewById(R.id.employeur_layout);
        entreprise_layout = findViewById(R.id.entreprise_layout);

        plantation = findViewById(R.id.plantation);
        domaine = findViewById(R.id.domaine);
        employeur = findViewById(R.id.employeur);
        entreprise = findViewById(R.id.entreprise);


        radio_layout = findViewById(R.id.radio_layout);
        //spiner_layout_cour = findViewById(R.id.spiner_layout_cour);
        //spiner_layout_cour.setVisibility(View.GONE);
        bottom_layout = findViewById(R.id.bottom_layout);


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        inscription_button = findViewById(R.id.inscription_button);
        inscription_button.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter_spiner = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.type_users, android.R.layout.simple_spinner_item);

        adapter_spiner.setDropDownViewResource(R.layout.optimum_spiner_dropdown);
        type_spinner.setAdapter(adapter_spiner);

        //ajouter ecouteru sur le type d utilistateur
        type_spinner.setOnItemSelectedListener(this);


        //default UI for agriculteur
        plantation_layout.setVisibility(View.VISIBLE);
        employeur_layout.setVisibility(View.GONE);
        domaine_layout.setVisibility(View.GONE);
        entreprise_layout.setVisibility(View.GONE);

        default_type_ = 100;

    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            // TODO: alert the user with a Snackbar/AlertDialog giving them the permission rationale
            // To use the Snackbar from the design support library, ensure that the activity extends
            // AppCompatActivity and uses the Theme.AppCompat theme.
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity2.class));
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptRegister() {
        if (mAuthTask != null) {
            return;
        }
        //Enlever les message d'Erreur s'il y en a
        nom.setError(null);
        postnom.setError(null);
        phone.setError(null);
        //radio_male.setError(null);
        //radio_female.setError(null);
        mots_de_passe.setError(null);
        mots_de_passe_conf.setError(null);
        adresse.setError(null);

        plantation.setError(null);
        domaine.setError(null);
        employeur.setError(null);
        entreprise.setError(null);

        //recuperer les differentes donnees
        //String type_spinner_ = type_spinner.getText().toString().trim();
        String nom_ = nom.getText().toString().trim();
        String postnom_ = postnom.getText().toString().trim();
        String phone_ = phone.getText().toString().trim();
        //String gender_group_ = gender_group.getText().toString().trim();
        //String radio_male_ = radio_male.getText().toString().trim();
        //String radio_female_ = radio_female.getText().toString().trim();
        String mots_de_passe_ = mots_de_passe.getText().toString().trim();
        String mots_de_passe_conf_ = mots_de_passe_conf.getText().toString().trim();
        String adresse_ = adresse.getText().toString().trim();
        //String plantation_ = plantation.getText().toString().trim();
        //String spinner_cours_eau_ = spinner_cours_eau.getText().toString().trim();

        //les string de diffence entre les type des utilisateur
        String plantation_ = plantation.getText().toString().trim();
        String domaine_ = domaine.getText().toString().trim();
        String employeur_ = employeur.getText().toString().trim();
        String entreprise_ = entreprise.getText().toString().trim();


        //default_type_ = 100;

        boolean cancel = false;
        View focusView = null;

        //Check si erreur il y a
        if (!TextUtils.isEmpty(nom_) && !isNameValid(nom_)) {
            nom.setError(getString(R.string.error_invalid_length));
            focusView = nom;
            cancel = true;
        }
        if (!TextUtils.isEmpty(postnom_) && !isNameValid(postnom_)) {
            postnom.setError(getString(R.string.error_invalid_length));
            focusView = postnom;
            cancel = true;
        }
        if (!validatePhone(phone_)) {
            phone.setError(getString(R.string.error_invalid_phone));
            focusView = phone;
            cancel = true;
        }
        if (!TextUtils.isEmpty(mots_de_passe_) && !isNameValid(mots_de_passe_)) {
            mots_de_passe.setError(getString(R.string.error_invalid_password));
            focusView = mots_de_passe;
            cancel = true;
        }
        if (!TextUtils.isEmpty(mots_de_passe_conf_) && !isNameValid(mots_de_passe_conf_)) {
            mots_de_passe_conf.setError(getString(R.string.error_invalid_password));
            focusView = mots_de_passe_conf;
            cancel = true;
        }

        if (!TextUtils.isEmpty(adresse_) && !isAddressValid(adresse_)) {
            adresse.setError(getString(R.string.error_invalid_adress));
            focusView = adresse;
            cancel = true;
        }
        if (!TextUtils.isEmpty(plantation_) && !isPlantationValide(plantation_)) {
            plantation.setError(getString(R.string.error_field_required));
            focusView = plantation;
            cancel = true;
        }
        if (!TextUtils.isEmpty(domaine_) && !isNameValid(domaine_)) {
            domaine.setError(getString(R.string.error_field_required));
            focusView = domaine;
            cancel = true;
        }
        if (!TextUtils.isEmpty(employeur_) && !isNameValid(employeur_)) {
            employeur.setError(getString(R.string.error_field_required));
            focusView = employeur;
            cancel = true;
        }
        if (!TextUtils.isEmpty(entreprise_) && !isNameValid(entreprise_)) {
            entreprise.setError(getString(R.string.error_field_required));
            focusView = entreprise;
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
            mAuthTask = new UserRegisterTask(nom_,
                    postnom_,
                    phone_,
                    mots_de_passe_,
                    mots_de_passe_conf_,
                    adresse_,
                    plantation_,
                    domaine_,
                    employeur_,
                    entreprise_,
                    default_type_);
            mAuthTask.execute((Void) null);
        }

    }

    private boolean isAddressValid(String adress) {
        return adress.length() > 4;
    }

    private boolean isPlantationValide(String dim) {
        return dim.length() > 0;
    }

    public boolean validatePhone(String phone) {
        Matcher match = phoneRegex.matcher(phone);
        return match.find();
    }

    private boolean isNameValid(String password) {
        return password.length() > 3;
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

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(Registration.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        //mEmailView.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        //adapterView.getS
        int a = type_spinner.getSelectedItemPosition();

        if (a == 0) { //agriculteur 100
            default_type_ = 100;
            nom.setVisibility(View.VISIBLE);
            postnom.setVisibility(View.VISIBLE);
            phone.setVisibility(View.VISIBLE);
            radio_layout.setVisibility(View.VISIBLE);
            //spiner_layout_cour.setVisibility(View.VISIBLE);
            /*gender_group.setVisibility(View.GOVISIBLE*/
            /*radio_male.setVisibility(View.GOVISIBLE*/
            /*radio_female.setVisibility(View.GOVISIBLE*/
            mots_de_passe.setVisibility(View.VISIBLE);
            mots_de_passe_conf.setVisibility(View.VISIBLE);
            adresse.setVisibility(View.VISIBLE);
            bottom_layout.setVisibility(View.VISIBLE);

            plantation_layout.setVisibility(View.VISIBLE);
            //invisibles
            domaine_layout.setVisibility(View.GONE);
            employeur_layout.setVisibility(View.GONE);
            entreprise_layout.setVisibility(View.GONE);

        } else if (a == 1) {
            default_type_ = 101;
            //petit commercant 101
            nom.setVisibility(View.VISIBLE);
            postnom.setVisibility(View.VISIBLE);
            phone.setVisibility(View.VISIBLE);
            radio_layout.setVisibility(View.VISIBLE);
            //spiner_layout_cour.setVisibility(View.VISIBLE);
            /*gender_group.setVisibility(View.GOVISIBLE*/
            /*radio_male.setVisibility(View.GOVISIBLE*/
            /*radio_female.setVisibility(View.GOVISIBLE*/
            mots_de_passe.setVisibility(View.VISIBLE);
            mots_de_passe_conf.setVisibility(View.VISIBLE);
            adresse.setVisibility(View.VISIBLE);
            bottom_layout.setVisibility(View.VISIBLE);

            plantation_layout.setVisibility(View.GONE);
            //invisibles
            domaine_layout.setVisibility(View.VISIBLE);
            employeur_layout.setVisibility(View.GONE);
            entreprise_layout.setVisibility(View.GONE);
        } else if (a == 2) {
            default_type_ = 102;
            //employer 102
            nom.setVisibility(View.VISIBLE);
            postnom.setVisibility(View.VISIBLE);
            phone.setVisibility(View.VISIBLE);
            radio_layout.setVisibility(View.VISIBLE);
            //spiner_layout_cour.setVisibility(View.VISIBLE);
            /*gender_group.setVisibility(View.GOVISIBLE*/
            /*radio_male.setVisibility(View.GOVISIBLE*/
            /*radio_female.setVisibility(View.GOVISIBLE*/
            mots_de_passe.setVisibility(View.VISIBLE);
            mots_de_passe_conf.setVisibility(View.VISIBLE);
            adresse.setVisibility(View.VISIBLE);
            bottom_layout.setVisibility(View.VISIBLE);

            plantation_layout.setVisibility(View.GONE);
            //invisibles
            domaine_layout.setVisibility(View.GONE);
            employeur_layout.setVisibility(View.VISIBLE);
            entreprise_layout.setVisibility(View.GONE);
        } else if (a == 3) {
            default_type_ = 103;
            //Entrepreneur 103
            nom.setVisibility(View.VISIBLE);
            postnom.setVisibility(View.VISIBLE);
            phone.setVisibility(View.VISIBLE);
            radio_layout.setVisibility(View.VISIBLE);
            //spiner_layout_cour.setVisibility(View.VISIBLE);
            /*gender_group.setVisibility(View.GOVISIBLE*/
            /*radio_male.setVisibility(View.GOVISIBLE*/
            /*radio_female.setVisibility(View.GOVISIBLE*/
            mots_de_passe.setVisibility(View.VISIBLE);
            mots_de_passe_conf.setVisibility(View.VISIBLE);
            adresse.setVisibility(View.VISIBLE);
            bottom_layout.setVisibility(View.VISIBLE);

            plantation_layout.setVisibility(View.GONE);
            //invisibles
            domaine_layout.setVisibility(View.GONE);
            employeur_layout.setVisibility(View.GONE);
            entreprise_layout.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Operation non permise!",
                    Toast.LENGTH_SHORT).show();
            nom.setVisibility(View.GONE);
            postnom.setVisibility(View.GONE);
            phone.setVisibility(View.GONE);
            radio_layout.setVisibility(View.GONE);
            //spiner_layout_cour.setVisibility(View.GONE);
            /*gender_group.setVisibility(View.GONE);*/
            /*radio_male.setVisibility(View.GONE);*/
            /*radio_female.setVisibility(View.GONE);*/
            mots_de_passe.setVisibility(View.GONE);
            mots_de_passe_conf.setVisibility(View.GONE);
            adresse.setVisibility(View.GONE);
            bottom_layout.setVisibility(View.GONE);

            plantation_layout.setVisibility(View.GONE);
            //invisibles
            domaine_layout.setVisibility(View.GONE);
            employeur_layout.setVisibility(View.GONE);
            entreprise_layout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.inscription_button) {
            attemptRegister();
        }
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private String nom_;
        private String postnom_;
        private String phone_;
        //private String gender_group_;
        //private String radio_male_;
        //private String radio_female_;
        private String mots_de_passe_;
        private String mots_de_passe_conf_;
        private String adresse_;
        private String plantation_;
        private boolean gender_;

        private String domaine_, employeur_, entreprise_;
        private int default_type_;

        public UserRegisterTask(String nom_, String postnom_, String phone_, String mots_de_passe_, String mots_de_passe_conf_, String adresse_,
                                String plantation_, String domaine_, String employeur_, String entreprise_, int default_type_) {
            this.nom_ = nom_;
            this.postnom_ = postnom_;
            this.phone_ = phone_;
            this.mots_de_passe_ = mots_de_passe_;
            this.mots_de_passe_conf_ = mots_de_passe_conf_;
            this.adresse_ = adresse_;
            this.plantation_ = plantation_;
            this.domaine_ = domaine_;
            this.employeur_ = employeur_;
            this.entreprise_ = entreprise_;
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
            if (!dbQueries.checkPersonne(phone_)) {

                if (default_type_ == 100) {
                    Agriculteurs agriculteurs = new Agriculteurs(0, nom_, phone_, postnom_, gender_ ? "M" : "F", mots_de_passe_, mots_de_passe_conf_,
                            adresse_, 0, 0, 0, null, 0, default_type_, 0, plantation_);

                    if (!dbQueries.checkPersonne(phone_)) {
                        dbQueries.insertAgriculteur(agriculteurs);
                    } else {
                        dbQueries.close();
                        return false;
                    }
                } else if (default_type_ == 101) {
                    Petit_commercant petit_commercant = new Petit_commercant(0, nom_, phone_, postnom_, gender_ ? "M" : "F", mots_de_passe_, mots_de_passe_conf_,
                            adresse_, 0, 0, 0, null, 0, default_type_, 0, domaine_);

                    if (!dbQueries.checkPersonne(phone_)) {
                        dbQueries.insertPetit_com(petit_commercant);
                    } else {
                        dbQueries.close();
                        return false;
                    }
                } else if (default_type_ == 102) {
                    Employer employer = new Employer(0, nom_, phone_, postnom_, gender_ ? "M" : "F", mots_de_passe_, mots_de_passe_conf_,
                            adresse_, 0, 0, 0, null, 0, default_type_, 0, employeur_);

                    if (!dbQueries.checkPersonne(phone_)) {
                        dbQueries.insertEmployer(employer);
                    } else {
                        dbQueries.close();
                        return false;
                    }
                } else if (default_type_ == 103) {
                    Entrepreneurs entrepreneurs = new Entrepreneurs(0, nom_, phone_, postnom_, gender_ ? "M" : "F", mots_de_passe_, mots_de_passe_conf_,
                            adresse_, 0, 0, 0, null, 0, default_type_, 0, entreprise_);

                    if (!dbQueries.checkPersonne(phone_)) {
                        dbQueries.insertEntrepreneur(entrepreneurs);
                    } else {
                        dbQueries.close();
                        return false;
                    }
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
                Toast.makeText(Registration.this, "ENREGISTREMENT AVEC SUCCESS", Toast.LENGTH_SHORT).show();
                finish();
                //finish();
                //shared preference
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getResources().getString(R.string.application_key),
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(getResources().getString(R.string.app_id), true);
                editor.putInt(getResources().getString(R.string.g_name), default_type_);
                editor.putString(getResources().getString(R.string.user_name), nom_ + " " + postnom_);
                editor.putString(getResources().getString(R.string.user_phone), phone_);
                editor.putString(getResources().getString(R.string.user_password), mots_de_passe_);
                editor.apply();

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                //finish();

            } else {
                Toast.makeText(Registration.this, "Erreur, ce numero de telephone existe deja", Toast.LENGTH_SHORT).show();

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

