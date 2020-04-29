package com.foan.crm.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

/**
 * A simple utility of GSON.
 *
 * @author jacobhsiao
 */
public class GsonUtil {
	private static final Gson gson =
			new GsonBuilder()
					.excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
					.create();

	/**
	 * Serialize the specific object into json representation.
	 *
	 * @param obj The object want to serialize.
	 * @return a json string roughly represents the object.
	 */
	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	/**
	 * Deserialize a json string into an object of the specified class.
	 *
	 * @param json     The json string.
	 * @param classOfT The specified class.
	 * @param <T>      The type of the desired object.
	 * @return an object of the specified class. Return <code>null</code> if json is <code>null</code> or if json is
	 * empty.
	 */
	public static <T> T fromJson(String json, Class<T> classOfT) {
		return gson.fromJson(json, classOfT);
	}
}
