package com.hypebeast.sdk.application.hypebeast;

import com.hypebeast.sdk.api.model.hbeditorial.Foundation;

/**
 * Created by hesk on 1/9/15.
 */
public interface sync {
    void syncDone(ConfigurationSync me, Foundation data);
}
