package christmas.service.dto;

import java.util.List;
import java.util.Optional;

public record BenefitResult(List<Event> benefitList,
                            int totalDiscountAmount,
                            Optional<Gift> gift,
                            int totalBenefitAmount,
                            int finalBillsAmount) {
}
