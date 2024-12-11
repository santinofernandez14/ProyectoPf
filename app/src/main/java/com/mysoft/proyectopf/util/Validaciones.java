package com.mysoft.proyectopf.util;

public class Validaciones {
    public static boolean validarTexto(String texto) {
        return texto != null && !texto.isEmpty() && texto.length() >= 3;
    }

    public static int validarNumero(String numero) {
        try {
            int valor = Integer.parseInt(numero);
            return valor >= 0 ? valor : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static boolean validarMail(String email) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(emailPattern);
    }

    public static String validarPass(String pass, String pass1) {
        if (pass == null || pass.isEmpty() || pass1 == null || pass1.isEmpty()) {
            return "La contraseña no puede estar vacía";
        }
        if (pass.length() < 6) {
            return "La contraseña debe tener al menos 6 caracteres";
        }
        if (!pass.equals(pass1)) {
            return "Las contraseñas no coinciden";
        }
        return null;
    }

    public static boolean controlarPasword(String pass) {
        return (pass != null && pass.length() >= 6);
    }
}