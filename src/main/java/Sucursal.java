import java.util.ArrayList;

public class Sucursal {
    private String nombre;
    private final ArrayList<Cuenta> cuentas;

    public Sucursal(String nombre) {
        setNombre(nombre);
        cuentas = new ArrayList<>();
    }

    public void registrarCuentaSucursal(String nombre, String email, int pin, boolean permisosAdmin, TipoCuenta tipoCuenta) {
        cuentas.add(new Cuenta(nombre, email, pin, permisosAdmin, this, tipoCuenta));
    }

    public void depositar(Cuenta cuenta, double monto) {
        if (cuenta == null) {
            throw new IllegalArgumentException("(Sucursal, depositar) la cuenta es nula");
        }
        if (monto < 1) {
            throw new IllegalArgumentException("(Sucursal, depositar) el monto a depositar no es un número positivo");
        }
        cuenta.setSaldo(cuenta.getSaldo() + monto);
    }

    public void retirar(Cuenta cuenta, double monto) {
        if (cuenta == null) {
            throw new IllegalArgumentException("(Sucursal, retirar) la cuenta es nula");
        }
        if (monto < 1) {
            throw new IllegalArgumentException("(Sucursal, retirar) el monto a retirar no es un número positivo");
        }
        if (cuenta.getSaldo() < monto) {
            throw new IllegalArgumentException("(Sucursal, retirar) saldo insuficiente");
        }
        cuenta.setSaldo(cuenta.getSaldo() - monto);
    }

    public void transferir(Cuenta transferente, Cuenta transferido, double monto) {
        if (transferente == null || transferido == null) {
            throw new IllegalArgumentException("(Sucursal, transferir) la cuenta del transferente o del transferido es nula");
        }
        if (monto < 1) {
            throw new IllegalArgumentException("(Sucursal, transferir) el monto a transferir no es un número positivo");
        }
        if (transferente.getSaldo() < monto) {
            throw new IllegalArgumentException("(Sucursal, transferir) el monto a transferir es menor al saldo disponible");
        }
        transferente.setSaldo(transferente.getSaldo() - monto);
        transferido.setSaldo(transferido.getSaldo() + monto);
    }

    public void mostrarCuenta(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("(Sucursal, mostrarCuenta) el email está vacío");
        }
        for (Cuenta cuenta : cuentas) {
            Cuenta cuentaBuscada = this.buscarCuenta(email);
            if (cuentaBuscada != null) {
                System.out.println(cuentaBuscada);
            }
        }
    }

    public void mostrarCuentas() {
        for (Cuenta cuenta : cuentas) {
            System.out.println(cuenta);
        }
    }

    public void eliminarCuenta(Cuenta cuenta) {
        if (cuenta == null) {
            throw new IllegalArgumentException("(Sucursal, eliminarCuenta) la cuenta a eliminar es nula");
        }
        cuentas.remove(cuenta);
    }

    public Cuenta buscarCuenta(String email) {
        Cuenta cuentaBuscada = null;

        for (Cuenta cuenta : cuentas) {
            if (cuenta.getEmail().equalsIgnoreCase(email)) {
                cuentaBuscada = cuenta;
            }
        }
        return cuentaBuscada;
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("(Sucursal, setNombre) el nombre de la sucursal está vacío");
        }
        this.nombre = nombre;
    }
}