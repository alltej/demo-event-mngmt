package com.alltej.eventmngmt.util;

import com.alltej.eventmngmt.exception.BadRequestException;

/**
 * @author atejano
 */
public class PagingValidator {

    public static void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }
}
