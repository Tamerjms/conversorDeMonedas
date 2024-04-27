import java.util.concurrent.ExecutionException;

public class ConvertirMoneda {
    public static String convertir(String monedaDeseada, String monedaOrigen, int valorAConvertir){
        try {
            // Llamada al método en la clase ConsultaMoneda para obtener la tasa de conversión
            double tasaObtenida = ConsultaMoneda.obtenerTasaDeConversion(monedaDeseada, monedaOrigen).get();
            double resultado = tasaObtenida*valorAConvertir;
            System.out.println("El resultado de convetir " + valorAConvertir + " " + monedaOrigen + " a " + monedaDeseada + " es: " + resultado + " "
                    + monedaDeseada);
            System.out.println("La tasa actual de conversion es: " + tasaObtenida + " " + monedaDeseada + " por cada " + monedaOrigen);
            return new ConversionResultado(resultado, tasaObtenida).toString();
        } catch (InterruptedException e) {
            // Manejo de InterruptedException
            Thread.currentThread().interrupt(); // Restablece la bandera interrupt para mantener el flujo de interrupción
            System.err.println("La operación de conversión fue interrumpida: " + e.getMessage());
        } catch (ExecutionException e) {
            // Manejo de ExecutionException
            System.err.println("Error al ejecutar la operación de conversión: " + e.getMessage());
        }
        return null;
    }
}
