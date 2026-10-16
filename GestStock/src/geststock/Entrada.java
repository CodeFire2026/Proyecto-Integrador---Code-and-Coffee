// Creamos la Clase y el Scanner

package geststock;

import java.util.Scanner;

public class Entrada {
    private static Scanner scanner = new Scanner (System.in);
    
    // Leemos el texto
    
    public static String leerTexto (String mensaje){
        String valor = "";
        boolean valido = false;
        while (!valido){
            System.out.println(mensaje);
            valor = scanner.nextLine().trim();
            if (valor.length() > 0){
                valido = true;
            } else {
                System.out.println("  >> El dato no puede quedar vacío.");
            }
        }
        return valor;
    }
    
    // Leemos los números
    
    public static int leerEntero (String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("  >> Tenés que ingresar un numero entero.");
            }
        }
    }
    
    public static int leerEnteroPositivo (String mensaje) {
        while (true) {
             int valor = leerEntero(mensaje);
             if (valor >= 0) {
                 return valor;
             }
             System.out.println("  >> El valor no puede ser negativo.");
        }
    }
    
    public static int leerEnteroEnRango (String mensaje, int minimo, int maximo) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor >= minimo && valor <= maximo) {
                return valor;
            }
            System.out.println("  >> Ingresá un valor entre " + minimo + " y " + maximo + ".");
        }
    }
            
    public static double leerDecimalPositivo (String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String entrada = scanner.nextLine().trim().replace(",", ".");
            try {
                double valor = Double.parseDouble (entrada);
                if (valor >= 0) {
                    return valor;
                }
                System.out.println("  >> El valor no puede ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println(" >>  Tenés que ingresar un numero.");
            }
        }
    }
    
    // Confirmamos y pausamos
    
    public static boolean confirmar (String mensaje) {
        while (true) {
            System.out.println(mensaje + " (S/N): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (respuesta.equals("s") || respuesta.equals("si")) {
                return true;
            }
            if (respuesta.equals("n") || respuesta.equals("no")) {
                return false;  
            }
            System.out.println("  >> Respondé con S o N.");
        }
    }
    
    public static void pausar () {
        System.out.println();
        System.out.println("Presiona ENTER para continuar...");
        scanner.nextLine();
    }
    
    public static void limpiarPantalla() {
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }
    
    // Pedimos una sección
    
    public static Seccion leerSeccion (String mensaje) {
        Seccion.mostrarOpciones();
        int numero = leerEnteroEnRango(mensaje, 1, Seccion.values().length);
        return Seccion.porNumero(numero);
    }
}







