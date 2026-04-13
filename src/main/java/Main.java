public class Main {
    static void main(String[] args) {
        Banco banco = new Banco(2);
        Cuenta cuenta1 = new Cuenta("Pepito", "Avenida Roble 3885", TipoCuenta.CUENTA_CORRIENTE);
        Cuenta cuenta2 = new Cuenta("Martina", "Avenida Jabón 981", TipoCuenta.CAJA_AHORRO);
        banco.agregarCuenta(cuenta1);
        banco.agregarCuenta(cuenta2);

        banco.mostrarCuenta(cuenta1);
        banco.mostrarCuenta(cuenta2);
        banco.agregarSaldo(cuenta1, 1000);
        banco.mostrarCuenta(cuenta1);
        banco.procesarTransferencia(cuenta1, cuenta2, 1000);
        banco.mostrarCuentas();
        banco.agregarSaldo(cuenta1, 2000);
        banco.mostrarCuenta(cuenta1);
        banco.procesarTransferencia(cuenta1, cuenta2, 2000);
        banco.mostrarCuentas();
        banco.procesarTransferencia(cuenta1, cuenta2, 1000);
        banco.procesarTransferencia(cuenta1, cuenta2, -200);
        banco.procesarTransferencia(cuenta1, cuenta2, 0);
    }
}