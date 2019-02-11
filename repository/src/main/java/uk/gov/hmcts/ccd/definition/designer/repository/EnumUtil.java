package uk.gov.hmcts.ccd.definition.designer.repository;

public class EnumUtil {

    private EnumUtil() {
        // This util class provides a public static method
    }

    /**
     * A common method for all enums since they cannot have another base class.  See also
     * https://stackoverflow.com/questions/604424/lookup-enum-by-string-value
     *
     * @param <T> Enum type
     * @param c enum type. All enums must be all caps.
     * @param enumConstant case insensitive representation of Enum
     * @return corresponding enum, or null
     */
    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String enumConstant) {
        if (c == null) {
            throw new IllegalArgumentException("Class argument cannot be null");
        }
        if (enumConstant == null) {
            throw new IllegalArgumentException("Enum constant argument cannot be null");
        }
        return Enum.valueOf(c, enumConstant.toUpperCase());
    }
}
