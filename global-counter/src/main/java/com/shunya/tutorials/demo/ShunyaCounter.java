package com.shunya.tutorials.demo;

import javax.persistence.*;

@Entity
@Table(name = "shunya_counter")
public class ShunyaCounter {
    @Id
    @Enumerated(EnumType.STRING)
    private CounterType counterType;
    private long value;

    @Version
    private int version;

    public CounterType getCounterType() {
        return counterType;
    }

    public void setCounterType(CounterType counterType) {
        this.counterType = counterType;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}