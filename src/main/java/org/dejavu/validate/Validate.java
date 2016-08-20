package org.dejavu.validate;
@SuppressWarnings("unused")
public class Validate {

	private static final String DEFAULT_NOT_NAN_EX_MESSAGE =
        "The validated value is not a number";
    private static final String DEFAULT_FINITE_EX_MESSAGE =
        "The value is invalid: %f";
    private static final String DEFAULT_GREATER_EX_MESSAGE =
        "The value %s is not greater than %s";
    private static final String DEFAULT_GREATER_OR_EQUAL_EX_MESSAGE =
        "The value %s is not greater than or equal to %s";
    private static final String DEFAULT_SMALLER_EX_MESSAGE =
        "The value %s is not smaller than %s";
    private static final String DEFAULT_SMALLER_OR_EQUAL_EX_MESSAGE =
        "The value %s is not smaller than or equal to %s";
    private static final String DEFAULT_DIFFERENT_EX_MESSAGE =
        "The value %s is invalid";
    private static final String DEFAULT_EXCLUSIVE_BETWEEN_EX_MESSAGE =
        "The value %s is not in the specified exclusive range of %s to %s";
    private static final String DEFAULT_INCLUSIVE_BETWEEN_EX_MESSAGE =
        "The value %s is not in the specified inclusive range of %s to %s";
    private static final String DEFAULT_MATCHES_PATTERN_EX = "The string %s does not match the pattern %s";
    private static final String DEFAULT_IS_NULL_EX_MESSAGE = "The validated object is null";
    private static final String DEFAULT_IS_TRUE_EX_MESSAGE = "The validated expression is false";
    private static final String DEFAULT_NO_NULL_ELEMENTS_ARRAY_EX_MESSAGE =
        "The validated array contains null element at index: %d";
    private static final String DEFAULT_NO_NULL_ELEMENTS_COLLECTION_EX_MESSAGE =
        "The validated collection contains null element at index: %d";
    private static final String DEFAULT_NOT_BLANK_EX_MESSAGE = "The validated character sequence is blank";
    private static final String DEFAULT_NOT_EMPTY_ARRAY_EX_MESSAGE = "The validated array is empty";
    private static final String DEFAULT_NOT_EMPTY_CHAR_SEQUENCE_EX_MESSAGE =
        "The validated character sequence is empty";
    private static final String DEFAULT_NOT_EMPTY_COLLECTION_EX_MESSAGE = "The validated collection is empty";
    private static final String DEFAULT_NOT_EMPTY_MAP_EX_MESSAGE = "The validated map is empty";
    private static final String DEFAULT_VALID_INDEX_ARRAY_EX_MESSAGE = "The validated array index is invalid: %d";
    private static final String DEFAULT_VALID_INDEX_CHAR_SEQUENCE_EX_MESSAGE =
        "The validated character sequence index is invalid: %d";
    private static final String DEFAULT_VALID_INDEX_COLLECTION_EX_MESSAGE =
        "The validated collection index is invalid: %d";
    private static final String DEFAULT_VALID_STATE_EX_MESSAGE = "The validated state is false";
    private static final String DEFAULT_IS_ASSIGNABLE_EX_MESSAGE = "Cannot assign a %s to a %s";
    private static final String DEFAULT_IS_INSTANCE_OF_EX_MESSAGE = "Expected type: %s, actual: %s";
	
	public static <T> T notNull(final T object) {
	    return notNull(object, DEFAULT_IS_NULL_EX_MESSAGE);
	}
	
	
	/**
	 * <p>验证指定的参数不为 {@code null};
	 * 否则抛出一个附加指定信息的异常.
	 *
	 * <pre>Validate.notNull(myObject, "The object must not be null");</pre>
	 *
	 * @param <T> 对象类型
	 * @param object  需要检测的对象
	 * @param message  检测为{@code null}对象抛出的消息, 该参数不能为null
	 * @param values  格式化的异常消息（可选）
	 * @return 被验证的对象 (调用方法链中永远不为 {@code null})
	 * @throws NullPointerException 如果对象为 {@code null}
	 * @see #notNull(Object)
	 */
	public static <T> T notNull(final T object, final String message, final Object... values) {
	    if (object == null) {
	        throw new NullPointerException(String.format(message, values));
	    }
	    return object;
	}
	
	// notEmpty array
	//---------------------------------------------------------------------------------
	
	/**
	 * <p>验证指定的数组参数不能为{@code null}也不能长度为零；
	 * 否则将抛出一个附加指定消息的异常.
	 *
	 * <pre>Validate.notEmpty(myArray, "The array must not be empty");</pre>
	 *
	 * @param <T> 数组类型
	 * @param array  需要检测的数组, 通过这个方法验证不为空
	 * @param message  检测为{@code null}对象抛出的消息, 该参数不能为null
	 * @param values  格式化的异常消息（可选）, 不提倡使用空数组
	 * @return 被验证的数组 (调用方法链中永远不为 {@code null})
	 * @throws NullPointerException 如果数组为 {@code null}
	 * @throws IllegalArgumentException 如果数组长度为零
	 * @see #notEmpty(Object[])
	 */
	public static <T> T[] notEmpty(final T[] array, final String message, final Object... values) {
	    if (array == null) {
	        throw new NullPointerException(String.format(message, values));
	    }
	    if (array.length == 0) {
	        throw new IllegalArgumentException(String.format(message, values));
	    }
	    return array;
	}
	
	public static <T> T[] notEmpty(final T[] array) {
	    return notEmpty(array, DEFAULT_NOT_EMPTY_ARRAY_EX_MESSAGE);
	}
}
