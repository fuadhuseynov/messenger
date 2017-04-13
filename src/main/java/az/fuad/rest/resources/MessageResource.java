package az.fuad.rest.resources;

import az.fuad.rest.models.Message;
import az.fuad.rest.services.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages(@QueryParam("year") int year,
                                     @QueryParam("start") int start,
                                     @QueryParam("size") int size) {
        if (year > 0)
            return messageService.getAllMessagesForYear(year);
        if (start >= 0 && size > 0)
            return messageService.getAllMessagesPaginated(start, size);
        return messageService.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long messageId) {
        return messageService.getMessage(messageId);
    }

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) {
        Message addedMessage = messageService.addMessage(message);
        String addedId = String.valueOf(addedMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(addedId).build();
        return Response.created(uri)
                .entity(addedMessage)
                .build();
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
        message.setId(messageId);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") long messageId) {
        messageService.deleteMessage(messageId);
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }

}















