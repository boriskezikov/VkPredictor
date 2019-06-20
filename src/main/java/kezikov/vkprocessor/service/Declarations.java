package kezikov.vkprocessor.service;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class Declarations {
    private  static String access_token = "dcef741ff67807c6938192ea4dc348052251bf05d8c5340582be36d43de19d4876a08d5df44df25537553";

    private static String rv_programm_loc = "/Users/kezikovboris/Downloads/testXML/xmltv.xml";

    private static String[] sport = {
            "мотоспорт",
            "Авиамодельный",
            "гребля",
            "Акробатика",
            "Бадминтон",
            "Баскетбол",
            "Биатлон",
            "Бильярд",
            "Бейсбол",
            "Бокс",
            "Бобслей",
            "Бодибилдинг",
            "Боулинг",
            "Велоспорт",
            "велосипед",
            "поло",
            "Волейбол",
            "борьба",
            "Гандбол",
            "Гольф",
            "Дзюдо",
            "Единоборства",
            "Киберспорт",
            "Коньки",
            "Покер",
            "батут",
            "Теннис",
            "Тяжелая атлетика",
            "Фехтование",
            "Фигурное катание",
            "Футбол",
            "Хоккей",
            "гимнастика",
            "Шахматы",
            "Экстрим"};


     static String getAccess_token() {
        return access_token;
    }

    public static String[] getSportArray() {
        return sport;
    }

    public static String getRv_programm_loc() {
        return rv_programm_loc;
    }
}
