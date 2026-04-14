import java.util.ArrayList;

public final class Banco {
    private String nombre;
    private static Banco instancia;
    private final ArrayList<Sucursal> sucursales;

    private Banco(String nombre) throws IllegalArgumentException {
        setNombre(nombre);
        sucursales = new ArrayList<>();
    }

    public void crearSucursal(String nombre) {
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("(Banco, crearSucursal) el nombre de la sucursal está vacío");
        }
        Sucursal sucursalNueva = new Sucursal(nombre);
        sucursales.add(sucursalNueva);
    }

    public void mostrarCuenta(String emailBuscado) {
        for (Sucursal sucursal : sucursales) {
            if (sucursal == null) {
                throw new IllegalArgumentException("(Banco, mostrarCuenta) una sucursal iterada es nula");
            }
            Cuenta cuentaBuscada = sucursal.buscarCuenta(emailBuscado);
            if (cuentaBuscada != null) {
                System.out.println(cuentaBuscada);
            }
        }
    }

    public void mostrarCuentas() {
        System.out.println("-----Detalles de las cuentas del banco-----");
        for (Sucursal sucursal : sucursales) {
            if (sucursal == null) {
                throw new IllegalArgumentException("(Banco, mostrarCuentas) una sucursal iterada es nula");
            }
            System.out.println("-----Detalles de las cuentas de la sucursal " + sucursal.getNombre() + "-----");
            sucursal.mostrarCuentas();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public static Banco getInstancia(String nombre) {
        if (instancia == null) {
            instancia = new Banco(nombre);
        }
        return instancia;
    }

    private void setNombre(String nombre) {
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("(Banco, setNombre) el nombre del banco está vacío");
        }
        this.nombre = nombre;
    }
}