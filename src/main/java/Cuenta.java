public class Cuenta {
    private final String nombre, direccion;
    private final TipoCuenta tipoCuenta;
    private int saldo;

    public Cuenta(String nombre, String direccion, TipoCuenta tipoCuenta) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipoCuenta = tipoCuenta;
        saldo = 0;
    }

    public void depositar(int saldo) {
        this.saldo += saldo;
    }

    public boolean transferirDinero(Cuenta cuenta, int dinero) {
        if (dinero <= 0 || this.saldo < dinero) {
            return false;
        } else {
            this.saldo -= dinero;
            cuenta.depositar(dinero);
            return true;
        }
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + '\n' +
                "Dirección: " + direccion + '\n' +
                "Tipo de cuenta: " + tipoCuenta + '\n' +
                "Saldo: " + saldo;
    }

    public String getNombre() {
        return nombre;
    }
}