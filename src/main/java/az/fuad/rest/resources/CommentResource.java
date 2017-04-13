package az.fuad.rest.resources;

import az.fuad.rest.models.Comment;
import az.fuad.rest.services.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

//The path is: "/messages/{messageId}/comments"
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

    private CommentService commentService = new CommentService();

    @GET
    public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
        return commentService.getAllComments(messageId);
    }

    @GET
    @Path("/{commentId}")
    public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        return commentService.getComment(messageId, commentId);
    }

    @POST
    public Response addComment(@PathParam("messageId") long messageId, Comment comment, @Context UriInfo uriInfo) {
        Comment addedComment = commentService.addComment(messageId, comment);
        String addedId = String.valueOf(addedComment.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(addedId).build();
        return Response.created(uri)
                .entity(addedComment)
                .build();
    }

    @PUT
    @Path("/{commentId}")
    public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId, Comment comment) {
        comment.setId(commentId);
        return commentService.updateComment(messageId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public void deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        commentService.deleteComment(messageId, commentId);
    }

}











