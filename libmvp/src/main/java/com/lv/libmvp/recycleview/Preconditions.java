package com.lv.libmvp.recycleview;

import androidx.annotation.NonNull;

public final class Preconditions {

  public static @NonNull
  <T> T checkNotNull(final T reference) {
    if (reference == null) {
      throw new NullPointerException();
    }
    return reference;
  }


  private Preconditions() {}
}