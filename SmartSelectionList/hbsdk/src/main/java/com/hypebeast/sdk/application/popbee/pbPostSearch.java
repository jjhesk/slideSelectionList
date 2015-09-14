package com.hypebeast.sdk.application.popbee;

import com.hypebeast.sdk.api.exception.NotFoundException;
import com.hypebeast.sdk.api.model.popbees.pbpost;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hesk on 9/7/15.
 */
public class pbPostSearch implements Comparator<pbpost>, Comparable<pbpost> {

    public static pbpost findByIdBinary(long id, List<pbpost> l) throws NotFoundException {
        if (l.size() == 0) throw new NotFoundException("not found");
        pbpost o = new pbpost();
        o.id = id;
        final int h = Collections.binarySearch(l, o, new pbPostSearch());
        if (h < 0) throw new NotFoundException("not found");
        return l.get(h);
    }


    public static pbpost findById(long id, List<pbpost> list) throws NotFoundException {
        for (int i = 0; i < list.size(); i++) {
            pbpost l = list.get(i);
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
    public int compareTo(pbpost another) {
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
    public int compare(pbpost lhs, pbpost rhs) {
        return lhs.id == rhs.id ? 1 : 0;
    }
}
