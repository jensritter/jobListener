package org.jens.test.model;

/**
 * @author Jens Ritter on 02.05.2015.
 */
public class Message {
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    String value;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
