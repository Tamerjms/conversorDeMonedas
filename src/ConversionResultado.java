public class ConversionResultado {
    private final double resultado;
    private final double tasaObtenida;

    public ConversionResultado(double resultado, double tasaObtenida) {
        this.resultado = resultado;
        this.tasaObtenida = tasaObtenida;
    }

    public double getResultado() {
        return resultado;
    }

    public double getTasaObtenida() {
        return tasaObtenida;
    }

    @Override
    public String toString() {
        return "el resultado de la conversion fue: " + resultado +
                " y la tasa de conversion fue: " + tasaObtenida;
    }
}
