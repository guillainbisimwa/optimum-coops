package com.weza_lab.benenfance.optimumcoops.credit.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.weza_lab.benenfance.optimumcoops.R;
import com.weza_lab.benenfance.optimumcoops.database.DBHelper;
import com.weza_lab.benenfance.optimumcoops.database.DBQueries;
import com.weza_lab.benenfance.optimumcoops.pojo.Credit;

public class CreditDialogFragment extends DialogFragment implements DialogInterface.OnClickListener,
        View.OnClickListener {

    // UI references.
    private CreditRegisterTask mAuthTask = null;
    private View mProgressView;
    private Button inscription_button;
    private ScrollView login_form;

    private AutoCompleteTextView somme_cr;
    private AutoCompleteTextView echeance_cr, motif_cr;

    private Spinner spinner_coop_cr;

    private DBQueries dbQueries;
    private DBHelper dbHelper;

    private String mPhone, uName;
    private int user_categorie;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Categorize all users
        SharedPreferences prefs = getContext().getSharedPreferences(getResources().getString(R.string.application_key),
                Context.MODE_PRIVATE);
        user_categorie = prefs.getInt(getResources().getString(R.string.g_name), 0);
        mPhone = prefs.getString(getResources().getString(R.string.user_phone), "");
        uName = prefs.getString(getResources().getString(R.string.user_name), "");

        dbQueries = new DBQueries(getContext());
        dbHelper = new DBHelper(getContext());
        // Get the layout inflater
        View viewContainer = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_credit, null);

        mProgressView = viewContainer.findViewById(R.id.login_progress_insc);
        login_form = viewContainer.findViewById(R.id.login_form);

        inscription_button = viewContainer.findViewById(R.id.inscription_button_insc);
        inscription_button.setOnClickListener(this);

        spinner_coop_cr = viewContainer.findViewById(R.id.spinner_coop_cr);
        somme_cr = viewContainer.findViewById(R.id.somme_cr);
        echeance_cr = viewContainer.findViewById(R.id.echeance_cr);
        motif_cr = viewContainer.findViewById(R.id.motif_cr);

        //spinner traitement
        ArrayAdapter<CharSequence> adapter_spiner = ArrayAdapter.createFromResource(getContext(),
                R.array.coop_array, R.layout.optimum_simple_spinner_item);

        adapter_spiner.setDropDownViewResource(R.layout.optimum_spiner_dropdown);
        spinner_coop_cr.setAdapter(adapter_spiner);


        builder.setView(viewContainer)
                .setNegativeButton("Annuler", this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.inscription_button_insc) {
            attemptAddCredit();
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

    private void attemptAddCredit() {
        if (mAuthTask != null) {
            return;
        }
        //Enlever les message d'Erreur s'il y en a
        somme_cr.setError(null);
        echeance_cr.setError(null);
        motif_cr.setError(null);

        float somme_cr_ = Float.parseFloat(somme_cr.getText().toString().trim());
        int echeance_cr_ = Integer.parseInt(echeance_cr.getText().toString().trim());
        String motif_cr_ = motif_cr.getText().toString().trim();


        boolean cancel = false;
        View focusView = null;

        if (!isValid(somme_cr_)) {
            somme_cr.setError(getString(R.string.error_invalid_numbr));
            focusView = somme_cr;
            cancel = true;
        }
        if (!isValid(echeance_cr_)) {
            echeance_cr.setError(getString(R.string.error_invalid_numbr));
            focusView = echeance_cr;
            cancel = true;
        }
        if (!TextUtils.isEmpty(motif_cr_) && !isNameValid(motif_cr_)) {
            motif_cr.setError(getString(R.string.error_invalid_length));
            focusView = motif_cr;
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
            mAuthTask = new CreditRegisterTask(somme_cr_, echeance_cr_, motif_cr_, mPhone, user_categorie);
            mAuthTask.execute((Void) null);
        }

    }

    private boolean isValid(int nbr) {
        return nbr > 0;
    }

    private boolean isValid(float nbr) {
        return nbr > 0;
    }

    private boolean isNameValid(String password) {
        return password.length() > 3;
    }

    public class CreditRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private float somme_;
        private int echeance_;
        private String motif_;
        private String mPhone_;
        private int mType_;

        public CreditRegisterTask(float somme_, int echeance_, String motif_, String mPhone_, int mType_) {
            this.somme_ = somme_;
            this.echeance_ = echeance_;
            this.motif_ = motif_;
            this.mPhone_ = mPhone_;
            this.mType_ = mType_;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt creating against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }
            dbQueries.open();
            Credit c = new Credit(0, 0, null, null, 0, somme_, mPhone_, mType_, 0, 2, motif_, echeance_);

            if (dbQueries.insertCredit(c)) {
                dbQueries.close();
                return true;
            } else {
                dbQueries.close();
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                Toast.makeText(getContext(), "ENREGISTREMENT AVEC SUCCESS", Toast.LENGTH_SHORT).show();
                dismiss();

            } else {
                Toast.makeText(getContext(), "Erreur ... ", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }

}
