package com.absolut.task;

import java.util.ArrayList;
import java.util.List;

public class ActionCounter {

    /**
     * Константа, при желании - можно вынести в проперти
     */
    private final int TIME_BUFFER = 300;

    private final List<Action> list = new ArrayList<>();


    public void call(int timestamp) throws Exception {
        if (!list.isEmpty()) {
            //Берём последний элемент списка
            Action lastAction;
            synchronized (this) {
                lastAction = list.get(list.size() - 1);
            }
            // если пришедший action в прошлое - бросаем исключение
            if (timestamp < lastAction.getTimestamp()) {
                throw new Exception();
            }
            // если последний и текущий timestamp один и тот же - инкрементируем счётчик
            if (lastAction.getTimestamp() == timestamp) {
                lastAction.inc();
            } else {
                synchronized (this){
                    list.add(new Action(timestamp));
                }
            }
        } else {
            synchronized (this){
                list.add(new Action(timestamp));
            }
        }
    }

    public int getActions(int timestamp) {
        if (list.isEmpty())
            return 0;
        int counter = 0;
        // Ищем максимально подходящий нам индекс
        int targetIndex = findTargetIndex(timestamp);
        //Проходимся по листу начиная с нужного нам индекса
        while (targetIndex >= 0) {
            Action targetAction = list.get(targetIndex);
            if (Math.abs(timestamp - targetAction.getTimestamp()) < TIME_BUFFER) {
                counter += targetAction.getCounter().get();
                targetIndex--;
            } else {
                break;
            }
        }
        return counter;
    }

    private int findTargetIndex(int timestamp) {
        int left = 0;
        int right = list.size() - 1;
        if (list.get(right).getTimestamp() < timestamp) {
            return right;
        }
        if (list.get(left).getTimestamp() > timestamp) {
            return -1;
        }
        int middle = 0;
        while (left <= right) {
            middle = (right + left) / 2;
            // Если значение где-то между индексами - отдаем предпочтение бОльшему
            if (middle == 1){
                return right;
            }
                int target = list.get(middle).getTimestamp();
            if (target == timestamp) {
                return middle;
            } else if (target <= timestamp) {
                left = middle;
            } else {
                right = middle;
            }

        }
        return middle;
    }
}
