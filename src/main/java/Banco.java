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
        int i = 1;

        for (Sucursal sucursal : sucursales) {
            sucursal.crearCuenta("Cliente " + i, "cliente" + i + "@gmail.com", 999 + i, false, TipoCuenta.CUENTA_CORRIENTE);
            i++;
            if (i == 10) {
                break;
            }
        }
    }

    public void mostrarSucursales() {
        for (int i = 0; i < sucursales.size(); i++) {
            System.out.println(i + 1 + ") " + sucursales.get(i).getNombre());
        }
    }

    public Sucursal buscarSucursalBanco(String nombre) {
        Sucursal sucursalBuscada = null;

        for (Sucursal sucursal : sucursales) {
            if (sucursal.getNombre().equalsIgnoreCase(nombre)) {
                sucursalBuscada = sucursal;
            }
        }
        return sucursalBuscada;
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