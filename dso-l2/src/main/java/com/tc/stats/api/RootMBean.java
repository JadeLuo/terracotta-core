/*
 * All content copyright (c) 2003-2008 Terracotta, Inc., except as may otherwise be noted in a separate copyright
 * notice. All rights reserved.
 */
package com.tc.stats.api;

import com.tc.object.ObjectID;

public interface RootMBean {

  public String getRootName();

  public ObjectID getObjectID();

}