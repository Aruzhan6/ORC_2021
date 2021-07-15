package com.pixel.meirlen.orc.helper;

public enum StatusOrder {

    PROCESSING("В обработке", 1),
    ACCEPTED("Заявка принята", 2),
    COURIER("Курьер принял", 3),
    DELIVERED("Заявка доставлена", 4),
    COMPLETED("Заявка завершена", 5);

    private String name;
    private int status;

    StatusOrder(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public static String getStatus(int status) {
        return StatusOrder.values()[status-1].getName();
    }
}
