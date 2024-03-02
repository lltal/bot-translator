package com.github.lltal.converter.shared.ifc;

public interface AbstractConverter<T> {

    T convertToEnumValue(String stringView);

    String convertToStringView(T value);
}
