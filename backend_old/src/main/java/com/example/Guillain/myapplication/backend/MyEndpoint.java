/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.Guillain.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.ConflictException;
import com.googlecode.objectify.ObjectifyService;

import javax.inject.Named;

//import static com.example.Guillain.myapplication.backend.OfyHelper.ofy;
import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Guillain.example.com",
                ownerName = "backend.myapplication.Guillain.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }


    @ApiMethod(name = "enregistrerAbonnees")
    public Abonnees enregistrerAbonnees(@Named("login") String login, @Named("compteur") String compteur,
                                        @Named("identite") String identite, @Named("sommes") String sommes) throws ConflictException {
        //If if is not null, then check if it exists. If yes, throw an Exception
        //that it is already present
        Abonnees abonne = new Abonnees(login, "", "", identite, sommes);
        if (abonne.getId_a() != null) {
            if (findRecord(abonne.getId_a()) != null) {
                throw new ConflictException("Object already exists");
            }
        }
        //Since our @Id field is a Long, Objectify will generate a unique value for us
        //when we use put
        //bjectifyService.init();
        ObjectifyService.ofy().save().entity(abonne).now();
        return abonne;
    }

    private Abonnees findRecord(Long id) {
        return ObjectifyService.ofy().load().type(Abonnees.class).id(id).now();
        //or return ofy().load().type(Historique.class).filter("id",id).first.now();
    }
}
