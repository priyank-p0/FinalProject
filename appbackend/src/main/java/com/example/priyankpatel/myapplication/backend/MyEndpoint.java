/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.priyankpatel.myapplication.backend;

import com.example.joke.Generatejoke;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.myapplication.priyankpatel.example.com",
    ownerName = "backend.myapplication.priyankpatel.example.com",
    packagePath=""
  )
)
public class MyEndpoint {
    Generatejoke generatejoke=new Generatejoke();

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi() {
        MyBean response = new MyBean();
        //Joker joker = new Joker();

        response.setData(generatejoke.getJoke());
        return response;
    }

}
