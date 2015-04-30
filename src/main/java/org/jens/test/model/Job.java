package org.jens.test.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Jens Ritter on 29.04.15.
 */
public class Job {

    String name;
    int kredi;

    LocalDateTime created = LocalDateTime.now();
    LocalDateTime finished = null;
    LocalDateTime started = null;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Job{");
        sb.append("id='").append(getId()).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", kredi=").append(kredi);
        sb.append(", created=").append(created);
        sb.append(", finished=").append(finished);
        sb.append(", started=").append(started);
        sb.append('}');
        return sb.toString();
    }

    public String getId() {
        StringBuilder out = new StringBuilder();
        ZonedDateTime zdt = ZonedDateTime.ofLocal(created, ZoneId.systemDefault(), null);
        Instant instant = zdt.toInstant();
        long seconds = instant.getEpochSecond();
        int nano = instant.getNano();
        out.append(name).append("-").append(kredi).append(seconds).append(".").append(nano);

        return out.toString();
    }

    public boolean isRunning() {
        return started != null;
    }

    public boolean isFinished() {
        return finished != null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKredi() {
        return kredi;
    }

    public void setKredi(int kredi) {
        this.kredi = kredi;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getFinished() {
        return finished;
    }

    public void setFinished(LocalDateTime finished) {
        this.finished = finished;
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public void setStarted(LocalDateTime started) {
        this.started = started;
    }
}
