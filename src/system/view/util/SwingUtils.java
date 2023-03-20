package system.view.util;

import com.google.common.collect.Iterators;

import javax.swing.*;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.StreamSupport;

import static java.util.Spliterator.ORDERED;

public class SwingUtils {

    public static String getSelectedButton(ButtonGroup buttonGroup) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(Iterators.forEnumeration(buttonGroup.getElements()),
                                ORDERED),
                        false)
                .filter(AbstractButton::isSelected)
                .map(AbstractButton::getText)
                .findFirst()
                .orElse(null);
    }

    public static Function<Object, Function<Object, String>> getTimeString =
            t1 -> t2 -> String.format("%02d", t1) + ":" + String.format("%02d", t2);

    public static Function<String, Function<String, String>> htmlString =
            s1 -> s2 -> "<html>" + s1 + ":<br>" + s2 + "</html>";

}
