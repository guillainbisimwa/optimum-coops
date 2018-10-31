package com.weza_lab.benenfance.optimumcoops.cloudEndpoint;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.guillain.myapplication.backend.optimumCoopsApi.OptimumCoopsApi;

//import com.example.guillain.myapplication.backend.optimumCoopsApi.OptimumCoopsApi;


public class UserAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static OptimumCoopsApi optimumCoopsApi = null;
    //private static MyApi myApiService = null;
    private Context context;
    //private Achat.AsyncTaskResponse asyncCallback;
    private ProgressDialog pDialog;


    public UserAsyncTask() {
    }

    @Override
    protected String doInBackground(Pair<Context, String>... pairs) {
       /* if (optimumCoopsApi == null)
        {
            OptimumCoopsApi.Builder builder = new OptimumCoopsApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer(){
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            optimumCoopsApi = builder.build();
        }
        context = pairs[0].first;
        String myparam = pairs[0].second;
        try {
            //asyncCallback.asyncTaskWait();
            //TextView txt = (TextView)findView(R.id.txtachat);;
            //txt.getText(" ");
            //return myApiService.connectToKcbLoginPassApi(login,password,compteur).execute().getData();

            return optimumCoopsApi.listPersonnes().execute().getData();

            //myApiService.listAbonnees().execute().toString();
            // return  "succes";
            //return myApiService.
        } catch (IOException e) {
            return e.getMessage();
        }*/
        return null;
    }
}
