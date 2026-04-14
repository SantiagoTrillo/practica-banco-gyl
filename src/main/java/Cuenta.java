public class Cuenta {
    private final String nombre, email;
    private double saldo;
    private final Sucursal sucursal;
    private final TipoCuenta tipoCuenta;

    public Cuenta(String nombre, String email, TipoCuenta tipoCuenta, Sucursal sucursal) {
        this.nombre = nombre;
        this.email = email;
        saldo = 0;
        this.sucursal = sucursal;
        this.tipoCuenta = tipoCuenta;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + '\n' +
                "Email: " + email + '\n' +
                "Saldo: " + saldo +
                "Sucursal: " + sucursal + '\n' +
                "Tipo de cuenta: " + tipoCuenta + '\n';
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}