package atmsservice;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class OrderPlannerImpl implements OrderPlanner {

    @Override
    public List<ServiceTask> plan(List<ServiceTask> serviceTasks) {
        return new Sorter().plan(serviceTasks);
    }
}
