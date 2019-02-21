package com.zyc_summarize.base.binding.command;

public interface BindingConsumer<T> {
    void call(T t);
}
