/*
 * All content copyright (c) 2003-2008 Terracotta, Inc., except as may otherwise be noted in a separate copyright
 * notice. All rights reserved.
 */
package com.tc.stats.api;

import com.tc.management.TerracottaMBean;
import com.tc.management.beans.TerracottaOperatorEventsMBean;
import com.tc.management.beans.l1.L1InfoMBean;
import com.tc.net.protocol.tcm.ChannelID;

import javax.management.ObjectName;

public interface ClientMBean extends TerracottaMBean {
  public static final String TUNNELED_BEANS_REGISTERED = "tunneled.beans.registered";

  long getClientID();

  String getNodeID();

  boolean isTunneledBeansRegistered();

  ObjectName getL1InfoBeanName();

  L1InfoMBean getL1InfoBean();

  ObjectName getL1DumperBeanName();

  ObjectName getL1OperatorEventsBeanName();

  ObjectName getEnterpriseTCClientBeanName();

  TerracottaOperatorEventsMBean getL1OperatorEventsBean();

  ChannelID getChannelID();

  String getRemoteAddress();

  long getTransactionRate();

  long getReadRate();

  long getWriteRate();

  long getPendingTransactionsCount();

  Number[] getStatistics(String[] names);

  int getLiveObjectCount();

  void killClient();
}