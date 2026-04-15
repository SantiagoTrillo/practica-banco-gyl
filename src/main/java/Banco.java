import java.util.ArrayList;

public final class Banco {
    private static Banco instancia;
    private final ArrayList<Sucursal> sucursales;

    private Banco() {
        sucursales = new ArrayList<>();
        inicializarSucursales();
        inicializarCuentas();
    }

    private void inicializarSucursales(){
        sucursales.add(new Sucursal("Parque Patricios"));
        sucursales.add(new Sucursal("Boedo"));
    }

    private void inicializarCuentas() {
        for (Sucursal sucursal : sucursales) {
            for (int i = 1; i <= 10; i++) {
                sucursal.crearCuenta("Cliente " + i, "cliente" + i + "@gmail.com", 999 + i, false, TipoCuenta.CUENTA_CORRIENTE);
            }
            sucursal.crearCuenta("Admin", "admin@gmail.com", 2007, true, TipoCuenta.CAJA_AHORRO);
        }
    }

    public void mostrarSucursales() {
        for (int i = 0; i < sucursales.size(); i++) {
            System.out.println(i + 1 + ") " + sucursales.get(i).getNombre());
        }
    }

    public void mostrarCuentas() {
        System.out.println("-----Detalles de las cuentas del banco-----");
        for (Sucursal sucursal : sucursales) {
            System.out.println("-----Detalles de las cuentas de la sucursal " + sucursal.getNombre() + "-----");
            sucursal.mostrarCuentas();
        }
    }

    public void crearSucursal(String nombre) {
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("(Banco, crearSucursal) el nombre de la sucursal está vacío");
        }
        sucursales.add(new Sucursal(nombre));
    }

    public Sucursal buscarSucursal(String nombre) {
        Sucursal sucursalBuscada = null;

        for (Sucursal sucursal : sucursales) {
            if (sucursal.getNombre().equalsIgnoreCase(nombre)) {
                sucursalBuscada = sucursal;
            }
        }
        return sucursalBuscada;
    }

    public Cuenta buscarCuenta(String email) {
        Cuenta cuentaBuscada = null;

        for (Sucursal sucursal : sucursales) {
            cuentaBuscada = sucursal.buscarCuenta(email);
            if (cuentaBuscada != null) {
                break;
            }
        }
        return cuentaBuscada;
    }

    public static Banco getInstancia(){
        if (instancia == null) {
            instancia = new Banco();
        }
        return instancia;
    }
}