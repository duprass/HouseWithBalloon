import baseclasses.Balloon;
import baseclasses.House;

/**
 * Created by Olga on 25.09.2014.
 */
public class HouseUp {

    public static void main(String[] args) {

        // переменная для изменения "весовой способности"
        double liftWeightBal;

        // переменные для дальнейшего измерения времени
        long startTime;
        long timeBalloon;
        long timeInt;

        // если введены все значения, запускаем программу
        if (args.length >= 3) {


            int floor = Integer.parseInt(args[0]);
            double width = Double.parseDouble(args[1]);
            double length = Double.parseDouble(args[2]);
            if (args.length == 4) { // если есть параметр для "весовой способности"
                liftWeightBal = Double.parseDouble(args[3]);
                Balloon.setBalloonAbility(liftWeightBal); //назначаем новое значение
            }

            House house = new House(floor, width, length);
            System.out.println("The house weight is " + house.getHouseWeight());


            startTime = System.currentTimeMillis(); // измеряем стартовое время для цикла с шарами

            while (!house.compareWeight()) { // если дом взлетел, прекращаем цикл
                house.addBalloon(new Balloon());
            }

            // измеряем затраченное время на цикл с шарами
            timeBalloon = System.currentTimeMillis() - startTime;

            System.out.println("The house is flying now!");
            System.out.println(Balloon.getBalloonNumber());

            Balloon.deleteBalloons(house); // открепляем шары

            startTime = System.currentTimeMillis();  // измеряем стартовое время для цикла с int

            while (!house.compareWeight()) {
                house.addBalloon(1);
            }

            // измеряем затраченное время на цикл с int'ами
            timeInt = System.currentTimeMillis() - startTime;

            // Выводим сообщение о том, что быстрее
            if (timeBalloon < timeInt) {
                System.out.println("Ballon's faster in " + (timeInt - timeBalloon)
                                    + " millisecond");
            } else {
                System.out.println("Int's faster in " + (timeBalloon - timeInt) + " millisecond");
            }

            /*Вывод
              примитив быстрее, чем использование объектов
              В конструкторе объекта используется рандом, а также есть поле с типом String,
              что значительно увеличивает время работы с Balloon
              Замечание: был проведены также тесты с использованием класс Ballon без поля knot
              и рандома, в данном случае, цикл с объектом Balloon происходил быстрее
             */
        }
    }
}

