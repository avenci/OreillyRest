package com.tutorialspoint;

/**
 * Created by avenci on 2/24/18.
 */

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
@Path("/UserService")

public class UserService {
    UserDao userDao = new UserDao();
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_XML)
    public List<User> getUsers(){
        return userDao.getAllUsers();
    }

    @GET
    @Path("/user")
    public void addUsers(@QueryParam("name") String name, @QueryParam("profession") String profession) {
        userDao.addUser(name, profession);
    }
}
