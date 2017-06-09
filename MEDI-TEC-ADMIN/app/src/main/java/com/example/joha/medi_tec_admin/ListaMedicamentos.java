package com.example.joha.medi_tec_admin;

/**
 * Created by Bryan on 29/5/2017.
 */

public class ListaMedicamentos {
    private static final ListaMedicamentos ourInstance = new ListaMedicamentos();

    public static ListaMedicamentos getInstance() {
        return ourInstance;
    }

    private ListaMedicamentos() {
    }
}
