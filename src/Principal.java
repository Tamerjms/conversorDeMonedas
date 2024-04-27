import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Principal {
    private static final int opcionDolar_A_ARS = 1;
    private static final int opcion_ARS_A_Dolar = 2;
    private static final int opcionDolar_A_BRL = 3;
    private static final int opcion_BRL_A_Dolar = 4;
    private static final int opcionDolar_A_COP = 5;
    private static final int opcion_COP_A_Dolar = 6;
    private static final int opcionMonedasDiferentes = 7;
    private static final int opcionSalir = 8;

    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ArrayList<String> historialConversiones = new ArrayList<>();
        int opcionUsuario = 0;
        String monedaOrigen = "";
        String monedaDeseada = "";
        int valorAConvertir = 0;
        String USD = "USD";
        String ARS = "ARS";
        String BRL = "BRL";
        String COP = "COP";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            while (opcionUsuario != opcionSalir){
                System.out.println("""
                    \n*******************************************
                    Sea Bienvenido/a al Conversor de Monedas, por favor seleccione una de las siguientes opciones:
                    
                    1) Dolar =>> Peso argentino.
                    2) Peso argentino ==> Dolar
                    3) Dolar =>> Real brasilero
                    4) Real brasilero =>> Dolar
                    5) Dolar =>> Peso colombiano
                    6) Peso colombiano =>> Dolar
                    7) Elegir monedas diferentes
                    8) Salir
                    
                    *******************************************
                    Elija una opcion valida:
                    """);
                //pendiente revisar y corregir que no ingresen palabras
                if(lectura.hasNextInt()){
                    opcionUsuario = lectura.nextInt();
                    if (opcionUsuario == opcionSalir) {
                        System.out.println("Historial de conversiones:");
                        for (String conversion : historialConversiones){
                            System.out.println(conversion);
                        }
                        System.out.println("Gracias por usar el conversor de monedas, esperamos verte pronto.");
                    }else if (opcionUsuario >= opcionDolar_A_ARS && opcionUsuario <= opcionSalir){
                        System.out.println("Ingrese el valor que desea convertir:");
                        valorAConvertir = lectura.nextInt(); // Valor a convertir
                        String resultado;
                        LocalDateTime fechaHoraActual = LocalDateTime.now();
                        String fechaHoraFormateada = fechaHoraActual.format(formatter);
                        switch (opcionUsuario) {
                            case opcionDolar_A_ARS:
                                monedaOrigen = USD;
                                monedaDeseada = ARS;
                                resultado = ConvertirMoneda.convertir(monedaDeseada, monedaOrigen, valorAConvertir);
                                historialConversiones.add("[" + fechaHoraFormateada + "] Convertio " + valorAConvertir + " " + monedaOrigen + " a " + monedaDeseada + " " + resultado.toString());
                                break;
                            case opcion_ARS_A_Dolar:
                                monedaOrigen = ARS;
                                monedaDeseada = USD;
                                resultado = ConvertirMoneda.convertir(monedaDeseada, monedaOrigen, valorAConvertir);
                                historialConversiones.add("[" + fechaHoraFormateada + "] Convertio " + valorAConvertir + " " + monedaOrigen + " a " + monedaDeseada + " " + resultado.toString());
                                break;
                            case opcionDolar_A_BRL:
                                monedaOrigen = USD;
                                monedaDeseada = BRL;
                                resultado = ConvertirMoneda.convertir(monedaDeseada, monedaOrigen, valorAConvertir);
                                historialConversiones.add("[" + fechaHoraFormateada + "] Convertio " + valorAConvertir + " " + monedaOrigen + " a " + monedaDeseada + " " + resultado.toString());
                                break;
                            case opcion_BRL_A_Dolar:
                                monedaOrigen = BRL;
                                monedaDeseada = USD;
                                resultado = ConvertirMoneda.convertir(monedaDeseada, monedaOrigen, valorAConvertir);
                                historialConversiones.add("[" + fechaHoraFormateada + "] Convertio " + valorAConvertir + " " + monedaOrigen + " a " + monedaDeseada + " " + resultado.toString());
                                break;
                            case opcionDolar_A_COP:
                                monedaOrigen = USD;
                                monedaDeseada = COP;
                                resultado = ConvertirMoneda.convertir(monedaDeseada, monedaOrigen, valorAConvertir);
                                historialConversiones.add("[" + fechaHoraFormateada + "] Convertio " + valorAConvertir + " " + monedaOrigen + " a " + monedaDeseada + " " + resultado.toString());
                                break;
                            case opcion_COP_A_Dolar:
                                monedaOrigen = COP;
                                monedaDeseada = USD;
                                resultado = ConvertirMoneda.convertir(monedaDeseada, monedaOrigen, valorAConvertir);
                                historialConversiones.add("[" + fechaHoraFormateada + "] Convertio " + valorAConvertir + " " + monedaOrigen + " a " + monedaDeseada + " " + resultado.toString());
                                break;
                            case opcionMonedasDiferentes:
                                System.out.println("Seleccione la moneda que desea cambiar:");
                                monedaOrigen = lectura.next().toUpperCase();  // Moneda que se convertira
                                System.out.println("Seleccione la moneda a la cual desea cambiar: ");
                                monedaDeseada = lectura.next().toUpperCase(); // Moneda que deseas obtener
                                resultado = ConvertirMoneda.convertir(monedaDeseada, monedaOrigen, valorAConvertir);
                                historialConversiones.add("[" + fechaHoraFormateada + "] Convertio " + valorAConvertir + " " + monedaOrigen + " a " + monedaDeseada + " " + resultado.toString());
                                break;
                            default:
                                System.out.println("Opcion invalida, por favor seleccione una oopcion del menu.");
                        }
                    } else {
                        System.out.println("Por favor, ingrese un numero valido.");
                        lectura.next();//limpiar el buffer de entrada
                    }
                }else{
                    System.out.println("Por favor, ingrese un numero valido.");
                    lectura.next();//limpiar el buffer de entrada
                }
            }
        } finally {
            lectura.close();//cierra el scanner
        }
    }
}
