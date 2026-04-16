package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Cuenta {
    private String nombre, email;
    private int pin;
    private double saldo;
    private final boolean admin;
    private Sucursal sucursal;
    private TipoCuenta tipoCuenta;
    private final ArrayList<Transaccion> historialTransacciones;

    public Cuenta(String nombre, String email, int pin, boolean permisosAdmin, Sucursal sucursal, TipoCuenta tipoCuenta) {
        setNombre(nombre);
        setEmail(email);
        setPin(pin);
        this.admin = permisosAdmin;
        setSucursal(sucursal);
        setTipoCuenta(tipoCuenta);
        saldo = 0;
        historialTransacciones = new ArrayList<>();
    }

    public void agregarSaldo(double monto) {
        if (monto < 1) {
            throw new IllegalArgumentException("(Cuenta, agregarSaldo) monto inválido");
        }
        saldo += monto;
    }

    public void restarSaldo(double monto) {
        if (monto < 1) {
            throw new IllegalArgumentException("(Cuenta, restarSaldo) monto inválido");
        }
        if (saldo < monto) {
            throw new IllegalArgumentException("(Cuenta, restarSaldo) saldo insuficiente");
        }
        saldo -= monto;
    }

    public String armarHistorialTransacciones() {
        StringBuilder historial = new StringBuilder();

        for (Transaccion transaccion : this.getHistorialTransacciones()) {
            historial.append(transaccion).append("\n");
        }

        return historial.toString();
    }

    public void agregarTransaccionHistorial(Transaccion transaccionNueva) {
        historialTransacciones.add(transaccionNueva);
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + '\n' +
                "Email: " + email + '\n' +
                "Pin: " + pin + '\n' +
                "Saldo: $" + saldo + '\n' +
                "Admin: " + admin + '\n' +
                "modelo.Sucursal: " + sucursal.getNombre() + '\n' +
                "Tipo de cuenta: " + tipoCuenta + '\n';
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public int getPin() {
        return pin;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isAdmin() {
        return admin;
    }

    public List<Transaccion> getHistorialTransacciones() {
        return Collections.unmodifiableList(historialTransacciones);
    }

    private void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("(modelo.Cuenta, setNombre) el nombre de la cuenta está vacío");
        }
        this.nombre = nombre;
    }

    private void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("(modelo.Cuenta, setEmail) el email de la cuenta está vacío");
        }
        this.email = email;
    }

    private void setPin(int pin) {
        if (pin > 9999 || pin < 1000) {
            throw new IllegalArgumentException("(modelo.Cuenta, setPin) el pin es inválido");
        }
        this.pin = pin;
    }

    private void setSucursal(Sucursal sucursal) {
        if (sucursal == null) {
            throw new IllegalArgumentException("(modelo.Cuenta, setSucursal) la sucursal de la cuenta es nula");
        }
        this.sucursal = sucursal;
    }

    private void setTipoCuenta(TipoCuenta tipoCuenta) {
        if (tipoCuenta == null) {
            throw new IllegalArgumentException("(modelo.Cuenta, setTipoCuenta) el tipo de la cuenta está vacío");
        }
        this.tipoCuenta = tipoCuenta;
    }
}