public class Cuenta {
    private String nombre, email, contraseña;
    private double saldo;
    private Sucursal sucursal;
    private TipoCuenta tipoCuenta;

    public Cuenta(String nombre, String email, String contraseña, Sucursal sucursal, TipoCuenta tipoCuenta) {
        setNombre(nombre);
        setEmail(email);
        setContraseña(contraseña);
        setSucursal(sucursal);
        setTipoCuenta(tipoCuenta);
        saldo = 0;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + '\n' +
                "Email: " + email + '\n' +
                "Saldo: " + saldo +
                "Sucursal: " + sucursal.getNombre() + '\n' +
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

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("(Cuenta, setNombre) el nombre de la cuenta está vacío");
        }
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("(Cuenta, setEmail) el email de la cuenta está vacío");
        }
        this.email = email;
    }

    public void setContraseña(String contraseña) {
        if (contraseña == null || contraseña.isBlank() || contraseña.length() < 8) {
            throw new IllegalArgumentException("(Cuenta, setContraseña) la contraseña de la cuenta está vacía o es demasiado corta");
        }
        this.contraseña = contraseña;
    }

    public void setSucursal(Sucursal sucursal) {
        if (sucursal == null) {
            throw new IllegalArgumentException("(Cuenta, setSucursal) la sucursal de la cuenta es nula");
        }
        this.sucursal = sucursal;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        if (tipoCuenta == null) {
            throw new IllegalArgumentException("(Cuenta, setTipoCuenta) el tipo de la cuenta está vacío");
        }
        this.tipoCuenta = tipoCuenta;
    }

    public void setSaldo(double saldo) {
        if (saldo < 1) {
            throw new IllegalArgumentException("(Cuenta, setSaldo) el saldo de la cuenta no es un número positivo");
        }
        this.saldo = saldo;
    }
}