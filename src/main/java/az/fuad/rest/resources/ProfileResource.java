package az.fuad.rest.resources;

import az.fuad.rest.models.Profile;
import az.fuad.rest.services.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

    ProfileService profileService = new ProfileService();

    @GET
    public List<Profile> getMessages() {
        return profileService.getAllProfiles();
    }

    @GET
    @Path("/{profileName}")
    public Profile getProfile(@PathParam("profileName") String profileName) {
        return profileService.getProfile(profileName);
    }

    @POST
    public Response addProfile(Profile profile, @Context UriInfo uriInfo) {
        Profile addedProfile = profileService.addProfile(profile);
        String addedId = String.valueOf(addedProfile.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(addedId).build();
        return Response.created(uri)
                .entity(addedProfile)
                .build();
    }

    @PUT
    @Path("/{profileName}")
    public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
        profile.setProfileName(profileName);
        return profileService.updateProfile(profile);
    }

    @DELETE
    @Path("/{profileName}")
    public void deleteProfile(@PathParam("profileName") String profileName) {
        profileService.deleteProfile(profileName);
    }

}








