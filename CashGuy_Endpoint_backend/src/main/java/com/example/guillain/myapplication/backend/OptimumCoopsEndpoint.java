package com.example.guillain.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.ConflictException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

import data.GroupsEp;
import data.PersonnesEp;

import static com.example.guillain.myapplication.backend.OfyHelper.ofy;

@Api(name = "optimumCoopsApi", version = "v1", namespace = @ApiNamespace(
        ownerDomain = "backend.myapplication.guillain.example.com",
        ownerName = "backend.myapplication.guillain.example.com",
        packagePath = "")
)
public class OptimumCoopsEndpoint {
    // Make sure to add this endpoint to your web.xml file if this is a web application.

    private static final Logger LOG = Logger.getLogger(OptimumCoopsEndpoint.class.getName());

    public OptimumCoopsEndpoint() {
    }

    //TODO enregister personnes
    @ApiMethod(name = "enregistrerPersonnes")
    public PersonnesEp enregistrerPersonnes(
            @Named("id_a") int id_a,
            @Named("nom_a") String nom_a,
            @Named("phone_a") String phone_a,
            @Named("postnom_a") String postnom_a,
            @Named("gender_a") String gender_a,
            @Named("mots_de_passe_a") String mots_de_passe_a,
            @Named("mots_de_passe_conf_a") String mots_de_passe_conf_a,
            @Named("adresse_a") String adresse_a,
            @Named("is_sync_a") int is_sync_a,
            @Named("is_validate_a") int is_validate_a,
            @Named("is_update_a") int is_update_a,
            //@Named("contactPhoto") byte[] contactPhoto,
            @Named("id_group") int id_group,
            @Named("default_type") int default_type,
            @Named("is_chef_group") int is_chef_group,
            @Named("name_group") String name_group,
            @Named("_info_caracteristique") String _info_caracteristique

    ) throws ConflictException {
        //If if is not null, then check if it exists. If yes, throw an Exception
        //that it is already present
        PersonnesEp personnesEp = new PersonnesEp(
                id_a,
                nom_a,
                phone_a,
                postnom_a,
                gender_a,
                mots_de_passe_a,
                mots_de_passe_conf_a,
                adresse_a,
                is_sync_a,
                is_validate_a,
                is_update_a,
                //contactPhoto,
                id_group,
                default_type,
                is_chef_group,
                name_group,
                _info_caracteristique);
        if (personnesEp.getId() != null) {
            if (findRecord_personnes(personnesEp.getId()) != null) {
                throw new ConflictException("Object already exists");
            }
        }
        //Since our @Id field is a Long, Objectify will generate a unique value for us
        //when we use put
        ofy().save().entity(personnesEp).now();
        return personnesEp;
    }

    @ApiMethod(name = "enregistrerPersonnesClass")
    public PersonnesEp enregistrerPersonnesClass(PersonnesEp personnesEp) throws ConflictException {
        //If if is not null, then check if it exists. If yes, throw an Exception
        //that it is already present
        if (personnesEp.getId() != null) {
            if (findRecord_personnes(personnesEp.getId()) != null) {
                throw new ConflictException("Object already exists");
            }
        }
        //Since our @Id field is a Long, Objectify will generate a unique value for us
        //when we use put
        ofy().save().entity(personnesEp).now();
        return personnesEp;
    }


    //TODO liste personnes all
    @ApiMethod(name = "listPersonnes")
    public CollectionResponse<PersonnesEp> listPersonnes(@Nullable @Named("cursor") String cursorString,
                                                         @Nullable @Named("count") Integer count) {

        Query<PersonnesEp> query = ofy().load().type(PersonnesEp.class);
        if (count != null) query.limit(count);
        if (cursorString != null && cursorString != "") {
            query = query.startAt(Cursor.fromWebSafeString(cursorString));
        }

        List<PersonnesEp> records = new ArrayList<PersonnesEp>();
        QueryResultIterator<PersonnesEp> iterator = query.iterator();
        int num = 0;
        while (iterator.hasNext()) {
            records.add(iterator.next());
            if (count != null) {
                num++;
                if (num == count) break;
            }
        }

        //Find the next cursor
        if (cursorString != null && cursorString != "") {
            Cursor cursor = iterator.getCursor();
            if (cursor != null) {
                cursorString = cursor.toWebSafeString();
            }
        }
        return CollectionResponse.<PersonnesEp>builder().setItems(records).setNextPageToken(cursorString).build();
    }


    //TODO liste personnes tri

    //TODO enregistrer groupes
    @ApiMethod(name = "enregistrerGroupes")
    public GroupsEp enregistrerGroupes(
            @Named("id_group") int id_group,
            @Named("name_group") String name_group,
            @Named("adress_group") String adress_group,
            //@Named("photo_profile_group") byte[] photo_profile_group,
            @Named("type_group") int type_group

    ) throws ConflictException {
        //If if is not null, then check if it exists. If yes, throw an Exception
        //that it is already present
        GroupsEp groupsEp = new GroupsEp(id_group,
                name_group,
                adress_group,
                //photo_profile_group,
                type_group
        );
        if (groupsEp.getId() != null) {
            if (findRecord_groupes(groupsEp.getId()) != null) {
                throw new ConflictException("Object already exists");
            }
        }
        //Since our @Id field is a Long, Objectify will generate a unique value for us
        //when we use put
        ofy().save().entity(groupsEp).now();
        return groupsEp;
    }

    @ApiMethod(name = "enregistrerGroupesClass")
    public GroupsEp enregistrerGroupesClass(GroupsEp groupsEp) throws ConflictException {
        //If if is not null, then check if it exists. If yes, throw an Exception
        //that it is already present
        if (groupsEp.getId() != null) {
            if (findRecord_groupes(groupsEp.getId()) != null) {
                throw new ConflictException("Object already exists");
            }
        }
        //Since our @Id field is a Long, Objectify will generate a unique value for us
        //when we use put
        ofy().save().entity(groupsEp).now();
        return groupsEp;
    }

    //TODO liste Groupe all
    @ApiMethod(name = "listGroupes")
    public CollectionResponse<GroupsEp> listGroupes(@Nullable @Named("cursor") String cursorString,
                                                    @Nullable @Named("count") Integer count) {

        Query<GroupsEp> query = ofy().load().type(GroupsEp.class);
        if (count != null) query.limit(count);
        if (cursorString != null && cursorString != "") {
            query = query.startAt(Cursor.fromWebSafeString(cursorString));
        }

        List<GroupsEp> records = new ArrayList<GroupsEp>();
        QueryResultIterator<GroupsEp> iterator = query.iterator();
        int num = 0;
        while (iterator.hasNext()) {
            records.add(iterator.next());
            if (count != null) {
                num++;
                if (num == count) break;
            }
        }

        //Find the next cursor
        if (cursorString != null && cursorString != "") {
            Cursor cursor = iterator.getCursor();
            if (cursor != null) {
                cursorString = cursor.toWebSafeString();
            }
        }
        return CollectionResponse.<GroupsEp>builder().setItems(records).setNextPageToken(cursorString).build();
    }
    //TODO liste Groupe tri


    private PersonnesEp findRecord_personnes(Long id) {
        return ofy().load().type(PersonnesEp.class).id(id).now();
    }

    private GroupsEp findRecord_groupes(Long id) {
        return ofy().load().type(GroupsEp.class).id(id).now();
    }

}

