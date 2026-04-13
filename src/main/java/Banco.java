public class Banco {
    private final Cuenta[] cuentas;

    public Banco(int cantidadCuentas) {
        cuentas = new Cuenta[cantidadCuentas];
    }

    public void agregarCuenta(Cuenta cuenta) {
        boolean cuentaAgregada = false;
        for (int i = 0; i < cuentas.length && !cuentaAgregada; i++) {
            if (cuentas[i] == null) {
                cuentas[i] = cuenta;
                cuentaAgregada = true;
            }
        }
    }

    public void agregarSaldo(Cuenta cuenta, int saldo) {
        cuenta.depositar(saldo);
    }

    public void procesarTransferencia(Cuenta cuentaTransferente, Cuenta cuentaTransferido, int saldo) {
        boolean resultadoTransferencia = cuentaTransferente.transferirDinero(cuentaTransferido, saldo);
        if (!resultadoTransferencia) {
            System.out.println("Error: saldo insuficiente o transferencia nula");
        } else {
            System.out.println("Transferencia realizada con éxito");
        }
    }

    public void mostrarCuenta(Cuenta cuenta) {
        System.out.println("-----Detalles de la cuenta de " + cuenta.getNombre() + "-----");
        System.out.println(cuenta);
    }

    public void mostrarCuentas() {
        System.out.println("-----Detalles de las cuentas del banco-----");
        for (Cuenta cuenta : cuentas) {
            System.out.println(cuenta);
        }
    }
}