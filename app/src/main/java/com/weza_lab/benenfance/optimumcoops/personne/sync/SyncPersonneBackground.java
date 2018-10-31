package com.weza_lab.benenfance.optimumcoops.personne.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.weza_lab.benenfance.optimumcoops.R;
import com.weza_lab.benenfance.optimumcoops.events.SaveDataEvent;
import com.weza_lab.benenfance.optimumcoops.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.UUID;

public class SyncPersonneBackground extends IntentService {
    private String mPhone;

    public SyncPersonneBackground() {
        super("");
    }

    public SyncPersonneBackground(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // intent get phone number
        if (intent.hasExtra("mPhone")) {
            mPhone = intent.getStringExtra("mPhone");
        } else mPhone = "";

        //check if internet is available
        //sync if network available
        if (Utils.isNetworkAvailable(getApplicationContext())) {
            EventBus.getDefault().post(new SaveDataEvent(getResources().getString(R.string.enregistrement_success), true));
            //start job service
            Intent intentSync = new Intent(this, SyncPersonneToApi.class);

            intent.putExtra("mPhone", mPhone);
            startService(intentSync);
        } else {
            //schedule a job if not network is available
            FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(getApplicationContext()));

            Job job = dispatcher.newJobBuilder()
                    .setService(SyncJobDispatcher.class)
                    .setTag(UUID.randomUUID().toString())
                    .setConstraints(Constraint.ON_ANY_NETWORK)
                    .build();
            dispatcher.mustSchedule(job);
            EventBus.getDefault().post(new SaveDataEvent(getResources().getString(R.string.enregistrement_apres), true));

        }


    }
}
