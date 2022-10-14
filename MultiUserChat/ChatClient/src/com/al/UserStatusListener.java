package com.al;

public interface UserStatusListener {
    public void online(String login);
    public void offline(String login);
}
