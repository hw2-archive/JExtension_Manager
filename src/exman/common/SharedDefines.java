/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exman.common;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Giuseppe
 */
public class SharedDefines {

    public static String[] java_ver={"joomla 1.5","joomla 1.6+"};

    // formattatore di data
    private static final SimpleDateFormat dataFormatter = new SimpleDateFormat("dd/MM/yyyy");

    private static NumberFormat valutaFormat;
    private static NumberFormat interoFormat;

    public static String getDataFormat(Date data) {
        return dataFormatter.format(data);
    }

    public static NumberFormat getValutaFormat() {
        if(valutaFormat == null) {
            valutaFormat = NumberFormat.getNumberInstance();
            valutaFormat.setMaximumFractionDigits(2);
            valutaFormat.setGroupingUsed(false);
            valutaFormat.setRoundingMode(RoundingMode.HALF_UP);
        }

        return valutaFormat;
    }

    public static NumberFormat getInteroFormat() {
        if(interoFormat == null) {
            interoFormat = NumberFormat.getNumberInstance();
            interoFormat.setMaximumFractionDigits(0);
            interoFormat.setGroupingUsed(false);
            interoFormat.setRoundingMode(RoundingMode.HALF_UP);
        }

        return interoFormat;
    }
}
