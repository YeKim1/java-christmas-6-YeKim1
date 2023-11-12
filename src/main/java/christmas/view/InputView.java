package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static String inputDate() {
        OutputView.printInputDateMessage();
        return Console.readLine();
    }
}
