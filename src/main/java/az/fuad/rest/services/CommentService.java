package az.fuad.rest.services;

import az.fuad.rest.database.DatabaseClass;
import az.fuad.rest.exceptions.DataNotFoundException;
import az.fuad.rest.models.Comment;
import az.fuad.rest.models.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Comment> getAllComments(long messageId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return new ArrayList<>(comments.values());
    }

    public Comment getComment(long messageId, long commentId) {
        Message message = messages.get(messageId);
        //Check if message exists
        if (message == null)
            throw new DataNotFoundException("Message with id " + messageId + " does not exist.");
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        Comment comment = comments.get(commentId);
        //Check if comment exists
        if (comment == null)
            throw new DataNotFoundException("Comment with id " + commentId + " does not exist.");
        return comment;
    }

    public Comment addComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comment.setId(comments.size() + 1);
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment updateComment(long messageId, Comment comment) {
        Message message = messages.get(messageId);
        //Check if message exists
        if (message == null)
            throw new DataNotFoundException("Message with id " + messageId + " does not exist.");
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        if (comment.getId() <= 0)
            return null;
        //Check if comment exists
        if (comments.get(comment.getId()) == null)
            throw new DataNotFoundException("Comment with id " + comment.getId() + " does not exist.");
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment deleteComment(long messageId, long commentId) {
        Message message = messages.get(messageId);
        //Check if message exists
        if (message == null)
            throw new DataNotFoundException("Message with id " + messageId + " does not exist.");
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        //Check if comment exists
        if (comments.get(commentId) == null)
            throw new DataNotFoundException("Comment with id " + commentId + " does not exist.");
        return comments.remove(commentId);
    }

}












