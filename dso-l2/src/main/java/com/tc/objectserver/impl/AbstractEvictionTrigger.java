/*
 * All content copyright Terracotta, Inc., unless otherwise indicated. All rights reserved.
 */
package com.tc.objectserver.impl;

import com.tc.object.ObjectID;
import com.tc.objectserver.api.EvictableMap;
import com.tc.objectserver.api.EvictionTrigger;
import java.util.Collections;
import java.util.Map;

/**
 *
 * @author mscott
 */
public abstract class AbstractEvictionTrigger implements EvictionTrigger {
    
    private final ObjectID oid;
    private boolean started = false;
    private boolean  evicting = false;
    boolean processed = false;
    private String name;
    private boolean pinned;
    

    public AbstractEvictionTrigger(ObjectID oid) {
        this.oid = oid;
    }

    @Override
    public ObjectID getId() {
        return oid;
    }
    
    public int boundsCheckSampleSize(int sampled) {
        if ( sampled < 0 ) {
            sampled = 0;
        }
        if ( sampled > 100000 ) {
            sampled = 100000;
        }
        return sampled;
    }
    
    @Override
    public boolean startEviction(EvictableMap map) {
        started = true;
        name = map.getCacheName();
        pinned = map.getMaxTotalCount() == 0;
        if ( !map.isEvicting() ) {
            return map.startEviction();
        } else {
            return false;
        }
    }
    
    @Override
    public void completeEviction(EvictableMap map) {
        if ( !started ) {
            throw new AssertionError("sample not started");
        }
        if ( !processed ) {
            throw new AssertionError("sample not processed");
        }
        if ( !evicting ) {
            map.evictionCompleted();
        }
    }    
    
    protected Map<Object, ObjectID> processSample(Map<Object, ObjectID> sample) {
        evicting = !sample.isEmpty();
        processed = true;
        return sample;
    }

    @Override
    public String toString() {
        return "AbstractEvictionTrigger{" 
                + "name=" + name + " - " + getId() + (( pinned ) ? " - PINNED" : "")
                + ", started=" + started
                + ", processed=" + processed
                + ", evicting=" + evicting + '}';
    }
}
