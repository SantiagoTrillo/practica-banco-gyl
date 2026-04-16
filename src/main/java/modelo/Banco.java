package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public final class Banco {
    private static Banco instancia;
    private final ArrayList<Sucursal> sucursales;

    private Banco() {
        sucursales = new ArrayList<>();
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

    public List<Sucursal> getSucursales() {
        return Collections.unmodifiableList(sucursales);
    }
}