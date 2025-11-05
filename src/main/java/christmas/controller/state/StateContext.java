package christmas.controller.state;

import christmas.controller.state.session.OrderSession;
import christmas.domain.model.Menu;
import christmas.domain.model.Order;
import christmas.domain.model.Orders;
import christmas.repository.MenuRepository;
import christmas.service.BenefitCalculator;
import christmas.service.dto.BenefitResult;
import christmas.util.input.InputFactory;
import christmas.util.input.InputReader;
import christmas.util.validator.DateValidator;
import christmas.util.validator.IntegerValidator;
import christmas.view.InputView;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class StateContext {
    private final Deque<State> stateStack = new ArrayDeque<>();
    private final List<StateObserver> observers = new ArrayList<>();

    private final OrderSession session = new OrderSession();

    private final InputReader<Integer> integerReader;
    private final InputReader<String> stringReader;

    private final DateValidator dateValidator;
    private final BenefitCalculator benefitCalculator;
    private final MenuRepository menuRepository;

    public StateContext(InputView input, DateValidator dateValidator, BenefitCalculator benefitCalculator, MenuRepository menuRepository) {
        InputFactory factory = new InputFactory(input);
        this.integerReader = factory.createIntegerReader();
        this.stringReader = factory.createStringReader();
        this.dateValidator = dateValidator;
        this.benefitCalculator = benefitCalculator;
        this.menuRepository = menuRepository;
    }

    //상태 스택
    public void run() {
        while (!stateStack.isEmpty()) {
            State state = stateStack.peek();
            state.stateHandler(this);
        }
    }

    public State peek() {
        return stateStack.peek();
    }

    public void push(State state) {
        stateStack.push(state);
        noticeObservers();
    }

    public void pop() {
        if (!stateStack.isEmpty()) {
            stateStack.pop();
            noticeObservers();
        }
    }

    public boolean isEmpty() {
        return stateStack.isEmpty();
    }

    //옵저버 관리
    public void addObserver(StateObserver observer) {
        observers.add(observer);
    }

    public void noticeObservers() {
        observers.forEach(observer -> observer.updateState(this));
    }

    public int getDateSession() {
        return session.getDate();
    }

    //오더 세션 관리
    public void setDateSession(int date) {
        session.setDate(date);
        noticeObservers();
    }

    public Menu getSelectedMenu() {
        return session.getSelectedMenu();
    }

    public void setSelectedMenu(Menu selectedMenu) {
        session.setSelectedMenu(selectedMenu);
        noticeObservers();
    }

    public List<Menu> getMenuList() {
        return menuRepository.getMenuList();
    }

    public void addOrder(Order order) {
        session.addOrder(order);
        noticeObservers();
    }

    public Orders getOrders() {
        return session.toOrders();
    }

    public void clearOrder() {
        session.clearOrder();
        noticeObservers();
    }

    public boolean hasOrders() {
        return session.hasOrders();
    }

    public boolean isOrderListEmpty() {
        return session.isOrderListEmpty();
    }

    //비지니스 - 혜택 계산
    public BenefitResult calculateBenefit() {
        return benefitCalculator.calculateBenefit(getOrders(), getDateSession());
    }


    //입력 및 검증
    public int parseJustInt() {
        return integerReader.read();
    }

    public int quantityValidate() {
        return new IntegerValidator().quantityValidate(readString());
    }

    public int validateVisit() {
        return dateValidator.validateVisitDate(readString());
    }

    public String readString() {
        return stringReader.read();
    }

    public void validateOrders(){
        Orders orders = getOrders();
        orders.ensureOrdersEmpty();
        orders.totalQuantityOfOrder();
        orders.ensureNotOnlyDrink();
    }
}
