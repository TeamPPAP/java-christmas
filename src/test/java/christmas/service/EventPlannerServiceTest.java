package christmas.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventPlannerServiceTest {

    @Test
    void getMenuList() {
        EventPlannerService eventPlannerService = new EventPlannerService();
        eventPlannerService.getMenuList();
    }
}