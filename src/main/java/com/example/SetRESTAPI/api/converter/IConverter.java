package com.example.SetRESTAPI.api.converter;

import java.util.List;

public interface IConverter<T,T2> {
    T2 convertObject(T objectToConvert);
    List<T2> convertList(List<T> objectsToConvert);
}
