package com.utils;

public enum Browsers {
    FIREFOX,
    CHROME,
    SAFARI,
    IE;

    public static Browsers browserForName(String browser) throws IllegalArgumentException{
        for(Browsers b: values()){
            if(b.toString().equalsIgnoreCase(browser)){
                return b;
            }
        }
        throw browserNotFound(browser);
    }

    private static IllegalArgumentException browserNotFound(String outcome) {
        return new IllegalArgumentException(("Invalid browser [" + outcome + "]"));
    }
}
