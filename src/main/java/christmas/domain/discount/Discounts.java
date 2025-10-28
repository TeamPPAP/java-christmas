package christmas.domain.discount;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>{@code 할인 정보를 관리하는 일급 컬렉션 클래스
 * 
 * 역할 및 책임:
 * 1. 여러 개의 할인 정책(DiscountPolicy)을 적용한 결과를 통합 관리
 * 2. 할인명과 할인 금액을 Key-Value 쌍으로 저장하여 각 할인 내역을 추적
 * 3. 전체 할인 금액의 합계를 계산하는 기능 제공
 * 4. 할인 내역의 캡슐화를 통해 외부에서 직접 수정할 수 없도록 보호
 * 
 * 일급 컬렉션 패턴:
 * - Map을 직접 노출하지 않고 Discounts 클래스로 감싸서 관리
 * - 비즈니스 로직(총액 계산, 할인 추가 등)을 클래스 내부에 캡슐화
 * - 컬렉션 관련 동작을 의미 있는 메서드 이름으로 제공
 * 
 * 사용 시나리오:
 * 1. 다양한 할인 정책(크리스마스 디데이, 평일/주말, 특별 할인 등)을 적용
 * 2. 각 할인 정책의 calculateDiscount() 결과를 add()로 추가
 * 3. getTotalAmount()로 모든 할인의 합계를 조회
 * 4. getDiscounts()로 할인 내역을 외부에 전달 (읽기 전용)}</pre>
 */
public class Discounts {

    /**
     * 할인 내역을 저장하는 Map
     * Key: 할인명 (예: "크리스마스 디데이 할인", "평일 할인" 등)
     * Value: 할인 금액 (단위: 원)
     */
    private final Map<String, Integer> discounts;

    /**
     * 빈 할인 내역으로 Discounts 객체를 초기화
     * 이후 add() 메서드를 통해 각 할인 정책의 결과를 추가
     */
    public Discounts() {
        this.discounts = new HashMap<>();
    }

    /**
     * 특정 할인 정책의 결과를 추가
     * 
     * @param discountName 할인 정책의 이름 (예: "크리스마스 디데이 할인")
     * @param amount 할인 금액 (단위: 원, 양수 값)
     * 
     * <p>사용 예시:
     * - 각 DiscountPolicy 구현체의 calculateDiscount() 결과를 저장
     * - 동일한 할인명이 이미 존재하면 덮어씌워짐
     */
    public void add(String discountName, int amount) {
        discounts.put(discountName, amount);
    }

    /**
     * 모든 할인 금액의 총합을 계산
     * 
     * @return 전체 할인 금액의 합계 (단위: 원)
     *
     * 계산 방식:
     * - Map에 저장된 모든 할인 금액을 Stream으로 처리하여 합산
     * - 할인이 하나도 없으면 0을 반환
     * 사용처:
     * - 총 혜택 금액 계산
     * - 할인 후 최종 결제 금액 산출
     */
    public int getTotalAmount() {
        return discounts.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    /**
     * 할인 내역이 비어있는지 확인
     * 
     * @return 할인이 하나도 적용되지 않았으면 true, 하나라도 있으면 false
     * 
     * <p>사용처:
     * - 할인 혜택이 전혀 없는 경우를 판단
     * - 출력 시 "없음" 등의 메시지 표시 여부 결정
     */
    public boolean isEmpty() {
        return discounts.isEmpty();
    }

    /**
     * 할인 내역을 조회 (읽기 전용)
     * - View 레이어에서 할인 내역을 출력할 때
     * - 각 할인 정책별 금액을 개별적으로 확인할 때
     * @return 할인명과 할인 금액의 불변 Map
     */
    public Map<String, Integer> getDiscounts() {
        return Collections.unmodifiableMap(discounts);
    }
}
