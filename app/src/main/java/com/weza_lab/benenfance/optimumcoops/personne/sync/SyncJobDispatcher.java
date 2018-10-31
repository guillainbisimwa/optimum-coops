package com.weza_lab.benenfance.optimumcoops.personne.sync;

import android.content.Intent;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class SyncJobDispatcher extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        startService(new Intent(this, SyncPersonneToApi.class));
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
