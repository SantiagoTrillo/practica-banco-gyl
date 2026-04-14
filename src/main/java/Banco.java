import java.util.ArrayList;

public final class Banco {
    private static Banco instancia;
    private final ArrayList<Sucursal> sucursales;

    private Banco() {
        sucursales = new ArrayList<>(

        );
    }

    public void crearSucursal(String nombre) {
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("(Banco, crearSucursal) el nombre de la sucursal está vacío");
        }
        sucursales.add(new Sucursal(nombre));
    }

    public void mostrarCuentas() {
        System.out.println("-----Detalles de las cuentas del banco-----");
        for (Sucursal sucursal : sucursales) {
            System.out.println("-----Detalles de las cuentas de la sucursal " + sucursal.getNombre() + "-----");
            sucursal.mostrarCuentas();
        }
    }

    public static Banco getInstancia(){
        if (instancia == null) {
            instancia = new Banco();
        }
        return instancia;
    }
}