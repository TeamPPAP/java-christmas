package christmas.service;

import christmas.repository.EventPlanRepository;

public class DateService {
    private final EventPlanRepository eventPlanRepo;

    public DateService(EventPlanRepository eventPlanRepo) {
        this.eventPlanRepo = eventPlanRepo;
    }
}
