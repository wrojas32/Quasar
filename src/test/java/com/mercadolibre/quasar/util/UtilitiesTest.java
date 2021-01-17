package com.mercadolibre.quasar.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UtilitiesTest {

    @Test
    void calculateThreeCircleIntersection() {
        String[] intertectionPosition = Utilities.calculateThreeCircleIntersection(-500, -200, 670.82, 100, -100, 200,
            500, 100, 400);
        assertEquals(intertectionPosition[0], "100.0");
        assertEquals(intertectionPosition[1], "100.0");
    }

    @Test
    void getFinalMessage(){
        String[][] messages = { {"Este", "", "","mensaje", ""}, {"", "es", "","", "secreto"}
        , {"Este", "", "un","", ""}};
        String[] finalMessage = Utilities.getMessage(messages);
        assertEquals(finalMessage[0], "Este");
        assertEquals(finalMessage[1], "es");
        assertEquals(finalMessage[2], "un");
        assertEquals(finalMessage[3], "mensaje");
        assertEquals(finalMessage[4], "secreto");
    }
}