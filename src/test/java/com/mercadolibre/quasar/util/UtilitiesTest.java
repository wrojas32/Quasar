package com.mercadolibre.quasar.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UtilitiesTest {

    @Test
    void calculateThreeCircleIntersection() {
        String[] intertectionPosition = Utilities.calculateThreeCircleIntersection(3, 0, 2, 1, 2, 2,
            1, -3, 3);
        assertEquals(intertectionPosition[0], "1,0");
        assertEquals(intertectionPosition[1], "0,0");
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