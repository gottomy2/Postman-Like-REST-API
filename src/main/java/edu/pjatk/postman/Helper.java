package edu.pjatk.postman;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Igor Motowidło (gottomy2)
 * Right now unused
 */
public class Helper {
    public static <T> List<T> toList(Optional<T> opt) {
        return opt.isPresent()
                ? Collections.singletonList(opt.get())
                : Collections.emptyList();
    }
}
