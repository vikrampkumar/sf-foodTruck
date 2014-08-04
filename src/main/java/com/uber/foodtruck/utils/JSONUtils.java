/*
 * Copyright 2014 Mobile Iron, Inc.
 * All rights reserved.
 */

package com.uber.foodtruck.utils;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Wrappers utility functions for the jackson JSON library.
 */
public final class JSONUtils {

    private static JsonMapper defaultMapper = JsonMapper.getDefault();
    private static JsonMapper mapperWithFormatting = JsonMapper.getDefault()
            .configure(SerializationFeature.INDENT_OUTPUT, true);

    private JSONUtils() {

    }

    /**
     * Converts an object to its JSON representation.
     * 
     * @param o The object to convert.
     * @return The JSON string representation of the specified object.
     */
    public static String toJSON(final Object o) {
        return defaultMapper.toJSON(o);
    }

    /**
     * Converts an object to its JSON representation (without indentation).
     * 
     * @param o The object to convert.
     * @return The JSON string representation of the specified object.
     */
    public static String toJSONWithFormatting(final Object o) {
        return mapperWithFormatting.toJSON(o);
    }

    /**
     * Converts an object to its JSON representation, overriding annotations that target has with the annotations from
     * mixinSource.
     * 
     * @param o The object to convert.
     * @param mixinSource specifies the class or interface that is used to augment annotations of a target class
     * @return The JSON string representation of the specified object.
     */
    public static String toJSONUsingMixInAnnotations(final Object o, final Class mixinSource) {
        final JsonMapper mapper = JsonMapper.getDefault();
        mapper.getMapper().addMixInAnnotations(o.getClass(), mixinSource);
        return mapper.toJSON(o);
    }

    /**
     * Converts an object to its JSON representation, overriding annotations that target has with the annotations from
     * mixinSource.
     * 
     * @param o The object to convert.
     * @param mixinSource specifies the class or interface that is used to augment annotations of a target class
     * @param classesToConfigure The classes to configured for mixIn - don't use object o's class.
     * @return The JSON string representation of the specified object.
     */
    public static String toJSONUsingMixInAnnotations(final Object o, final Class mixinSource, final Class... classesToConfigure) {
        final JsonMapper mapper = JsonMapper.getDefault();
        for (Class classToConfigure : classesToConfigure) {
            mapper.getMapper().addMixInAnnotations(classToConfigure, mixinSource);
        }
        return mapper.toJSON(o);
    }

    /**
     * Instantiates an object using its JSON string representation.
     * 
     * @param json The JSON string representation of the class.
     * @param classType The type of object to instantiate.
     * @param <T> Generic used to provide the class type.
     * @return An object instantiated from the specified JSON.
     */
    public static <T> T fromJSON(final String json, final Class<T> classType) {
        return defaultMapper.fromJSON(json, classType);
    }

    /**
     * Instantiates an object using its JSON string representation.
     * 
     * @param json The JSON string representation of the class.
     * @param ref The type of object to instantiate.
     * @param <T> Generic used to provide the class type.
     * @return An object instantiated from the specified JSON.
     */
    public static <T> T fromJSON(final String json, final TypeReference ref) {
        return defaultMapper.fromJSON(json, ref);
    }

    /**
     * Instantiates an object using its JSON string representation, overriding annotations that target has with the
     * annotations from mixinSource.
     * 
     * @param json The JSON string representation of the class.
     * @param classType The type of object to instantiate.
     * @param <T> Generic used to provide the class type.
     * @param mixinSource specifies the class or interface that is used to augment annotations of a target class
     * @return An object instantiated from the specified JSON.
     */
    public static <T> T fromJSONUsingMixInAnnotations(final String json, final Class<T> classType, final Class mixinSource) {
        final JsonMapper mapper = JsonMapper.getDefault();
        mapper.getMapper().addMixInAnnotations(classType.getClass(), mixinSource);
        return mapper.fromJSON(json, classType);
    }

    /**
     * Instantiates a java object like List<T> from it's JSON representation.
     * 
     * @param <T> The parameterized type.
     * @param json the json blob.
     * @param type The java type to be converted to.
     * @return List of java objects of type T.
     */
    public static <T> List<T> fromJSONList(final String json, final Class<T> type) {
        final JavaType javaType = defaultMapper.getMapper().getTypeFactory().constructParametricType(List.class, type);
        return defaultMapper.fromJSON(json, javaType);
    }

}
