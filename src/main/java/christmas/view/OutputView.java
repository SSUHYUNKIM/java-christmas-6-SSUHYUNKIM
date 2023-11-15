package christmas.view;

import christmas.util.message.OutputMessage;

import java.text.DecimalFormat;
import java.util.List;

public class OutputView {
    public void printWelcomeMessage() {
        System.out.println(OutputMessage.OUT_WELCOME.getMessage());
    }

    public void printEventPreviewMessage(int visitingDate) {
        System.out.printf((OutputMessage.OUT_PREVIEW.getMessage()) + "%n", visitingDate);
        System.out.println();
    }

    public void printOrderMenuMessage(List<String> orderedMenuInfo) {
        System.out.println("<주문 메뉴>");
        for (String orderedMenu : orderedMenuInfo) {
            System.out.println(orderedMenu);
        }
        System.out.println();
    }

    public void printTotalPriceBeforeDiscountMessage(int totalPriceBeforeDiscount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(new DecimalFormat("###,###").format(totalPriceBeforeDiscount) + "원");
        System.out.println();
    }

    public void printGiveawayMenuMessage(String giveawayMenu) {
        System.out.println("<증정 메뉴>");
        System.out.println(giveawayMenu);
        System.out.println();
    }

    public void printBenefitDetailsMessage(List<String> benefitDetails) {
        System.out.println("<혜택 내역>");
        for (String benefitDetail : benefitDetails) {
            System.out.println(benefitDetail);
        }
        System.out.println();
    }

    public void printTotalBenefitsAmountMessage(int totalBenefitsAmount) {
        System.out.println("<총혜택 금액>");
        System.out.println(new DecimalFormat("###,###").format(totalBenefitsAmount) + "원");
        System.out.println();
    }

    public void printExpectedAmountAfterDiscountMessage(int expectedAmountAfterDiscount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(new DecimalFormat("###,###").format(expectedAmountAfterDiscount) + "원");
        System.out.println();
    }

    public void printEventBadgeMessage(String badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge);
        System.out.println();
    }

}
