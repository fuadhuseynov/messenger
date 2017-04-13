package az.fuad.rest.services;

import az.fuad.rest.database.DatabaseClass;
import az.fuad.rest.exceptions.DataNotFoundException;
import az.fuad.rest.models.Message;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class MessageService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService() {
        messages.put(1L, new Message(1, "Message 1", "fuad"));
        messages.put(2L, new Message(2, "Message 2", "andrey"));
    }

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public List<Message> getAllMessagesForYear(int year) {
        List<Message> messagesForYear = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (Message message : messages.values()) {
            calendar.setTime(message.getCreated());
            if (calendar.get(Calendar.YEAR) == year)
                messagesForYear.add(message);
        }
        if (messagesForYear.isEmpty())
            throw new DataNotFoundException("No messages found for year " + year);
        return messagesForYear;
    }

    public List<Message> getAllMessagesPaginated(int start, int size) {
        ArrayList<Message> list = new ArrayList<Message>(messages.values());
        if (start + size > list.size())
            return new ArrayList<Message>();
        if (list.subList(start, start + size).isEmpty())
            throw new DataNotFoundException("No messages found.");
        return list.subList(start, start + size);
    }

    public Message getMessage(long id) {
        Message message = messages.get(id);
        if (message == null)
            throw new DataNotFoundException("Message with id " + id + " not found!");
        return message;
    }

    public Message addMessage(Message message) {
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message) {
        if (message.getId() <= 0)
            return null;
        if (messages.get(message.getId()) == null)
            throw new DataNotFoundException("Message with id " + message.getId() + " does not exist.");
        messages.put(message.getId(), message);
        return message;
    }

    public Message deleteMessage(long id) {
        if (messages.get(id) == null)
            throw new DataNotFoundException("Message with id " + id + " does not exist.");
        return messages.remove(id);
    }

}








