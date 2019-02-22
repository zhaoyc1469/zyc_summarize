package com.app.frame.binding.command;

public interface BindingConsumer<T> {
    void call(T t);
}
