import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Sucursal {
    private String nombre;
    private final ArrayList<Cuenta> cuentas;

    public Sucursal(String nombre) throws IllegalArgumentException {
        setNombre(nombre);
        cuentas = new ArrayList<>();
    }

    public void mostrarMenu() {
        Scanner teclado = new Scanner(System.in);
        boolean sesionActiva = true;

        System.out.println("Bienvenido a la sucursal " + getNombre());

        while (sesionActiva) {
            int opcion;
            System.out.println("Ingrese el número que corresponda con la acción que desee realizar\n1) Registrar cuenta\n2) Depositar dinero\n3) Retirar dinero\n4) Realizar una transferencia\n5) Eliminar cuenta\n6) Salir");
            opcion = teclado.nextInt();

            if (opcion == 1) {
                TipoCuenta tipoCuenta;
                System.out.println("Ingrese su nombre");
                String nombre = teclado.nextLine();
                System.out.println("Ingrese su email");
                String email = teclado.nextLine();
                System.out.println("Indique el tipo de cuenta que le gustaría crear:\n1) Caja de ahorro\n2) Cuenta corriente");
                int opcionCuenta = teclado.nextInt();
                if (opcionCuenta == 1) {
                    tipoCuenta = TipoCuenta.CAJA_AHORRO;
                } else if (opcionCuenta == 2) {
                    tipoCuenta = TipoCuenta.CUENTA_CORRIENTE;
                } else {
                    throw new IllegalArgumentException("(Sucursal, mostrarMenu) el número ingresado para elegir el tipo de cuenta es inválido");
                }
                registrarCuenta(nombre, email, tipoCuenta);
            } else if (opcion == 2) {
                System.out.println("Ingrese el email de la cuenta a la que desea hacer un depósito:");
                String emailBuscado = teclado.nextLine();
                Cuenta cuentaBuscada = buscarCuenta(emailBuscado);
                System.out.println("Ingrese el monto que desea depositar:");
                double monto = teclado.nextFloat();
                depositar(cuentaBuscada, monto);
            } else if (opcion == 3) {
                System.out.println("Ingrese el email de la cuenta a la que desea hacer un retiro:");
                String emailBuscado = teclado.nextLine();
                Cuenta cuentaBuscada = buscarCuenta(emailBuscado);
                System.out.println("Ingrese el monto que desea retirar:");
                double monto = teclado.nextFloat();
                retirar(cuentaBuscada, monto);
            } else if (opcion == 4) {
                System.out.println("Ingrese el email de la cuenta desde la que quiere enviar la transferencia:");
                String emailTransferente = teclado.nextLine();
                Cuenta cuentaTransferente = buscarCuenta(emailTransferente);
                System.out.println("Ingrese el email de la cuenta a la que quiere enviar la transferencia:");
                String emailTransferido = teclado.nextLine();
                Cuenta cuentaTransferido = buscarCuenta(emailTransferido);
                System.out.println("Ingrese el monto que desea transferir:");
                double monto = teclado.nextFloat();
                transferir(cuentaTransferente, cuentaTransferido, monto);
            } else if (opcion == 5) {
                System.out.println("Ingrese el email de la cuenta que desea eliminar:");
                String emailBuscado = teclado.nextLine();
                Cuenta cuentaBuscada = buscarCuenta(emailBuscado);
                System.out.println("¿Está seguro que desea eliminar la cuenta? (S o N)");
                String eleccion = teclado.nextLine().toUpperCase();
                if (eleccion.equals("S")) {
                    eliminarCuenta(cuentaBuscada);
                } else if (!eleccion.equals("N")) {
                    throw new IllegalArgumentException("(Sucursal, mostrarMenu) la letra enviada para la confirmación de la eliminación de la cuenta es inválida");
                }
            } else if (opcion == 6) {
                System.out.println("Gracias por visitar nuestra sucursal");
                sesionActiva = false;
            } else {
                throw new IllegalArgumentException("(Sucursal, mostrarMenu) el número enviado para el menú de opciones es inválido");
            }
        }
    }

    private void registrarCuenta(String nombre, String email, TipoCuenta tipoCuenta) {
        if (nombre.isBlank() || email.isBlank()) {
            throw new IllegalArgumentException("(Sucursal, registrarCuenta) el nombre, email o contraseña de la cuenta nueva está vacío");
        }
        cuentas.add(new Cuenta(nombre, email, tipoCuenta, this));
    }

    private void depositar(Cuenta cuenta, double monto) {
        if (cuenta == null) {
            throw new IllegalArgumentException("(Sucursal, depositar) la cuenta es nula");
        }
        if (monto < 1) {
            throw new IllegalArgumentException("(Sucursal, depositar) el monto a depositar no es un número positivo");
        }
        cuenta.setSaldo(cuenta.getSaldo() + monto);
    }

    private void retirar(Cuenta cuenta, double monto) {
        if (cuenta == null) {
            throw new IllegalArgumentException("(Sucursal, retirar) la cuenta es nula");
        }
        if (monto < 1) {
            throw new IllegalArgumentException("(Sucursal, retirar) el monto a retirar no es un número positivo");
        }
        cuenta.setSaldo(cuenta.getSaldo() - monto);
    }

    private void transferir(Cuenta transferente, Cuenta transferido, double monto) {
        if (transferente == null || transferido == null) {
            throw new IllegalArgumentException("(Sucursal, transferir) la cuenta del transferente o del transferido es nula");
        }
        if (monto < 1) {
            throw new IllegalArgumentException("(Sucursal, transferir) el monto a transferir no es un número positivo");
        }
        if (transferente.getSaldo() <= monto) {
            throw new IllegalArgumentException("(Sucursal, transferir) el monto a transferir es menor al saldo disponible");
        }
        transferente.setSaldo(transferente.getSaldo() - monto);
        transferido.setSaldo(transferido.getSaldo() + monto);
    }

    public void mostrarCuentas() {
        for (Cuenta cuenta : cuentas) {
            if (cuenta == null) {
                throw new IllegalArgumentException("(Sucursal, mostrarCuentas) una cuenta iterada es nula");
            }
            System.out.println(cuenta);
        }
    }

    private void eliminarCuenta(Cuenta cuenta) {
        if (cuenta == null) {
            throw new IllegalArgumentException("(Sucursal, eliminarCuenta) la cuenta a eliminar es nula");
        }
        cuentas.remove(cuenta);
    }

    public Cuenta buscarCuenta(String emailBuscado) {
        Cuenta cuentaBuscada = null;
        int i = 0;

        while (i < this.cuentas.size() && cuentaBuscada == null) {
            if (this.cuentas.get(i).getEmail().equals(emailBuscado)) {
                cuentaBuscada = this.cuentas.get(i);
            }
            i++;
        }
        return cuentaBuscada;
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("(Sucursal, setNombre) el nombre de la sucursal está vacío");
        }
        this.nombre = nombre;
    }
}