/**
 * Copyright 2014-2015 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//[START all]
package com.example.Guillain.myapplication.backend;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextEvent;


/**
 * OfyHelper, a ServletContextListener, is setup in web.xml to run before a JSP is run.  This is
 * required to let JSP's access Ofy.
 **/
public class OfyHelper {

    /**
     * Initialize a ServletContextListener.
     * @param event .
     */
    public void contextInitialized(ServletContextEvent event) {
        // This will be invoked as part of a warmup request, or the first user request if no warmup
        // request.
        ObjectifyService.register(Abonnees.class);
        //ObjectifyService.register(Greeting.class);
    }

    public void contextDestroyed(ServletContextEvent event) {
        // App Engine does not currently invoke this method.
    }
/*
    static {
        try {
            //helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));
            //helper.setUp();
            //ObjectifyService.register(MyClass.class);
            //DatastoreServiceFactory.getDatastoreService();
            //ObjectifyService.init();

            ObjectifyService.register(Abonnees.class);
            //DatastoreServiceFactory.getDatastoreService();

        } catch (Exception e) {
            System.out.println("Erreur" + e.getMessage());
        }
        //ObjectifyService.register(Historique.class);
        //ObjectifyService.register(List_Compteur.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }*/
}
//[END all]
