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
        if (cuenta != null && saldo > 0) {
            cuenta.depositar(saldo);
        }
    }

    public void procesarTransferencia(Cuenta transferente, Cuenta transferido, int saldo) {
        if (transferente != null && transferido != null) {
            boolean resultadoTransferencia = transferente.transferirDinero(transferido, saldo);
            if (!resultadoTransferencia) {
                System.out.println("Error: saldo insuficiente, monto negativo");
            } else {
                System.out.println("Transferencia realizada con éxito");
            }
        } else {
            System.out.println("Error: la cuenta del transferente o del transferido es nula");
        }
    }

    public void mostrarCuenta(Cuenta cuenta) {
        if (cuenta != null) {
            System.out.println("-----Detalles de la cuenta de " + cuenta.getNombre() + "-----");
            System.out.println(cuenta);
        }
    }

    public void mostrarCuentas() {
        System.out.println("-----Detalles de las cuentas del banco-----");
        for (Cuenta cuenta : cuentas) {
            if (cuenta != null) {
                System.out.println(cuenta);
            }
        }
    }
}