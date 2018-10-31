package com.weza_lab.benenfance.optimumcoops.personne.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.weza_lab.benenfance.optimumcoops.R;
import com.weza_lab.benenfance.optimumcoops.database.DBHelper;
import com.weza_lab.benenfance.optimumcoops.database.DBQueries;
import com.weza_lab.benenfance.optimumcoops.events.SyncDataEvent;
import com.weza_lab.benenfance.optimumcoops.pojo.Agriculteurs;
import com.weza_lab.benenfance.optimumcoops.pojo.Employer;
import com.weza_lab.benenfance.optimumcoops.pojo.Entrepreneurs;
import com.weza_lab.benenfance.optimumcoops.pojo.Personnes;
import com.weza_lab.benenfance.optimumcoops.pojo.Petit_commercant;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class SyncPersonneToApi extends IntentService {
    DBQueries dbQueries;
    DBHelper dbHelper;

    Personnes personnes;
    List<Personnes> items;

    public SyncPersonneToApi() {
        super("");
    }

    public SyncPersonneToApi(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        dbQueries = new DBQueries(this);
        dbHelper = new DBHelper(this);

        //TODO list all users not synced
        personnes = new Personnes();
        dbQueries.open();
        items = dbQueries.readPersonnesNotSynced();

        //TODO (optionnaly) chek if they ever exists in cloud datastore
        //TODO add them to cloud datastore via api


        //TODO synch them in local db
        String aa = "";
        String phone = "";
        int type_default = 0;
        for (Personnes i : items) {
            phone = i.getPhone_a();
            type_default = i.getDefault_type();
            if (type_default == 100) //agri
            {
                Agriculteurs a = new Agriculteurs(i.getId_a(), i.getNom_a(), i.getPhone_a(), i.getPostnom_a(), i.getGender_a(),
                        i.getMots_de_passe_a(), i.getMots_de_passe_conf_a(), i.getAdresse_a(), 1, i.getIs_validate_a(),
                        i.getIs_update_a(), i.getContactPhoto(), i.getId_group(), i.getDefault_type(), i.getIs_chef_group());
                dbQueries.updateAgriculteur(a, phone);
            }
            if (type_default == 101) //commecant
            {
                Petit_commercant p = new Petit_commercant(i.getId_a(), i.getNom_a(), i.getPhone_a(), i.getPostnom_a(), i.getGender_a(),
                        i.getMots_de_passe_a(), i.getMots_de_passe_conf_a(), i.getAdresse_a(), 1, i.getIs_validate_a(),
                        i.getIs_update_a(), i.getContactPhoto(), i.getId_group(), i.getDefault_type(), i.getIs_chef_group());
                dbQueries.updatePetit_com(p, phone);
            }
            if (type_default == 102) //emp
            {
                Employer e = new Employer(i.getId_a(), i.getNom_a(), i.getPhone_a(), i.getPostnom_a(), i.getGender_a(),
                        i.getMots_de_passe_a(), i.getMots_de_passe_conf_a(), i.getAdresse_a(), 1, i.getIs_validate_a(),
                        i.getIs_update_a(), i.getContactPhoto(), i.getId_group(), i.getDefault_type(), i.getIs_chef_group());
                dbQueries.updateEmployer(e, phone);
            }
            if (type_default == 103) //entre
            {
                Entrepreneurs en = new Entrepreneurs(i.getId_a(), i.getNom_a(), i.getPhone_a(), i.getPostnom_a(), i.getGender_a(),
                        i.getMots_de_passe_a(), i.getMots_de_passe_conf_a(), i.getAdresse_a(), 1, i.getIs_validate_a(),
                        i.getIs_update_a(), i.getContactPhoto(), i.getId_group(), i.getDefault_type(), i.getIs_chef_group());
                dbQueries.updateEntrepreneur(en, phone);
            }


            //update is sync only

        }

        dbQueries.close();
        EventBus.getDefault().post(new SyncDataEvent(getResources().getString(R.string.synchronisation_success), true));
        //EventBus.getDefault().post(new SyncDataEvent(aa, true));
    }
}
