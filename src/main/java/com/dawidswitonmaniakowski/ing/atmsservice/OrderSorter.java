package com.dawidswitonmaniakowski.ing.atmsservice;

import java.util.List;

interface OrderSorter {

  List<ServiceTask> buildPlan(List<ServiceTask> serviceTasks);
}
