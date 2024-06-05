package BanksStuff.Banks.TimeMachine;

import BanksStuff.Users.User;
import lombok.AllArgsConstructor;
/**
 * Time machine from Doctor Who.
 * "You never took me where I wanted to go!"
 *  — But always where you needed to go.
 */
@AllArgsConstructor
public class TARDIS {

    User user;

    public void SkipMonth() {

        for (int numbersOfDaysInMonth = 0; numbersOfDaysInMonth < 30; ++numbersOfDaysInMonth) {
            user.chargePercentageForDay();
        }

        user.chargePercentageForMonth();
    }


    public void SkipYear() {
        for (int numbersOfDaysInMonth = 0; numbersOfDaysInMonth < 12; ++numbersOfDaysInMonth) {
            SkipMonth();
        }
    }

}
