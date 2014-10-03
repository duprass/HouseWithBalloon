package baseclasses;

import java.util.Random;


/**
 * Created by Olga on 25.09.2014.
 */
public class Balloon {

    /**
     * Рандом
     */
    final static Random RANDOM = new Random();
    /**
     * Что может быть привязано к нитке
     * статическая константа, потому что это набор вариантов присущий всем шарикам
     */
    private static final String[] TIE = {"rope", "cord", "yarn", "ribbon"};

    /**
     * способность одного шарика поднимать вес в кг
     * не константа, т.к. пользователь может использовать шарик с большей или меньшей способностью
     * опционально устанавливается в последнем аргументе строки
     * статическое, потому что предполагается, что все прикрепленные шары одного размера
     * следовательно относится ко всем полям
     */
    private static double balloonability = 0.004;

    /**
     * статическое поле для подсчета прикрепленных шаров
     * не константа, потому что изменяется
     * статическое, потому что относится ко всем шарам
     */
    private static int ballonnumber;

    /**
     * Чем будет привязан щарик?
     */
    private String knot;

    /**
     * Конструктор шарика
     * каждый раз, когда создается,  количество шариков + 1
     */
    public Balloon() {
        ballonnumber++;
        this.setKnot(RANDOM.nextInt(4));
    }

    /**
     * Мутатор для задания значения привязвающей веревки
     * @param knot1 индекс определяемый рандомом
     */
    public void setKnot(int knot1) {
        this.knot = TIE[knot1];
    }

    public String getKnot() {
        return this.knot;
    }

    /**
     * @return "весовую способность" шарика
     */
    public double getBalloonAbility() {
        return balloonability;
    }

    /**
     * мутатор для того, чтобы проверить не нарушает ли границы значение
     * если значение меньше или равно нулю, значение ставится по дефолту
     *
     * @param ability значение "весовая способность"
     */
    public static void setBalloonAbility(double ability) {
        if (ability <= 0) {
            balloonability = 0.004;
        } else {
            balloonability = ability;
        }
    }

    /**
     * @return строку для удобного форматирования
     */
    public static String getBalloonNumber() {
        String result = "The number of used balloons: " + ballonnumber;
        return result;
    }

    /**
     * Метод, который открепляет шары
     * т.к. шары откреплены, поднятый вес дома должен также равняться нулю
     *
     * @param house дом, от которого будут откреплены шаров
     */
    public static void deleteBalloons(House house) {
        ballonnumber = 0;
        house.deleteLiftWeight();
    }

}
