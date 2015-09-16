package com.hkm.slideselection.worker;

/**
 * Created by hesk on 15/9/15.
 */
public class MessageEvent {
    private int position;

    public MessageEvent(int position_count) {
        this.position = position_count;
    }

    public int At() {
        return position;
    }
}
