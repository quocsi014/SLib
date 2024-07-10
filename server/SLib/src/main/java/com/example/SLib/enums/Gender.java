package com.example.SLib.enums;


import com.example.SLib.exception.InvalidEnumDataException;

public enum Gender {
    MALE,
    FEMALE,
    ;
    public static Gender fromString(String gender){
        try {
            return Gender.valueOf(gender.toUpperCase());
        } catch (IllegalArgumentException e) {
            InvalidEnumDataException invalidEnumDataException = new InvalidEnumDataException("Gender", Gender.values()); 
            throw invalidEnumDataException;
        }
    }
}
