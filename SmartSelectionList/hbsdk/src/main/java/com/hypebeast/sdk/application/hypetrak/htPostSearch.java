package com.hypebeast.sdk.application.hypetrak;

import com.hypebeast.sdk.api.exception.NotFoundException;
import com.hypebeast.sdk.api.model.hypetrak.htpost;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hesk on 8/9/15.
 */
public class htPostSearch implements Comparator<htpost>, Comparable<htpost> {

    public static htpost findByIdBinary(long id, List<htpost> l) throws NotFoundException {
        if (l.size() == 0) throw new NotFoundException("not found");
        htpost o = new htpost();
        o.id = id;
        final int h = Collections.binarySearch(l, o, new htPostSearch());
        if (h < 0) throw new NotFoundException("not found");
        return l.get(h);
    }


    public static htpost findById(long id, List<htpost> list) throws NotFoundException {
        for (int i = 0; i < list.size(); i++) {
            htpost l = list.get(i);
            if (l.id == id) {
                return list.get(i);
            }
        }
        throw new NotFoundException("not found");
    }

    /**
     * the comparision for the another input of the pbpost
     *
     * @param another the another
     * @return the integer return
     */
    @Override
    public int compareTo(htpost another) {
        return 0;
    }

    /**
     * the comparison for input A and input B
     *
     * @param lhs left side
     * @param rhs right side
     * @return the integer for the return
     */
    @Override
    public int compare(htpost lhs, htpost rhs) {
        return lhs.id == rhs.id ? 1 : 0;
    }
}
