# 크리스마스 프로모션 이벤트 플래너 로직 흐름

## 전체 실행 흐름

```
main()
    print welcome message
    
    visitDate = readAndValidateDate()
    orders = readAndValidateOrders()
    
    print event preview header with visitDate
    
    print order menu
    
    totalAmountBeforeDiscount = calculateTotalAmount(orders)
    print totalAmountBeforeDiscount
    
    giveaway = determineGiveaway(totalAmountBeforeDiscount)
    print giveaway
    
    if totalAmountBeforeDiscount < 10000
        print no benefits
        print totalAmountBeforeDiscount as final amount
        print no badge
        return
    
    discounts = calculateAllDiscounts(visitDate, orders)
    print benefit details(discounts, giveaway)
    
    totalBenefitAmount = sum(discounts) + giveaway.price
    print totalBenefitAmount
    
    finalAmount = totalAmountBeforeDiscount - sum(discounts)
    print finalAmount
    
    badge = determineBadge(totalBenefitAmount)
    print badge
```

## 상세 로직

### 1. 날짜 입력 및 검증
```
readAndValidateDate()
    while true
        try
            input = readDate()
            validate input is number
            validate 1 <= input <= 31
            return VisitDate(input)
        catch exception
            print "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."
```

### 2. 주문 입력 및 검증
```
readAndValidateOrders()
    while true
        try
            input = readOrders()
            validate format matches "메뉴-개수,메뉴-개수,..."
            
            orderItems = parse input to list of (menu, quantity)
            
            validate all menus exist in menu list
            validate all quantities >= 1
            validate no duplicate menus
            validate total quantity <= 20
            validate not only beverages
            
            return Orders(orderItems)
        catch exception
            print "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
```

### 3. 총 주문 금액 계산
```
calculateTotalAmount(orders)
    total = 0
    for each orderItem in orders
        total += orderItem.menu.price * orderItem.quantity
    return total
```

### 4. 증정 메뉴 결정
```
determineGiveaway(totalAmount)
    if totalAmount >= 120000
        return Giveaway(샴페인, 1개)
    return Giveaway(없음)
```

### 5. 할인 계산
```
calculateAllDiscounts(visitDate, orders)
    discounts = []
    
    // 크리스마스 디데이 할인
    if 1 <= visitDate.day <= 25
        discountAmount = 1000 + (visitDate.day - 1) * 100
        discounts.add(ChristmasDDayDiscount: discountAmount)
    
    // 평일 할인 (일~목)
    if visitDate.isWeekday()
        dessertCount = count dessert menus in orders
        if dessertCount > 0
            discountAmount = dessertCount * 2025
            discounts.add(WeekdayDiscount: discountAmount)
    
    // 주말 할인 (금~토)
    if visitDate.isWeekend()
        mainCount = count main menus in orders
        if mainCount > 0
            discountAmount = mainCount * 2025
            discounts.add(WeekendDiscount: discountAmount)
    
    // 특별 할인 (일요일 + 25일)
    if visitDate.isSpecialDay()
        discounts.add(SpecialDiscount: 1000)
    
    return discounts
```

### 6. 배지 결정
```
determineBadge(totalBenefitAmount)
    if totalBenefitAmount >= 20000
        return "산타"
    if totalBenefitAmount >= 10000
        return "트리"
    if totalBenefitAmount >= 5000
        return "별"
    return "없음"
```

## 도메인 로직 핵심 규칙

### 날짜 관련
```
VisitDate
    isWeekday() // 일(0), 월(1), 화(2), 수(3), 목(4)
        return dayOfWeek in [0, 1, 2, 3, 4]
    
    isWeekend() // 금(5), 토(6)
        return dayOfWeek in [5, 6]
    
    isSpecialDay() // 일요일 + 25일
        return dayOfWeek == 0 OR day == 25
    
    getDayOfWeek()
        // 2025년 12월 1일은 월요일
        return (day + 0) % 7
```

### 메뉴 관련
```
Menu (enum or constant)
    양송이수프(6000, APPETIZER)
    타파스(5500, APPETIZER)
    시저샐러드(8000, APPETIZER)
    티본스테이크(55000, MAIN)
    바비큐립(54000, MAIN)
    해산물파스타(35000, MAIN)
    크리스마스파스타(25000, MAIN)
    초코케이크(15000, DESSERT)
    아이스크림(5000, DESSERT)
    제로콜라(3000, BEVERAGE)
    레드와인(60000, BEVERAGE)
    샴페인(25000, BEVERAGE)

MenuType (enum)
    APPETIZER
    MAIN
    DESSERT
    BEVERAGE
```

### 주문 검증 규칙
- 총 주문 금액 10,000원 이상부터 이벤트 적용
- 음료만 주문 불가
- 메뉴 최대 20개까지
- 중복 메뉴 불가
- 메뉴 개수는 1 이상

### 혜택 계산 규칙
- 총 혜택 금액 = 할인 금액 합계 + 증정 메뉴 가격
- 할인 후 예상 결제 금액 = 할인 전 총 주문 금액 - 할인 금액 (증정 메뉴 가격 제외)
