package com.visitscotland.brxm.personalisation;

@FunctionalInterface
interface Validator<T> {
    boolean isValid(T t);
}