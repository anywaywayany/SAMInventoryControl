package com.samic.samic.data.foundation;

import com.samic.samic.exceptions.SamicException;
import org.springframework.util.Assert;

import java.util.function.Predicate;

import static java.lang.String.format;

public abstract class Guard {

private static final String isNotNullMsg = "'%s' must not be null!";

  public static final Predicate<? super Object> isNull = (arg) -> arg == null;
  public static final Predicate<? super Object> isNotNull = isNull.negate();

  public static <Q> Q ensureNotNull(Q argument) {
    return ensureNotNull(argument, "argument");
  }

  public static <Q> Q ensureNotNull(Q argument, String name) {
    if (argument == null) {
      throw new SamicException("'%s' must not be null!".formatted(name));
    }
    return argument;
  }

  public static <Q> Q isNotNull(Q obj, String name){
    Assert.notNull(obj, () -> format(isNotNullMsg, name));
    return obj;
  }

  public static boolean isNullBlankOrEmpty(String argument) {
    return argument.isBlank() || argument.isEmpty() || argument == null;
  }
}
