package com.arthurf.testesicred.api.utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Business utils class.
 */
public class BusinessUtils {

    /**
     * Private constructor to avoid class instantiation.
     */
    private BusinessUtils() {
    }

    /**
     * Parse the errors list to a string.
     * 
     * @param errors the errors list.
     * @return the errors list as a string.
     */
    public static String parseErrorsList(final List<String> errors) {
        return errors.stream().collect(Collectors.joining(", "));
    }

    /**
     * Check if a string is a valid UUID.
     * 
     * @param uuid the string to check.
     * @return true if the string is a valid UUID, false otherwise.
     */
    public static boolean isUUID(String uuid) {
        return uuid.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
    }

    /**
     * Generate a random CPF.
     * DOESNT GARANTEE A VALID CPF.
     * 
     * @return a random CPF.
     */
    public static String generateRandomCPF() {
        final var cpf = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            cpf.append((int) (Math.random() * 10));
        }
        return cpf.toString();
    }
}
