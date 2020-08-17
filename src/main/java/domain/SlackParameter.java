package domain;

import java.util.ArrayList;

public class SlackParameter {
    private String channel;
    private String username;
    private String text;
    private ArrayList<SlackAttachment> attachments;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<SlackAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<SlackAttachment> attachments) {
        this.attachments = attachments;
    }
}
