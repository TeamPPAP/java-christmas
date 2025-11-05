package christmas.controller;

import christmas.controller.state.DateInputState;
import christmas.controller.state.StateContext;
import christmas.repository.EventPlanRepository;
import christmas.repository.MenuRepository;
import christmas.service.BenefitCalculator;
import christmas.service.EventPolicy;
import christmas.service.GiftPolicy;
import christmas.service.MenuService;
import christmas.service.impl.GiftEventPolicy;
import christmas.service.impl.SpecialDayPolicy;
import christmas.service.impl.WeekEventPolicy;
import christmas.service.impl.XmasDdayPolicy;
import christmas.util.validator.DateValidator;
import christmas.util.validator.IntegerValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.UIObserver;

import java.util.List;

public class RestaurantController {
    private final InputView inputView;
    private final OutputView outputView;
    private final IntegerValidator integerValidator;
    private final DateValidator dateValidator;
    private final MenuRepository menuRepository;
    private final BenefitCalculator calculator;

    public RestaurantController(InputView inputView,
                                OutputView outputView,
                                IntegerValidator integerValidator,
                                DateValidator dateValidator,
                                MenuRepository menuRepository,
                                BenefitCalculator calculator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.integerValidator = integerValidator;
        this.dateValidator = dateValidator;
        this.menuRepository = menuRepository;
        this.calculator = calculator;
    }

    public static RestaurantController createDefault() {
        InputView input = new InputView();
        OutputView output = new OutputView();
        IntegerValidator intVal = new IntegerValidator();
        DateValidator dateVal = new DateValidator(intVal);
        MenuRepository menuRepo = new MenuRepository();

        EventPolicy dday = new XmasDdayPolicy();
        EventPolicy weekdays = new WeekEventPolicy();
        EventPolicy special = new SpecialDayPolicy(new EventPlanRepository());
        GiftPolicy gift = new GiftEventPolicy(new MenuService(new MenuRepository()));
        BenefitCalculator calc = new BenefitCalculator(List.of(dday, weekdays, special), gift);

        return new RestaurantController(input, output, intVal, dateVal, menuRepo, calc);
    }

    public void run() {
        StateContext context = new StateContext(inputView, dateValidator, calculator,menuRepository);


        UIObserver uiObserver = new UIObserver(outputView);
        context.addObserver(uiObserver);

        context.push(new DateInputState());
        context.run();
    }

}
