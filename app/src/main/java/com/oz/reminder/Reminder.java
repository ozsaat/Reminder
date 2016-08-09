package com.oz.reminder;

import java.io.Serializable;

public class Reminder implements Serializable {

    private final String title;
    private final String message;

    Reminder(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Reminder reminder = (Reminder) o;

        if (!title.equals(reminder.title)) {
            return false;
        }
        return message.equals(reminder.message);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Reminder{" +
               "title='" + title + '\'' +
               ", message='" + message + '\'' +
               '}';
    }
}
