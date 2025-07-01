package com.example.SetRESTAPI.api.converter;

import java.util.List;

public interface IConverter<T> {
    T convertToObject();
    T convertFromObject(T object);
    List<Object> convertToList(List<T> objects);
    List<Object> convertFromList(List<T> objects);
}
