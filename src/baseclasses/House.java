package baseclasses;

import baseclasses.Balloon;

/**
 * Created by Olga on 25.09.2014.
 */
public class House {
    /**
     * Константы, т.к. высота стены, их вес и вес потолка пола неизменяемы
     * Статические, потому что в данном случае у всех домов одинаковые
     */
    private final static double HEIGHT = 2.5;
    private final static int WALL_WEIGHT = 250;
    private final static int CEILING_WEIGHT = 150;

    /**Закрытые, т.к. пользователь не должен изменять
     * их значение без проверки, либо вообще не должен (houseWeight)
     * Нестатические, т.к. эти значения для каждого дома  разные
     */
    private double liftedWeight;
    private int floor;
    private double length;
    private double width;
    private double houseWeight;

    /**Конструктор дома
     * также вычисляет его вес
     * @param floor количество этажей
     * @param length длина дома
     * @param width ширина дома
     */
    public House(int floor, double length, double width) {
        this.setFloor(floor);
        this.setLength(length);
        this.setWidth(width);
        this.setHouseWeight(); // вычисление веса
    }

    /**
     * Мутатор, проверяющий количество введенных этажей
     * если меньше или равно 0, ставится значение по дефолту
     * Открытый, т.к. теоретически, можно построить ещё один этаж
     *
     * @param floor количество этажей
     */
    public void setFloor(int floor) {
        if (floor <= 0) {
            floor = 1;
        }
        this.floor = floor;
    }

    // Два следующих мутатор открытые, т.к. теоретически можно сделать пристройки к дому

    /**
     * Мутатор, проверяющий количество введенных этажей
     * если меньше или равно 0, ставится значение по дефолту
     *
     * @param length длина дома
     */
    public void setLength(double length) {
        if (length <= 0) {
            this.length = 8.9;
            return;
        }
        this.length = length;
    }

    /**
     * Мутатор, проверяющий количество введенных этажей
     * если меньше или равно 0, ставится значение по дефолту
     *
     * @param width ширина дома
     */
    public void setWidth(double width) {
        if (width <= 0) {
            this.width = 5.4;
            return;
        }
        this.width = width;
    }

    /**
     * Акссесор к плошади стен
     * Закрытый, потому что пользователь может изменить это значение,
     * если только изменит планировку, либо увеличит ширину/длину дома
     * 3 стены находятся на ширине, 2 -- длине и 1 "на половине длины"
     *
     * @return площадь стен
     */
    private double getWallSquare() {
        return (3 * HEIGHT * width + 2 * HEIGHT * length + HEIGHT * (length / 2));
    }

    /**
     * @return вес стен
     */
    private double getWallsWeight() {
        return getWallSquare() * WALL_WEIGHT;
    }

    /**
     *
     * @return площадь потолка
     */
    private double getCeilingSquare() {
        return length * width;
    }

    /**
     *
     * @return вес потолка
     */
    private double getCeilingWeight() {
        return getCeilingSquare() * CEILING_WEIGHT;
    }

    /**Закрытый, т.к. пользователь может изменить вес дома,
     * только если изменит кол-во этажей, длины и ширины
     */
    private void setHouseWeight() {
        houseWeight = getWallsWeight() * this.floor + getCeilingWeight() * (this.floor + 1);
    }

    //... но он может узнать значение веса
    public double getHouseWeight() {
        return houseWeight;
    }


    private double getLiftedWeight() {
        return liftedWeight;
    }

    /**
     * Метод, проверяющий взлетел ли дом
     *
     * @return true, если взлетел
     * false, если нет
     */
    public boolean compareWeight() {
        boolean result = ((int) ((getHouseWeight() - getLiftedWeight()) * 1000)) == 0;
        return result;
    }

    /**
     * Добавляет шар, следовательно количество поднятого веса увеличиваем на "весовую способность"
     *
     * @param balloon шар, который добавляем
     */
    public void addBalloon(Balloon balloon) {
        liftedWeight += balloon.getBalloonAbility();
    }

    /**
     * Перегрузка с примитивом, добавляет шар
     *
     * @param balloon int, представляющий количество шаров
     */
    public void addBalloon(int balloon) {
        liftedWeight += balloon * 0.004;
    }

    /**
     * Метод "устраняющий" поднятый вес
     * Закрытый для главного класса, т.к. это можно сделать,
     * только если шары будут откреплены
     */
    void deleteLiftWeight() {
        this.liftedWeight = 0;
    }


}
